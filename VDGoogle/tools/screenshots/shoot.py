#!/usr/bin/env python3
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
#
#  WebUI screenshot harness. Renders the real webui/webroot in a headless
#  Chrome under a mocked `ksu.exec` bridge. The webroot ships RAW shell to
#  ksu.exec and parses the stdout itself, so the mock answers each reader
#  (RCS, Wallet, Play Integrity attestations) with fictional, privacy-safe
#  fixtures (NEVER real device or account data), then captures the five
#  documented tabs in each of the 22 languages. A synthetic device chrome
#  (status bar + nav bar) frames the shot; the in-page app bar is the
#  product banner. Output: docs/screenshots/<lang>/.
#
#  Why this lives in the repo:
#   - every label, the app list and the RTL mirroring come from the live
#     webroot + lang/*.json, so a UI or string change shows on the next run
#     with no edit here;
#   - the shown version is read from webui/module.prop, so a release bump
#     needs no change here either;
#   - fixtures are declared once below; extend a reader branch when the UI
#     starts reading a new source.
#
#  Usage:
#     python3 tools/screenshots/shoot.py            # all 22 languages
#     python3 tools/screenshots/shoot.py pt en ar   # only the given codes
#  Requires: python3-playwright + a system Google Chrome/Chromium.
# ======================================================================

import datetime
import json
import os
import socketserver
import sys
import threading
from http.server import SimpleHTTPRequestHandler

from playwright.sync_api import sync_playwright

HERE = os.path.dirname(os.path.abspath(__file__))
NESTED = os.path.abspath(os.path.join(HERE, "..", ".."))        # VDGoogle/ (gradle project)
REPO = os.path.abspath(os.path.join(NESTED, ".."))              # repo root (has README/.github)
WEBROOT = os.path.join(NESTED, "webui", "webroot")
MODULE_PROP = os.path.join(NESTED, "webui", "module.prop")
OUTDIR = os.path.join(REPO, "docs", "screenshots")

LANGS = ["ar", "cs", "de", "en", "es", "fa", "fr", "hi", "id", "it", "ja",
         "ko", "nl", "pl", "pt", "ru", "sv", "th", "tr", "uk", "vi", "zh"]

# (tab id, output basename) - one shot per webroot tab, in nav order.
SCREENS = [
    ("about", "about"),
    ("droidguard", "droidguard"),
    ("rcs", "rcs"),
    ("wallet", "wallet"),
    ("att", "attestations"),
]

VIEWPORT = {"width": 400, "height": 890}
SCALE = 3
TZID = "Etc/UTC"


def module_version():
    try:
        with open(MODULE_PROP, encoding="utf-8") as f:
            for line in f:
                if line.startswith("version="):
                    return line.split("=", 1)[1].strip()
    except OSError:
        pass
    return "v1.0"


def ms(y, mo, d, h, mi, s=0):
    return int(datetime.datetime(y, mo, d, h, mi, s, tzinfo=datetime.timezone.utc).timestamp() * 1000)


def secs(y, mo, d, h, mi, s=0):
    return ms(y, mo, d, h, mi, s) // 1000


def kvblock(pairs):
    return "".join("%s=%s\n" % (k, "" if v is None else v) for (k, v) in pairs)


