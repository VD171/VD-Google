#!/usr/bin/env bash
# ======================================================================
#  VD GOOGLE  ::  your Google services, read on-device
#  RCS, Google Wallet and Play Integrity attestations - local, no internet.
#
#  Copyright (C) 2026  VD171
#  SPDX-License-Identifier: AGPL-3.0-or-later
#
#  Free software under the GNU AGPL v3 or later. NETWORK COPYLEFT: run a
#  modified version, even as a service, and you MUST offer its source.
#
#  Site           : https://vd171.ru
#  Site           : https://vd.priv8.ru
#  Source         : https://github.com/VD171/VD-Google
#  GitHub         : @VD171 https://github.com/VD171
#  XDA-Developers : @VD171 https://xdaforums.com/m/vd171.4699873/
#  Telegram       : @VD_Priv8 https://t.me/VD_Priv8
#  Telegram       : @Priv8_VD https://t.me/Priv8_VD
#  Discord        : @VD.Priv8 https://discord.com/users/1296831918989639721
#  E-mail         : vd.priv8@pm.me
# ======================================================================
#
#  Assemble bin/<abi>/sqlite3 (+ its shared-library closure) by FETCHING the
#  prebuilt sqlite package from the official Termux package REPOSITORY
#  (packages.termux.dev/apt) - the signed .debs, never from a Termux installed on
#  some Android device. VD Google needs exactly one native tool, sqlite3, to read
#  Google's databases the same way on every device (see docs/ARCHITECTURE.md).
#
#  For each ABI: resolve the sqlite package + its transitive Depends from the
#  Termux Packages index, download and extract the .debs, copy sqlite3, then copy
#  ONLY the shared-library closure it needs (resolved with readelf) into bin/<abi>/lib.
#  The binary runs standalone once those libs ship alongside it and the caller sets
#  LD_LIBRARY_PATH=bin/<abi>/lib (Termux uses versioned sonames, so no clash with
#  the Android framework's own libraries). A PROVENANCE.json per ABI records the
#  source .deb (url, version, sha256) and the sha256 of every shipped file.
#
#  The same tree is mirrored into the app's assets/bin, so the app and the WebUI
#  module carry byte-identical binaries and libraries.
#
#  Requirements: curl, ar (binutils), tar, xz, zstd, readelf. No NDK, no compiler.

set -euo pipefail
ROOT="$(cd "$(dirname "$0")/../.." && pwd)"
OUT_BASE="$ROOT/VDGoogle/webui/bin"
MIRROR_BASE="$ROOT/VDGoogle/app/src/main/assets/bin"
WORK="${WORK:-$(mktemp -d)}"
REPO="${TERMUX_REPO:-https://packages.termux.dev/apt/termux-main}"

termux_arch() { case "$1" in
  arm64-v8a) echo aarch64 ;; armeabi-v7a) echo arm ;;
esac; }

ABIS="arm64-v8a armeabi-v7a"
TOOL_PKGS="sqlite"
WANT_BINS="sqlite3"
BIONIC="libc.so libm.so libdl.so liblog.so libandroid.so libz.so libselinux.so libc++.so libc++_shared.so libstdc++.so libEGL.so libnativehelper.so libbinder.so"

pkgfield() { awk -v p="$1" -v f="$2" '
    $1=="Package:" {cur=$2}
    cur==p && $1==f":" {$1=""; sub(/^ /,""); print; exit}' "$3"; }

sha() { { sha256sum "$1" 2>/dev/null || true; } | cut -d' ' -f1; }

write_provenance() {
  local abi="$1" out="$OUT_BASE/$abi" w="$WORK/$abi" f rel first=1
  {
    printf '{\n'
    printf '  "abi": "%s",\n' "$abi"
    printf '  "generated_utc": "%s",\n' "$(date -u +%Y-%m-%dT%H:%M:%SZ)"
    printf '  "policy": "sqlite3 and its libraries are fetched at build time from the official Termux apt repository, never from a device. Audit: re-fetch the pinned sources, extract, and compare the file sha256 values.",\n'
    printf '  "sources": [\n'
    [ -s "$w/src.jsonl" ] && sed -e 's/^/    /' -e '$!s/$/,/' "$w/src.jsonl"
    printf '  ],\n'
    printf '  "files": [\n'
    for f in "$out"/* "$out"/lib/*; do
      [ -f "$f" ] || continue
      case "$f" in */.gitkeep|*/PROVENANCE.json) continue ;; esac
      rel="${f#"$out"/}"
      [ "$first" = 1 ] || printf ',\n'; first=0
      printf '    {"path": "%s", "bytes": %s, "sha256": "%s"}' "$rel" "$(stat -c%s "$f")" "$(sha "$f")"
    done
    printf '\n  ]\n}\n'
  } > "$out/PROVENANCE.json"
  echo "  provenance: $out/PROVENANCE.json ($(grep -c '"sha256"' "$out/PROVENANCE.json") files, $(grep -c '"version"' "$out/PROVENANCE.json") source packages)"
}

