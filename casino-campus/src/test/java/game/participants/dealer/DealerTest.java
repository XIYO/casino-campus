package game.participants.dealer;

import game.components.card.Card;
import game.components.card.Rank;
import game.components.card.Suit;
import game.participants.player.IPlayer;
import game.participants.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Dealer 클래스 테스트
 * 
 * 학생들이 Dealer 클래스를 올바르게 구현했는지 확인합니다.
 */
public class DealerTest {
    
    private Dealer dealer;
    private List<Player> players;
    
    @BeforeEach
    public void setUp() {
        dealer = new Dealer();
        players = new ArrayList<>();
        
        // 4명의 테스트 플레이어 생성
        players.add(new Player("플레이어1", 10000));
        players.add(new Player("플레이어2", 10000));
        players.add(new Player("플레이어3", 10000));
        players.add(new Player("플레이어4", 10000));
    }
    
    @Test
    @DisplayName("새 게임 시작 테스트")
    public void testStartNewGame() {
        // when
        assertDoesNotThrow(() -> {
            dealer.startNewGame();
        }, "새 게임 시작이 정상적으로 수행되어야 합니다");
    }
    
    @Test
    @DisplayName("카드 분배 테스트")
    public void testDealCards() {
        // given
        dealer.startNewGame();
        
        // when
        dealer.dealCards(players);
        
        // then
        for (Player player : players) {
            assertEquals(5, player.getHand().getCards().size(), 
                "각 플레이어는 5장의 카드를 받아야 합니다");
        }
    }
    
    @Test
    @DisplayName("카드 중복 분배 방지 테스트")
    public void testNoDuplicateCardsDealt() {
        // given
        dealer.startNewGame();
        dealer.dealCards(players);
        
        // when
        List<String> allCards = new ArrayList<>();
        for (Player player : players) {
            for (var card : player.getHand().getCards()) {
                String cardString = card.toString();
                
                // then
                assertFalse(allCards.contains(cardString), 
                    "같은 카드가 여러 플레이어에게 분배되면 안됩니다: " + cardString);
                allCards.add(cardString);
            }
        }
    }
    
    @Test
    @DisplayName("승자 결정 테스트 - 단독 승자")
    public void testDetermineWinnersSingle() {
        // given
        dealer.startNewGame();
        
        // 플레이어들에게 다른 족보의 핸드를 수동으로 설정
        // 플레이어1: 원페어
        players.get(0).getHand().addCard(new Card(Suit.SPADES, Rank.ACE));
        players.get(0).getHand().addCard(new Card(Suit.HEARTS, Rank.ACE));
        players.get(0).getHand().addCard(new Card(Suit.DIAMONDS, Rank.TWO));
        players.get(0).getHand().addCard(new Card(Suit.CLUBS, Rank.THREE));
        players.get(0).getHand().addCard(new Card(Suit.SPADES, Rank.FOUR));
        
        // 플레이어2: 하이카드
        players.get(1).getHand().addCard(new Card(Suit.SPADES, Rank.KING));
        players.get(1).getHand().addCard(new Card(Suit.HEARTS, Rank.QUEEN));
        players.get(1).getHand().addCard(new Card(Suit.DIAMONDS, Rank.JACK));
        players.get(1).getHand().addCard(new Card(Suit.CLUBS, Rank.NINE));
        players.get(1).getHand().addCard(new Card(Suit.SPADES, Rank.SEVEN));
        
        // 플레이어3: 쓰리카드 (승자)
        players.get(2).getHand().addCard(new Card(Suit.SPADES, Rank.FIVE));
        players.get(2).getHand().addCard(new Card(Suit.HEARTS, Rank.FIVE));
        players.get(2).getHand().addCard(new Card(Suit.DIAMONDS, Rank.FIVE));
        players.get(2).getHand().addCard(new Card(Suit.CLUBS, Rank.EIGHT));
        players.get(2).getHand().addCard(new Card(Suit.SPADES, Rank.TEN));
        
        // 플레이어4: 투페어
        players.get(3).getHand().addCard(new Card(Suit.SPADES, Rank.SIX));
        players.get(3).getHand().addCard(new Card(Suit.HEARTS, Rank.SIX));
        players.get(3).getHand().addCard(new Card(Suit.DIAMONDS, Rank.NINE));
        players.get(3).getHand().addCard(new Card(Suit.CLUBS, Rank.NINE));
        players.get(3).getHand().addCard(new Card(Suit.SPADES, Rank.QUEEN));
        
        // when
        List<? extends IPlayer> winners = dealer.determineWinners(players);
        
        // then
        assertEquals(1, winners.size(), "승자는 한 명이어야 합니다");
        assertEquals(players.get(2), winners.get(0), "쓰리카드를 가진 플레이어3이 승자여야 합니다");
    }
    
