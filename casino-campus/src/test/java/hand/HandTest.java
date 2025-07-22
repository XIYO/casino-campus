package hand;

import game.components.card.Card;
import game.components.card.ICard;
import game.components.card.Suit;
import game.components.card.Rank;
import game.components.hand.Hand;
import game.components.hand.HandRank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * Hand 클래스 테스트
 * 
 * 학생들이 Hand 클래스와 포커 족보 판정을 올바르게 구현했는지 확인합니다.
 */
public class HandTest {
    
    private Hand hand;
    
    @BeforeEach
    public void setUp() {
        hand = new Hand();
    }
    
    @Test
    @DisplayName("핸드에 카드 추가 테스트")
    public void testAddCard() {
        // given
        ICard card = new Card(Suit.SPADES, Rank.ACE);
        
        // when
        hand.addCard(card);
        
        // then
        assertEquals(1, hand.getCards().size(), "카드가 추가되어야 합니다");
        assertEquals(card, hand.getCards().get(0), "추가한 카드가 핸드에 있어야 합니다");
    }
    
    @Test
    @DisplayName("핸드 카드 수 제한 테스트")
    public void testHandSizeLimit() {
        // given
        for (int i = 0; i < 5; i++) {
            hand.addCard(new Card(Suit.SPADES, Rank.values()[i]));
        }
        
        // when & then
        assertThrows(Exception.class, () -> {
            hand.addCard(new Card(Suit.HEARTS, Rank.ACE));
        }, "5장을 초과하여 카드를 추가하면 예외가 발생해야 합니다");
    }
    
    @Test
    @DisplayName("핸드 초기화 테스트")
    public void testClearHand() {
        // given
        hand.addCard(new Card(Suit.SPADES, Rank.ACE));
        hand.addCard(new Card(Suit.HEARTS, Rank.KING));
        
        // when
        hand.clear();
        
        // then
        assertEquals(0, hand.getCards().size(), "핸드가 비어있어야 합니다");
    }
    
    @Test
    @DisplayName("로열 플러시 판정 테스트")
    public void testRoyalFlush() {
        // given
        hand.addCard(new Card(Suit.SPADES, Rank.TEN));
        hand.addCard(new Card(Suit.SPADES, Rank.JACK));
        hand.addCard(new Card(Suit.SPADES, Rank.QUEEN));
        hand.addCard(new Card(Suit.SPADES, Rank.KING));
        hand.addCard(new Card(Suit.SPADES, Rank.ACE));
        
        // when
        HandRank rank = hand.evaluateHand();
        
        // then
        assertEquals(HandRank.ROYAL_FLUSH, rank, "로열 플러시로 판정되어야 합니다");
        assertEquals(1000, rank.getScore(), "로열 플러시는 1000점이어야 합니다");
    }
    
    @Test
    @DisplayName("스트레이트 플러시 판정 테스트")
    public void testStraightFlush() {
        // given
        hand.addCard(new Card(Suit.HEARTS, Rank.FIVE));
        hand.addCard(new Card(Suit.HEARTS, Rank.SIX));
        hand.addCard(new Card(Suit.HEARTS, Rank.SEVEN));
        hand.addCard(new Card(Suit.HEARTS, Rank.EIGHT));
        hand.addCard(new Card(Suit.HEARTS, Rank.NINE));
        
        // when
        HandRank rank = hand.evaluateHand();
        
        // then
        assertEquals(HandRank.STRAIGHT_FLUSH, rank, "스트레이트 플러시로 판정되어야 합니다");
        assertEquals(900, rank.getScore(), "스트레이트 플러시는 900점이어야 합니다");
    }
    
    @Test
    @DisplayName("포카드 판정 테스트")
    public void testFourOfAKind() {
        // given
        hand.addCard(new Card(Suit.SPADES, Rank.SEVEN));
        hand.addCard(new Card(Suit.HEARTS, Rank.SEVEN));
        hand.addCard(new Card(Suit.DIAMONDS, Rank.SEVEN));
        hand.addCard(new Card(Suit.CLUBS, Rank.SEVEN));
        hand.addCard(new Card(Suit.SPADES, Rank.ACE));
        
        // when
        HandRank rank = hand.evaluateHand();
        
        // then
        assertEquals(HandRank.FOUR_OF_A_KIND, rank, "포카드로 판정되어야 합니다");
        assertEquals(800, rank.getScore(), "포카드는 800점이어야 합니다");
    }
    
    @Test
    @DisplayName("풀하우스 판정 테스트")
    public void testFullHouse() {
        // given
        hand.addCard(new Card(Suit.SPADES, Rank.THREE));
        hand.addCard(new Card(Suit.HEARTS, Rank.THREE));
        hand.addCard(new Card(Suit.DIAMONDS, Rank.THREE));
        hand.addCard(new Card(Suit.CLUBS, Rank.KING));
        hand.addCard(new Card(Suit.SPADES, Rank.KING));
        
        // when
        HandRank rank = hand.evaluateHand();
        
        // then
        assertEquals(HandRank.FULL_HOUSE, rank, "풀하우스로 판정되어야 합니다");
        assertEquals(700, rank.getScore(), "풀하우스는 700점이어야 합니다");
    }
    
