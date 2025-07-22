package game.components.deck;

import game.components.card.ICard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Deck 클래스 테스트
 * 
 * 학생들이 Deck 클래스를 올바르게 구현했는지 확인합니다.
 */
public class DeckTest {
    
    private Deck deck;
    
    @BeforeEach
    public void setUp() {
        deck = new Deck();
    }
    
    @Test
    @DisplayName("덱 초기화 테스트 - 52장의 카드")
    public void testDeckInitialization() {
        // when
        int remainingCards = deck.getRemainingCards();
        
        // then
        assertEquals(52, remainingCards, "새로운 덱은 52장의 카드를 가져야 합니다");
    }
    
    @Test
    @DisplayName("카드 뽑기 테스트")
    public void testDrawCard() {
        // when
        ICard card = deck.drawCard();
        int remainingCards = deck.getRemainingCards();
        
        // then
        assertNotNull(card, "뽑은 카드는 null이 아니어야 합니다");
        assertEquals(51, remainingCards, "카드를 뽑은 후 남은 카드는 51장이어야 합니다");
    }
    
    @Test
    @DisplayName("여러 장 카드 뽑기 테스트")
    public void testDrawMultipleCards() {
        // given
        int cardsToDraw = 5;
        
        // when
        List<ICard> cards = deck.drawCards(cardsToDraw);
        
        // then
        assertEquals(cardsToDraw, cards.size(), "요청한 수만큼 카드를 받아야 합니다");
        assertEquals(47, deck.getRemainingCards(), "5장을 뽑은 후 47장이 남아야 합니다");
        
        // 중복 카드가 없는지 확인
        Set<ICard> uniqueCards = new HashSet<>(cards);
        assertEquals(cardsToDraw, uniqueCards.size(), "뽑은 카드에 중복이 없어야 합니다");
    }
    
    @Test
    @DisplayName("덱 초과 뽑기 시 예외 테스트")
    public void testDrawMoreCardsThanAvailable() {
        // given
        deck.drawCards(50);  // 50장 뽑기
        
        // when & then
        assertThrows(Exception.class, () -> {
            deck.drawCards(5);  // 남은 2장보다 많이 뽑으려 시도
        }, "남은 카드보다 많이 뽑으려 하면 예외가 발생해야 합니다");
    }
    
    @Test
    @DisplayName("빈 덱에서 카드 뽑기 시 예외 테스트")
    public void testDrawFromEmptyDeck() {
        // given
        deck.drawCards(52);  // 모든 카드 뽑기
        
        // when & then
        assertThrows(Exception.class, () -> {
            deck.drawCard();
        }, "빈 덱에서 카드를 뽑으려 하면 예외가 발생해야 합니다");
    }
    
    @RepeatedTest(3)
    @DisplayName("셔플 테스트 - 카드 순서 변경 확인")
    public void testShuffle() {
        // given
        List<ICard> beforeShuffle = deck.drawCards(10);
        deck.reset();
        
        // when
        deck.shuffle();
        List<ICard> afterShuffle = deck.drawCards(10);
        
        // then
        assertNotEquals(beforeShuffle, afterShuffle, 
            "셔플 후 카드 순서가 변경되어야 합니다 (매우 낮은 확률로 같을 수 있음)");
    }
    
    @Test
    @DisplayName("덱 리셋 테스트")
    public void testReset() {
        // given
        deck.drawCards(30);  // 30장 뽑기
        assertEquals(22, deck.getRemainingCards());
        
        // when
        deck.reset();
        
        // then
        assertEquals(52, deck.getRemainingCards(), "리셋 후 다시 52장이 되어야 합니다");
    }
    
    @Test
    @DisplayName("전체 덱의 카드 중복 검사")
    public void testNoDuplicateCards() {
        // given
        Set<String> cardSet = new HashSet<>();
        
        // when
        while (deck.getRemainingCards() > 0) {
            ICard card = deck.drawCard();
            String cardString = card.toString();
            
            // then
            assertFalse(cardSet.contains(cardString), 
                "덱에 중복된 카드가 있으면 안됩니다: " + cardString);
            cardSet.add(cardString);
        }
        
        assertEquals(52, cardSet.size(), "덱은 정확히 52장의 서로 다른 카드를 가져야 합니다");
    }
}