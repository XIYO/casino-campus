# 🎰 카지노 캠퍼스

Java OOP를 활용한 포커 게임 구현 프로젝트

## 📝 프로젝트 소개

"라스베가스 드림 카지노"의 첫 번째 게임 개발 프로젝트입니다. 
객체지향 프로그래밍의 핵심 개념을 활용하여 터미널 기반의 포커 게임을 구현합니다.

## 🎮 게임 특징

- **게임 종류**: 파이브 카드 드로우 포커
- **플레이어**: 4명 (자동 플레이)
- **게임 횟수**: 100판 자동 진행
- **승리 보상**: +100원

## 🛠️ 기술 스택

- Java 11+
- 객체지향 프로그래밍 (OOP)
- JUnit (테스트)

## 📁 프로젝트 구조

```
casino-campus/
├── src/
│   ├── card/         # 카드 관련 클래스
│   ├── deck/         # 덱 관련 클래스
│   ├── player/       # 플레이어 관련 클래스
│   ├── hand/         # 손패 및 족보 판정
│   ├── dealer/       # 딜러 및 게임 진행
│   └── Casino.java   # 메인 진입점
└── test/             # 테스트 코드
```

## 🚀 시작하기

### 요구사항
- Java 11 이상
- IDE (IntelliJ IDEA, Eclipse 등)

### 실행 방법
```bash
# 프로젝트 클론
git clone https://github.com/XIYO/casino-campus.git

# 프로젝트 디렉토리로 이동
cd casino-campus

# 컴파일 및 실행
javac -d out src/**/*.java
java -cp out Casino
```

## 📖 개발 가이드

자세한 요구사항과 구현 가이드는 [REFINED_REQUIREMENTS.md](REFINED_REQUIREMENTS.md)를 참고하세요.

## 📄 라이선스

이 프로젝트는 교육 목적으로 만들어졌습니다.
