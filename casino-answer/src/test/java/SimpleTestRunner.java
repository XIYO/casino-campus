import game.participants.player.Player;
import game.participants.dealer.Dealer;
import game.components.card.*;
import game.components.deck.Deck;
import game.components.hand.Hand;

import java.util.ArrayList;
import java.util.List;

public class SimpleTestRunner {
    private static int testCount = 0;
    private static int passedCount = 0;
    private static int failedCount = 0;
    
    public static void main(String[] args) {
        System.out.println("=== 간단한 테스트 실행기 ===\n");
        
        // Player 테스트
        testPlayerCreation();
        testPlayerAddMoney();
        testPlayerRemoveMoney();
        
        // Dealer 테스트
        testDealerCardDistribution();
        testDealerWinnerDetermination();
        
        // Card 테스트
        testCardValue();
        testCardComparison();
        
        // Deck 테스트
        testDeckCreation();
        testDeckShuffle();
        
        // Hand 테스트
        testHandEvaluation();
        
        // 결과 출력
        System.out.println("\n=== 테스트 결과 ===");
        System.out.println("총 테스트: " + testCount);
        System.out.println("성공: " + passedCount);
        System.out.println("실패: " + failedCount);
    }
    
    private static void testPlayerCreation() {
        testCount++;
        try {
            Player player = new Player("테스트", 10000);
            assertEqual("테스트", player.getName(), "Player 이름");
            assertEqual(10000, player.getMoney(), "Player 초기 자금");
            System.out.println("✓ Player 생성 테스트 통과");
            passedCount++;
        } catch (Exception e) {
            System.out.println("✗ Player 생성 테스트 실패: " + e.getMessage());
            failedCount++;
        }
    }
    
    private static void testPlayerAddMoney() {
        testCount++;
        try {
            Player player = new Player("테스트", 10000);
            player.addMoney(500);
            assertEqual(10500, player.getMoney(), "Player 돈 추가");
            System.out.println("✓ Player 돈 추가 테스트 통과");
            passedCount++;
        } catch (Exception e) {
            System.out.println("✗ Player 돈 추가 테스트 실패: " + e.getMessage());
            failedCount++;
        }
    }
    
    private static void testPlayerRemoveMoney() {
        testCount++;
        try {
            Player player = new Player("테스트", 10000);
            boolean result = player.removeMoney(3000);
            assertTrue(result, "Player 돈 차감 성공");
            assertEqual(7000, player.getMoney(), "Player 돈 차감 후 잔액");
            System.out.println("✓ Player 돈 차감 테스트 통과");
            passedCount++;
        } catch (Exception e) {
            System.out.println("✗ Player 돈 차감 테스트 실패: " + e.getMessage());
            failedCount++;
        }
    }
    
    private static void testDealerCardDistribution() {
        testCount++;
        try {
            Dealer dealer = new Dealer();
            List<Player> players = new ArrayList<>();
            players.add(new Player("플레이어1", 10000));
            players.add(new Player("플레이어2", 10000));
            
            dealer.startNewGame();
            dealer.dealCards(players);
            
            for (Player player : players) {
                assertEqual(5, player.getHand().getCards().size(), "플레이어 카드 수");
            }
            System.out.println("✓ Dealer 카드 분배 테스트 통과");
            passedCount++;
        } catch (Exception e) {
            System.out.println("✗ Dealer 카드 분배 테스트 실패: " + e.getMessage());
            failedCount++;
        }
    }
    
    private static void testDealerWinnerDetermination() {
        testCount++;
        try {
            Dealer dealer = new Dealer();
            List<Player> players = new ArrayList<>();
            Player player1 = new Player("플레이어1", 10000);
            Player player2 = new Player("플레이어2", 10000);
            
            // 수동으로 핸드 설정
            Hand royalFlush = new Hand();
            royalFlush.add(new Card(Suit.SPADES, Rank.ACE));
            royalFlush.add(new Card(Suit.SPADES, Rank.KING));
            royalFlush.add(new Card(Suit.SPADES, Rank.QUEEN));
            royalFlush.add(new Card(Suit.SPADES, Rank.JACK));
            royalFlush.add(new Card(Suit.SPADES, Rank.TEN));
            
            Hand pair = new Hand();
            pair.add(new Card(Suit.HEARTS, Rank.ACE));
            pair.add(new Card(Suit.SPADES, Rank.ACE));
            pair.add(new Card(Suit.DIAMONDS, Rank.KING));
            pair.add(new Card(Suit.CLUBS, Rank.QUEEN));
            pair.add(new Card(Suit.HEARTS, Rank.JACK));
            
            player1.setHand(royalFlush);
            player2.setHand(pair);
            players.add(player1);
            players.add(player2);
            
            List<? extends Player> winners = dealer.determineWinners(players);
            assertEqual(1, winners.size(), "승자 수");
            assertEqual("플레이어1", winners.get(0).getName(), "승자 이름");
            System.out.println("✓ Dealer 승자 판정 테스트 통과");
            passedCount++;
        } catch (Exception e) {
            System.out.println("✗ Dealer 승자 판정 테스트 실패: " + e.getMessage());
            failedCount++;
        }
    }
    
