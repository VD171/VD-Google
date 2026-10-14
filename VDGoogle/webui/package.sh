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
#  Discord        : @VD.Priv8 https://discord.com/users/1296831918989639721
#  E-mail         : vd.priv8@pm.me
# ======================================================================
set -eu

HERE="$(cd "$(dirname "$0")" && pwd)"
VERSION="$(sed -n 's/^version=//p' "$HERE/module.prop" | head -1)"
OUT_DIR="$(cd "$HERE/../.." && pwd)/dist"
ZIP="$OUT_DIR/VDGoogle-WebUI-${VERSION}.zip"

mkdir -p "$OUT_DIR"
rm -f "$ZIP"

cd "$HERE"
zip -r9 -X "$ZIP" \
    module.prop customize.sh uninstall.sh update.json \
    META-INF bin webroot \
    -x '*/.git*' >/dev/null

echo "built $ZIP"
unzip -l "$ZIP" | tail -n 1
