# 🃏 카드 게임 연습 프로젝트

## 📌 프로젝트 소개
이 프로젝트는 Java OOP 과제를 위한 연습 프로젝트입니다. 카드 게임의 핵심 구성 요소인 Card, Deck, Hand를 단계별로 구현하면서 객체지향 프로그래밍을 학습합니다.

## 🎯 학습 목표
- 인터페이스와 구현체의 분리 이해
- 불변 객체(Immutable Object) 패턴 학습
- Collections Framework 활용
- 테스트 주도 개발(TDD) 경험

## 📁 프로젝트 구조
```
card-game-practice/
├── src/
│   ├── card/           # 카드 관련 인터페이스
│   │   ├── Card.java
│   │   ├── Suit.java
│   │   └── Rank.java
│   ├── deck/           # 덱 관련 인터페이스
│   │   └── Deck.java
│   ├── hand/           # 손패 관련 인터페이스
│   │   └── Hand.java
│   └── Main.java       # 메인 실행 클래스
├── test/
│   ├── card/           # 카드 테스트
│   │   └── CardTest.java
│   ├── deck/           # 덱 테스트
│   │   └── DeckTest.java
│   └── hand/           # 손패 테스트
│       └── HandTest.java
├── LEARNING_PLAN.md    # 4일 학습 계획
└── README.md           # 이 파일
```

## 🚀 시작하기

### 1. 프로젝트 열기
IntelliJ IDEA에서 이 폴더를 프로젝트로 열어주세요.

### 2. 구현 순서
1. **Day 1**: Card 인터페이스 구현 → CardTest 통과
2. **Day 2**: Deck 인터페이스 구현 → DeckTest 통과
3. **Day 3**: Hand 인터페이스 구현 → HandTest 통과
4. **Day 4**: 통합 및 Main 클래스 완성

### 3. 테스트 실행 방법
각 테스트 클래스의 main 메서드를 실행하세요:
```bash
# 컴파일
javac -d out src/**/*.java test/**/*.java

# 테스트 실행
java -cp out card.CardTest
java -cp out deck.DeckTest
java -cp out hand.HandTest
```

## 📝 구현 가이드

### Card 구현 예시
```java
public class PlayingCard implements Card {
    private final Suit suit;
    private final Rank rank;
    
    public PlayingCard(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }
    
    // 나머지 메서드 구현...
}
```

### 주의사항
- 인터페이스는 수정하지 마세요
- 테스트 코드의 주석을 해제하면서 진행하세요
- 모든 테스트가 통과해야 합니다

## 📚 참고 자료
- [Java Collections Framework](https://docs.oracle.com/javase/8/docs/technotes/guides/collections/overview.html)
- [Effective Java - 불변성](https://www.oracle.com/technical-resources/articles/java/bloch-effective-java-3e.html)
- [Java Comparator Interface](https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html)

## ✅ 체크리스트
- [ ] Card 구현 완료
- [ ] Deck 구현 완료
- [ ] Hand 구현 완료
- [ ] 모든 테스트 통과
- [ ] Main 클래스 시연 동작

## 🎉 완성 후
이 연습 프로젝트를 완성하면 실제 포커 게임 구현을 위한 기반이 완성됩니다!

상세한 학습 계획은 [LEARNING_PLAN.md](LEARNING_PLAN.md)를 참고하세요.