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
#  XDA thread     : https://xdaforums.com/t/VD-Google.4803837/
#  Telegram       : @VD_Priv8 https://t.me/VD_Priv8
#  Channel        : @Priv8_VD https://t.me/Priv8_VD
#  Discord        : @VD.Priv8 https://discord.com/users/1296831918989639721
#  E-mail         : vd.priv8@pm.me
# ======================================================================

ui_print ""
ui_print "  VD Google"
MODULE_VERSION=$(grep_prop version "$MODPATH/module.prop")
MODULE_VERSION_CODE=$(grep_prop versionCode "$MODPATH/module.prop")
ui_print "  version $MODULE_VERSION (versionCode $MODULE_VERSION_CODE)"
ui_print ""

if ! $BOOTMODE; then
  ui_print "  ! Install from Magisk, KernelSU or APatch, not recovery."
  abort "  ! Recovery install is not supported."
fi

if [ "$API" -lt 26 ]; then
  ui_print "  ! Android 8.0 (API 26) or newer is required."
  abort "  ! Android version is too old."
fi

# Supported CPU only - never ship a wrong-arch sqlite3.
VDG_ARCH="$ARCH"
[ -z "$VDG_ARCH" ] && case "$(getprop ro.product.cpu.abi)" in arm64*) VDG_ARCH=arm64 ;; arm*) VDG_ARCH=arm ;; *) VDG_ARCH=other ;; esac
case "$VDG_ARCH" in
  arm64) BINABI=arm64-v8a ;;
  arm)   BINABI=armeabi-v7a ;;
  *) ui_print "  ! Unsupported CPU ($VDG_ARCH); VD Google needs arm or arm64."; abort "  ! Unsupported architecture." ;;
esac

# Integrity gate: every bundled binary must match its recorded checksum (PROVENANCE.json).
PROV="$MODPATH/bin/$BINABI/PROVENANCE.json"
if [ -f "$PROV" ]; then
  ui_print "  Verifying bundled binaries ($BINABI)..."
  VDG_FAIL=0
  for f in "$MODPATH/bin/$BINABI/sqlite3" "$MODPATH/bin/$BINABI/lib/"*; do
    [ -f "$f" ] || continue
    rel=${f#"$MODPATH/bin/$BINABI/"}
    want=$(grep "\"path\": \"$rel\"" "$PROV" | grep -oE '[0-9a-f]{64}' | head -1)
    got=$(sha256sum "$f" 2>/dev/null | cut -d' ' -f1)
    if [ -z "$want" ]; then ui_print "  ! no recorded checksum for $rel"; VDG_FAIL=1
    elif [ "$want" != "$got" ]; then ui_print "  ! checksum mismatch: $rel"; VDG_FAIL=1; fi
  done
  [ "$VDG_FAIL" = 1 ] && abort "  ! Bundled binary verification failed; not installing."
  ui_print "  Binaries verified."
  ui_print ""
fi

ui_print "  Open the WebUI from your root manager (gear icon on this module)."
ui_print "  It reads on-device only. The app has no internet access."
ui_print ""

set_perm_recursive "$MODPATH/bin" 0 0 0755 0755
set_perm_recursive "$MODPATH/webroot" 0 0 0755 0644

MID="${MODID:-$(grep_prop id "$MODPATH/module.prop")}"
ACTIVE="/data/adb/modules/$MID"
if [ "$BOOTMODE" = "true" ] && [ -n "$MID" ] && [ "$MODPATH" != "$ACTIVE" ]; then
  rm -rf "$ACTIVE.new"
  if cp -af "$MODPATH" "$ACTIVE.new" 2>/dev/null; then
    rm -rf "$ACTIVE"
    mv "$ACTIVE.new" "$ACTIVE"
    rm -f "$ACTIVE/update" "$ACTIVE/remove" "$ACTIVE/disable"
    setsid sh -c "n=0; while [ \$n -lt 30 ]; do rm -f '$ACTIVE/update' '$ACTIVE/remove' '$ACTIVE/disable'; sleep 1; n=\$((n+1)); done" >/dev/null 2>&1 &
    ui_print "  Activated now - no reboot needed."
  else
    rm -rf "$ACTIVE.new"
    ui_print "  ! Live activation failed; active after a reboot."
  fi
  ui_print ""
fi
