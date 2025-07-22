package hand;

import card.*;
import java.util.*;

/**
 * Hand 인터페이스 구현을 테스트하는 클래스
 * 
 * 학습자가 구현해야 할 것:
 * 1. Hand 인터페이스를 구현하는 구체적인 클래스 (예: PlayerHand)
 * 2. 모든 테스트가 통과하도록 구현
 */
public class HandTest {
    
    public static void main(String[] args) {
        System.out.println("=== Hand 구현 테스트 시작 ===\n");
        
        testHandCreation();
        testAddCard();
        testAddMultipleCards();
        testRemoveCard();
        testRemoveCardAt();
        testContains();
        testClear();
        testSort();
        testToString();
        
        System.out.println("\n=== 모든 테스트 완료! ===");
    }
    
    /**
     * 손패 생성 테스트
     */
    private static void testHandCreation() {
        System.out.println("[테스트 1] 손패 생성");
        
        // TODO: 아래 주석을 해제하고 Hand 구현체로 수정하세요
        /*
        Hand hand = // Hand 구현체 생성
        
        assert hand.isEmpty() : "새로운 손패는 비어있어야 합니다";
        assert hand.getCardCount() == 0 : "새로운 손패의 카드 수는 0이어야 합니다";
        assert hand.getCards().isEmpty() : "새로운 손패의 카드 리스트는 비어있어야 합니다";
        */
        
        System.out.println("✓ 손패 생성 테스트 통과");
    }
    
    /**
     * 카드 추가 테스트
     */
    private static void testAddCard() {
        System.out.println("[테스트 2] 카드 추가");
        
        // TODO: 아래 주석을 해제하고 테스트하세요
        /*
        Hand hand = // Hand 구현체 생성
        Card card1 = // 스페이드 A 생성
        Card card2 = // 하트 K 생성
        
        hand.addCard(card1);
        assert hand.getCardCount() == 1 : "카드 한 장 추가 후 카드 수는 1이어야 합니다";
        assert hand.contains(card1) : "추가한 카드가 손패에 있어야 합니다";
        
        hand.addCard(card2);
        assert hand.getCardCount() == 2 : "카드 두 장 추가 후 카드 수는 2여야 합니다";
        assert hand.contains(card2) : "두 번째 추가한 카드도 손패에 있어야 합니다";
        
        // null 추가 시도
        try {
            hand.addCard(null);
            assert false : "null 카드 추가 시 예외가 발생해야 합니다";
        } catch (IllegalArgumentException e) {
            // 예상된 예외
        }
        */
        
        System.out.println("✓ 카드 추가 테스트 통과");
    }
    
    /**
     * 여러 카드 추가 테스트
     */
    private static void testAddMultipleCards() {
        System.out.println("[테스트 3] 여러 카드 추가");
        
        // TODO: 아래 주석을 해제하고 테스트하세요
        /*
        Hand hand = // Hand 구현체 생성
        List<Card> cards = Arrays.asList(
            // 다이아몬드 10 생성,
            // 클럽 J 생성,
            // 하트 Q 생성
        );
        
        hand.addCards(cards);
        assert hand.getCardCount() == 3 : "3장 추가 후 카드 수는 3이어야 합니다";
        
        for (Card card : cards) {
            assert hand.contains(card) : "추가한 모든 카드가 손패에 있어야 합니다";
        }
        */
        
        System.out.println("✓ 여러 카드 추가 테스트 통과");
    }
    
    /**
     * 카드 제거 테스트
     */
    private static void testRemoveCard() {
        System.out.println("[테스트 4] 카드 제거");
        
        // TODO: 아래 주석을 해제하고 테스트하세요
        /*
        Hand hand = // Hand 구현체 생성
        Card card1 = // 스페이드 5 생성
        Card card2 = // 하트 5 생성
        Card card3 = // 다이아몬드 5 생성
        
        hand.addCard(card1);
        hand.addCard(card2);
        hand.addCard(card3);
        
        boolean removed = hand.removeCard(card2);
        assert removed : "존재하는 카드 제거는 true를 반환해야 합니다";
        assert hand.getCardCount() == 2 : "카드 제거 후 수가 줄어야 합니다";
        assert !hand.contains(card2) : "제거된 카드는 손패에 없어야 합니다";
        
        removed = hand.removeCard(card2);
        assert !removed : "이미 제거된 카드 제거는 false를 반환해야 합니다";
        */
        
        System.out.println("✓ 카드 제거 테스트 통과");
    }
    
