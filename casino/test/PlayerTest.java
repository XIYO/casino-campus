import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {

    @Test
    @DisplayName("똑같은 이름의 플레이어가 참가할 수 없다.")
    void test() {
        Player.newPlayer("고니"); // 고니 참가

        try {
            Player.newPlayer("고니"); // 또, 고니 참가
        } catch (IllegalArgumentException e) {
            assertEquals("이미 사용 중인 닉네임입니다.", e.getMessage());
        }
    }

    @Test
    @DisplayName("닉네임은 20자를 넘길 수 없다.")
    void test2() {
        try {
            Player.newPlayer("123456789012345678901");
        } catch (IllegalArgumentException e) {
            assertEquals("닉네임은 20자 이하여야 합니다.", e.getMessage());
        }
    }
}
