package dealer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.Player;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DealerTest {
    @Test
    @DisplayName("에러, 플레이어가 4명을 초과하면 예외가 발생한다.")
    void test2() {
        Dealer dealer = Dealer.newDealer();
        List<Player> players = getRandomNamePlayers();
        for (Player player : players) dealer.enrollPlayer(player);

        try {
            dealer.enrollPlayer(Player.newPlayer("김태희"));
        } catch (IllegalStateException e) {
            assertEquals("플레이어는 4명까지만 가능합니다.", e.getMessage());
        } finally {
            dealer.retrieveCard();
        }
    }

    @Test
    @DisplayName("에러, 하나의 덱에서 카드를 두번 나눠주면 예외가 발생한다.")
    void test3() {
        Dealer dealer = Dealer.newDealer();
        List<Player> players = getRandomNamePlayers();
        for (Player player : players) dealer.enrollPlayer(player);

        dealer.newGame();
        dealer.shuffle();
        dealer.dealCard();

        try {
            dealer.dealCard();
        } catch (IllegalStateException e) {
            assertEquals("덱이 준비되지 않았습니다. newGame() 메서드를 호출하세요.", e.getMessage());
        } finally {
            dealer.retrieveCard();
        }
    }

    @Test
    @DisplayName("모든 플레이어의 카드 확인")
    void openCard() {
        Dealer dealer = Dealer.newDealer();
        List<Player> players = getRandomNamePlayers();
        for (Player player : players) dealer.enrollPlayer(player);

        assertDoesNotThrow(() -> {
            dealer.newGame();
            dealer.shuffle();
            dealer.dealCard();
            dealer.handOpen();
            dealer.retrieveCard();
        });
    }

    @Test
    @DisplayName("연속 게임 진행")
    void testConsecutiveGame() {
        Dealer dealer = Dealer.newDealer();
        List<Player> players = getRandomNamePlayers();
        for (Player player : players) dealer.enrollPlayer(player);

        assertDoesNotThrow(() -> {
            for (int i = 0; i < 100; i++) {
                dealer.newGame();
                dealer.shuffle();
                dealer.dealCard();
                dealer.handOpen();
                dealer.retrieveCard();
            }
        });
    }

    @Test
    @DisplayName("플레이어의 패 상세 정보 확인")
    void showPlayerHand() {
        Dealer dealer = Dealer.newDealer();
        List<Player> players = getRandomNamePlayers();
        for (Player player : players) dealer.enrollPlayer(player);

        dealer.newGame();
        dealer.shuffle();
        dealer.dealCard();
        dealer.handOpen();

        assertDoesNotThrow(() -> {
            for (Player player : dealer.getPlayers()) {
                System.out.println(player.getHand().toString());
            }
        });

        dealer.retrieveCard();
    }

    List<Player> getRandomNamePlayers() {
        return List.of(
                Player.newPlayer("고니" + UUID.randomUUID().toString().substring(0, 10)),
                Player.newPlayer("평경장" + UUID.randomUUID().toString().substring(0, 10)),
                Player.newPlayer("짝귀" + UUID.randomUUID().toString().substring(0, 10)),
                Player.newPlayer("아귀" + UUID.randomUUID().toString().substring(0, 10))
        );
    }
}
