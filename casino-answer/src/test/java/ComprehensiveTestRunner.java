import game.components.card.*;
import game.components.deck.Deck;
import game.components.hand.*;
import game.participants.player.Player;
import game.participants.dealer.Dealer;
import game.management.casino.Casino;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

public class ComprehensiveTestRunner {
    private static int totalTests = 0;
    private static int passedTests = 0;
    private static int failedTests = 0;
    private static List<String> failedTestDetails = new ArrayList<>();
    
    public static void main(String[] args) {
        System.out.println("=== 포괄적 테스트 실행 ===\n");
        
        // Card 테스트
        runCardTests();
        
        // Deck 테스트
        runDeckTests();
        
        // Hand 테스트
        runHandTests();
        
        // Player 테스트
        runPlayerTests();
        
        // Dealer 테스트
        runDealerTests();
        
        // Casino 테스트
        runCasinoTests();
        
        // 최종 결과
        printFinalResults();
    }
    
    private static void runCardTests() {
        System.out.println("=== Card 테스트 ===");
        
        // 1. 카드 생성 테스트
        test("Card 생성", () -> {
            Card card = new Card(Suit.HEARTS, Rank.ACE);
            assertEqual(Suit.HEARTS, card.getSuit());
            assertEqual(Rank.ACE, card.getRank());
        });
        
        // 2. null 방어 테스트
        test("Card null 방어", () -> {
            assertThrows(() -> new Card(null, Rank.ACE), IllegalArgumentException.class);
            assertThrows(() -> new Card(Suit.HEARTS, null), IllegalArgumentException.class);
        });
        
        // 3. getValue 테스트
        test("Card getValue", () -> {
            assertEqual(2, new Card(Suit.HEARTS, Rank.TWO).getValue());
            assertEqual(14, new Card(Suit.HEARTS, Rank.ACE).getValue());
            assertEqual(13, new Card(Suit.HEARTS, Rank.KING).getValue());
        });
        
        // 4. compareTo 테스트
        test("Card compareTo", () -> {
            Card ace = new Card(Suit.HEARTS, Rank.ACE);
            Card king = new Card(Suit.SPADES, Rank.KING);
            assertTrue(ace.compareTo(king) > 0);
            assertTrue(king.compareTo(ace) < 0);
            assertEqual(0, ace.compareTo(ace));
        });
        
        // 5. toString 테스트
        test("Card toString", () -> {
            Card card = new Card(Suit.HEARTS, Rank.ACE);
            String str = card.toString();
            assertTrue(str.contains("A") && str.contains("♥"));
        });
        
        // 6. equals/hashCode 테스트
        test("Card equals/hashCode", () -> {
            Card card1 = new Card(Suit.HEARTS, Rank.ACE);
            Card card2 = new Card(Suit.HEARTS, Rank.ACE);
            Card card3 = new Card(Suit.SPADES, Rank.ACE);
            
            assertTrue(card1.equals(card2));
            assertFalse(card1.equals(card3));
            assertEqual(card1.hashCode(), card2.hashCode());
        });
        
        System.out.println();
    }
    
    private static void runDeckTests() {
        System.out.println("=== Deck 테스트 ===");
        
        // 1. 덱 생성 - 52장 확인
        test("Deck 52장 생성", () -> {
            Deck deck = new Deck();
            int count = 0;
            while (!deck.isEmpty()) {
                deck.drawCard();
                count++;
            }
            assertEqual(52, count);
        });
        
        // 2. 중복 카드 없음 확인
        test("Deck 중복 카드 없음", () -> {
            Deck deck = new Deck();
            Set<Card> cards = new HashSet<>();
            while (!deck.isEmpty()) {
                Card card = deck.drawCard();
                assertFalse(cards.contains(card));
                cards.add(card);
            }
        });
        
        // 3. 셔플 테스트
        test("Deck 셔플", () -> {
            Deck deck1 = new Deck();
            Deck deck2 = new Deck();
            deck2.shuffle();
            
            boolean different = false;
            for (int i = 0; i < 10; i++) {
                if (!deck1.drawCard().equals(deck2.drawCard())) {
                    different = true;
                    break;
                }
            }
            assertTrue(different);
        });
        
        // 4. 빈 덱에서 카드 뽑기
        test("빈 Deck에서 drawCard", () -> {
            Deck deck = new Deck();
            while (!deck.isEmpty()) {
                deck.drawCard();
            }
            assertThrows(() -> deck.drawCard(), IllegalStateException.class);
        });
        
        System.out.println();
    }
    
