import common.Card;
import common.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HandTest {

    @Test
    @DisplayName("로얄 플러시")
    void royalStraightFlush() {
        Hand hand = new Hand();
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.KING)); // ♠️K
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.ACE)); // ♠️A
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.QUEEN)); // ♠️Q
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.JACK)); // ♠️J
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.TEN)); // ♠️10

        assertEquals(Hand.TierType.ROYAL_FLUSH, hand.getTier());
    }

    @Test
    @DisplayName("일반적인 스트레이트 플러시")
    void normalStraightFlush() {
        Hand hand = new Hand();
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.TWO)); // ♠️2
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.THREE)); // ♠️3
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.FOUR)); // ♠️4
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.FIVE)); // ♠️5
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.SIX)); // ♠️6

        assertEquals(Hand.TierType.STRAIGHT_FLUSH, hand.getTier());
    }

    @Test
    @DisplayName("A가 1로 취급되는 스트레이트 플러시")
    void firstIncludeAceStraightFlush() {
        Hand hand = new Hand();
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.ACE)); // ♠️A
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.TWO)); // ♠️2
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.THREE)); // ♠️3
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.FOUR)); // ♠️4
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.FIVE)); // ♠️5

        assertEquals(Hand.TierType.STRAIGHT_FLUSH, hand.getTier());
    }

    @Test
    @DisplayName("일반적인 스트레이트")
    void normalStraight() {
        Hand hand = new Hand();
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.FIVE)); // ♠️5
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.SIX)); // ♠️6
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.TWO)); // ♠️2
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.THREE)); // ♠️3
        hand.add(new Card(Card.Suit.HEARTS, Card.Rank.FOUR)); // ♥️4

        assertEquals(Hand.TierType.STRAIGHT, hand.getTier());
    }

    @Test
    @DisplayName("포카드")
    void fourOfAKind() {
        Hand hand = new Hand();
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.FIVE)); // ♠️5
        hand.add(new Card(Card.Suit.HEARTS, Card.Rank.FIVE)); // ♥️5
        hand.add(new Card(Card.Suit.DIAMONDS, Card.Rank.FIVE)); // ♦️5
        hand.add(new Card(Card.Suit.CLUBS, Card.Rank.FIVE)); // ♣️5
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.ACE)); // ♠️A

        assertEquals(Hand.TierType.FOUR_OF_A_KIND, hand.getTier());
    }

    @Test
    @DisplayName("풀하우스")
    void fullHouse() {
        Hand hand = new Hand();
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.FIVE)); // ♠️5
        hand.add(new Card(Card.Suit.HEARTS, Card.Rank.FIVE)); // ♥️5
        hand.add(new Card(Card.Suit.DIAMONDS, Card.Rank.FIVE)); // ♦️5
        hand.add(new Card(Card.Suit.CLUBS, Card.Rank.ACE)); // ♣️A
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.ACE)); // ♠️A

        assertEquals(Hand.TierType.FULL_HOUSE, hand.getTier());
    }

    @Test
    @DisplayName("플러시")
    void flush() {
        Hand hand = new Hand();
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.FIVE)); // ♠️5
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.TEN)); // ♠️10
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.ACE)); // ♠️A
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.NINE)); // ♠️9
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.SIX)); // ♠️6

        assertEquals(Hand.TierType.FLUSH, hand.getTier());
    }

    @Test
    @DisplayName("쓰리 카드")
    void threeOfAKind() {
        Hand hand = new Hand();
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.TEN)); // ♠️10
        hand.add(new Card(Card.Suit.HEARTS, Card.Rank.TEN)); // ♥️10
        hand.add(new Card(Card.Suit.DIAMONDS, Card.Rank.TEN)); // ♦️10
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.NINE)); // ♠️9
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.SIX)); // ♠️6

        assertEquals(Hand.TierType.THREE_OF_A_KIND, hand.getTier());
    }

    @Test
    @DisplayName("투페어")
    void twoPair() {
        Hand hand = new Hand();
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.TEN)); // ♠️10
        hand.add(new Card(Card.Suit.HEARTS, Card.Rank.TEN)); // ♥️10
        hand.add(new Card(Card.Suit.DIAMONDS, Card.Rank.NINE)); // ♦️9
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.NINE)); // ♠️9
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.SIX)); // ♠️6

        assertEquals(Hand.TierType.TWO_PAIR, hand.getTier());
    }

    @Test
    @DisplayName("원페어")
    void onePair() {
        Hand hand = new Hand();
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.TEN)); // ♠️10
        hand.add(new Card(Card.Suit.HEARTS, Card.Rank.TEN)); // ♥️10
        hand.add(new Card(Card.Suit.DIAMONDS, Card.Rank.NINE)); // ♦️9
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.EIGHT)); // ♠️8
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.SIX)); // ♠️6

        assertEquals(Hand.TierType.ONE_PAIR, hand.getTier());
    }

    @Test
    @DisplayName("하이카드")
    void highCard() {
        Hand hand = new Hand();
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.TEN)); // ♠️10
        hand.add(new Card(Card.Suit.HEARTS, Card.Rank.TWO)); // ♥️2
        hand.add(new Card(Card.Suit.DIAMONDS, Card.Rank.NINE)); // ♦️9
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.FIVE)); // ♠️5
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.KING)); // ♠️K

        assertEquals(Hand.TierType.HIGH_CARD, hand.getTier());
    }
}