    @Test
    @DisplayName("승자 결정 테스트 - 동점자 처리")
    public void testDetermineWinnersMultiple() {
        // given
        dealer.startNewGame();
        
        // 두 플레이어에게 같은 족보 설정 (원페어)
        players.get(0).getHand().addCard(new Card(Suit.SPADES, Rank.KING));
        players.get(0).getHand().addCard(new Card(Suit.HEARTS, Rank.KING));
        players.get(0).getHand().addCard(new Card(Suit.DIAMONDS, Rank.TWO));
        players.get(0).getHand().addCard(new Card(Suit.CLUBS, Rank.THREE));
        players.get(0).getHand().addCard(new Card(Suit.SPADES, Rank.FOUR));
        
        players.get(1).getHand().addCard(new Card(Suit.DIAMONDS, Rank.KING));
        players.get(1).getHand().addCard(new Card(Suit.CLUBS, Rank.KING));
        players.get(1).getHand().addCard(new Card(Suit.HEARTS, Rank.FIVE));
        players.get(1).getHand().addCard(new Card(Suit.SPADES, Rank.SIX));
        players.get(1).getHand().addCard(new Card(Suit.DIAMONDS, Rank.SEVEN));
        
        // 나머지는 하이카드
        players.get(2).getHand().addCard(new Card(Suit.SPADES, Rank.ACE));
        players.get(2).getHand().addCard(new Card(Suit.HEARTS, Rank.JACK));
        players.get(2).getHand().addCard(new Card(Suit.DIAMONDS, Rank.NINE));
        players.get(2).getHand().addCard(new Card(Suit.CLUBS, Rank.SEVEN));
        players.get(2).getHand().addCard(new Card(Suit.SPADES, Rank.FIVE));
        
        players.get(3).getHand().addCard(new Card(Suit.HEARTS, Rank.QUEEN));
        players.get(3).getHand().addCard(new Card(Suit.DIAMONDS, Rank.TEN));
        players.get(3).getHand().addCard(new Card(Suit.CLUBS, Rank.EIGHT));
        players.get(3).getHand().addCard(new Card(Suit.SPADES, Rank.THREE));
        players.get(3).getHand().addCard(new Card(Suit.HEARTS, Rank.TWO));
        
        // when
        List<? extends IPlayer> winners = dealer.determineWinners(players);
        
        // then
        assertEquals(2, winners.size(), "동점자가 2명이어야 합니다");
        assertTrue(winners.contains(players.get(0)), "플레이어1이 승자 목록에 있어야 합니다");
        assertTrue(winners.contains(players.get(1)), "플레이어2가 승자 목록에 있어야 합니다");
    }
    
    @Test
    @DisplayName("상금 분배 테스트 - 단독 승자")
    public void testDistributePrizeSingle() {
        // given
        List<Player> winners = new ArrayList<>();
        winners.add(players.get(0));
        int initialMoney = players.get(0).getMoney();
        int prizeAmount = 100;
        
        // when
        dealer.distributePrize(winners, prizeAmount);
        
        // then
        assertEquals(initialMoney + prizeAmount, players.get(0).getMoney(), 
            "승자는 상금을 받아야 합니다");
    }
    
    @Test
    @DisplayName("상금 분배 테스트 - 다수 승자")
    public void testDistributePrizeMultiple() {
        // given
        List<Player> winners = new ArrayList<>();
        winners.add(players.get(0));
        winners.add(players.get(1));
        int initialMoney0 = players.get(0).getMoney();
        int initialMoney1 = players.get(1).getMoney();
        int prizeAmount = 100;
        
        // when
        dealer.distributePrize(winners, prizeAmount);
        
        // then
        assertEquals(initialMoney0 + prizeAmount, players.get(0).getMoney(), 
            "첫 번째 승자는 상금을 받아야 합니다");
        assertEquals(initialMoney1 + prizeAmount, players.get(1).getMoney(), 
            "두 번째 승자도 상금을 받아야 합니다");
    }
    
    @Test
    @DisplayName("전체 게임 진행 테스트")
    public void testPlayGame() {
        // given
        int rounds = 10;
        
        // when
        dealer.playGame(players, rounds);
        
        // then
        int totalGamesPerPlayer = 0;
        for (Player player : players) {
            totalGamesPerPlayer = player.getWinCount() + 
                                 player.getLoseCount() + 
                                 player.getDrawCount();
            assertEquals(rounds, totalGamesPerPlayer, 
                "각 플레이어는 정확히 " + rounds + "번의 게임을 해야 합니다");
        }
        
        // 모든 플레이어의 승리 횟수 합이 라운드 수와 일치하는지 확인
        // (무승부가 있을 수 있으므로 >= 사용)
        int totalWins = players.stream()
            .mapToInt(Player::getWinCount)
            .sum();
        assertTrue(totalWins >= rounds, 
            "전체 승리 횟수는 라운드 수 이상이어야 합니다");
    }
}