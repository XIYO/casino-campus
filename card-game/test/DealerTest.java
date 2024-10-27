import dealer.Dealer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.Player;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DealerTest {

    @Test
    @DisplayName("에러, 한명만 참가하고 딜러가 카드를 나눠준다.")
    void test() {
        Dealer dealer = Dealer.newDealer();
        dealer.enrollPlayer(Player.newPlayer("고니"));

        try {
            dealer.dealCard();
        } catch (IllegalStateException e) {
            assertEquals("플레이어가 2명 이상이어야 합니다.", e.getMessage());
        }
    }

    @Test
    @DisplayName("에러, 플레이어가 4명을 초과하면 예외가 발생한다.")
    void test2() {
        Dealer dealer = Dealer.newDealer();
        dealer.enrollPlayer(Player.newPlayer("고니"));
        dealer.enrollPlayer(Player.newPlayer("평경장"));
        dealer.enrollPlayer(Player.newPlayer("짝귀"));
        dealer.enrollPlayer(Player.newPlayer("아귀"));

        try {
            dealer.enrollPlayer(Player.newPlayer("김태희"));
        } catch (IllegalStateException e) {
            assertEquals("플레이어는 4명까지만 가능합니다.", e.getMessage());
        }
    }

    @Test
    @DisplayName("에러, 하나의 덱에서 카드를 두번 나눠주면 예외가 발생한다.")
    void test3() {
        Dealer dealer = Dealer.newDealer();
        dealer.enrollPlayer(Player.newPlayer("고니"));
        dealer.enrollPlayer(Player.newPlayer("평경장"));
        dealer.enrollPlayer(Player.newPlayer("짝귀"));
        dealer.enrollPlayer(Player.newPlayer("아귀"));

        dealer.newGame();
        dealer.dealCard();

        try {
            dealer.dealCard();
        } catch (IllegalStateException e) {
            assertEquals("덱이 준비되지 않았습니다. newGame() 메서드를 호출하세요.", e.getMessage());
        }
    }

    @Test
    @DisplayName("모든 플레이어의 카드 확인")
    void openCard() {
        Dealer dealer = Dealer.newDealer();
        dealer.enrollPlayer(Player.newPlayer("고니"));
        dealer.enrollPlayer(Player.newPlayer("평경장"));
        dealer.enrollPlayer(Player.newPlayer("짝귀"));
        dealer.enrollPlayer(Player.newPlayer("아귀"));

        assertDoesNotThrow(() -> {
            dealer.newGame();
            dealer.dealCard();
            dealer.cardOpen();
        });
    }

    @Test
    @DisplayName("연속 게임 진행")
    void testConsecutiveGame() {
        Dealer dealer = Dealer.newDealer();
        dealer.enrollPlayer(Player.newPlayer("고니"));
        dealer.enrollPlayer(Player.newPlayer("평경장"));
        dealer.enrollPlayer(Player.newPlayer("짝귀"));
        dealer.enrollPlayer(Player.newPlayer("아귀"));

        for (int i = 0; i < 100; i++) {
            assertDoesNotThrow(() -> {
                dealer.newGame();
                dealer.dealCard();
                dealer.cardOpen();
                dealer.retrieveCard();
            });
        }
    }
}
