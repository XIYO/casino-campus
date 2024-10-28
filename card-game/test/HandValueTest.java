import common.Card;
import common.HandValue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.Player;

import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HandValueTest {

    @Test
    @DisplayName("로얄 스트레이트 플러시")
    void royalStraightFlush() {
        Player player = Player.newPlayer("고니");
        player.receiveCard(new Card(Card.Suit.SPADES, Card.Rank.KING)); // ♠️K
        player.receiveCard(new Card(Card.Suit.SPADES, Card.Rank.ACE)); // ♠️A
        player.receiveCard(new Card(Card.Suit.SPADES, Card.Rank.QUEEN)); // ♠️Q
        player.receiveCard(new Card(Card.Suit.SPADES, Card.Rank.JACK)); // ♠️J
        player.receiveCard(new Card(Card.Suit.SPADES, Card.Rank.TEN)); // ♠️10

        HandValue handValue = HandValue.evaluate(player.openHand());
        assertEquals(HandValue.TierType.ROYAL_STRAIGHT_FLUSH, handValue.getTier());
    }

    @Test
    @DisplayName("일반적인 스트레이트 플러시")
    void normalStraightFlush() {
        SortedSet<Card> hand = new TreeSet<>();
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.TWO)); // ♠️2
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.THREE)); // ♠️3
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.FOUR)); // ♠️4
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.FIVE)); // ♠️5
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.SIX)); // ♠️6

        HandValue handValue = HandValue.evaluate(hand);
        assertEquals(HandValue.TierType.STRAIGHT_FLUSH, handValue.getTier());
    }

    @Test
    @DisplayName("A가 1로 취급되는 스트레이트 플러시")
    void firstIncludeAceStraightFlush() {
        SortedSet<Card> hand = new TreeSet<>();
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.ACE)); // ♠️A
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.TWO)); // ♠️2
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.THREE)); // ♠️3
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.FOUR)); // ♠️4
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.FIVE)); // ♠️5

        HandValue handValue = HandValue.evaluate(hand);
        assertEquals(HandValue.TierType.STRAIGHT_FLUSH, handValue.getTier());
    }

    @Test
    @DisplayName("일반적인 스트레이트")
    void normalStraight() {
        SortedSet<Card> hand = new TreeSet<>();
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.FIVE)); // ♠️5
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.ACE)); // ♠️A
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.TWO)); // ♠️2
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.THREE)); // ♠️3
        hand.add(new Card(Card.Suit.HEARTS, Card.Rank.FOUR)); // ♥️4

        HandValue handValue = HandValue.evaluate(hand);
        assertEquals(HandValue.TierType.STRAIGHT, handValue.getTier());
    }

    @Test
    @DisplayName("포카드")
    void fourOfAKind() {
        SortedSet<Card> hand = new TreeSet<>();
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.FIVE)); // ♠️5
        hand.add(new Card(Card.Suit.HEARTS, Card.Rank.FIVE)); // ♥️5
        hand.add(new Card(Card.Suit.DIAMONDS, Card.Rank.FIVE)); // ♦️5
        hand.add(new Card(Card.Suit.CLUBS, Card.Rank.FIVE)); // ♣️5
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.ACE)); // ♠️A

        HandValue handValue = HandValue.evaluate(hand);
        assertEquals(HandValue.TierType.FOUR_OF_A_KIND, handValue.getTier());
    }

    @Test
    @DisplayName("풀하우스")
    void fullHouse() {
        SortedSet<Card> hand = new TreeSet<>();
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.FIVE)); // ♠️5
        hand.add(new Card(Card.Suit.HEARTS, Card.Rank.FIVE)); // ♥️5
        hand.add(new Card(Card.Suit.DIAMONDS, Card.Rank.FIVE)); // ♦️5
        hand.add(new Card(Card.Suit.CLUBS, Card.Rank.ACE)); // ♣️A
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.ACE)); // ♠️A

        HandValue handValue = HandValue.evaluate(hand);
        assertEquals(HandValue.TierType.FULL_HOUSE, handValue.getTier());
    }

    @Test
    @DisplayName("플러시")
    void flush() {
        SortedSet<Card> hand = new TreeSet<>();
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.FIVE)); // ♠️5
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.TEN)); // ♠️10
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.ACE)); // ♠️A
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.NINE)); // ♠️9
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.SIX)); // ♠️6

        HandValue handValue = HandValue.evaluate(hand);
        assertEquals(HandValue.TierType.FLUSH, handValue.getTier());
    }

    @Test
    @DisplayName("쓰리 카드")
    void threeOfAKind() {
        SortedSet<Card> hand = new TreeSet<>();
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.TEN)); // ♠️10
        hand.add(new Card(Card.Suit.HEARTS, Card.Rank.TEN)); // ♥️10
        hand.add(new Card(Card.Suit.DIAMONDS, Card.Rank.TEN)); // ♦️10
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.NINE)); // ♠️9
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.SIX)); // ♠️6

        HandValue handValue = HandValue.evaluate(hand);
        assertEquals(HandValue.TierType.THREE_OF_A_KIND, handValue.getTier());
    }

    @Test
    @DisplayName("투페어")
    void twoPair() {
        SortedSet<Card> hand = new TreeSet<>();
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.TEN)); // ♠️10
        hand.add(new Card(Card.Suit.HEARTS, Card.Rank.TEN)); // ♥️10
        hand.add(new Card(Card.Suit.DIAMONDS, Card.Rank.NINE)); // ♦️9
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.NINE)); // ♠️9
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.SIX)); // ♠️6

        HandValue handValue = HandValue.evaluate(hand);
        assertEquals(HandValue.TierType.TWO_PAIR, handValue.getTier());
    }

    @Test
    @DisplayName("원페어")
    void onePair() {
        SortedSet<Card> hand = new TreeSet<>();
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.TEN)); // ♠️10
        hand.add(new Card(Card.Suit.HEARTS, Card.Rank.TEN)); // ♥️10
        hand.add(new Card(Card.Suit.DIAMONDS, Card.Rank.NINE)); // ♦️9
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.EIGHT)); // ♠️8
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.SIX)); // ♠️6

        HandValue handValue = HandValue.evaluate(hand);
        assertEquals(HandValue.TierType.ONE_PAIR, handValue.getTier());
    }

    @Test
    @DisplayName("하이카드")
    void highCard() {
        SortedSet<Card> hand = new TreeSet<>();
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.TEN)); // ♠️10
        hand.add(new Card(Card.Suit.HEARTS, Card.Rank.TWO)); // ♥️2
        hand.add(new Card(Card.Suit.DIAMONDS, Card.Rank.NINE)); // ♦️9
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.FIVE)); // ♠️5
        hand.add(new Card(Card.Suit.SPADES, Card.Rank.KING)); // ♠️K

        HandValue handValue = HandValue.evaluate(hand);
        assertEquals(HandValue.TierType.HIGH_CARD, handValue.getTier());
    }
}
