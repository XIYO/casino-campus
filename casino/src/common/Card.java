package common;

import java.util.HashMap;
import java.util.Map;

/**
 * 카드 클래스는 카드 한 장을 나타내며, 무늬(Suit)와 숫자(Rank)를 가집니다.
 */
public class Card implements Comparable<Card> {
    // 이미 생성된 카드들을 저장하는 Map
    private static final Map<String, Card> cardCache = new HashMap<>();

    private final Suit suit; // 카드의 무늬
    private final Rank rank; // 카드의 숫자

    // 프라이빗 생성자 (외부에서 호출 불가)
    private Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * 카드 인스턴스를 가져오는 정적 메서드.
     * 주어진 무늬와 숫자의 카드가 존재하지 않으면 새로 생성하여 반환.
     *
     * @param suit 카드의 무늬
     * @param rank 카드의 숫자
     * @return 무늬와 숫자가 같은 동일한 카드 객체
     */
    public static Card getInstance(Suit suit, Rank rank) {
        // 고유 키 생성 (Suit와 Rank 조합)
        String key = suit.toString() + rank.toString();

        // Map에 해당 키가 없으면 새로운 카드 생성 후 추가
        cardCache.putIfAbsent(key, new Card(suit, rank));

        // 존재하면 기존 객체 반환
        return cardCache.get(key);
    }

    @Override
    public int compareTo(Card o) {
        int compare = Integer.compare(this.rank.ordinal(), o.rank.ordinal());
        if (compare == 0) {
            return Integer.compare(this.suit.ordinal(), o.suit.ordinal());
        }
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
        TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"),
        EIGHT("8"), NINE("9"), TEN("10"), JACK("J"), QUEEN("Q"), KING("K"), ACE("A");

        private final String symbol;

        Rank(String symbol) {
            this.symbol = symbol;
        }

        @Override
        public String toString() {
            return symbol;
        }
    }

    public Suit getSuit() {
        return this.suit;
    }

    public Rank getRank() {
        return rank;
    }
}
