package card;

import game.components.card.Card;
import game.components.card.Suit;
import game.components.card.Rank;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Card 클래스 테스트
 * 
 * 학생들이 Card 클래스를 올바르게 구현했는지 확인합니다.
 */
public class CardTest {
    
    @Test
    @DisplayName("카드 생성 및 기본 속성 테스트")
    public void testCardCreation() {
        // given
        Suit expectedSuit = Suit.SPADES;
        Rank expectedRank = Rank.ACE;
        
        // when
        Card card = new Card(expectedSuit, expectedRank);
        
        // then
        assertEquals(expectedSuit, card.getSuit(), "카드의 무늬가 올바르게 설정되어야 합니다");
        assertEquals(expectedRank, card.getRank(), "카드의 숫자가 올바르게 설정되어야 합니다");
    }
    
    @Test
    @DisplayName("카드 문자열 표현 테스트")
    public void testCardToString() {
        // given
        Card card = new Card(Suit.HEARTS, Rank.KING);
        
        // when
        String result = card.toString();
        
        // then
        assertTrue(result.contains("♥"), "하트 기호가 포함되어야 합니다");
        assertTrue(result.contains("K"), "K가 포함되어야 합니다");
    }
    
    @Test
    @DisplayName("카드 비교 테스트 - Rank 기준")
    public void testCardCompareByRank() {
        // given
        Card card1 = new Card(Suit.SPADES, Rank.TWO);
        Card card2 = new Card(Suit.HEARTS, Rank.ACE);
        Card card3 = new Card(Suit.DIAMONDS, Rank.TWO);
        
        // when & then
        assertTrue(card1.compareTo(card2) < 0, "2는 A보다 작아야 합니다");
        assertTrue(card2.compareTo(card1) > 0, "A는 2보다 커야 합니다");
        assertEquals(0, card1.compareTo(card3), "같은 숫자는 0이어야 합니다 (무늬 무시)");
    }
    
    @Test
    @DisplayName("카드 비교 테스트 - Suit 기준 (Rank가 같을 때)")
    public void testCardCompareBySuit() {
        // given
        Card card1 = new Card(Suit.CLUBS, Rank.ACE);
        Card card2 = new Card(Suit.SPADES, Rank.ACE);
        
        // when
        int result = card1.compareTo(card2);
        
        // then
        // Suit enum의 순서에 따라 비교됩니다
        assertTrue(result != 0, "같은 Rank일 때 Suit로 비교되어야 합니다");
    }
    
    @Test
    @DisplayName("equals와 hashCode 테스트")
    public void testEqualsAndHashCode() {
        // given
        Card card1 = new Card(Suit.HEARTS, Rank.QUEEN);
        Card card2 = new Card(Suit.HEARTS, Rank.QUEEN);
        Card card3 = new Card(Suit.HEARTS, Rank.KING);
        
        // when & then
        assertEquals(card1, card2, "같은 무늬와 숫자의 카드는 동일해야 합니다");
        assertNotEquals(card1, card3, "다른 카드는 동일하지 않아야 합니다");
        assertEquals(card1.hashCode(), card2.hashCode(), "같은 카드의 hashCode는 동일해야 합니다");
    }
    
    @Test
    @DisplayName("모든 카드 조합 생성 테스트")
    public void testAllCardCombinations() {
        // given
        int expectedCardCount = Suit.values().length * Rank.values().length;
        int actualCardCount = 0;
        
        // when
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                Card card = new Card(suit, rank);
                assertNotNull(card, "모든 조합의 카드가 생성되어야 합니다");
                actualCardCount++;
            }
        }
        
        // then
        assertEquals(52, actualCardCount, "총 52장의 카드가 생성되어야 합니다");
        assertEquals(expectedCardCount, actualCardCount, "예상한 카드 수와 실제 카드 수가 일치해야 합니다");
    }
}