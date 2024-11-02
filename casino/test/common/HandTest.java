package common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HandTest {

    @Test
    @DisplayName("로얄 플러시가 올바르게 판정된다.")
    void shouldIdentifyRoyalFlush() {
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
    @DisplayName("스트레이트 플러시가 올바르게 판정된다.")
    void shouldIdentifyStraightFlush() {
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
    @DisplayName("A가 1로 포함된 스트레이트 플러시가 올바르게 판정된다.")
    void shouldIdentifyAceLowStraightFlush() {
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
    @DisplayName("스트레이트가 올바르게 판정된다.")
    void shouldIdentifyStraight() {
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
    @DisplayName("포카드가 올바르게 판정된다.")
    void shouldIdentifyFourOfAKind() {
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
    @DisplayName("풀하우스가 올바르게 판정된다.")
    void shouldIdentifyFullHouse() {
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
    @DisplayName("플러시가 올바르게 판정된다.")
    void shouldIdentifyFlush() {
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
    @DisplayName("쓰리 카드가 올바르게 판정된다.")
    void shouldIdentifyThreeOfAKind() {
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
    @DisplayName("투페어가 올바르게 판정된다.")
    void shouldIdentifyTwoPair() {
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
    @DisplayName("원페어가 올바르게 판정된다.")
    void shouldIdentifyOnePair() {
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
    @DisplayName("하이카드가 올바르게 판정된다.")
    void shouldIdentifyHighCard() {
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
