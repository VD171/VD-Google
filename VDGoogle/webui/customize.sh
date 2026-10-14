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

ui_print ""
ui_print "  VD Google (WebUI)"
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

ui_print "  Open the WebUI from your root manager (gear icon on this module)."
ui_print "  It reads on-device only. The app has no internet access."
ui_print ""

set_perm_recursive "$MODPATH/bin" 0 0 0755 0755
set_perm_recursive "$MODPATH/webroot" 0 0 0755 0644
