# Changelog

All notable changes to VD Google. Dates are ISO (YYYY-MM-DD).

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
