package player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlayerTest {

    @Test
    @DisplayName("중복 닉네임으로 플레이어를 생성할 때 예외가 발생한다.")
    void shouldThrowExceptionWhenCreatingPlayerWithDuplicateName() {
        Player.newPlayer("고니"); // 첫 번째 고니 참가

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                Player.newPlayer("고니") // 중복된 고니 참가 시도
        );
        assertEquals("이미 사용 중인 닉네임입니다.", exception.getMessage());
    }

    @Test
    @DisplayName("닉네임이 20자를 초과할 때 예외가 발생한다.")
    void shouldThrowExceptionWhenNicknameExceeds20Characters() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                Player.newPlayer("123456789012345678901") // 21자 닉네임
        );
        assertEquals("닉네임은 20자 이하여야 합니다.", exception.getMessage());
    }
}
