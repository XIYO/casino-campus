package player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PlayerRecordTest {

    @Test
    @DisplayName("플레이어의 기록 생성")
    void createRecord() {
        PlayerRecord playerRecord = new PlayerRecord();
        assertNotNull(playerRecord);
    }

    @Test
    @DisplayName("플레이어의 기록에 승리 추가")
    void addWin() {
        PlayerRecord playerRecord = new PlayerRecord();
        playerRecord.incrementWins();
        assertEquals(1, playerRecord.getWins());
    }

    @Test
    @DisplayName("플레이어의 기록에 패배 추가")
    void addLose() {
        PlayerRecord playerRecord = new PlayerRecord();
        playerRecord.incrementLosses();
        assertEquals(1, playerRecord.getLosses());
    }

    @Test
    @DisplayName("플레이어의 기록에 무승부 추가")
    void addDraw() {
        PlayerRecord playerRecord = new PlayerRecord();
        playerRecord.incrementDraws();
        assertEquals(1, playerRecord.getDraws());
    }
}
