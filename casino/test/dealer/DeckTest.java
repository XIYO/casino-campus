package dealer;

import common.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    @DisplayName("카드 뽑기, 카드 뽑는데 문제가 없다")
    void drawTest00() {
        Deck deck = Deck.newDeck();
        Card card = deck.drawCard();

        assertNotNull(card);
    }

    @Test
    @DisplayName("두 덱에서 셔플 없이 카드 뽑아 비교, 동일한 카드")
    void drawTest01() {
        Deck deck1 = Deck.newDeck();
        Deck deck2 = Deck.newDeck();

        Card card1 = deck1.drawCard();
        Card card2 = deck2.drawCard();

        assertNotNull(card1);
        assertNotNull(card2);

        // 두 덱에서 뽑은 카드가 같은지 비교
        assertEquals(card1, card2);
    }

    @Test
    @DisplayName("두 덱에서 셔플 후 카드 뽑아 비교, 다른 카드가 나와야 함")
    void drawTest02() {
        Deck deck1 = Deck.newDeck();
        Deck deck2 = Deck.newDeck();

        deck1.shuffle();
        deck2.shuffle();

        Card card1 = deck1.drawCard();
        Card card2 = deck2.drawCard();

        assertNotNull(card1);
        assertNotNull(card2);

        // 두 덱에서 뽑은 카드가 같은지 비교
        assertNotEquals(card1, card2);
    }
}
