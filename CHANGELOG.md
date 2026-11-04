# Changelog

All notable changes to VD Google. Dates are ISO (YYYY-MM-DD).

## [1.13] - 2026-11-04

- RCS and Google service details now load reliably on more phones. On some devices every value could come up blank; they now read correctly.
- You can now swipe left or right to move between tabs.
- RCS Connection status is now correct on newer Android and Google Messages (such as Android 17), where it could wrongly read as not connected even though RCS was working.
- The RCS "RCS-capable contacts" count now reflects your real contacts, instead of always showing 0.
- The RCS "availability", "state" and "feature" lines are now labeled as cached flags, so a "No" or "0" there is not mistaken for the real status (the Connection line is what counts).
- The WebUI export now matches the app's export exactly, and both now include an optional diagnostics section (no personal data) to help troubleshoot device-specific issues.
- The Priv8_VD Telegram channel is now listed in the contacts, and VD Backup appears in "See also".

## [1.12] - 2026-11-03

- On dual-SIM phones, the carrier and RCS details now follow your data SIM, instead of sometimes showing another SIM's. The app and the WebUI now always agree.
- The WebUI module now works right after you flash it, with no reboot: VD Google mounts nothing, so it is placed live and your root manager can list it and open its WebUI at once. (The app is unaffected; it never needed a reboot.)
- Fixed the module update in your root manager failing with a "Fetch changelog failed" error: the update now carries a changelog link so the manager can show what changed.

## [1.11] - 2026-11-01

- New Gemini section in the RCS tab, with Gemini and Magic Compose status chips at the top: whether Magic Compose is available in Google Messages, whether the on-device AI engine (AICore) is present, and whether Gemini chat (delivered over RCS) works. It lives under RCS because Gemini in Messages rides RCS.
- The top bar and the About header now show the version on its own line under the name, so a longer version like v1.10.1 no longer wraps or overlaps the title.
- The bottom tab labels now stay on one line instead of wrapping (DroidGuard, Attestations).
- The Wallet "Clear" button now also drops the saved attestation result, not just its time, so the status reads Unknown instead of a stale No until Google re-checks on its own.

## [1.10.1] - 2026-10-26

- The Wallet "Device attested" status now reads Unknown, not No, when this device has no saved attestation result, so a device whose Wallet works is never shown as not attested.
- Fixed the Reload button icon briefly stretching into an oval when tapped.

## [1.10] - 2026-10-26

- VD Google now speaks Ukrainian, for 22 languages in all.
- New Reload button in the top bar: it refreshes everything on screen without reopening the app, with a one-second spin so the refresh is clearly visible.
- New light/dark theme toggle in the top bar.
- The Wallet "Device attested" status and its "Last checked" time now come from the same place, so they always match. The status is the last result Google saved on this device, so it can lag the Wallet app until Google re-checks.
- The RCS tab reads right when RCS is connected: the backend shows green, and signals that do not apply to a Google-provisioned line (UCE, Carrier Services and a couple of cached flags) stay neutral instead of looking like errors.

## [1.09] - 2026-10-15

- New DroidGuard tab. It shows your device's Play Protect certification: whether Google Play recognizes this device as certified, the same state shown in the Play Store. Read on-device, no internet.
- The DroidGuard tab also lists the attestations your device has cached for Play Integrity and DroidGuard, with when each one expires.

## [1.08.1] - 2026-10-14

- The Wallet security-check reset now restores the file's original security context after it clears the last-checked time, so the file is left exactly as the system labels it.

## [1.08] - 2026-10-10

- New WebUI edition. Every release now ships two ways to use VD Google: the app (APK) and a flashable WebUI module (ZIP) you open from your root manager (KernelSU, Magisk or APatch). Both read the same RCS, Google Wallet and Play Integrity state on-device, in the same 21 languages, and neither uses the internet.

## [1.07] - 2026-10-04

- New in the Wallet tab: "Device attested" now matches what Google Wallet shows, plus a button (with confirmation) to clear the last security-check time so Wallet re-checks on its next attestation. Every action is kept in a local log on your device. Still no internet.
- More reliable reading of the on-device databases, so RCS details (availability, contacts, last message) and the carrier now show up on phones where they were blank.
- Text in the RCS export now reads normally instead of showing codes like '.

## [1.06] - 2026-10-04

- "Last RCS message" now also counts messages you sent, not only ones you received.

## [1.05] - 2026-10-04

- The carrier is now detected on more phones (some Samsung and others did not report it before).
- The "Marked unavailable" line is hidden when RCS is connected, since that backend flag can stay stale.
- Saved exports are now named with the date and time, like vdgoogle-20261004-2014.json.

## [1.04] - 2026-10-04

- Fixed a layout glitch where a long status value could stack vertically, one letter per line.
- The invalid-configuration reason is now hidden when there is nothing wrong, and shown without its long technical prefix when there is.
- Extra safety on the RCS export so provisioning session cookies are never included, even in rare layouts.

## [1.03] - 2026-10-04

- RCS now shows a reliable Connection status, so it no longer reads as unavailable when RCS is actually working. The old availability flag stays as extra info.
- The RCS export now includes more of the provisioning details (sensitive data like your number and passwords is left out).
- Small fix to how the timezone is shown.

## [1.02] - 2026-10-04

- Export is now JSON, and you can save it to a file (not just share it).
- RCS now shows your carrier and, when the configuration is invalid, the reason.

## [1.01] - 2026-10-04

- Export/share a summary of your RCS and Wallet info. The phone number is left out.

## [1.00] - 2026-10-04

- First release.
- RCS: availability, your number, configuration and sync details.
- Google Wallet: payment attestation, NFC, tap-to-pay and related settings.
- Attestations: which apps have asked Google Play Integrity about the device, with search and reload.
- In-app language selector (21 languages). No internet permission. Root required.
