> **Languages:** [English](../BINARIES.md) · [العربية](../ar/BINARIES.md) · [Čeština](../cs/BINARIES.md) · [Deutsch](../de/BINARIES.md) · [Español](../es/BINARIES.md) · [فارسی](../fa/BINARIES.md) · [Français](../fr/BINARIES.md) · [हिन्दी](../hi/BINARIES.md) · [Bahasa Indonesia](../in/BINARIES.md) · [Italiano](../it/BINARIES.md) · [日本語](../ja/BINARIES.md) · 한국어 · [Nederlands](../nl/BINARIES.md) · [Polski](../pl/BINARIES.md) · [Português](../pt/BINARIES.md) · [Русский](../ru/BINARIES.md) · [Svenska](../sv/BINARIES.md) · [ไทย](../th/BINARIES.md) · [Türkçe](../tr/BINARIES.md) · [Tiếng Việt](../vi/BINARIES.md) · [中文](../zh/BINARIES.md)

---

# 내장 바이너리: 출처와 감사

VD Google는 불투명한 것은 아무것도 내장하지 않는다고 약속합니다. root로 실행되며 가장
사적인 상태를 읽으므로, 무엇을 담고 있는지 정확히 볼 수 있어야 합니다. 이 페이지는 앱과
모듈이 내장하는 하나의 네이티브 도구, 그것이 어디서 오는지, 그리고 그것을 검증하는 방법을
나열합니다.

## 원칙

VD Google는 정확히 하나의 네이티브 도구만 내장합니다: `sqlite3` 명령과, 그것이 필요로 하는
소수의 공유 라이브러리 집합입니다. 이것이 필요한 이유는 Android 자체 프레임워크 SQLite가
일부 제조사 ROM에서 다른 앱의 데이터베이스를 열지 못하기 때문이며, 그렇게 되면 VD Google가
그런 기기에서 아무것도 읽지 못하게 됩니다. 이유는 [아키텍처](ARCHITECTURE.md)를
참조하세요.

- **기기가 아니라 업스트림에서 가져옴.** `sqlite3`와 그 라이브러리는 공식 **Termux** 패키지
  저장소(`packages.termux.dev/apt`)의 서명된 `.deb` 패키지에서 오며, 어떤 전화기에 설치된
  Termux에서 복사해 온 것이 결코 아닙니다.
- **어디서나 동일한 바이트.** 동일한 파일이 앱(`assets/bin/` 아래)과 WebUI 모듈(`bin/`
  아래)에 들어 있어, 둘 다 하나의 엔진으로 읽고 일치합니다.
- **체크섬이 곧 신원.** 복사본이 어느 경로에 있든, 그 SHA-256이 기록된 값과 일치할 때만
  진짜 도구입니다.
- **기록된 감사 추적.** 모든 ABI 디렉터리에는 각 소스 패키지(이름, 버전, URL, `.deb`
  SHA-256)와 내장된 모든 파일의 SHA-256을 나열하는 `PROVENANCE.json`이 들어 있습니다. 확인할
  수 있는 항목 없이는 어떤 것도 내장되지 않습니다.

## 출처

`.github/scripts/build_bin.sh`가 컴파일러도 NDK도 없이 빌드 시점에 페이로드를 조립합니다.
각 ABI에 대해 다음을 수행합니다:

1. Termux Packages 색인을 읽고 `sqlite` 패키지와 그 전이적 의존성(`readline`, `ncurses`,
   `zlib`, `libandroid-support`)을 해석합니다;
2. 공식 서명된 `.deb` 파일을 다운로드하고 추출합니다;
3. `sqlite3`를 복사한 다음, 바이너리가 실제로 필요로 하는 공유 라이브러리 클로저만
   복사하고(`readelf`로 해석), Android가 항상 스스로 제공하는 라이브러리는 건너뜁니다;
4. `PROVENANCE.json`을 작성합니다.