def build_fixtures(version):
    # Fictional, privacy-safe sample state: RCS set up and working, a Google
    # Wallet attestation present, a Play Integrity / DroidGuard verdict with
    # the device-certified label and a few attesting apps. All invented;
    # NEVER derived from a real device, SIM, carrier or Google account.
    rcs = kvblock([
        ("avail", 1),
        ("selfnum", "+15555550199"),
        ("cap", 4),
        ("msglast", ms(2026, 11, 24, 19, 42)),
        ("caplast", ms(2026, 11, 25, 8, 15)),
        ("uce", 1),
        ("simname", "Example Mobile"),
        ("simmcc", "001"),
        ("simmnc", "01"),
        ("mversion", 12),
        ("rcsstate", 2),
        ("cfgupd", secs(2026, 11, 24, 8, 15)),
        ("cfgval", 604800),
        ("tokenexp", ms(2026, 12, 20, 8, 15)),
        ("invalid", ""),
        ("engine", "ConfiguredState"),
        ("mdomain", ""),
        ("provlast", secs(2026, 11, 24, 8, 10)),
        ("provnext", secs(2026, 11, 26, 8, 10)),
        ("fullsync", ms(2026, 11, 24, 8, 16)),
        ("lastsync", ms(2026, 11, 25, 8, 15)),
        ("rcsenabled", "true"),
        ("pev2", 3),
        ("configstate", 2),
        ("tachyon", "true"),
        ("provraw", ""),
        ("flagen", "true"),
        ("flagun", "false"),
        ("magic", "true"),
        ("aicore", 1),
        ("ims", 1),
        ("ent", "true"),
        ("gsmalpha", ""),
        ("gsmnum", ""),
    ])

    wallet = kvblock([
        ("nfc", 1),
        ("wpkg", 1),
        ("watt", "true"),
        ("wattt", ms(2026, 11, 25, 7, 40)),
        ("wsvc", "true"),
        ("wtap", "true"),
        ("wpwd", "true"),
        ("wtok", "true"),
    ])

    att_apps = [
        ("com.google.android.apps.walletnfcrel", ms(2026, 11, 25, 9, 12), 2),
        ("com.nianticlabs.pokemongo", ms(2026, 11, 25, 8, 3), 3),
        ("com.google.android.gms", ms(2026, 11, 24, 21, 55), 5),
        ("com.android.vending", ms(2026, 11, 24, 15, 10), 1),
        ("com.netflix.mediaclient", ms(2026, 11, 23, 19, 30), 1),
        ("com.vd171.vdcheck", ms(2026, 11, 22, 10, 5), 1),
    ]
    dg_flows = [
        ("pia_attest_e1", secs(2026, 11, 25, 6, 0), secs(2026, 11, 25, 18, 0)),
        ("fast", secs(2026, 11, 19, 12, 0), secs(2026, 11, 26, 12, 0)),
        ("hades_persephone_risk", secs(2026, 11, 24, 18, 0), secs(2026, 11, 25, 18, 0)),
        ("pia_express", secs(2026, 11, 25, 2, 0), secs(2026, 11, 26, 0, 0)),
    ]
    att = "---TS---\n"
    att += "".join('<long name="%s" value="%d"/>\n' % (p, t) for (p, t, _c) in att_apps)
    att += "---CNT---\n"
    att += "".join('<long name="%s" value="%d"/>\n' % (p, c) for (p, _t, c) in att_apps)
    att += "---CERT---\n   8   1\n---DG---\n"
    att += "".join("%s|%d|%d\n" % (f, i, e) for (f, i, e) in dg_flows)

    dev = kvblock([
        ("model", "Pixel 7"),
        ("man", "Google"),
        ("android", "16"),
        ("sdk", "36"),
    ])

    return {"version": version, "rcs": rcs, "wallet": wallet, "att": att, "dev": dev}


MOCK_JS = r"""
(function () {
  window.__VDG_FIX = %s;
  window.__VDG_LANG = "%s";
  try { localStorage.setItem("vdgoogle_lang", window.__VDG_LANG); } catch (e) {}
  function reply(cmd) {
    var f = window.__VDG_FIX;
    if (/module\.prop/.test(cmd) && /version=/.test(cmd)) return f.version + "\n";
    if (/---TABLES---/.test(cmd)) return "";
    if (/ro\.product\.model/.test(cmd)) return f.dev;
    if (/---DG---/.test(cmd)) return f.att;
    if (/is_rcs_available/.test(cmd)) return f.rcs;
    if (/tapandpay/i.test(cmd)) return f.wallet;
    return "";
  }
  window.ksu = {
    exec: function (cmd, opts, cbName) {
      var out = reply(String(cmd));
      setTimeout(function () {
        try { if (typeof window[cbName] === "function") window[cbName](0, out, ""); } catch (e) {}
      }, 0);
    }
  };
})();
"""


