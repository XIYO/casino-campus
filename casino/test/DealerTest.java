import dealer.Dealer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.Player;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DealerTest {

    static Dealer dealer;

    static {
        DealerTest.dealer = Dealer.newDealer();
        DealerTest.dealer.enrollPlayer(Player.newPlayer("고니"));
        DealerTest.dealer.enrollPlayer(Player.newPlayer("평경장"));
        DealerTest.dealer.enrollPlayer(Player.newPlayer("짝귀"));
        DealerTest.dealer.enrollPlayer(Player.newPlayer("아귀"));
    }

    @Test
    @DisplayName("에러, 플레이어가 4명을 초과하면 예외가 발생한다.")
    void test2() {
        try {
            DealerTest.dealer.enrollPlayer(Player.newPlayer("김태희"));
        } catch (IllegalStateException e) {
            assertEquals("플레이어는 4명까지만 가능합니다.", e.getMessage());
        } finally {
            DealerTest.dealer.retrieveCard();
        }
    }

    @Test
    @DisplayName("에러, 하나의 덱에서 카드를 두번 나눠주면 예외가 발생한다.")
    void test3() {
        DealerTest.dealer.newGame();
        DealerTest.dealer.dealCard();

        try {
            DealerTest.dealer.dealCard();
        } catch (IllegalStateException e) {
            assertEquals("덱이 준비되지 않았습니다. newGame() 메서드를 호출하세요.", e.getMessage());
        } finally {
            DealerTest.dealer.retrieveCard();
        }
    }

    @Test
    @DisplayName("모든 플레이어의 카드 확인")
    void openCard() {
        assertDoesNotThrow(() -> {
            DealerTest.dealer.newGame();
            DealerTest.dealer.dealCard();
            DealerTest.dealer.cardOpen();
            DealerTest.dealer.retrieveCard();
        });
    }

    @Test
    @DisplayName("연속 게임 진행")
    void testConsecutiveGame() {
        for (int i = 0; i < 100; i++) {
            assertDoesNotThrow(() -> {
                DealerTest.dealer.newGame();
                DealerTest.dealer.dealCard();
                DealerTest.dealer.cardOpen();
                DealerTest.dealer.retrieveCard();
            });
        }
    }

    @Test
    @DisplayName("플레이어의 패 상세 정보 확인")
    void showPlayerHand() {
        DealerTest.dealer.newGame();
        DealerTest.dealer.dealCard();
        DealerTest.dealer.cardOpen();

        assertDoesNotThrow(() -> {
            for(Player player : DealerTest.dealer.getPlayers()) {
                System.out.println(player.openHand().toDetailString());
            }
        });

        DealerTest.dealer.retrieveCard();
    }
}
