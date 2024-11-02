package player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PlayerRecordTest {

    @Test
    @DisplayName("플레이어 기록 객체가 정상적으로 생성된다.")
    void shouldCreatePlayerRecord() {
        PlayerRecord playerRecord = new PlayerRecord();
        assertNotNull(playerRecord, "PlayerRecord 객체가 null이 아니어야 합니다.");
    }

    @Test
    @DisplayName("플레이어의 승리 횟수를 올바르게 증가시킨다.")
    void shouldIncrementWinsInPlayerRecord() {
        PlayerRecord playerRecord = new PlayerRecord();
        playerRecord.incrementWins();
        assertEquals(1, playerRecord.getWins(), "플레이어의 승리 횟수는 1이어야 합니다.");
    }

    @Test
    @DisplayName("플레이어의 패배 횟수를 올바르게 증가시킨다.")
    void shouldIncrementLossesInPlayerRecord() {
        PlayerRecord playerRecord = new PlayerRecord();
        playerRecord.incrementLosses();
        assertEquals(1, playerRecord.getLosses(), "플레이어의 패배 횟수는 1이어야 합니다.");
    }

    @Test
    @DisplayName("플레이어의 무승부 횟수를 올바르게 증가시킨다.")
    void shouldIncrementDrawsInPlayerRecord() {
        PlayerRecord playerRecord = new PlayerRecord();
        playerRecord.incrementDraws();
        assertEquals(1, playerRecord.getDraws(), "플레이어의 무승부 횟수는 1이어야 합니다.");
    }
}
