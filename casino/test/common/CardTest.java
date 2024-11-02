package common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CardTest {

    @Test
    @DisplayName("카드 생성 - 클럽 A가 올바르게 생성된다.")
    void shouldCreateClubAceCard() {
        Card card = Card.getInstance(Card.Suit.CLUBS, Card.Rank.ACE);
        assertEquals("♣️ A", card.toString());
    }

    @Test
    @DisplayName("카드 동일 비교 - 동일한 무늬와 랭크의 카드는 동일하다.")
    void shouldReturnTrueForEqualCards() {
        Card card1 = Card.getInstance(Card.Suit.CLUBS, Card.Rank.ACE);
        Card card2 = Card.getInstance(Card.Suit.CLUBS, Card.Rank.ACE);
        assertEquals(card1, card2);
    }

    @Test
    @DisplayName("카드 비동일 비교 - 다른 랭크의 카드는 동일하지 않다.")
    void shouldReturnFalseForDifferentRankCards() {
        Card card1 = Card.getInstance(Card.Suit.CLUBS, Card.Rank.ACE);
        Card card2 = Card.getInstance(Card.Suit.CLUBS, Card.Rank.TWO);
        assertNotEquals(card1, card2);
    }
}
