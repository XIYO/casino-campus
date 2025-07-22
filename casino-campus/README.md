# 🎰 카지노 캠퍼스 - 포커 게임 프로젝트

## 프로젝트 소개
Java OOP를 학습하기 위한 포커 게임 구현 프로젝트입니다.

## 구현 목표
- 5 Card Draw 포커 게임
- 4명의 플레이어, 100라운드 자동 진행
- 네트워크 멀티플레이어 지원 (선택사항)

## 프로젝트 구조
```
src/main/java/
├── game/
│   ├── components/     # 카드, 덱, 핸드
│   ├── participants/   # 플레이어, 딜러
│   └── management/     # 게임 운영
├── network/           # 네트워크 기능
└── Main.java          # 시작점
```

## 실행 방법
IntelliJ IDEA에서 `Main.java`를 실행하세요.

## 구현 순서
1. Card → Deck → Hand
2. Player → Dealer
3. Casino (메인 게임)
4. Network 기능 (선택사항)

## 참고
- 각 인터페이스의 JavaDoc을 읽고 구현하세요
- 테스트 코드를 통해 구현을 검증하세요
- 완성된 예제는 `../casino/` 폴더 참고