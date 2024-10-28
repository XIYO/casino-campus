package common;

import java.util.*;

public class Hand implements Iterable<Card>, Comparable<Hand> {
    public enum TierType {
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

    SortedSet<Card> cards;

    private Hand.TierType tier;
    private final SortedSet<Card> mainValues;     // 티어 밸류, 보조 점수 계산 1
    private final SortedSet<Card> kickers;        // 키커, 보조 점수 계산 2

    public Hand.TierType getTier() {
        return tier;
    }

    // 랭크와 수트를 카운트하는 맵
    private final Map<Card.Rank, Integer> rankCount;
    private final Map<Card.Suit, Integer> suitCount;

    public Hand() {
        this.cards = new TreeSet<>();
        this.mainValues = new TreeSet<>();
        this.kickers = new TreeSet<>();
        this.rankCount = new EnumMap<>(Card.Rank.class);
        this.suitCount = new EnumMap<>(Card.Suit.class);
    }

    public boolean add(Card card) {
        if (this.cards.size() >= 5) {
            throw new IllegalStateException("손에 들 수 있는 카드는 5장까지입니다.");
        }
        boolean result = this.cards.add(card);
        rankCount.merge(card.getRank(), 1, Integer::sum);
        suitCount.merge(card.getSuit(), 1, Integer::sum);

        if (result && this.cards.size() == 5)
            this.evaluate();

        return result;
    }

    public void clear() {
        cards.clear();
        tier = null;
        mainValues.clear();
        kickers.clear();

        rankCount.clear();
        suitCount.clear();
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }

    @Override
    public String toString() {
        return this.tier.toString();
    }

    public String toDetailString() {
        return this.cards.toString();
    }

    public Card first() {
        return cards.first();
    }

    public Card last() {
        return cards.last();
    }

    @Override
    public int compareTo(Hand o) {
        // 1. 티어 비교 (내림차순)
        if (this.tier != o.tier) {
            return o.tier.compareTo(this.tier);  // 높은 티어가 먼저 오도록
        }

        // 2. 메인밸류 비교 (내림차순)
        Iterator<Card> thisMainValue = this.mainValues.iterator();
        Iterator<Card> oMainValue = o.mainValues.iterator();
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
            this.tier = Hand.TierType.ROYAL_FLUSH;
        } else if (this.isStraightFlush()) {
            this.tier = Hand.TierType.STRAIGHT_FLUSH;
        } else if (this.isFourOfAKind()) {
            this.tier = Hand.TierType.FOUR_OF_A_KIND;
        } else if (this.isFullHouse()) {
            this.tier = Hand.TierType.FULL_HOUSE;
        } else if (this.isFlush()) {
            this.tier = Hand.TierType.FLUSH;
        } else if (this.isStraight()) {
            this.tier = Hand.TierType.STRAIGHT;
        } else if (this.isThreeOfAKind()) {
            this.tier = Hand.TierType.THREE_OF_A_KIND;
        } else if (this.isTwoPair()) {
            this.tier = Hand.TierType.TWO_PAIR;
        } else if (this.isOnePair()) {
            this.tier = Hand.TierType.ONE_PAIR;
        } else {
            this.highCard(); // 하이카드
            this.tier = Hand.TierType.HIGH_CARD;
        }
    }

    /**
     * 하이카드일 경우에는 비교 밸류를 다 제거하여 모든 하이카드가 동등한 가치로 판단한다.
     */
    private void highCard() {
        this.mainValues.clear(); // 정리
        this.kickers.clear(); // 정리
    }

    private boolean isOnePair() {
        Card.Rank pairRank = rankCount.entrySet().stream()
                .filter(entry -> entry.getValue() == 2)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);

        if (pairRank == null) return false; // 원페어가 아님

        for (Card card : this.cards) {
            if (card.getRank() == pairRank) {
                this.mainValues.add(card); // 페어는 메인밸류에 추가
            } else {
                this.kickers.add(card); // 나머지 카드는 키커에 추가
            }
        }

