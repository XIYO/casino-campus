package common;

import java.util.Comparator;

public class Card implements Comparable<Card> {
    @Override
    public int compareTo(Card o) {
        // 숫자로 먼저 정렬
        int compare = Integer.compare(this.rank.ordinal(), o.rank.ordinal());

        // 숫자가 같다면 모양으로 정렬
        if (compare == 0)
            return Integer.compare(this.suit.ordinal(), o.suit.ordinal());

        return compare;
    }

    public static final Comparator<Card> RANK_ORDER = new RankInsensitiveComparator();

    /**
     * 이너 클래스로 Comparator 구현, String 의 String.CASE_INSENSITIVE_ORDER 와 같이 구현
     */
    private static class RankInsensitiveComparator implements Comparator<Card> {
        @Override
        public int compare(Card c1, Card c2) {
            return Integer.compare(c1.rank.ordinal(), c2.rank.ordinal());
        }
    }

    /**
     * 에이스는 가장 높은 등급이지만 2, 3, 4, 5 가 있을때는 A를 1로 취급하여 스트레이트로 취급해야할 때가 있다.
     */
    public static final Comparator<Card> ACE_LOW_ORDER = new AceLowComparator();

    // 이너 클래스: 에이스가 가장 낮은 카드로 취급되는 Comparator
    private static class AceLowComparator implements Comparator<Card> {
        @Override
        public int compare(Card c1, Card c2) {
            // 에이스가 나오면 최저 등급으로 처리 (ACE의 ordinal을 -1로 간주)
            int rank1 = (c1.rank == Rank.ACE) ? -1 : c1.rank.ordinal();
            int rank2 = (c2.rank == Rank.ACE) ? -1 : c2.rank.ordinal();
            return Integer.compare(rank1, rank2);
        }
    }

    @Override
    public String toString() {
        return this.suit + String.format("%2s", this.rank);
    }

    public enum Suit {
        CLUBS("♣️"), // 가장 낮은 등급
        DIAMONDS("♦️"),
        HEARTS("♥️"),
        SPADES("♠️"); // 가장 높은 등급
        // 그러나 포커에서는 모양이 승패에 영향을 미치지 않음

        private final String symbol;

        Suit(String symbol) {
            this.symbol = symbol;
        }

        @Override
        public String toString() {
            return symbol;
        }
    }


    public enum Rank {
        TWO("2"),
        THREE("3"),
        FOUR("4"),
        FIVE("5"),
        SIX("6"),
        SEVEN("7"),
        EIGHT("8"),
        NINE("9"),
        TEN("10"),
        JACK("J"),
        QUEEN("Q"),
        KING("K"),
        ACE("A");

        private final String symbol;

        Rank(String symbol) {
            this.symbol = symbol;
        }

        @Override
        public String toString() {
            return symbol;
        }
    }

    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return this.suit;
    }

    public Rank getRank() {
        return rank;
    }
}