    private static void runHandTests() {
        System.out.println("=== Hand 테스트 ===");
        
        // 1. 카드 추가 테스트
        test("Hand 카드 추가", () -> {
            Hand hand = new Hand();
            Card card = new Card(Suit.HEARTS, Rank.ACE);
            hand.add(card);
            assertEqual(1, hand.getCards().size());
            assertTrue(hand.getCards().contains(card));
        });
        
        // 2. null 카드 추가 방어
        test("Hand null 카드 방어", () -> {
            Hand hand = new Hand();
            assertThrows(() -> hand.add(null), IllegalArgumentException.class);
        });
        
        // 3. 가득 찬 핸드
        test("Hand 가득 참", () -> {
            Hand hand = new Hand();
            for (int i = 0; i < 5; i++) {
                hand.add(new Card(Suit.HEARTS, Rank.values()[i]));
            }
            assertTrue(hand.isFull());
            assertThrows(() -> hand.add(new Card(Suit.SPADES, Rank.ACE)), IllegalStateException.class);
        });
        
        // 4. 로열 플러시 판정
        test("Hand 로열 플러시", () -> {
            Hand hand = createRoyalFlush();
            assertEqual(HandRank.ROYAL_FLUSH, hand.evaluate());
            assertEqual(1000, hand.open());
        });
        
        // 5. 스트레이트 플러시 판정
        test("Hand 스트레이트 플러시", () -> {
            Hand hand = createStraightFlush();
            assertEqual(HandRank.STRAIGHT_FLUSH, hand.evaluate());
            assertEqual(900, hand.open());
        });
        
        // 6. 포카드 판정
        test("Hand 포카드", () -> {
            Hand hand = createFourOfAKind();
            assertEqual(HandRank.FOUR_OF_A_KIND, hand.evaluate());
            assertEqual(800, hand.open());
        });
        
        // 7. 풀하우스 판정
        test("Hand 풀하우스", () -> {
            Hand hand = createFullHouse();
            assertEqual(HandRank.FULL_HOUSE, hand.evaluate());
            assertEqual(700, hand.open());
        });
        
        // 8. 플러시 판정
        test("Hand 플러시", () -> {
            Hand hand = createFlush();
            assertEqual(HandRank.FLUSH, hand.evaluate());
            assertEqual(600, hand.open());
        });
        
        // 9. 스트레이트 판정
        test("Hand 스트레이트", () -> {
            Hand hand = createStraight();
            assertEqual(HandRank.STRAIGHT, hand.evaluate());
            assertEqual(500, hand.open());
        });
        
        // 10. 백스트레이트 판정
        test("Hand 백스트레이트", () -> {
            Hand hand = createBackStraight();
            assertEqual(HandRank.STRAIGHT, hand.evaluate());
            assertEqual(500, hand.open());
        });
        
        // 11. 쓰리카드 판정
        test("Hand 쓰리카드", () -> {
            Hand hand = createThreeOfAKind();
            assertEqual(HandRank.THREE_OF_A_KIND, hand.evaluate());
            assertEqual(400, hand.open());
        });
        
        // 12. 투페어 판정
        test("Hand 투페어", () -> {
            Hand hand = createTwoPair();
            assertEqual(HandRank.TWO_PAIR, hand.evaluate());
            assertEqual(300, hand.open());
        });
        
        // 13. 원페어 판정
        test("Hand 원페어", () -> {
            Hand hand = createOnePair();
            assertEqual(HandRank.ONE_PAIR, hand.evaluate());
            assertEqual(200, hand.open());
        });
        
        // 14. 하이카드 판정
        test("Hand 하이카드", () -> {
            Hand hand = createHighCard();
            assertEqual(HandRank.HIGH_CARD, hand.evaluate());
            assertEqual(100, hand.open());
        });
        
        // 15. 핸드 비교
        test("Hand compareTo", () -> {
            Hand royalFlush = createRoyalFlush();
            Hand pair = createOnePair();
            assertTrue(royalFlush.compareTo(pair) > 0);
            assertTrue(pair.compareTo(royalFlush) < 0);
            assertEqual(0, royalFlush.compareTo(royalFlush));
        });
        
        System.out.println();
    }
    
