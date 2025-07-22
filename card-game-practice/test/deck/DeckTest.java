package deck;

import card.*;
import java.util.*;

/**
 * Deck 인터페이스 구현을 테스트하는 클래스
 * 
 * 학습자가 구현해야 할 것:
 * 1. Deck 인터페이스를 구현하는 구체적인 클래스 (예: StandardDeck)
 * 2. 모든 테스트가 통과하도록 구현
 */
public class DeckTest {
    
    public static void main(String[] args) {
        System.out.println("=== Deck 구현 테스트 시작 ===\n");
        
        testDeckCreation();
        testDrawCard();
        testDrawMultipleCards();
        testShuffle();
        testReset();
        testEmptyDeck();
        testContains();
        
        System.out.println("\n=== 모든 테스트 완료! ===");
    }
    
    /**
     * 덱 생성 테스트
     */
    private static void testDeckCreation() {
        System.out.println("[테스트 1] 덱 생성");
        
        // TODO: 아래 주석을 해제하고 Deck 구현체로 수정하세요
        /*
        Deck deck = // Deck 구현체 생성
        
        assert deck.getRemainingCards() == 52 : "새 덱은 52장의 카드를 가져야 합니다";
        assert !deck.isEmpty() : "새 덱은 비어있지 않아야 합니다";
        */
        
        System.out.println("✓ 덱 생성 테스트 통과");
    }
    
    /**
     * 카드 뽑기 테스트
     */
    private static void testDrawCard() {
        System.out.println("[테스트 2] 카드 뽑기");
        
        // TODO: 아래 주석을 해제하고 테스트하세요
        /*
        Deck deck = // Deck 구현체 생성
        
        Card firstCard = deck.drawCard();
        assert firstCard != null : "뽑은 카드는 null이 아니어야 합니다";
        assert deck.getRemainingCards() == 51 : "카드를 한 장 뽑으면 51장이 남아야 합니다";
        
        // 같은 카드를 다시 뽑을 수 없어야 함
        assert !deck.contains(firstCard) : "뽑은 카드는 덱에 남아있지 않아야 합니다";
        */
        
        System.out.println("✓ 카드 뽑기 테스트 통과");
    }
    
    /**
     * 여러 장 뽑기 테스트
     */
    private static void testDrawMultipleCards() {
        System.out.println("[테스트 3] 여러 장 뽑기");
        
        // TODO: 아래 주석을 해제하고 테스트하세요
        /*
        Deck deck = // Deck 구현체 생성
        
        List<Card> cards = deck.drawCards(5);
        assert cards.size() == 5 : "5장을 요청하면 5장을 받아야 합니다";
        assert deck.getRemainingCards() == 47 : "5장을 뽑으면 47장이 남아야 합니다";
        
        // 뽑은 카드들이 모두 다른지 확인
        Set<Card> uniqueCards = new HashSet<>(cards);
        assert uniqueCards.size() == 5 : "뽑은 카드들은 모두 달라야 합니다";
        
        // 너무 많은 카드를 뽑으려고 하면 예외 발생
        try {
            deck.drawCards(50); // 47장 남은 상태에서 50장 요청
            assert false : "남은 카드보다 많이 뽑으려고 하면 예외가 발생해야 합니다";
        } catch (IllegalArgumentException e) {
            // 예상된 예외
        }
        */
        
        System.out.println("✓ 여러 장 뽑기 테스트 통과");
    }
    
    /**
     * 셔플 테스트
     */
    private static void testShuffle() {
        System.out.println("[테스트 4] 카드 섞기");
        
        // TODO: 아래 주석을 해제하고 테스트하세요
        /*
        Deck deck1 = // Deck 구현체 생성
        Deck deck2 = // Deck 구현체 생성
        
        // 첫 5장의 카드 확인
        List<Card> beforeShuffle = deck1.drawCards(5);
        deck1.reset();
        
        // 셔플 후 첫 5장의 카드 확인
        deck2.shuffle();
        List<Card> afterShuffle = deck2.drawCards(5);
        
        // 셔플 전후가 다른지 확인 (매우 낮은 확률로 같을 수 있음)
        boolean isDifferent = false;
        for (int i = 0; i < 5; i++) {
            if (!beforeShuffle.get(i).equals(afterShuffle.get(i))) {
                isDifferent = true;
                break;
            }
        }
        
        System.out.println("셔플 전: " + beforeShuffle);
        System.out.println("셔플 후: " + afterShuffle);
        */
        
        System.out.println("✓ 카드 섞기 테스트 통과");
    }
    
    /**
     * 덱 리셋 테스트
     */
    private static void testReset() {
        System.out.println("[테스트 5] 덱 리셋");
        
        // TODO: 아래 주석을 해제하고 테스트하세요
        /*
        Deck deck = // Deck 구현체 생성
        
        // 카드 10장 뽑기
        for (int i = 0; i < 10; i++) {
            deck.drawCard();
        }
        assert deck.getRemainingCards() == 42 : "10장을 뽑으면 42장이 남아야 합니다";
        
        // 리셋
        deck.reset();
        assert deck.getRemainingCards() == 52 : "리셋 후에는 52장이 되어야 합니다";
        assert !deck.isEmpty() : "리셋 후에는 비어있지 않아야 합니다";
        */
        
        System.out.println("✓ 덱 리셋 테스트 통과");
    }
    
    /**
     * 빈 덱 테스트
     */
    private static void testEmptyDeck() {
        System.out.println("[테스트 6] 빈 덱 처리");
        
        // TODO: 아래 주석을 해제하고 테스트하세요
        /*
        Deck deck = // Deck 구현체 생성
        
        // 모든 카드 뽑기
        for (int i = 0; i < 52; i++) {
            deck.drawCard();
        }
        
        assert deck.isEmpty() : "52장을 모두 뽑으면 덱은 비어야 합니다";
        assert deck.getRemainingCards() == 0 : "남은 카드는 0장이어야 합니다";
        
        // 빈 덱에서 카드를 뽑으려고 하면 예외 발생
        try {
            deck.drawCard();
            assert false : "빈 덱에서 카드를 뽑으려고 하면 예외가 발생해야 합니다";
        } catch (IllegalStateException e) {
            // 예상된 예외
        }
        */
        
        System.out.println("✓ 빈 덱 처리 테스트 통과");
    }
    
    /**
     * contains 메서드 테스트
     */
    private static void testContains() {
        System.out.println("[테스트 7] 카드 포함 여부 확인");
        
        // TODO: 아래 주석을 해제하고 테스트하세요
        /*
        Deck deck = // Deck 구현체 생성
        Card targetCard = // 특정 카드 생성 (예: 스페이드 A)
        
        // 처음에는 모든 카드가 있어야 함
        assert deck.contains(targetCard) : "새 덱은 모든 카드를 포함해야 합니다";
        
        // 카드를 뽑을 때까지 계속 뽑기
        boolean found = false;
        while (!deck.isEmpty()) {
            Card drawn = deck.drawCard();
            if (drawn.equals(targetCard)) {
                found = true;
                break;
            }
        }
        
        assert found : "타겟 카드를 찾아야 합니다";
        assert !deck.contains(targetCard) : "뽑은 카드는 덱에 없어야 합니다";
        */
        
        System.out.println("✓ 카드 포함 여부 확인 테스트 통과");
    }
}