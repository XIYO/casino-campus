package common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CardTest {

    @Test
    @DisplayName("카드 생성")
    void createCard() {
        Card card = Card.getInstance(Card.Suit.CLUBS, Card.Rank.ACE);
        assertEquals("♣️ A", card.toString());
    }

    @Test
    @DisplayName("카드 비교")
    void compareCardEquals() {
        Card card1 = Card.getInstance(Card.Suit.CLUBS, Card.Rank.ACE);
        Card card2 = Card.getInstance(Card.Suit.CLUBS, Card.Rank.ACE);
        assertEquals(card1, card2);
    }

    @Test
    @DisplayName("카드 비교")
    void compareCardNotEquals() {
        Card card1 = Card.getInstance(Card.Suit.CLUBS, Card.Rank.ACE);
        Card card2 = Card.getInstance(Card.Suit.CLUBS, Card.Rank.TWO);
        assertNotEquals(card1, card2);
    }
}
