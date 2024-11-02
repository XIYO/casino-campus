package dealer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.Player;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DealerTest {

    @Test
    @DisplayName("플레이어 등록 제한: 플레이어가 4명을 초과할 때 예외가 발생한다.")
    void shouldThrowExceptionWhenMoreThanFourPlayers() {
        Dealer dealer = Dealer.newDealer();
        List<Player> players = getRandomNamePlayers();
        for (Player player : players) dealer.enrollPlayer(player);

        // 예외 발생 여부만 검증
        assertThrows(IllegalStateException.class,
                () -> dealer.enrollPlayer(Player.newPlayer("김태희"))
        );

        dealer.retrieveCard();
    }

    @Test
    @DisplayName("덱 사용 제한: 하나의 덱에서 카드를 두 번 나눠주면 예외가 발생한다.")
    void shouldThrowExceptionWhenDealingCardTwiceWithoutReset() {
        Dealer dealer = Dealer.newDealer();
        List<Player> players = getRandomNamePlayers();
        for (Player player : players) dealer.enrollPlayer(player);

        dealer.newGame();
        dealer.shuffle();
        dealer.dealCard();

        // 예외 발생 여부만 검증
        assertThrows(IllegalStateException.class, dealer::dealCard);

        dealer.retrieveCard();
    }

    @Test
    @DisplayName("모든 플레이어의 카드 확인 기능 정상 작동")
    void shouldOpenAllPlayersCardsWithoutException() {
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
    @DisplayName("연속 게임 진행: 여러 게임이 연속으로 진행되어도 예외가 발생하지 않는다.")
    void shouldAllowConsecutiveGamesWithoutException() {
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
    @DisplayName("플레이어의 패 상세 정보 출력 기능 확인")
    void shouldShowPlayerHandDetailsWithoutException() {
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
