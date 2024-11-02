package dealer;

import common.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    @DisplayName("카드 뽑기 - 덱에서 카드를 뽑을 때 카드가 정상적으로 반환된다.")
    void shouldDrawCardWithoutIssue() {
        Deck deck = Deck.newDeck();
        Card card = deck.drawCard();

        assertNotNull(card, "덱에서 카드를 뽑았을 때 null이 반환되지 않아야 합니다.");
    }

    @Test
    @DisplayName("두 덱에서 셔플 없이 카드 뽑기 - 두 덱의 첫 번째 카드가 동일해야 한다.")
    void shouldDrawSameCardWithoutShufflingFromTwoDecks() {
        Deck deck1 = Deck.newDeck();
        Deck deck2 = Deck.newDeck();

        Card card1 = deck1.drawCard();
        Card card2 = deck2.drawCard();

        assertNotNull(card1, "첫 번째 덱에서 뽑은 카드는 null이 아니어야 합니다.");
        assertNotNull(card2, "두 번째 덱에서 뽑은 카드는 null이 아니어야 합니다.");

        // 두 덱에서 뽑은 카드가 같은지 비교
        assertEquals(card1, card2, "셔플하지 않은 두 덱의 첫 번째 카드는 같아야 합니다.");
    }

    @Test
    @DisplayName("두 덱에서 셔플 후 카드 뽑기 - 두 덱의 첫 번째 카드가 다를 가능성이 높다.")
    void shouldDrawDifferentCardAfterShufflingFromTwoDecks() {
        Deck deck1 = Deck.newDeck();
        Deck deck2 = Deck.newDeck();

        deck1.shuffle();
        deck2.shuffle();

        Card card1 = deck1.drawCard();
        Card card2 = deck2.drawCard();

        assertNotNull(card1, "첫 번째 덱에서 뽑은 카드는 null이 아니어야 합니다.");
        assertNotNull(card2, "두 번째 덱에서 뽑은 카드는 null이 아니어야 합니다.");

        // 두 덱에서 뽑은 카드가 다른지 비교
        assertNotEquals(card1, card2, "셔플된 두 덱의 첫 번째 카드는 다를 가능성이 높습니다.");
    }
}
