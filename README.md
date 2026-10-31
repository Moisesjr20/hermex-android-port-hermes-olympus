<div align="center">

<img src="android/app/src/main/res/drawable-nodpi/olympus_play_store.png" alt="OLYMPUS" width="128" />

# OLYMPUS Mobile — Android

**Client Android nativo pra conversar com o seu agente Hermes auto-hospedado — rebranded, signed, publicado por Kyrius.**

Navy + laranja. Monte Olimpo + raio de Zeus. Zero Hermex no launcher.

[![Latest release](https://img.shields.io/github/v/release/Moisesjr20/hermex-android-port-hermes-olympus?label=release&color=F59E0B&style=for-the-badge)](https://github.com/Moisesjr20/hermex-android-port-hermes-olympus/releases/latest)
[![Android 8+](https://img.shields.io/badge/Android-8%2B-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.3-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)](https://kotlinlang.org)
[![License: MIT](https://img.shields.io/badge/License-MIT-brightgreen?style=for-the-badge)](LICENSE)

</div>

---

## O que é

OLYMPUS Mobile é um **client Android nativo** (Kotlin + Jetpack Compose) pra se conectar em instâncias [hermes-webui](https://github.com/nesquena/hermes-webui) auto-hospedadas. Rebrand visual + hardening de segurança do upstream [`Hungbocluaqua/hermex-android-port`](https://github.com/Hungbocluaqua/hermex-android-port) sob a identidade OLYMPUS.

- 🎨 **Logo oficial Monte Olimpo + raio** nos launchers (adaptive icon mono/light/dark)
- 🎨 **Theme Material3 navy+laranja** (`#0A1628` / `#F59E0B`)
- 🌍 **18 locales i18n** limpos — zero menção a "Hermex"
- 🔒 **TLS strict por padrão** — cleartext só pra RFC 1918 + mDNS (`.local`)
- 🔒 **APK signed** com keystore Kyrius RSA 4096 (válida 27 anos)
- 📦 Package Android: `com.uzairansar.hermex` (preservado pra compat)

## Download

### APK release signed
Baixa da [última release](https://github.com/Moisesjr20/hermex-android-port-hermes-olympus/releases/latest):
`OLYMPUS-mobile-vX.Y.Z.apk` (~21 MB)

### Instalação
1. Baixa o `.apk` no celular (Android 8+)
2. Toca pra instalar → aceita "Install from unknown sources" na primeira vez
3. Google Play Protect pode avisar na primeira instalação (chave nova pra Google) → "Mais detalhes" → "Instalar assim mesmo"
4. Atualizações futuras instalam **por cima** sem conflito (mesma chave)

## Conectar no seu Hermes

Ao abrir o app, informa:

- **URL do servidor**: HTTPS pública da sua instância hermes-webui (ex: `https://meu-server.ts.net`)
- **Senha**: configurada em `HERMES_WEBUI_PASSWORD` no server
- **Cabeçalhos (opcional)**: pra Cloudflare Access, Zero Trust etc

## Fingerprint da assinatura

```
Alias:    olympus
Keystore: RSA 4096 / SHA384
Validade: 31/10/2026 → 18/03/2054
SHA-256:  25:4B:22:82:6C:78:67:A0:45:76:7C:A3:2B:D3:F0:37:
          6E:6F:BA:33:B9:54:C5:3C:43:0D:0E:13:4D:02:ED:B3
```

Qualquer APK OLYMPUS oficial deve bater com esse fingerprint. SHA256 diferente = adulterado.

Verifica localmente:
```bash
apksigner verify --print-certs OLYMPUS-mobile.apk | grep SHA-256
```

## Build local (dev)

```bash
git clone https://github.com/Moisesjr20/hermex-android-port-hermes-olympus.git
cd hermex-android-port-hermes-olympus/android

# Debug build (sem signing, pra testar)
./gradlew assembleDebug

# Release build (precisa env vars de signing)
export HERMEX_ANDROID_KEYSTORE_FILE=/caminho/pra/sua.keystore
export HERMEX_ANDROID_KEYSTORE_PASSWORD=...
export HERMEX_ANDROID_KEY_ALIAS=olympus
export HERMEX_ANDROID_KEY_PASSWORD=...
./gradlew assembleRelease

# Testes
./gradlew lintDebug          # lint static analysis
./gradlew testDebugUnitTest  # unit tests Kotlin
```

Requisitos: JDK 21+ (Temurin), Android SDK 36.

## Security hardening

Fork aplica defesa em profundidade além do upstream:

- **Network Security Config** restritivo (`network_security_config.xml`): base `cleartextTrafficPermitted=false`, allowlist cleartext apenas pra RFC 1918 + loopback + mDNS
- **User CA trust** só em debug builds (`<debug-overrides>`)
- **0 CVEs** nas 13 deps principais (OSV API audit: kotlin-stdlib, compose, room, okhttp, markwon, etc)
- **0 patterns perigosos**: Runtime.exec, WebView+JS, TrustManager custom, hardcoded tokens, Log de secrets

## Attribution

Este projeto é um **fork rebrandeado** sob licença MIT de:

- **Upstream base:** [`Hungbocluaqua/hermex-android-port`](https://github.com/Hungbocluaqua/hermex-android-port) (© 2024-2026, MIT)
- **WebUI backend:** [`nesquena/hermes-webui`](https://github.com/nesquena/hermes-webui) (nesquena)
- **Agent backend:** [NousResearch/hermes-agent](https://github.com/NousResearch/hermes-agent) (Nous Research)

Rebrand OLYMPUS (logo, strings, theme, network hardening, build pipeline) por **Moises Kyrius** sob Kyrius. Changes documentadas em `CHANGELOG.md`. Original README preservado em [`README.upstream.md`](README.upstream.md).

## License

MIT — ver [`LICENSE`](LICENSE). Preserva todos os termos do upstream.

---

<div align="center">

**OLYMPUS** · parte do stack multi-agente da Kyrius
Painel web: [olympus.kyrius.com.br](https://olympus.kyrius.com.br) · Desktop: [github.com/Moisesjr20/hermes-desktop-olympus](https://github.com/Moisesjr20/hermes-desktop-olympus)

</div>
