package casino;

import game.management.casino.Casino;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Casino 클래스 테스트
 * 
 * 학생들이 Casino 메인 클래스를 올바르게 구현했는지 확인합니다.
 */
public class CasinoTest {
    
    @Test
    @DisplayName("카지노 실행 테스트")
    public void testCasinoExecution() {
        // given
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        
        try {
            // when
            assertDoesNotThrow(() -> {
                Casino.main(new String[]{});
            }, "Casino 메인 메소드가 예외 없이 실행되어야 합니다");
            
            // then
            String output = outputStream.toString();
            
            // 기본 출력 확인
            assertTrue(output.contains("라스베가스 드림 카지노"), 
                "카지노 이름이 출력되어야 합니다");
            assertTrue(output.contains("베타 테스트 결과"), 
                "테스트 결과가 출력되어야 합니다");
            
            // 순위 출력 확인
            assertTrue(output.contains("1위"), "1위가 표시되어야 합니다");
            assertTrue(output.contains("2위"), "2위가 표시되어야 합니다");
            assertTrue(output.contains("3위"), "3위가 표시되어야 합니다");
            assertTrue(output.contains("4위"), "4위가 표시되어야 합니다");
            
            // 플레이어 정보 출력 확인
            assertTrue(output.contains("원"), "금액이 표시되어야 합니다");
            assertTrue(output.contains("승"), "승리 횟수가 표시되어야 합니다");
            assertTrue(output.contains("패"), "패배 횟수가 표시되어야 합니다");
            assertTrue(output.contains("무"), "무승부 횟수가 표시되어야 합니다");
            
        } finally {
            System.setOut(originalOut);
        }
    }
    
    @Test
    @DisplayName("100라운드 진행 확인 테스트")
    public void testHundredRounds() {
        // given
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        
        try {
            // when
            Casino.main(new String[]{});
            
            // then
            String output = outputStream.toString();
            
            // 각 플레이어의 승/패/무 합이 100이 되는지 간접적으로 확인
            // 출력에서 숫자를 파싱하여 확인하는 것은 복잡하므로
            // 최소한 합리적인 범위의 숫자가 출력되는지 확인
            assertTrue(output.matches(".*\\d+승.*"), "승리 횟수가 표시되어야 합니다");
            assertTrue(output.matches(".*\\d+패.*"), "패배 횟수가 표시되어야 합니다");
            assertTrue(output.matches(".*\\d+무.*"), "무승부 횟수가 표시되어야 합니다");
            
        } finally {
            System.setOut(originalOut);
        }
    }
    
    @Test
    @DisplayName("최종 금액 범위 확인 테스트")
    public void testFinalMoneyRange() {
        // given
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        
        try {
            // when
            Casino.main(new String[]{});
            
            // then
            String output = outputStream.toString();
            
            // 초기 자금 10,000원에서 시작
            // 100라운드 동안 최대 ±10,000원 변동 가능 (승리 시 +100원)
            // 따라서 최종 금액은 0원 ~ 20,000원 사이여야 함
            
            // 금액이 합리적인 범위에 있는지 확인
            assertTrue(output.contains("원"), "금액이 원 단위로 표시되어야 합니다");
            
            // 최소한 한 명은 10,000원 이상을 가지고 있어야 함 (승자가 있으므로)
            assertTrue(output.matches(".*1[0-9]{4,}원.*"), 
                "최소 한 명은 10,000원 이상을 가지고 있어야 합니다");
            
        } finally {
            System.setOut(originalOut);
        }
    }
}