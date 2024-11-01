package common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HandTest {

    @Test
    @DisplayName("로얄 플러시")
    void royalStraightFlush() {
        Hand hand = new Hand();
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.KING)); // ♠️K
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.ACE)); // ♠️A
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.QUEEN)); // ♠️Q
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.JACK)); // ♠️J
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.TEN)); // ♠️10

        hand.open();
        assertEquals(Hand.Tier.ROYAL_FLUSH, hand.getTier());
    }

    @Test
    @DisplayName("일반적인 스트레이트 플러시")
    void normalStraightFlush() {
        Hand hand = new Hand();
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.TWO)); // ♠️2
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.THREE)); // ♠️3
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.FOUR)); // ♠️4
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.FIVE)); // ♠️5
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.SIX)); // ♠️6

        hand.open();
        assertEquals(Hand.Tier.STRAIGHT_FLUSH, hand.getTier());
    }

    @Test
    @DisplayName("A가 1로 취급되는 스트레이트 플러시")
    void firstIncludeAceStraightFlush() {
        Hand hand = new Hand();
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.ACE)); // ♠️A
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.TWO)); // ♠️2
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.THREE)); // ♠️3
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.FOUR)); // ♠️4
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.FIVE)); // ♠️5

        hand.open();
        assertEquals(Hand.Tier.STRAIGHT_FLUSH, hand.getTier());
    }

    @Test
    @DisplayName("일반적인 스트레이트")
    void normalStraight() {
        Hand hand = new Hand();
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.FIVE)); // ♠️5
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.SIX)); // ♠️6
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.TWO)); // ♠️2
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.THREE)); // ♠️3
        hand.add(Card.getInstance(Card.Suit.HEARTS, Card.Rank.FOUR)); // ♥️4

        hand.open();
        assertEquals(Hand.Tier.STRAIGHT, hand.getTier());
    }

    @Test
    @DisplayName("포카드")
    void fourOfAKind() {
        Hand hand = new Hand();
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.FIVE)); // ♠️5
        hand.add(Card.getInstance(Card.Suit.HEARTS, Card.Rank.FIVE)); // ♥️5
        hand.add(Card.getInstance(Card.Suit.DIAMONDS, Card.Rank.FIVE)); // ♦️5
        hand.add(Card.getInstance(Card.Suit.CLUBS, Card.Rank.FIVE)); // ♣️5
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.ACE)); // ♠️A

        hand.open();
        assertEquals(Hand.Tier.FOUR_OF_A_KIND, hand.getTier());
    }

    @Test
    @DisplayName("풀하우스")
    void fullHouse() {
        Hand hand = new Hand();
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.FIVE)); // ♠️5
        hand.add(Card.getInstance(Card.Suit.HEARTS, Card.Rank.FIVE)); // ♥️5
        hand.add(Card.getInstance(Card.Suit.DIAMONDS, Card.Rank.FIVE)); // ♦️5
        hand.add(Card.getInstance(Card.Suit.CLUBS, Card.Rank.ACE)); // ♣️A
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.ACE)); // ♠️A

        hand.open();
        assertEquals(Hand.Tier.FULL_HOUSE, hand.getTier());
    }

    @Test
    @DisplayName("플러시")
    void flush() {
        Hand hand = new Hand();
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.FIVE)); // ♠️5
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.TEN)); // ♠️10
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.ACE)); // ♠️A
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.NINE)); // ♠️9
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.SIX)); // ♠️6

        hand.open();
        assertEquals(Hand.Tier.FLUSH, hand.getTier());
    }

    @Test
    @DisplayName("쓰리 카드")
    void threeOfAKind() {
        Hand hand = new Hand();
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.TEN)); // ♠️10
        hand.add(Card.getInstance(Card.Suit.HEARTS, Card.Rank.TEN)); // ♥️10
        hand.add(Card.getInstance(Card.Suit.DIAMONDS, Card.Rank.TEN)); // ♦️10
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.NINE)); // ♠️9
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.SIX)); // ♠️6

        hand.open();
        assertEquals(Hand.Tier.THREE_OF_A_KIND, hand.getTier());
    }

    @Test
    @DisplayName("투페어")
    void twoPair() {
        Hand hand = new Hand();
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.TEN)); // ♠️10
        hand.add(Card.getInstance(Card.Suit.HEARTS, Card.Rank.TEN)); // ♥️10
        hand.add(Card.getInstance(Card.Suit.DIAMONDS, Card.Rank.NINE)); // ♦️9
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.NINE)); // ♠️9
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.SIX)); // ♠️6

        hand.open();
        assertEquals(Hand.Tier.TWO_PAIR, hand.getTier());
    }

    @Test
    @DisplayName("원페어")
    void onePair() {
        Hand hand = new Hand();
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.TEN)); // ♠️10
        hand.add(Card.getInstance(Card.Suit.HEARTS, Card.Rank.TEN)); // ♥️10
        hand.add(Card.getInstance(Card.Suit.DIAMONDS, Card.Rank.NINE)); // ♦️9
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.EIGHT)); // ♠️8
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.SIX)); // ♠️6

        hand.open();
        assertEquals(Hand.Tier.ONE_PAIR, hand.getTier());
    }

    @Test
    @DisplayName("하이카드")
    void highCard() {
        Hand hand = new Hand();
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.TEN)); // ♠️10
        hand.add(Card.getInstance(Card.Suit.HEARTS, Card.Rank.TWO)); // ♥️2
        hand.add(Card.getInstance(Card.Suit.DIAMONDS, Card.Rank.NINE)); // ♦️9
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.FIVE)); // ♠️5
        hand.add(Card.getInstance(Card.Suit.SPADES, Card.Rank.KING)); // ♠️K

        hand.open();
        assertEquals(Hand.Tier.HIGH_CARD, hand.getTier());
    }
}