CHROME_JS = r"""
(function () {
  if (document.getElementById('vd-statusbar')) return;
  var SB = 34, NAV = 52;
  var I = {
    wifi: '<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M5 12.55a11 11 0 0 1 14.08 0"></path><path d="M1.42 9a16 16 0 0 1 21.16 0"></path><path d="M8.53 16.11a6 6 0 0 1 6.95 0"></path><line x1="12" y1="20" x2="12.01" y2="20"></line></svg>',
    sig: '<svg viewBox="0 0 24 24" fill="currentColor" stroke="none"><path d="M2 20h3v-6H2zM8 20h3V8H8zM14 20h3V4h-3zM20 20h2V2h-2z" opacity="0.92"></path></svg>',
    batt: '<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><rect x="2" y="7" width="16" height="10" rx="2"></rect><rect x="4" y="9" width="11" height="6" rx="1" fill="currentColor" stroke="none"></rect><line x1="21" y1="11" x2="21" y2="13"></line></svg>',
    back: '<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><polyline points="15 18 9 12 15 6"></polyline></svg>',
    home: '<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><circle cx="12" cy="12" r="9"></circle></svg>',
    recents: '<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><rect x="4" y="4" width="16" height="16" rx="2"></rect></svg>'
  };
  var css = document.createElement('style');
  css.textContent =
    'body{padding-top:' + SB + 'px !important;}' +
    '.wrap{padding-bottom:' + (NAV + 20) + 'px !important;}' +
    '#vd-statusbar{position:fixed;top:0;left:0;right:0;height:' + SB + 'px;z-index:99999;' +
      'display:flex;align-items:center;justify-content:space-between;padding:0 18px;' +
      'background:var(--bg);color:var(--on-surface);font-size:13.5px;font-weight:600;}' +
    '#vd-statusbar .r{display:flex;align-items:center;gap:7px;}' +
    '#vd-statusbar svg{width:15px;height:15px;display:block;}' +
    '#vd-nav{position:fixed;bottom:0;left:0;right:0;height:' + NAV + 'px;z-index:99999;' +
      'display:flex;align-items:center;justify-content:space-around;' +
      'background:var(--bg);color:var(--on-variant);}' +
    '#vd-nav svg{width:22px;height:22px;opacity:.65;display:block;}';
  document.head.appendChild(css);
  var sb = document.createElement('div');
  sb.id = 'vd-statusbar';
  sb.innerHTML = '<span>12:00</span><span class="r">' + I.wifi + I.sig + I.batt + '</span>';
  document.body.appendChild(sb);
  var nav = document.createElement('div');
  nav.id = 'vd-nav';
  nav.innerHTML = I.back + I.home + I.recents;
  document.body.appendChild(nav);
})();
"""


SELECT_JS = r"""
(function (tab) {
  var b = document.querySelector('.tab[data-tab="' + tab + '"]');
  if (b) b.click();
  var a = document.querySelector('.tab.active');
  var strip = document.getElementById('tabs');
  if (a && strip) strip.scrollLeft = Math.max(0, a.offsetLeft - 60);
  window.scrollTo(0, 0);
})
"""


def serve(directory):
    handler = lambda *a, **k: SimpleHTTPRequestHandler(*a, directory=directory, **k)
    httpd = socketserver.ThreadingTCPServer(("127.0.0.1", 0), handler)
    httpd.daemon_threads = True
    threading.Thread(target=httpd.serve_forever, daemon=True).start()
    return httpd, httpd.server_address[1]


def main(argv):
    langs = [a for a in argv[1:] if a in LANGS] or LANGS
    version = module_version()
    fixtures = build_fixtures(version)
    fix_json = json.dumps(fixtures, ensure_ascii=False)
    httpd, port = serve(WEBROOT)
    base = "http://127.0.0.1:%d/index.html" % port
    total = 0
    with sync_playwright() as pw:
        browser = pw.chromium.launch(channel="chrome", headless=True,
                                     args=["--force-color-profile=srgb"])
        try:
            for lang in langs:
                ctx = browser.new_context(viewport=VIEWPORT, device_scale_factor=SCALE,
                                          locale=lang, timezone_id=TZID)
                page = ctx.new_page()
                page.add_init_script(MOCK_JS % (fix_json, lang))
                page.goto(base, wait_until="networkidle")
                page.wait_for_timeout(600)   # let init() + helper microtasks settle
                page.evaluate(CHROME_JS)
                outlang = os.path.join(OUTDIR, lang)
                os.makedirs(outlang, exist_ok=True)
                for tab, name in SCREENS:
                    page.evaluate(SELECT_JS, tab)
                    page.wait_for_timeout(500)   # async ksu reply + render
                    page.evaluate(CHROME_JS)     # re-assert chrome after any reflow
                    page.wait_for_timeout(60)
                    page.screenshot(path=os.path.join(outlang, "%s.png" % name))
                    total += 1
                ctx.close()
                print("  %s: %d screens" % (lang, len(SCREENS)))
        finally:
            browser.close()
    httpd.shutdown()
    print("done: %d PNGs (%d langs) at version %s -> %s" %
          (total, len(langs), version, OUTDIR))


if __name__ == "__main__":
    main(sys.argv)