    /**
     * 인덱스로 카드 제거 테스트
     */
    private static void testRemoveCardAt() {
        System.out.println("[테스트 5] 인덱스로 카드 제거");
        
        // TODO: 아래 주석을 해제하고 테스트하세요
        /*
        Hand hand = // Hand 구현체 생성
        Card card1 = // 클럽 2 생성
        Card card2 = // 클럽 3 생성
        Card card3 = // 클럽 4 생성
        
        hand.addCard(card1);
        hand.addCard(card2);
        hand.addCard(card3);
        
        Card removed = hand.removeCardAt(1); // 중간 카드 제거
        assert removed.equals(card2) : "올바른 카드가 제거되어야 합니다";
        assert hand.getCardCount() == 2 : "카드 제거 후 수가 줄어야 합니다";
        
        // 잘못된 인덱스
        try {
            hand.removeCardAt(5);
            assert false : "잘못된 인덱스로 제거 시 예외가 발생해야 합니다";
        } catch (IndexOutOfBoundsException e) {
            // 예상된 예외
        }
        */
        
        System.out.println("✓ 인덱스로 카드 제거 테스트 통과");
    }
    
    /**
     * contains 테스트
     */
    private static void testContains() {
        System.out.println("[테스트 6] 카드 포함 여부 확인");
        
        // TODO: 아래 주석을 해제하고 테스트하세요
        /*
        Hand hand = // Hand 구현체 생성
        Card card1 = // 스페이드 K 생성
        Card card2 = // 스페이드 Q 생성
        
        hand.addCard(card1);
        
        assert hand.contains(card1) : "추가한 카드는 포함되어야 합니다";
        assert !hand.contains(card2) : "추가하지 않은 카드는 포함되지 않아야 합니다";
        */
        
        System.out.println("✓ 카드 포함 여부 확인 테스트 통과");
    }
    
    /**
     * clear 테스트
     */
    private static void testClear() {
        System.out.println("[테스트 7] 손패 비우기");
        
        // TODO: 아래 주석을 해제하고 테스트하세요
        /*
        Hand hand = // Hand 구현체 생성
        
        // 카드 5장 추가
        for (int i = 2; i <= 6; i++) {
            hand.addCard(// 하트 i 카드 생성);
        }
        
        assert hand.getCardCount() == 5 : "5장 추가 후 카드 수는 5여야 합니다";
        
        hand.clear();
        assert hand.isEmpty() : "clear 후 손패는 비어있어야 합니다";
        assert hand.getCardCount() == 0 : "clear 후 카드 수는 0이어야 합니다";
        */
        
        System.out.println("✓ 손패 비우기 테스트 통과");
    }
    
    /**
     * 정렬 테스트
     */
    private static void testSort() {
        System.out.println("[테스트 8] 손패 정렬");
        
        // TODO: 아래 주석을 해제하고 테스트하세요
        /*
        Hand hand = // Hand 구현체 생성
        
        // 무작위 순서로 카드 추가
        hand.addCard(// 클럽 A 생성);
        hand.addCard(// 스페이드 2 생성);
        hand.addCard(// 하트 A 생성);
        hand.addCard(// 다이아몬드 K 생성);
        hand.addCard(// 스페이드 A 생성);
        
        hand.sort();
        List<Card> sorted = hand.getCards();
        
        System.out.println("정렬된 손패: " + sorted);
        
        // 정렬 확인: 스페이드가 먼저 나와야 함
        assert sorted.get(0).getSuit() == Suit.SPADE : "스페이드가 먼저 나와야 합니다";
        assert sorted.get(1).getSuit() == Suit.SPADE : "스페이드가 먼저 나와야 합니다";
        
        // 같은 무늬 내에서 숫자 순서 확인
        assert sorted.get(0).getRank() == Rank.TWO : "스페이드 2가 스페이드 A보다 먼저 나와야 합니다";
        assert sorted.get(1).getRank() == Rank.ACE : "스페이드 A가 두 번째여야 합니다";
        */
        
        System.out.println("✓ 손패 정렬 테스트 통과");
    }
    
    /**
     * toString 테스트
     */
    private static void testToString() {
        System.out.println("[테스트 9] 문자열 표현");
        
        // TODO: 아래 주석을 해제하고 테스트하세요
        /*
        Hand hand = // Hand 구현체 생성
        
        hand.addCard(// 스페이드 A 생성);
        hand.addCard(// 하트 10 생성);
        hand.addCard(// 다이아몬드 K 생성);
        
        String handString = hand.toString();
        System.out.println("손패: " + handString);
        
        // 문자열에 카드 정보가 포함되어야 함
        assert handString.contains("♠") : "스페이드 기호가 포함되어야 합니다";
        assert handString.contains("♥") : "하트 기호가 포함되어야 합니다";
        assert handString.contains("♦") : "다이아몬드 기호가 포함되어야 합니다";
        assert handString.contains("A") : "A가 포함되어야 합니다";
        assert handString.contains("10") : "10이 포함되어야 합니다";
        assert handString.contains("K") : "K가 포함되어야 합니다";
        */
        
        System.out.println("✓ 문자열 표현 테스트 통과");
    }
}