    private static void testCardValue() {
        testCount++;
        try {
            Card ace = new Card(Suit.HEARTS, Rank.ACE);
            Card king = new Card(Suit.SPADES, Rank.KING);
            Card two = new Card(Suit.DIAMONDS, Rank.TWO);
            
            assertEqual(14, ace.getValue(), "ACE 값");
            assertEqual(13, king.getValue(), "KING 값");
            assertEqual(2, two.getValue(), "TWO 값");
            System.out.println("✓ Card 값 테스트 통과");
            passedCount++;
        } catch (Exception e) {
            System.out.println("✗ Card 값 테스트 실패: " + e.getMessage());
            failedCount++;
        }
    }
    
    private static void testCardComparison() {
        testCount++;
        try {
            Card ace = new Card(Suit.HEARTS, Rank.ACE);
            Card king = new Card(Suit.SPADES, Rank.KING);
            
            assertTrue(ace.compareTo(king) > 0, "ACE는 KING보다 커야 함");
            assertTrue(king.compareTo(ace) < 0, "KING은 ACE보다 작아야 함");
            assertEqual(0, ace.compareTo(ace), "같은 카드는 0");
            System.out.println("✓ Card 비교 테스트 통과");
            passedCount++;
        } catch (Exception e) {
            System.out.println("✗ Card 비교 테스트 실패: " + e.getMessage());
            failedCount++;
        }
    }
    
    private static void testDeckCreation() {
        testCount++;
        try {
            Deck deck = new Deck();
            int cardCount = 0;
            while (!deck.isEmpty()) {
                deck.drawCard();
                cardCount++;
            }
            assertEqual(52, cardCount, "덱의 카드 수");
            System.out.println("✓ Deck 생성 테스트 통과");
            passedCount++;
        } catch (Exception e) {
            System.out.println("✗ Deck 생성 테스트 실패: " + e.getMessage());
            failedCount++;
        }
    }
    
    private static void testDeckShuffle() {
        testCount++;
        try {
            Deck deck1 = new Deck();
            Deck deck2 = new Deck();
            deck2.shuffle();
            
            // 최소한 하나의 카드 위치는 달라야 함
            boolean different = false;
            for (int i = 0; i < 5; i++) {
                Card card1 = deck1.drawCard();
                Card card2 = deck2.drawCard();
                if (!card1.equals(card2)) {
                    different = true;
                    break;
                }
            }
            assertTrue(different, "셔플 후 카드 순서가 달라야 함");
            System.out.println("✓ Deck 셔플 테스트 통과");
            passedCount++;
        } catch (Exception e) {
            System.out.println("✗ Deck 셔플 테스트 실패: " + e.getMessage());
            failedCount++;
        }
    }
    
    private static void testHandEvaluation() {
        testCount++;
        try {
            // 로열 플러시 테스트
            Hand royalFlush = new Hand();
            royalFlush.add(new Card(Suit.SPADES, Rank.ACE));
            royalFlush.add(new Card(Suit.SPADES, Rank.KING));
            royalFlush.add(new Card(Suit.SPADES, Rank.QUEEN));
            royalFlush.add(new Card(Suit.SPADES, Rank.JACK));
            royalFlush.add(new Card(Suit.SPADES, Rank.TEN));
            
            assertEqual(1000, royalFlush.open(), "로열 플러시 점수");
            
            // 원페어 테스트
            Hand pair = new Hand();
            pair.add(new Card(Suit.HEARTS, Rank.ACE));
            pair.add(new Card(Suit.SPADES, Rank.ACE));
            pair.add(new Card(Suit.DIAMONDS, Rank.KING));
            pair.add(new Card(Suit.CLUBS, Rank.QUEEN));
            pair.add(new Card(Suit.HEARTS, Rank.JACK));
            
            assertEqual(200, pair.open(), "원페어 점수");
            
            System.out.println("✓ Hand 평가 테스트 통과");
            passedCount++;
        } catch (Exception e) {
            System.out.println("✗ Hand 평가 테스트 실패: " + e.getMessage());
            failedCount++;
        }
    }
    
    // 헬퍼 메서드들
    private static void assertEqual(Object expected, Object actual, String message) {
        if (!expected.equals(actual)) {
            throw new AssertionError(message + " - 예상: " + expected + ", 실제: " + actual);
        }
    }
    
    private static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}