    private static void runPlayerTests() {
        System.out.println("=== Player 테스트 ===");
        
        // 1. 플레이어 생성
        test("Player 생성", () -> {
            Player player = new Player("테스트", 10000);
            assertEqual("테스트", player.getName());
            assertEqual(10000, player.getMoney());
            assertNotNull(player.getHand());
        });
        
        // 2. null 이름 방어
        test("Player null 이름", () -> {
            assertThrows(() -> new Player(null, 10000), IllegalArgumentException.class);
            assertThrows(() -> new Player("", 10000), IllegalArgumentException.class);
            assertThrows(() -> new Player("   ", 10000), IllegalArgumentException.class);
        });
        
        // 3. 음수 자금 방어
        test("Player 음수 자금", () -> {
            assertThrows(() -> new Player("테스트", -1000), IllegalArgumentException.class);
        });
        
        // 4. 돈 추가
        test("Player 돈 추가", () -> {
            Player player = new Player("테스트", 10000);
            player.addMoney(500);
            assertEqual(10500, player.getMoney());
        });
        
        // 5. 음수 돈 추가 방어
        test("Player 음수 돈 추가", () -> {
            Player player = new Player("테스트", 10000);
            assertThrows(() -> player.addMoney(-100), IllegalArgumentException.class);
        });
        
        // 6. 돈 차감 성공
        test("Player 돈 차감", () -> {
            Player player = new Player("테스트", 10000);
            assertTrue(player.removeMoney(3000));
            assertEqual(7000, player.getMoney());
        });
        
        // 7. 잔액 부족
        test("Player 잔액 부족", () -> {
            Player player = new Player("테스트", 10000);
            assertFalse(player.removeMoney(15000));
            assertEqual(10000, player.getMoney());
        });
        
        // 8. 전적 기록
        test("Player 전적 기록", () -> {
            Player player = new Player("테스트", 10000);
            player.recordWin();
            player.recordWin();
            player.recordLose();
            player.recordDraw();
            
            assertEqual(2, player.getWinCount());
            assertEqual(1, player.getLoseCount());
            assertEqual(1, player.getDrawCount());
        });
        
        System.out.println();
    }
    
    private static void runDealerTests() {
        System.out.println("=== Dealer 테스트 ===");
        
        // 1. 딜러 생성
        test("Dealer 생성", () -> {
            Dealer dealer = new Dealer();
            assertNotNull(dealer);
        });
        
        // 2. 카드 분배
        test("Dealer 카드 분배", () -> {
            Dealer dealer = new Dealer();
            List<Player> players = createPlayers(4);
            
            dealer.startNewGame();
            dealer.dealCards(players);
            
            for (Player player : players) {
                assertEqual(5, player.getHand().getCards().size());
            }
        });
        
        // 3. 중복 카드 방지
        test("Dealer 중복 카드 방지", () -> {
            Dealer dealer = new Dealer();
            List<Player> players = createPlayers(4);
            
            dealer.startNewGame();
            dealer.dealCards(players);
            
            Set<Card> allCards = new HashSet<>();
            for (Player player : players) {
                for (Card card : player.getHand().getCards()) {
                    assertFalse(allCards.contains(card));
                    allCards.add(card);
                }
            }
        });
        
        // 4. 승자 판정
        test("Dealer 승자 판정", () -> {
            Dealer dealer = new Dealer();
            List<Player> players = createPlayers(2);
            
            players.get(0).setHand(createRoyalFlush());
            players.get(1).setHand(createOnePair());
            
            List<? extends Player> winners = dealer.determineWinners(players);
            assertEqual(1, winners.size());
            assertEqual(players.get(0), winners.get(0));
        });
        
        // 5. 상금 분배
        test("Dealer 상금 분배", () -> {
            Dealer dealer = new Dealer();
            List<Player> players = createPlayers(2);
            List<Player> winners = Arrays.asList(players.get(0));
            
            dealer.distributePrize(winners, 100);
            assertEqual(10100, players.get(0).getMoney());
            assertEqual(10000, players.get(1).getMoney());
        });
        
        // 6. 게임 진행
        test("Dealer 게임 진행", () -> {
            Dealer dealer = new Dealer();
            List<Player> players = createPlayers(4);
            
            dealer.playGame(players, 10);
            
            for (Player player : players) {
                int totalGames = player.getWinCount() + player.getLoseCount() + player.getDrawCount();
                assertEqual(10, totalGames);
            }
        });
        
        System.out.println();
    }
    