Termux 바이너리는 Termux 자체 라이브러리에 대해 동적으로 링크되므로, VD Google는 그
클로저를 `sqlite3`와 함께 내장하고, 내장된 `lib/` 디렉터리를 가리키는 `LD_LIBRARY_PATH`로
실행합니다. Termux는 버전이 붙은 soname(`libz.so.1`, `libreadline.so.8`,
`libncursesw.so.6`)을 사용하므로, Android 프레임워크 자체 라이브러리와 결코 충돌하지
않습니다. `LD_LIBRARY_PATH`는 오직 `sqlite3` 프로세스에만 설정되며, 전역으로는 결코 설정되지
않습니다.

## 파일

업스트림 **SQLite** 명령줄 셸, Termux 패키지 **sqlite 3.54.1-1**. ABI당 하나의 페이로드:
`sqlite3` 바이너리와 그 4개 라이브러리 클로저(`libreadline.so.8`, `libncursesw.so.6`,
`libz.so.1`, `libandroid-support.so`). 내장된 모든 파일을 SHA-256과 크기와 함께:

`arm64-v8a`:

```
7aa726994d3b6316941eebd5a19e8155492281cd10d86dd9e04c9f11a400dad3  sqlite3  (1584288 bytes)
739cf829511d71dafd6c67fdbb70f3f0c6048642ea2e1967790ee961fde14430  lib/libandroid-support.so  (20736 bytes)
795f855f5a988d9e89116847b2c9aa03720cedbc02026259ca735be25398c4c5  lib/libncursesw.so.6  (384496 bytes)
7437d5e30e2a326f3000286295cb9ba30a2e45f9ca5a91feba9ec12d0f1a161d  lib/libreadline.so.8  (321912 bytes)
df5e95e3ea1ffb8f2e0dcebfc43a6c0824b6aebec11752ad8f8764123e24f8a6  lib/libz.so.1  (74056 bytes)
```

`armeabi-v7a`:

```
ca1f5837b178a4b08b10baba585e80565b83ebe269d60770795cb8aa3bd3e053  sqlite3  (1079112 bytes)
2d569da2ffc5515fd4d634f22ab5d13c2686d38e8869ceed847ad3319c6bf999  lib/libandroid-support.so  (17384 bytes)
e7c301bc235e39984ab71f1906b00cc8cdfaea3451207597bc96132c8e247ca9  lib/libncursesw.so.6  (255700 bytes)
9151947397181668cc3cf5bdb9560f0e1b299d7b43bf2b5b65fc236de081c5b8  lib/libreadline.so.8  (206236 bytes)
fcf98d0343a3bc6326e08f92ca30f7a456eca87aa3834c05c1042a1c2daedeac  lib/libz.so.1  (56048 bytes)
```

동일한 체크섬이 바이너리 옆에 함께 제공되는 각 `bin/<abi>/PROVENANCE.json`에 기록되어
있습니다.

## 직접 검증하기

소스 체크아웃에서 내장된 모든 파일을 확인하세요:

```sh
sha256sum VDGoogle/app/src/main/assets/bin/*/sqlite3 VDGoogle/app/src/main/assets/bin/*/lib/* \
          VDGoogle/webui/bin/*/sqlite3 VDGoogle/webui/bin/*/lib/*
```

각 해시는 위 목록과 일치해야 하며, 앱과 WebUI 복사본은 서로 일치해야 합니다. 전체 페이로드를
처음부터 끝까지 감사하려면, 각 `PROVENANCE.json`에 나열된 고정된 `.deb` URL을 다시 받아
추출한 뒤, 파일 SHA-256 값을 비교하세요.

## 무엇에 쓰이는가

`sqlite3`는 따로 떠 둔 데이터베이스 복사본을 대상으로 root로 실행되며, 순전히 **읽기**
위해서만 쓰입니다. Google의 어떤 데이터베이스에도 쓰기에 사용되지 않습니다. 유일한 역할은
VD Google가 모든 기기에서 같은 값을 읽을 수 있게 하는 것입니다. 다른 어떤 네이티브
바이너리도 내장되지 않습니다.