    @Test
    @DisplayName("플러시 판정 테스트")
    public void testFlush() {
        // given
        hand.addCard(new Card(Suit.DIAMONDS, Rank.TWO));
        hand.addCard(new Card(Suit.DIAMONDS, Rank.FIVE));
        hand.addCard(new Card(Suit.DIAMONDS, Rank.NINE));
        hand.addCard(new Card(Suit.DIAMONDS, Rank.JACK));
        hand.addCard(new Card(Suit.DIAMONDS, Rank.KING));
        
        // when
        HandRank rank = hand.evaluateHand();
        
        // then
        assertEquals(HandRank.FLUSH, rank, "플러시로 판정되어야 합니다");
        assertEquals(600, rank.getScore(), "플러시는 600점이어야 합니다");
    }
    
    @Test
    @DisplayName("스트레이트 판정 테스트")
    public void testStraight() {
        // given
        hand.addCard(new Card(Suit.SPADES, Rank.FOUR));
        hand.addCard(new Card(Suit.HEARTS, Rank.FIVE));
        hand.addCard(new Card(Suit.DIAMONDS, Rank.SIX));
        hand.addCard(new Card(Suit.CLUBS, Rank.SEVEN));
        hand.addCard(new Card(Suit.SPADES, Rank.EIGHT));
        
        // when
        HandRank rank = hand.evaluateHand();
        
        // then
        assertEquals(HandRank.STRAIGHT, rank, "스트레이트로 판정되어야 합니다");
        assertEquals(500, rank.getScore(), "스트레이트는 500점이어야 합니다");
    }
    
    @Test
    @DisplayName("쓰리카드 판정 테스트")
    public void testThreeOfAKind() {
        // given
        hand.addCard(new Card(Suit.SPADES, Rank.NINE));
        hand.addCard(new Card(Suit.HEARTS, Rank.NINE));
        hand.addCard(new Card(Suit.DIAMONDS, Rank.NINE));
        hand.addCard(new Card(Suit.CLUBS, Rank.TWO));
        hand.addCard(new Card(Suit.SPADES, Rank.FIVE));
        
        // when
        HandRank rank = hand.evaluateHand();
        
        // then
        assertEquals(HandRank.THREE_OF_A_KIND, rank, "쓰리카드로 판정되어야 합니다");
        assertEquals(400, rank.getScore(), "쓰리카드는 400점이어야 합니다");
    }
    
    @Test
    @DisplayName("투페어 판정 테스트")
    public void testTwoPair() {
        // given
        hand.addCard(new Card(Suit.SPADES, Rank.JACK));
        hand.addCard(new Card(Suit.HEARTS, Rank.JACK));
        hand.addCard(new Card(Suit.DIAMONDS, Rank.FOUR));
        hand.addCard(new Card(Suit.CLUBS, Rank.FOUR));
        hand.addCard(new Card(Suit.SPADES, Rank.ACE));
        
        // when
        HandRank rank = hand.evaluateHand();
        
        // then
        assertEquals(HandRank.TWO_PAIR, rank, "투페어로 판정되어야 합니다");
        assertEquals(300, rank.getScore(), "투페어는 300점이어야 합니다");
    }
    
    @Test
    @DisplayName("원페어 판정 테스트")
    public void testOnePair() {
        // given
        hand.addCard(new Card(Suit.SPADES, Rank.SIX));
        hand.addCard(new Card(Suit.HEARTS, Rank.SIX));
        hand.addCard(new Card(Suit.DIAMONDS, Rank.TWO));
        hand.addCard(new Card(Suit.CLUBS, Rank.NINE));
        hand.addCard(new Card(Suit.SPADES, Rank.KING));
        
        // when
        HandRank rank = hand.evaluateHand();
        
        // then
        assertEquals(HandRank.ONE_PAIR, rank, "원페어로 판정되어야 합니다");
        assertEquals(200, rank.getScore(), "원페어는 200점이어야 합니다");
    }
    
    @Test
    @DisplayName("하이카드 판정 테스트")
    public void testHighCard() {
        // given
        hand.addCard(new Card(Suit.SPADES, Rank.TWO));
        hand.addCard(new Card(Suit.HEARTS, Rank.FIVE));
        hand.addCard(new Card(Suit.DIAMONDS, Rank.SEVEN));
        hand.addCard(new Card(Suit.CLUBS, Rank.JACK));
        hand.addCard(new Card(Suit.SPADES, Rank.KING));
        
        // when
        HandRank rank = hand.evaluateHand();
        
        // then
        assertEquals(HandRank.HIGH_CARD, rank, "하이카드로 판정되어야 합니다");
        assertEquals(100, rank.getScore(), "하이카드는 100점이어야 합니다");
    }
    
    @Test
    @DisplayName("핸드 비교 테스트")
    public void testHandComparison() {
        // given
        Hand hand1 = new Hand();
        hand1.addCard(new Card(Suit.SPADES, Rank.ACE));
        hand1.addCard(new Card(Suit.HEARTS, Rank.ACE));
        hand1.addCard(new Card(Suit.DIAMONDS, Rank.TWO));
        hand1.addCard(new Card(Suit.CLUBS, Rank.THREE));
        hand1.addCard(new Card(Suit.SPADES, Rank.FOUR));
        
        Hand hand2 = new Hand();
        hand2.addCard(new Card(Suit.SPADES, Rank.KING));
        hand2.addCard(new Card(Suit.HEARTS, Rank.KING));
        hand2.addCard(new Card(Suit.DIAMONDS, Rank.KING));
        hand2.addCard(new Card(Suit.CLUBS, Rank.FIVE));
        hand2.addCard(new Card(Suit.SPADES, Rank.SIX));
        
        // when
        int comparison = hand2.compareTo(hand1);
        
        // then
        assertTrue(comparison > 0, "쓰리카드가 원페어보다 높아야 합니다");
    }
}