    private static void runCasinoTests() {
        System.out.println("=== Casino 테스트 ===");
        
        // 1. 메인 메서드 실행
        test("Casino 메인 실행", () -> {
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outContent));
            
            try {
                Casino.main(new String[]{});
                String output = outContent.toString();
                
                assertTrue(output.contains("라스베가스 드림 카지노"));
                assertTrue(output.contains("게임을 시작합니다"));
                assertTrue(output.contains("베타 테스트 결과"));
                assertTrue(output.contains("🥇"));
                assertTrue(output.contains("럭키가이"));
            } finally {
                System.setOut(originalOut);
            }
        });
        
        // 2. 게임 전적 합계 검증
        test("Casino 전적 합계", () -> {
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outContent));
            
            try {
                Casino.main(new String[]{});
                String output = outContent.toString();
                
                // 전적 추출
                int totalWins = 0;
                int totalLoses = 0;
                int totalDraws = 0;
                String[] lines = output.split("\n");
                
                for (String line : lines) {
                    if (line.contains("승") && line.contains("패") && line.contains("무")) {
                        // 정규식으로 숫자 추출
                        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("(\\d+)승\\s+(\\d+)패\\s+(\\d+)무");
                        java.util.regex.Matcher matcher = pattern.matcher(line);
                        if (matcher.find()) {
                            totalWins += Integer.parseInt(matcher.group(1));
                            totalLoses += Integer.parseInt(matcher.group(2));
                            totalDraws += Integer.parseInt(matcher.group(3));
                        }
                    }
                }
                
                // 4명 * 100라운드 = 400게임
                assertEqual(400, totalWins + totalLoses + totalDraws);
            } finally {
                System.setOut(originalOut);
            }
        });
        
        // 3. 전체 자금 검증
        test("Casino 자금 총합", () -> {
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outContent));
            
            try {
                Casino.main(new String[]{});
                String output = outContent.toString();
                
                // 자금 추출
                int totalMoney = 0;
                String[] lines = output.split("\n");
                for (String line : lines) {
                    if (line.matches(".*\\d+,\\d+원.*승.*패.*무.*")) {
                        String moneyStr = line.replaceAll(".*?(\\d+,\\d+)원.*", "$1");
                        moneyStr = moneyStr.replace(",", "");
                        totalMoney += Integer.parseInt(moneyStr);
                    }
                }
                
                // 초기 자금 40,000원 + 상금은 가변적 (무승부 때문에)
                // 최소 40,000원, 최대 80,000원 (모든 라운드가 무승부인 경우)
                assertTrue(totalMoney >= 40000);
                assertTrue(totalMoney <= 80000);
            } finally {
                System.setOut(originalOut);
            }
        });
        
        System.out.println();
    }
    
    // 헬퍼 메서드들
    private static void test(String name, Runnable testCode) {
        totalTests++;
        try {
            testCode.run();
            System.out.println("✓ " + name);
            passedTests++;
        } catch (Exception e) {
            System.out.println("✗ " + name + " - " + e.getMessage());
            failedTests++;
            failedTestDetails.add(name + ": " + e.getMessage());
        }
    }
    
    private static void assertEqual(Object expected, Object actual) {
        if (!Objects.equals(expected, actual)) {
            throw new AssertionError("예상: " + expected + ", 실제: " + actual);
        }
    }
    
    private static void assertTrue(boolean condition) {
        if (!condition) {
            throw new AssertionError("조건이 false입니다");
        }
    }
    
    private static void assertFalse(boolean condition) {
        if (condition) {
            throw new AssertionError("조건이 true입니다");
        }
    }
    
    private static void assertNotNull(Object obj) {
        if (obj == null) {
            throw new AssertionError("객체가 null입니다");
        }
    }
    
    private static void assertThrows(Runnable code, Class<? extends Exception> expectedType) {
        try {
            code.run();
            throw new AssertionError(expectedType.getSimpleName() + " 예외가 발생하지 않았습니다");
        } catch (Exception e) {
            if (!expectedType.isInstance(e)) {
                throw new AssertionError("예상한 예외 타입이 아닙니다: " + e.getClass().getSimpleName());
            }
        }
    }
    
    private static List<Player> createPlayers(int count) {
        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            players.add(new Player("플레이어" + i, 10000));
        }
        return players;
    }
    
    // 핸드 생성 메서드들
    private static Hand createRoyalFlush() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.SPADES, Rank.ACE));
        hand.add(new Card(Suit.SPADES, Rank.KING));
        hand.add(new Card(Suit.SPADES, Rank.QUEEN));
        hand.add(new Card(Suit.SPADES, Rank.JACK));
        hand.add(new Card(Suit.SPADES, Rank.TEN));
        return hand;
    }
    
    private static Hand createStraightFlush() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.HEARTS, Rank.NINE));
        hand.add(new Card(Suit.HEARTS, Rank.EIGHT));
        hand.add(new Card(Suit.HEARTS, Rank.SEVEN));
        hand.add(new Card(Suit.HEARTS, Rank.SIX));
        hand.add(new Card(Suit.HEARTS, Rank.FIVE));
        return hand;
    }
    
    private static Hand createFourOfAKind() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.HEARTS, Rank.KING));
        hand.add(new Card(Suit.SPADES, Rank.KING));
        hand.add(new Card(Suit.DIAMONDS, Rank.KING));
        hand.add(new Card(Suit.CLUBS, Rank.KING));
        hand.add(new Card(Suit.HEARTS, Rank.THREE));
        return hand;
    }
    
    private static Hand createFullHouse() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.HEARTS, Rank.JACK));
        hand.add(new Card(Suit.SPADES, Rank.JACK));
        hand.add(new Card(Suit.DIAMONDS, Rank.JACK));
        hand.add(new Card(Suit.CLUBS, Rank.SEVEN));
        hand.add(new Card(Suit.HEARTS, Rank.SEVEN));
        return hand;
    }
    
    private static Hand createFlush() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.DIAMONDS, Rank.ACE));
        hand.add(new Card(Suit.DIAMONDS, Rank.QUEEN));
        hand.add(new Card(Suit.DIAMONDS, Rank.TEN));
        hand.add(new Card(Suit.DIAMONDS, Rank.FIVE));
        hand.add(new Card(Suit.DIAMONDS, Rank.THREE));
        return hand;
    }
    
    private static Hand createStraight() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.HEARTS, Rank.TEN));
        hand.add(new Card(Suit.SPADES, Rank.NINE));
        hand.add(new Card(Suit.DIAMONDS, Rank.EIGHT));
        hand.add(new Card(Suit.CLUBS, Rank.SEVEN));
        hand.add(new Card(Suit.HEARTS, Rank.SIX));
        return hand;
    }
    
    private static Hand createBackStraight() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.HEARTS, Rank.ACE));
        hand.add(new Card(Suit.SPADES, Rank.TWO));
        hand.add(new Card(Suit.DIAMONDS, Rank.THREE));
        hand.add(new Card(Suit.CLUBS, Rank.FOUR));
        hand.add(new Card(Suit.HEARTS, Rank.FIVE));
        return hand;
    }
    
    private static Hand createThreeOfAKind() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.HEARTS, Rank.JACK));
        hand.add(new Card(Suit.SPADES, Rank.JACK));
        hand.add(new Card(Suit.DIAMONDS, Rank.JACK));
        hand.add(new Card(Suit.CLUBS, Rank.NINE));
        hand.add(new Card(Suit.HEARTS, Rank.SEVEN));
        return hand;
    }
    
    private static Hand createTwoPair() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.HEARTS, Rank.KING));
        hand.add(new Card(Suit.SPADES, Rank.KING));
        hand.add(new Card(Suit.DIAMONDS, Rank.QUEEN));
        hand.add(new Card(Suit.CLUBS, Rank.QUEEN));
        hand.add(new Card(Suit.HEARTS, Rank.JACK));
        return hand;
    }
    
    private static Hand createOnePair() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.HEARTS, Rank.ACE));
        hand.add(new Card(Suit.SPADES, Rank.ACE));
        hand.add(new Card(Suit.DIAMONDS, Rank.KING));
        hand.add(new Card(Suit.CLUBS, Rank.QUEEN));
        hand.add(new Card(Suit.HEARTS, Rank.JACK));
        return hand;
    }
    
    private static Hand createHighCard() {
        Hand hand = new Hand();
        hand.add(new Card(Suit.HEARTS, Rank.ACE));
        hand.add(new Card(Suit.SPADES, Rank.KING));
        hand.add(new Card(Suit.DIAMONDS, Rank.QUEEN));
        hand.add(new Card(Suit.CLUBS, Rank.JACK));
        hand.add(new Card(Suit.HEARTS, Rank.NINE));
        return hand;
    }
    
    private static void printFinalResults() {
        System.out.println("\n=== 최종 테스트 결과 ===");
        System.out.println("총 테스트: " + totalTests);
        System.out.println("성공: " + passedTests);
        System.out.println("실패: " + failedTests);
        
        if (failedTests > 0) {
            System.out.println("\n실패한 테스트:");
            for (String detail : failedTestDetails) {
                System.out.println("  - " + detail);
            }
        } else {
            System.out.println("\n🎉 모든 테스트가 통과했습니다!");
        }
    }
}