build_abi() {
  local abi="$1" arch; arch="$(termux_arch "$abi")"
  local out="$OUT_BASE/$abi"; local w="$WORK/$abi"; local prefix="$w/prefix"
  rm -rf "$out/lib"; mkdir -p "$out/lib" "$w/deb" "$prefix"
  echo "== $abi ($arch) =="

  local idx="$w/Packages"
  curl -fsSL "$REPO/dists/stable/main/binary-$arch/Packages" -o "$idx"

  local seen="" queue="$TOOL_PKGS" pkg dep deps
  while [ -n "$queue" ]; do
    pkg="${queue%% *}"; queue="${queue#"$pkg"}"; queue="${queue# }"
    case " $seen " in *" $pkg "*) continue ;; esac; seen="$seen $pkg"
    deps="$(pkgfield "$pkg" Depends "$idx" | tr ',' ' ' | sed 's/([^)]*)//g')"
    for dep in $deps; do queue="$queue $dep"; done
  done

  : > "$w/src.jsonl"
  for pkg in $seen; do
    local fn ver; fn="$(pkgfield "$pkg" Filename "$idx")"; [ -n "$fn" ] || continue
    curl -fsSL "$REPO/$fn" -o "$w/deb/$pkg.deb"
    ver="$(pkgfield "$pkg" Version "$idx")"
    printf '{"type": "termux-deb", "package": "%s", "version": "%s", "url": "%s", "deb_sha256": "%s"}\n' \
      "$pkg" "$ver" "$REPO/$fn" "$(sha "$w/deb/$pkg.deb")" >> "$w/src.jsonl"
    ( cd "$w/deb" && ar p "$pkg.deb" data.tar.xz 2>/dev/null | tar -xJ -C "$prefix" 2>/dev/null \
      || ar p "$pkg.deb" data.tar.gz 2>/dev/null | tar -xz -C "$prefix" 2>/dev/null \
      || ar p "$pkg.deb" data.tar.zst 2>/dev/null | tar --zstd -x -C "$prefix" 2>/dev/null ) || true
  done
  local U="$prefix/data/data/com.termux/files/usr"

  local missing=""
  for b in $WANT_BINS; do
    if [ -e "$U/bin/$b" ]; then cp -Lf "$U/bin/$b" "$out/$b"; chmod 0755 "$out/$b"
    else missing="$missing $b"; fi
  done
  [ -n "$missing" ] && { echo "  ERROR missing bins:$missing" >&2; return 1; }

  local lqueue="" seenlib=""
  for b in $WANT_BINS; do [ -f "$out/$b" ] && lqueue="$lqueue $out/$b"; done
  while [ -n "$lqueue" ]; do
    f="${lqueue%% *}"; lqueue="${lqueue#"$f"}"; lqueue="${lqueue# }"
    for n in $(readelf -d "$f" 2>/dev/null | sed -n 's/.*(NEEDED).*\[\(.*\)\]/\1/p'); do
      case " $BIONIC " in *" $n "*) continue ;; esac
      case " $seenlib " in *" $n "*) continue ;; esac
      [ -f "$U/lib/$n" ] || continue
      seenlib="$seenlib $n"; cp "$U/lib/$n" "$out/lib/$n"; lqueue="$lqueue $out/lib/$n"
    done
  done

  echo "  bin: sqlite3 ($(stat -c%s "$out/sqlite3") bytes)"
  echo "  libs: $(ls "$out/lib" | wc -l) ($(du -sh "$out/lib" | cut -f1)): $(ls "$out/lib" | tr '\n' ' ')"
}

for abi in $ABIS; do build_abi "$abi"; write_provenance "$abi"; done

# mirror the exact tree into the app's assets/bin (app and WebUI stay byte-identical)
for abi in $ABIS; do
  rm -rf "${MIRROR_BASE:?}/$abi"; mkdir -p "$MIRROR_BASE/$abi"
  cp -a "$OUT_BASE/$abi/." "$MIRROR_BASE/$abi/"
done

echo "done. sqlite3 + lib closure under $OUT_BASE/<abi>/ and $MIRROR_BASE/<abi>/"
echo "runtime sets LD_LIBRARY_PATH=bin/<abi>/lib; PROVENANCE.json ships the audit trail"