        return true;
    }

    private boolean isTwoPair() {
        List<Card.Rank> pairRanks = new ArrayList<>();
        for (Map.Entry<Card.Rank, Integer> entry : rankCount.entrySet())
            if (entry.getValue() == 2) pairRanks.add(entry.getKey());

        if (pairRanks.size() != 2) return false; // 투페어가 아님

        for (Card card : this.cards) {
            if (pairRanks.contains(card.getRank())) {
                this.mainValues.add(card); // 페어는 메인밸류에 추가
            } else {
                this.kickers.add(card); // 나머지 카드는 키커에 추가
            }
        }

        return true;
    }

    private boolean isThreeOfAKind() {
        Card.Rank threeOfAKindRank = rankCount.entrySet().stream()
                .filter(entry -> entry.getValue() == 3)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);

        if (threeOfAKindRank == null) return false; // 쓰리카드 아님

        for (Card card : this.cards) {
            if (card.getRank() == threeOfAKindRank) {
                this.mainValues.add(card); // 쓰리카드는 메인밸류에 추가
            } else {
                this.kickers.add(card); // 나머지 카드는 키커에 추가
            }
        }

        return true;
    }

    private boolean isFullHouse() {
        // 1. 랭크별 카드 개수를 필터링하여 분류
        List<Card.Rank> threeOfAKindRanks = this.rankCount.entrySet().stream()
                .filter(entry -> entry.getValue() == 3)
                .map(Map.Entry::getKey)
                .toList();

        List<Card.Rank> pairRanks = this.rankCount.entrySet().stream()
                .filter(entry -> entry.getValue() == 2)
                .map(Map.Entry::getKey)
                .toList();

        // 2. 쓰리카드와 페어가 있어야 풀하우스가 성립
        if (threeOfAKindRanks.isEmpty() || pairRanks.isEmpty()) return false;

        // 3. 메인밸류 설정 (쓰리카드 + 페어)
        Card.Rank threeOfAKindRank = threeOfAKindRanks.getFirst();  // 첫 번째 쓰리카드 사용
        Card.Rank pairRank = pairRanks.getFirst();  // 첫 번째 페어 사용

        this.mainValues.clear();
        for (Card card : this.cards) {
            if (card.getRank() == threeOfAKindRank || card.getRank() == pairRank) {
                this.mainValues.add(card);  // 쓰리카드와 페어는 메인밸류에 추가
            }
        }

        // 4. 풀하우스는 키커가 필요하지 않음
        this.kickers.clear();

        return true;
    }

    private boolean isFourOfAKind() {
        // 1. 포카드 랭크를 찾는다. 단 하나만 나온다.
        Card.Rank fourOfAKindRank = this.rankCount.entrySet().stream()
                .filter(entry -> entry.getValue() == 4)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);

        if (fourOfAKindRank == null) return false;  // 포카드 아님

        // 2. 메인밸류, 키커 설정
        this.mainValues.clear(); // 정리
        this.kickers.clear(); // 정리

        for (Card card : this.cards) {
            if (card.getRank() == fourOfAKindRank) {
                this.mainValues.add(card); // 포카드는 메인밸류에 추가
            } else {
                this.kickers.add(card);  // 나머지 카드는 키커에 추가
            }
        }

        return true;
    }

    private boolean isRoyalFlush() {
        boolean isStraight = isStraight();
        boolean isFirstCardAceRank = this.cards.first().getRank() == Card.Rank.TEN; // 반드시 10과 비교해야한다. 왜냐하면 A는 1로도 계산되기 때문이다.
        return isStraight && isFirstCardAceRank;
    }

    private boolean isStraightFlush() {
        return isFlush() && isStraight();
    }

    // 패가 스트레이트인지 확인
    private boolean isStraight() {
        Iterator<Card> iterator = this.cards.iterator();
        Card beforeCard = iterator.next(); // 최초의 카드, 반드시 존재하기 때문에 null 체크 생략
        while (iterator.hasNext()) {
            Card nextCard = iterator.next();

            if (beforeCard.getRank().ordinal() == Card.Rank.FIVE.ordinal() && nextCard.getRank().ordinal() == Card.Rank.ACE.ordinal()) {
                // 5, A가 연속되면 스트레이트로 판단
                break;
            } else if (beforeCard.getRank().ordinal() + 1 != nextCard.getRank().ordinal()) return false; // 스트레이트 아님
            beforeCard = nextCard;
        }

        this.mainValues.clear(); // 정리

        this.mainValues.addAll(this.cards); // 스트레이트일 경우 메인 밸류에 추가
        this.kickers.clear();// 스트레이트일 경우 5장의 카드가 모두 사용되기 때문에 키커는 없다.

        return true;
    }

    private boolean isFlush() {
        boolean isFlush = this.suitCount.containsValue(5);
        if (isFlush) {
            this.mainValues.clear(); // 정리

            this.mainValues.addAll(this.cards); // 플러시일 경우 메인 밸류에 추가
            this.kickers.clear(); // 플러시일 경우 5장의 카드가 모두 사용되기 때문에 키커는 없다.
        }
        return isFlush;
    }
}
