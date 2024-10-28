package common;

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

    @Override
    public String toString() {
        return this.suit + String.format("%2s", this.rank);
    }

    public enum Suit {
        CLUBS("♣️"),
        DIAMONDS("♦️"),
        HEARTS("♥️"),
        SPADES("♠️");

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
