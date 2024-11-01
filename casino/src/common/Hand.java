package common;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Hand implements Iterable<Card>, Comparable<Hand> {
    public enum Tier {
        HIGH_CARD,           // 하이카드
        ONE_PAIR,            // 원페어
        TWO_PAIR,            // 투페어
        THREE_OF_A_KIND,     // 쓰리 카드
        STRAIGHT,            // 스트레이트
        FLUSH,               // 플러시
        FULL_HOUSE,          // 풀하우스
        FOUR_OF_A_KIND,      // 포카드
        STRAIGHT_FLUSH,      // 스트레이트 플러시
        ROYAL_FLUSH          // 로열 플러시
    }

    SortedMap<Card, Boolean> cards; // 패를 구성하는 카드의 집합

    private Tier tier;
    private final SortedSet<Card> tierValues;     // 티어 밸류, 보조 점수 계산 1, 티어를 구성하는 카드를 담는다.
    private final SortedSet<Card> kickers;        // 키커, 보조 점수 계산 2, 티어를 구성하지 않는 카드를 담는다.

    public Tier getTier() {
        return tier;
    }

    private final Map<Card.Rank, Integer> rankCount; // 랭크값을 카운트, 최대 5가지 값이 들어감, 숫자 5
    private final Map<Card.Suit, Integer> suitCount; // 수트값을 카운트, 최대 4가지 값이 들어감, 플러시 판단에 사용

    public Hand() {
        this.cards = new TreeMap<>();
        this.tierValues = new TreeSet<>();
        this.kickers = new TreeSet<>();
        this.rankCount = new EnumMap<>(Card.Rank.class);
        this.suitCount = new EnumMap<>(Card.Suit.class);
    }

    public boolean add(Card card) {
        if (this.cards.size() >= 5) {
            throw new IllegalStateException("손에 들 수 있는 카드는 5장까지입니다.");
        }

        this.cards.put(card, false); // 새로운 카드 추가
        this.rankCount.merge(card.getRank(), 1, Integer::sum); // 랭크 카운트
        this.suitCount.merge(card.getSuit(), 1, Integer::sum); // 수트 카운트

        return true;
    }

    public void clear() {
        cards.clear(); // 모든 카드 제거
        tier = Tier.HIGH_CARD; // 티어 초기화
        tierValues.clear(); // 티어를 구성하는 카드의 값 초기화
        kickers.clear(); // 패를 구성하는 키커 값 초기화

        rankCount.clear(); // 랭크 카운트 초기화
        suitCount.clear(); // 수트 카운트 초기화
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.keySet().iterator();
    }

    @Override
    public String toString() {
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_BOLD = "\u001B[1m";
        final String ANSI_UNDERLINE = "\u001B[4m";
        final String ANSI_RESET = "\u001B[0m";

        String cardsString = this.cards.entrySet().stream()
                .map(entry -> {
                    String cardStr = entry.getKey().toString();
                    if (entry.getValue()) {
                        // true인 카드에 녹색, 볼드, 밑줄 스타일 적용
                        return ANSI_GREEN + ANSI_BOLD + ANSI_UNDERLINE + cardStr + ANSI_RESET;
                    }
                    return cardStr;
                })
                .collect(Collectors.joining(", "));

        return this.tier.toString() + " " + cardsString;
    }

    @Override
    public int compareTo(Hand o) {
        // 1. 티어 비교 (내림차순)
        if (this.tier != o.tier) {
            return o.tier.compareTo(this.tier);  // 높은 티어가 먼저 오도록
        }

        // 2. 메인밸류 비교 (내림차순)
        Iterator<Card> thisMainValue = this.tierValues.iterator();
        Iterator<Card> oMainValue = o.tierValues.iterator();
        while (thisMainValue.hasNext() && oMainValue.hasNext()) {
            int compare = oMainValue.next().compareTo(thisMainValue.next());
            if (compare != 0) return compare;  // 높은 값이 먼저 오도록
        }

        // 3. 키커 비교 (내림차순)
        Iterator<Card> thisKicker = this.kickers.iterator();
        Iterator<Card> oKicker = o.kickers.iterator();
        while (thisKicker.hasNext() && oKicker.hasNext()) {
            int compare = oKicker.next().compareTo(thisKicker.next());
            if (compare != 0) return compare;  // 높은 키커가 먼저 오도록
        }

        // 4. 모든 값이 동일하면 무승부 (0 반환)
        return 0;
    }

    private void evaluate() {
        // 02. 티어를 판단한다.
        if (this.isRoyalFlush()) {
            this.tier = Tier.ROYAL_FLUSH;
        } else if (this.isStraightFlush()) {
            this.tier = Tier.STRAIGHT_FLUSH;
        } else if (this.isFourOfAKind()) {
            this.tier = Tier.FOUR_OF_A_KIND;
        } else if (this.isFullHouse()) {
            this.tier = Tier.FULL_HOUSE;
        } else if (this.isFlush()) {
            this.tier = Tier.FLUSH;
        } else if (this.isStraight()) {
            this.tier = Tier.STRAIGHT;
        } else if (this.isThreeOfAKind()) {
            this.tier = Tier.THREE_OF_A_KIND;
        } else if (this.isTwoPair()) {
            this.tier = Tier.TWO_PAIR;
        } else if (this.isOnePair()) {
            this.tier = Tier.ONE_PAIR;
        } else {
            this.tier = Tier.HIGH_CARD;
        }

        // 03. 메인밸류와 키커를 구분한다.
        for (Card card : this.cards.keySet()) {
            if (Boolean.TRUE.equals(this.cards.get(card))) {
                this.tierValues.add(card); // 티어를 구성하는 카드로 추가
            } else {
                this.kickers.add(card); // 티어를 구성하지 않는 카드로 추가
            }
        }
    }

    private int countPair() {
        int pairCount = 0;
        for (int sameCardRankCount : rankCount.values())
            if (sameCardRankCount == 2) pairCount++;
        if (pairCount == 0) return 0; // 페어가 없음

        this.setRankCard(card -> rankCount.get(card.getRank()) == 2); // 페어를 구성하는 카드를 체크
        return pairCount;
    }

    private boolean isOnePair() {
        return countPair() == 1;
    }

    private boolean isTwoPair() {
        return countPair() == 2;
    }

    private boolean isThreeOfAKind() {
        Optional<Card.Rank> threeOfAKindRank = rankCount.entrySet().stream()
                .filter(entry -> entry.getValue() == 3)
                .map(Map.Entry::getKey)
                .findFirst();
        if (threeOfAKindRank.isEmpty()) return false; // 비어있다면 쓰리카드 아님

        this.setRankCard(card -> card.getRank() == threeOfAKindRank.get()); // 쓰리카드를 구성하는 카드를 체크
        return true;
    }

    private boolean isFullHouse() {
        // 1. 랭크별 카드 개수를 필터링하여 분류
        boolean isThreeOfAKind = isThreeOfAKind();
        boolean isOnePair = isOnePair();

        return isThreeOfAKind && isOnePair;  // 풀 하우스의 조건을 성립하지 못함
    }

    private boolean isFourOfAKind() {
        // 1. 포카드 랭크를 찾는다. 단 하나만 나온다.
        Optional<Card.Rank> optionalFourOfAKindRank = this.rankCount.entrySet().stream()
                .filter(entry -> entry.getValue() == 4)
                .map(Map.Entry::getKey)
                .findFirst();
        if (optionalFourOfAKindRank.isEmpty()) return false;  // 포카드 아님

        this.setRankCard(card -> card.getRank() == optionalFourOfAKindRank.get()); // 포카드를 구성하는 카드를 체크
        return true;
    }

    private boolean isRoyalFlush() {
        boolean isStraight = isStraight();
        boolean isFirstCardAce = this.cards.firstKey().getRank() == Card.Rank.TEN; // 반드시 10과 비교해야한다. 2, 3, 4, 5, A도 스트레이트 이기 떄문에 마지막을 ACE 인지 확인하면 로열이
        boolean isFlush = isFlush();
        return isStraight && isFirstCardAce && isFlush;
    }

    private boolean isStraightFlush() {
        return isFlush() && isStraight();
    }

    // 패가 스트레이트인지 확인
    private boolean isStraight() {
        Iterator<Card> iterator = this.cards.keySet().iterator();
        Card beforeCard = iterator.next(); // 최초의 카드, 반드시 존재하기 때문에 null 체크 생략

        // 연속된 숫자를 검사하고, 5, A가 연속되면 스트레이트로 판단
        while (iterator.hasNext()) {
            Card nextCard = iterator.next();

            // 5, A가 연속되면 스트레이트로 판단, 아래에서 2 > 3 > 4 > 5 까지의 검사하기 때문에 여기서는 5 > A 만 검사
            if (beforeCard.getRank().ordinal() == Card.Rank.FIVE.ordinal() && nextCard.getRank().ordinal() == Card.Rank.ACE.ordinal()) {
                break; // 루프를 탈출하여 와일 밖의 로직을 계속 실행한다.
            } else if (beforeCard.getRank().ordinal() + 1 != nextCard.getRank().ordinal()) return false; // 스트레이트 아님
            beforeCard = nextCard;
        }

        this.cards.replaceAll((c, v) -> true); // 모든 카드를 족보를 이루는 구성으로 변경
        return true;
    }

    private boolean isFlush() {
        if (!this.suitCount.containsValue(5)) return false;

        this.cards.replaceAll((c, v) -> true);
        return true;
    }

    private void setRankCard(Predicate<Card> predicate) {
        for (Card card : this.cards.keySet())
            if (predicate.test(card)) this.cards.put(card, true); // 족보를 이루는 구성으로 변경
    }

    public Hand open() {
        if (this.cards.size() != 5) {
            throw new IllegalStateException("패를 공개하기 위해서는 5장의 카드가 필요합니다.");
        }
        this.evaluate(); // 패의 티어를 판단
        return this;
    }
}
