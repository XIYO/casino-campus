package game.components.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Card 클래스 테스트
 * 
 * Card 클래스의 compareTo()와 toString() 메서드를 중심으로 테스트합니다.
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
    @DisplayName("카드 toString() 테스트 - 다양한 카드")
    public void testCardToString() {
        // 스페이드 A
        Card aceOfSpades = new Card(Suit.SPADES, Rank.ACE);
        String result1 = aceOfSpades.toString();
        assertTrue(result1.contains("A") && result1.contains("♠"), 
            "스페이드 A는 'A♠' 형식이어야 합니다. 실제: " + result1);
        
        // 하트 K
        Card kingOfHearts = new Card(Suit.HEARTS, Rank.KING);
        String result2 = kingOfHearts.toString();
        assertTrue(result2.contains("K") && result2.contains("♥"), 
            "하트 K는 'K♥' 형식이어야 합니다. 실제: " + result2);
        
        // 다이아몬드 10
        Card tenOfDiamonds = new Card(Suit.DIAMONDS, Rank.TEN);
        String result3 = tenOfDiamonds.toString();
        assertTrue(result3.contains("10") && result3.contains("♦"), 
            "다이아몬드 10은 '10♦' 형식이어야 합니다. 실제: " + result3);
        
        // 클럽 2
        Card twoOfClubs = new Card(Suit.CLUBS, Rank.TWO);
        String result4 = twoOfClubs.toString();
        assertTrue(result4.contains("2") && result4.contains("♣"), 
            "클럽 2는 '2♣' 형식이어야 합니다. 실제: " + result4);
    }
    
    @Test
    @DisplayName("카드 compareTo() 테스트 - Rank 비교")
    public void testCardCompareByRank() {
        // A > K > Q > J > 10 > ... > 3 > 2
        Card ace = new Card(Suit.SPADES, Rank.ACE);
        Card king = new Card(Suit.HEARTS, Rank.KING);
        Card queen = new Card(Suit.DIAMONDS, Rank.QUEEN);
        Card jack = new Card(Suit.CLUBS, Rank.JACK);
        Card ten = new Card(Suit.SPADES, Rank.TEN);
        Card two = new Card(Suit.HEARTS, Rank.TWO);
        
        // ACE가 가장 강함
        assertTrue(ace.compareTo(king) > 0, "ACE는 KING보다 커야 합니다");
        assertTrue(ace.compareTo(queen) > 0, "ACE는 QUEEN보다 커야 합니다");
        assertTrue(ace.compareTo(two) > 0, "ACE는 TWO보다 커야 합니다");
        
        // KING > QUEEN > JACK
        assertTrue(king.compareTo(queen) > 0, "KING은 QUEEN보다 커야 합니다");
        assertTrue(queen.compareTo(jack) > 0, "QUEEN은 JACK보다 커야 합니다");
        assertTrue(jack.compareTo(ten) > 0, "JACK은 TEN보다 커야 합니다");
        
        // TWO가 가장 약함
        assertTrue(two.compareTo(ace) < 0, "TWO는 ACE보다 작아야 합니다");
        assertTrue(two.compareTo(king) < 0, "TWO는 KING보다 작아야 합니다");
        assertTrue(two.compareTo(ten) < 0, "TWO는 TEN보다 작아야 합니다");
    }
    
    @Test
    @DisplayName("카드 compareTo() 테스트 - 같은 Rank일 때 Suit 비교")
    public void testCardCompareBySuit() {
        // 같은 Rank(ACE)를 가진 다른 Suit 카드들
        Card aceSpades = new Card(Suit.SPADES, Rank.ACE);
        Card aceHearts = new Card(Suit.HEARTS, Rank.ACE);
        Card aceDiamonds = new Card(Suit.DIAMONDS, Rank.ACE);
        Card aceClubs = new Card(Suit.CLUBS, Rank.ACE);
        
        // Suit enum 순서: SPADES, HEARTS, DIAMONDS, CLUBS
        assertTrue(aceSpades.compareTo(aceHearts) < 0, "SPADES ACE < HEARTS ACE");
        assertTrue(aceHearts.compareTo(aceDiamonds) < 0, "HEARTS ACE < DIAMONDS ACE");
        assertTrue(aceDiamonds.compareTo(aceClubs) < 0, "DIAMONDS ACE < CLUBS ACE");
        
        // 역순 비교
        assertTrue(aceClubs.compareTo(aceSpades) > 0, "CLUBS ACE > SPADES ACE");
        
        // 같은 카드 비교
        Card anotherAceSpades = new Card(Suit.SPADES, Rank.ACE);
        assertEquals(0, aceSpades.compareTo(anotherAceSpades), "같은 카드는 0을 반환해야 합니다");
    }
    
    @Test
    @DisplayName("카드 compareTo() 테스트 - 실제 게임 시나리오")
    public void testCardCompareGameScenario() {
        // 포커에서 자주 나오는 비교 상황들
        Card playerCard = new Card(Suit.HEARTS, Rank.QUEEN);
        Card dealerCard = new Card(Suit.SPADES, Rank.JACK);
        
        assertTrue(playerCard.compareTo(dealerCard) > 0, 
            "플레이어의 Q가 딜러의 J를 이겨야 합니다");
        
        // 같은 숫자, 다른 무늬
        Card player2Card = new Card(Suit.DIAMONDS, Rank.QUEEN);
        assertTrue(player2Card.compareTo(playerCard) > 0, 
            "같은 Q일 때, 다이아몬드가 하트보다 커야 합니다 (Suit 순서)");
    }
    
    @Test
    @DisplayName("getValue() 테스트")
    public void testGetValue() {
        // given & when & then
        assertEquals(2, new Card(Suit.SPADES, Rank.TWO).getValue(), "TWO의 값은 2");
        assertEquals(3, new Card(Suit.HEARTS, Rank.THREE).getValue(), "THREE의 값은 3");
        assertEquals(10, new Card(Suit.DIAMONDS, Rank.TEN).getValue(), "TEN의 값은 10");
        assertEquals(11, new Card(Suit.CLUBS, Rank.JACK).getValue(), "JACK의 값은 11");
        assertEquals(12, new Card(Suit.SPADES, Rank.QUEEN).getValue(), "QUEEN의 값은 12");
        assertEquals(13, new Card(Suit.HEARTS, Rank.KING).getValue(), "KING의 값은 13");
        assertEquals(14, new Card(Suit.DIAMONDS, Rank.ACE).getValue(), "ACE의 값은 14");
    }
    
    @Test
    @DisplayName("equals()와 hashCode() 테스트")
    public void testEqualsAndHashCode() {
        // given
        Card card1 = new Card(Suit.HEARTS, Rank.QUEEN);
        Card card2 = new Card(Suit.HEARTS, Rank.QUEEN);
        Card card3 = new Card(Suit.HEARTS, Rank.KING);
        Card card4 = new Card(Suit.SPADES, Rank.QUEEN);
        
        // equals() 테스트
        assertEquals(card1, card2, "같은 무늬와 숫자의 카드는 동일해야 합니다");
        assertNotEquals(card1, card3, "다른 Rank의 카드는 동일하지 않아야 합니다");
        assertNotEquals(card1, card4, "다른 Suit의 카드는 동일하지 않아야 합니다");
        assertNotEquals(card1, null, "null과는 동일하지 않아야 합니다");
        assertNotEquals(card1, "string", "다른 타입과는 동일하지 않아야 합니다");
        
        // hashCode() 테스트
        assertEquals(card1.hashCode(), card2.hashCode(), 
            "같은 카드의 hashCode는 동일해야 합니다");
    }
}