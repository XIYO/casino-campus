package dealer;

import common.Card;

import java.util.*;

public class HandValue implements Comparable<HandValue> {
    // 하이카드
    public static final HandValue HIGH_CARD = new HandValue();

    @Override
    public int compareTo(HandValue o) {
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
        ROYAL_STRAIGHT_FLUSH // 로열 스트레이트 플러시
    }

    public static HandValue evaluate(SortedSet<Card> hand) {
        return new HandValue(hand);
    }

    private final SortedSet<Card> hand;
    private final TierType tier;
    private final SortedSet<Card> mainValues;     // 티어 밸류, 보조 점수 계산 1
    private final SortedSet<Card> kickers;        // 키커, 보조 점수 계산 2

    public TierType getTier() {
        return tier;
    }

    // 랭크와 수트를 카운트하는 맵
    Map<Card.Rank, Integer> rankCount;
    Map<Card.Suit, Integer> suitCount;

    private HandValue() {
        this.hand = Collections.unmodifiableSortedSet(new TreeSet<>());
        this.tier = TierType.HIGH_CARD;
        this.mainValues = Collections.unmodifiableSortedSet(new TreeSet<>());
        this.kickers = Collections.unmodifiableSortedSet(new TreeSet<>());
    }

    private HandValue(SortedSet<Card> hand) {
        if (hand.size() != 5) {
            throw new IllegalArgumentException("패가 5장이 아닙니다.");
        }

        this.hand = hand;

        // 패의 밸류를 계산,
        // 패의 밸류는 랭크로만 판단하고, 수트는 무시한다. 포커의 규칙!
        // 내림 차순으로 정렬
        Comparator<Card> rankOrderReversed = Card.RANK_ORDER.reversed();
        this.mainValues = new TreeSet<>(rankOrderReversed);
        this.kickers = new TreeSet<>(rankOrderReversed);

        // 01. 패의 랭크와 모양을 카운트한다.
        rankCount = new EnumMap<>(Card.Rank.class);
        suitCount = new EnumMap<>(Card.Suit.class);

        for (Card card : this.hand) {
            rankCount.merge(card.getRank(), 1, Integer::sum);
            suitCount.merge(card.getSuit(), 1, Integer::sum);
        }

        // 02. 티어를 판단한다.
        if (this.isRoyalStraightFlush()) {
            this.tier = TierType.ROYAL_STRAIGHT_FLUSH;
        } else if (this.isStraightFlush()) {
            this.tier = TierType.STRAIGHT_FLUSH;
        } else if (this.isFourOfAKind()) {
            this.tier = TierType.FOUR_OF_A_KIND;
        } else if (this.isFullHouse()) {
            this.tier = TierType.FULL_HOUSE;
        } else if (this.isFlush()) {
            this.tier = TierType.FLUSH;
        } else if (this.isStraight()) {
            this.tier = TierType.STRAIGHT;
        } else if (this.isThreeOfAKind()) {
            this.tier = TierType.THREE_OF_A_KIND;
        } else if (this.isTwoPair()) {
            this.tier = TierType.TWO_PAIR;
        } else if (this.isOnePair()) {
            this.tier = TierType.ONE_PAIR;
        } else {
            this.highCard(); // 하이카드
            this.tier = TierType.HIGH_CARD;
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

        for (Card card : this.hand) {
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

        for (Card card : this.hand) {
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

        for (Card card : this.hand) {
            if (card.getRank() == threeOfAKindRank) {
                this.mainValues.add(card); // 쓰리카드는 메인밸류에 추가
            } else {
                this.kickers.add(card); // 나머지 카드는 키커에 추가
            }
        }

        return true;
    }

    private boolean isFullHouse() {
        // 1. 쓰리카드 랭크를 찾는다. 단 하나만 나온다.
        Card.Rank threeOfAKindRank = this.rankCount.entrySet().stream()
                .filter(entry -> entry.getValue() == 3)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);

        if (threeOfAKindRank == null) return false;  // 풀하우스의 구성 요소가 아님

        // 2. 페어 랭크를 찾는다. 단 하나만 나온다.
        Card.Rank pairRank = this.rankCount.entrySet().stream()
                .filter(entry -> entry.getValue() == 2)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);

        if (pairRank == null) return false;  // 풀하우스의 구성 요소가 아님

        // 3. 메인밸류, 키커 설정
        for (Card card : this.hand) {
            if (card.getRank() == threeOfAKindRank || card.getRank() == pairRank) {
                this.mainValues.add(card); // 쓰리카드, 페어는 메인밸류에 추가
            } else {
                this.kickers.add(card);  // 나머지 카드는 키커에 추가
            }
        }

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

        for (Card card : this.hand) {
            if (card.getRank() == fourOfAKindRank) {
                this.mainValues.add(card); // 포카드는 메인밸류에 추가
            } else {
                this.kickers.add(card);  // 나머지 카드는 키커에 추가
            }
        }

        return true;
    }


    private boolean isRoyalStraightFlush() {
        boolean isStraight = isStraight();
        boolean isFirstCardAceRank = this.hand.first().getRank() == Card.Rank.TEN; // 반드시 10과 비교해야한다. 왜냐하면 A는 1로도 계산되기 때문이다.
        return isStraight && isFirstCardAceRank;
    }

    private boolean isStraightFlush() {
        return isFlush() && isStraight();
    }

    // 패가 스트레이트인지 확인
    private boolean isStraight() {
        SortedSet<Card> hand;
        // 2, 3, 4, 5, A 는 A, 2, 3, 4, 5 로 계산하기 위해 에이스를 가장 앞에 넣는다.
        if (this.hand.first().getRank() == Card.Rank.TWO && this.hand.last().getRank() == Card.Rank.ACE) {
            hand = new TreeSet<>(Card.ACE_LOW_ORDER);
        } else {
            hand = new TreeSet<>(Card.RANK_ORDER);
        }

        hand.addAll(this.hand);

        Iterator<Card> iterator = hand.iterator();
        Card beforeCard = iterator.next(); // 최초의 카드, 반드시 존재하기 때문에 null 체크 생략
        int beforeCardRankOrdinal = beforeCard.getRank() == Card.Rank.ACE ? -1 : beforeCard.getRank().ordinal();
        while (iterator.hasNext()) {
            Card nextCard = iterator.next();
            int nextCardRankOrdinal = nextCard.getRank().ordinal();

            if (beforeCardRankOrdinal + 1 != nextCardRankOrdinal) return false; // 스트레이트 아님
            beforeCardRankOrdinal = nextCardRankOrdinal;
        }

        this.mainValues.clear(); // 정리

        this.mainValues.addAll(hand); // 스트레이트일 경우 메인 밸류에 추가
        this.kickers.clear();// 스트레이트일 경우 5장의 카드가 모두 사용되기 때문에 키커는 없다.

        return true;
    }

    private boolean isFlush() {
        boolean isFlush = this.suitCount.containsValue(5);
        if (isFlush) {
            this.mainValues.clear(); // 정리

            this.mainValues.addAll(this.hand); // 플러시일 경우 메인 밸류에 추가
            this.kickers.clear(); // 플러시일 경우 5장의 카드가 모두 사용되기 때문에 키커는 없다.
        }
        return isFlush;
    }

    public String toString() {
        return this.tier.toString();
    }
}
