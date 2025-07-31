# 카지노 게임 인터페이스 구조 v1.1

이 프로젝트는 객체지향 프로그래밍의 인터페이스 원칙을 적용하여 카지노 게임 시스템을 설계했습니다.
**v1.1 업데이트**: 모든 클래스가 해당 인터페이스를 구현하도록 변경되었습니다.

## 📁 프로젝트 구조

```
src/main/java/
└── game/
    ├── components/
    │   ├── card/
    │   │   ├── ICard.java        # 카드 인터페이스
    │   │   ├── Card.java         # 카드 구현체 (implements ICard)
    │   │   ├── Suit.java         # 무늬 열거형
    │   │   └── Rank.java         # 랭크 열거형
    │   ├── deck/
    │   │   ├── IDeck.java        # 덱 인터페이스
    │   │   └── Deck.java         # 덱 구현체 (implements IDeck)
    │   └── hand/
    │       ├── IHand.java        # 손패 인터페이스
    │       ├── Hand.java         # 손패 구현체 (implements IHand)
    │       └── HandRank.java     # 포커 족보 열거형
    ├── participants/
    │   ├── player/
    │   │   ├── IPlayer.java      # 플레이어 인터페이스
    │   │   └── Player.java       # 플레이어 구현체 (implements IPlayer)
    │   └── dealer/
    │       ├── IDealer.java      # 딜러 인터페이스
    │       └── Dealer.java       # 딜러 구현체 (implements IDealer)
    └── management/
        └── casino/
            ├── Casino.java       # 메인 게임 클래스
            └── GameState.java    # 게임 상태 관리
```

## 🎯 v1.1의 주요 변경사항

### 1. 인터페이스 위치 변경
- **이전**: 별도 `interfaces/` 패키지
- **현재**: 각 클래스와 같은 패키지에 위치
- **장점**: 응집도 향상, 패키지 간 의존성 최소화

### 2. 구현체 통합
- **이전**: 연습용 빈 구현체(`implementations/`) + 완성된 구현체(`game/`) 분리
- **현재**: 완성된 구현체가 직접 인터페이스를 구현
- **장점**: 단일 책임 원칙, 코드 중복 제거

### 3. 인터페이스 기반 설계
모든 주요 클래스가 인터페이스를 구현:
- `Card implements ICard`
- `Deck implements IDeck`
- `Hand implements IHand`
- `Player implements IPlayer`
- `Dealer implements IDealer`

## 🔄 인터페이스별 상세 정보

### ICard 인터페이스 (`game.components.card.ICard`)
```java
public interface ICard extends Comparable<ICard> {
    Suit getSuit();
    Rank getRank();
    int getValue();
    // equals, hashCode, toString
}
```
**구현체**: `Card.java`

### IDeck 인터페이스 (`game.components.deck.IDeck`)
```java
public interface IDeck {
    void shuffle();
    Card drawCard();
    boolean isEmpty();
}
```
**구현체**: `Deck.java`

### IHand 인터페이스 (`game.components.hand.IHand`)
```java
public interface IHand extends Comparable<IHand> {
    void add(Card card);
    List<Card> getCards();
    boolean isFull();
    void clear();
    HandRank evaluate();
    int open();
}
```
**구현체**: `Hand.java`

### IPlayer 인터페이스 (`game.participants.player.IPlayer`)
```java
public interface IPlayer {
    String getName();
    int getMoney();
    void addMoney(int amount);
    boolean removeMoney(int amount);
    Hand getHand();
    void setHand(Hand hand);
    // 전적 관리 메서드들
}
```
**구현체**: `Player.java`

### IDealer 인터페이스 (`game.participants.dealer.IDealer`)
```java
public interface IDealer {
    void startNewGame();
    void dealCards(List<? extends Player> players);
    List<? extends Player> determineWinners(List<? extends Player> players);
    void distributePrize(List<? extends Player> winners, int prizeAmount);
    void playGame(List<? extends Player> players, int rounds);
}
```
**구현체**: `Dealer.java`

## ✅ 구현 완료 상태

- ✅ **ICard**: 완전 구현됨 (Card.java)
- ⚠️ **IDeck**: TODO 메서드들 구현 필요 (Deck.java)
- ⚠️ **IHand**: 일부 TODO 메서드들 구현 필요 (Hand.java)  
- ⚠️ **IPlayer**: TODO 메서드들 구현 필요 (Player.java)
- ⚠️ **IDealer**: TODO 메서드들 구현 필요 (Dealer.java)

## 🚀 실행 방법

```bash
# 전체 컴파일 테스트
javac -cp src/main/java src/main/java/game/**/*.java

# 메인 게임 실행 (구현 완료 후)
java -cp src/main/java game.management.casino.Casino

# 테스트 실행
./gradlew test
```

## 💡 학습 포인트

- **인터페이스 분리 원칙**: 각 인터페이스는 단일 책임을 가집니다.
- **의존성 역전 원칙**: 구체 클래스 대신 인터페이스에 의존합니다.
- **구현 교체 가능성**: 인터페이스를 통해 다양한 구현체를 사용할 수 있습니다.
- **테스트 용이성**: 인터페이스를 통해 목(Mock) 객체를 쉽게 만들 수 있습니다.
- **패키지 응집도**: 관련된 인터페이스와 구현체가 같은 패키지에 위치합니다.

이 구조를 통해 실제 프로덕션 코드에서 사용되는 인터페이스 기반 설계 패턴을 학습할 수 있습니다! 🎓