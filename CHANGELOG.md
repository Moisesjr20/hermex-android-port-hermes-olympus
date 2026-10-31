# OLYMPUS Mobile — Changelog

Mudanças notáveis desde o fork do upstream `Hungbocluaqua/hermex-android-port`.
Formato segue [Keep a Changelog](https://keepachangelog.com/).

## v1.1.3-olympus.1 (2026-10-31) — versionCode 100

### Added
- **Logo oficial OLYMPUS** (Monte Olimpo + raio de Zeus)
  - `hermex_app_icon_default.png` 1024x1024 (safe-zone 66%)
  - Adaptive-icon XML pra API 26+ e API 33+
  - Background navy #0A1628 em `colors.xml`
- **Network Security Config** restritivo (`network_security_config.xml`):
  - Base: `cleartextTrafficPermitted=false` (TLS enforced)
  - Allowlist cleartext: localhost + 127.0.0.1 + RFC 1918 (10/8, 172.16/12, 192.168/16) + 169.254/16 + mDNS .local
  - `<debug-overrides>` aceita user CAs só em debug builds (Charles Proxy etc)
- **CI workflow** `olympus-apk.yml`: signed release build + verify com apksigner + upload artifact
- `olympus_play_store.png` 512x512 pra listing
- `olympus_launcher_xxxhdpi.png` 192x192

### Changed
- **18 locales strings.xml** — "Hermex" → "OLYMPUS":
  app_name, share_target_label, 4 shortcut labels, widget_description
- **Theme.kt** Material3 reescrito com paleta OLYMPUS:
  - Primary `#F59E0B` (orange)
  - Background `#0A1628` (navy-900)
  - Surface `#1E3A5F` (navy-700)
  - SurfaceVariant `#162238` (navy-800)
  - OnSurface `#F1F5F9` (white)
- iOS (HermesMobile): APP_DISPLAY_NAME + Info.plist strings (5x) + 2 Swift strings
- `versionCode` bumped 33 → 100
- `versionName` "1.1.3" → "1.1.3-olympus.1"

### Preserved
- Package Android `com.uzairansar.hermex` (compat de instalação)
- Bundle ID iOS `com.uzairansar.hermesmobile`
- Classes/módulos Hermex* (identificadores Kotlin/Swift)

### Security
- APK signed com keystore Kyrius RSA 4096 válida 27 anos
  - SHA256: `25:4B:22:82:6C:78:67:A0:45:76:7C:A3:2B:D3:F0:37:6E:6F:BA:33:B9:54:C5:3C:43:0D:0E:13:4D:02:ED:B3`
  - Alias: `olympus`
- 0 CVEs nas 13 deps principais (consulta OSV API)
- 0 patterns perigosos no scan (Runtime.exec, WebView+JS, TrustManager custom, hardcoded tokens, Log de secrets)
- `AcceptsUserCertificates` warning do lint corrigido (release blinda user CAs)

### Build
- JDK 21 Temurin + Android SDK 36 + build-tools 36
- Gradle 9.6.1 + AGP 9.2.1 + Kotlin 2.3.21
- Lint: BUILD SUCCESSFUL (0 errors)

---

Para histórico anterior ao fork, ver [CHANGELOG.upstream.md](CHANGELOG.upstream.md).
