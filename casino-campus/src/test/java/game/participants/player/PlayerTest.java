package game.participants.player;

import game.components.hand.Hand;
import game.participants.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Player 클래스 테스트
 * 
 * 학생들이 Player 클래스를 올바르게 구현했는지 확인합니다.
 */
public class PlayerTest {
    
    private Player player;
    
    @BeforeEach
    public void setUp() {
        player = new Player("테스트플레이어", 10000);
    }
    
    @Test
    @DisplayName("플레이어 생성 테스트")
    public void testPlayerCreation() {
        // then
        assertEquals("테스트플레이어", player.getName(), "플레이어 이름이 올바르게 설정되어야 합니다");
        assertEquals(10000, player.getMoney(), "초기 자금이 올바르게 설정되어야 합니다");
        assertNotNull(player.getHand(), "핸드가 초기화되어야 합니다");
    }
    
    @Test
    @DisplayName("돈 추가 테스트")
    public void testAddMoney() {
        // given
        int initialMoney = player.getMoney();
        int addAmount = 500;
        
        // when
        player.addMoney(addAmount);
        
        // then
        assertEquals(initialMoney + addAmount, player.getMoney(), 
            "돈이 올바르게 추가되어야 합니다");
    }
    
    @Test
    @DisplayName("음수 금액 추가 방지 테스트")
    public void testAddNegativeMoney() {
        // given
        int initialMoney = player.getMoney();
        
        // when & then
        assertThrows(Exception.class, () -> {
            player.addMoney(-100);
        }, "음수 금액을 추가하려 하면 예외가 발생해야 합니다");
    }
    
    @Test
    @DisplayName("돈 차감 성공 테스트")
    public void testRemoveMoneySuccess() {
        // given
        int removeAmount = 1000;
        
        // when
        boolean result = player.removeMoney(removeAmount);
        
        // then
        assertTrue(result, "충분한 잔액이 있을 때 차감이 성공해야 합니다");
        assertEquals(9000, player.getMoney(), "돈이 올바르게 차감되어야 합니다");
    }
    
    @Test
    @DisplayName("돈 차감 실패 테스트 - 잔액 부족")
    public void testRemoveMoneyFailure() {
        // given
        int removeAmount = 15000;
        int initialMoney = player.getMoney();
        
        // when
        boolean result = player.removeMoney(removeAmount);
        
        // then
        assertFalse(result, "잔액이 부족할 때 차감이 실패해야 합니다");
        assertEquals(initialMoney, player.getMoney(), 
            "차감 실패 시 잔액이 변경되지 않아야 합니다");
    }
    
    @Test
    @DisplayName("핸드 설정 테스트")
    public void testSetHand() {
        // given
        Hand newHand = new Hand();
        
        // when
        player.setHand(newHand);
        
        // then
        assertEquals(newHand, player.getHand(), "핸드가 올바르게 설정되어야 합니다");
    }
    
    @Test
    @DisplayName("승리 기록 테스트")
    public void testRecordWin() {
        // given
        int initialWins = player.getWinCount();
        
        // when
        player.recordWin();
        player.recordWin();
        
        // then
        assertEquals(initialWins + 2, player.getWinCount(), 
            "승리 횟수가 올바르게 증가해야 합니다");
    }
    
    @Test
    @DisplayName("패배 기록 테스트")
    public void testRecordLose() {
        // given
        int initialLosses = player.getLoseCount();
        
        // when
        player.recordLose();
        player.recordLose();
        player.recordLose();
        
        // then
        assertEquals(initialLosses + 3, player.getLoseCount(), 
            "패배 횟수가 올바르게 증가해야 합니다");
    }
    
    @Test
    @DisplayName("무승부 기록 테스트")
    public void testRecordDraw() {
        // given
        int initialDraws = player.getDrawCount();
        
        // when
        player.recordDraw();
        
        // then
        assertEquals(initialDraws + 1, player.getDrawCount(), 
            "무승부 횟수가 올바르게 증가해야 합니다");
    }
    
    @Test
    @DisplayName("전체 게임 기록 테스트")
    public void testCompleteGameRecord() {
        // when
        player.recordWin();
        player.recordWin();
        player.recordLose();
        player.recordLose();
        player.recordLose();
        player.recordDraw();
        
        // then
        assertEquals(2, player.getWinCount(), "승리 횟수가 정확해야 합니다");
        assertEquals(3, player.getLoseCount(), "패배 횟수가 정확해야 합니다");
        assertEquals(1, player.getDrawCount(), "무승부 횟수가 정확해야 합니다");
        
        int totalGames = player.getWinCount() + player.getLoseCount() + player.getDrawCount();
        assertEquals(6, totalGames, "전체 게임 수가 정확해야 합니다");
    }
}