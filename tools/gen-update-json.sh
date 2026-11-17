#!/usr/bin/env bash
# ======================================================================
#  VD GOOGLE  ::  your Google services, read on-device
#  RCS, Google Wallet and Play Integrity attestations: local, no internet.
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
set -euo pipefail

repo_root="$(cd "$(dirname "$0")/.." && pwd)"
module_prop="$repo_root/VDGoogle/webui/module.prop"
update_json="$repo_root/VDGoogle/webui/update.json"

version="$(sed -n 's/^version=//p' "$module_prop")"
version_code="$(sed -n 's/^versionCode=//p' "$module_prop")"

if [ -z "$version" ] || [ -z "$version_code" ]; then
  echo "error: version or versionCode missing in $module_prop" >&2
  exit 1
fi

zip_url="https://github.com/VD171/VD-Google/releases/download/${version}/VDGoogle-WebUI-${version}.zip"
changelog_url="https://raw.githubusercontent.com/VD171/VD-Google/main/CHANGELOG.md"

cat > "$update_json" <<JSON
{
  "version": "${version}",
  "versionCode": ${version_code},
  "zipUrl": "${zip_url}",
  "changelog": "${changelog_url}"
}
JSON

printf 'wrote %s (version=%s versionCode=%s)\n' "$update_json" "$version" "$version_code"
