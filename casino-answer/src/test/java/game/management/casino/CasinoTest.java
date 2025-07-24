package game.management.casino;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Casino 클래스 테스트
 * 
 * 카지노 메인 클래스의 기능을 검증합니다.
 */
class CasinoTest {
    
    @Test
    @DisplayName("1. 메인 메서드 실행 테스트")
    void testMainMethod() {
        // given - 출력 캡처 준비
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        
        try {
            // when - 메인 메서드 실행
            Casino.main(new String[]{});
            
            // then - 출력 내용 검증
            String output = outputStream.toString();
            
            // 게임 시작 메시지 검증
            assertTrue(output.contains("🎰 라스베가스 드림 카지노 🎰"));
            assertTrue(output.contains("게임을 시작합니다!"));
            assertTrue(output.contains("플레이어 수: 4명"));
            assertTrue(output.contains("총 라운드: 100라운드"));
            assertTrue(output.contains("초기 자금: 10000원"));
            assertTrue(output.contains("라운드당 상금: 100원"));
            
            // 게임 종료 메시지 검증
            assertTrue(output.contains("베타 테스트 결과"));
            assertTrue(output.contains("🥇"));
            assertTrue(output.contains("🥈"));
            assertTrue(output.contains("🥉"));
            assertTrue(output.contains("😢"));
            
            // 플레이어 이름 검증
            assertTrue(output.contains("럭키가이"));
            assertTrue(output.contains("포커마스터"));
            assertTrue(output.contains("초보자"));
            assertTrue(output.contains("불운한자"));
            
            // 전적 형식 검증 (예: "27승 65패 8무")
            assertTrue(output.matches("(?s).*\\d+승 \\d+패 \\d+무.*"));
            
        } finally {
            // 원래 출력 스트림 복원
            System.setOut(originalOut);
        }
    }
    
    @Test
    @DisplayName("2. 게임 결과 합계 검증")
    void testGameResultTotals() {
        // given
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        
        try {
            // when
            Casino.main(new String[]{});
            
            // then - 출력에서 승/패/무 숫자 추출 및 검증
            String output = outputStream.toString();
            String[] lines = output.split("\n");
            
            int totalWins = 0;
            int totalLoses = 0;
            int totalDraws = 0;
            int playerCount = 0;
            
            for (String line : lines) {
                // 전적이 포함된 라인 찾기 (예: "27승 65패 8무")
                if (line.matches(".*\\d+승 \\d+패 \\d+무.*")) {
                    playerCount++;
                    
                    // 숫자 추출
                    String[] parts = line.split(" ");
                    for (int i = 0; i < parts.length; i++) {
                        if (parts[i].endsWith("승")) {
                            totalWins += Integer.parseInt(parts[i].replace("승", ""));
                        } else if (parts[i].endsWith("패")) {
                            totalLoses += Integer.parseInt(parts[i].replace("패", ""));
                        } else if (parts[i].endsWith("무")) {
                            totalDraws += Integer.parseInt(parts[i].replace("무", ""));
                        }
                    }
                }
            }
            
            // 검증
            assertEquals(4, playerCount, "플레이어는 4명이어야 합니다");
            
            // 각 플레이어의 승+패+무 = 100
            int totalGames = totalWins + totalLoses + totalDraws;
            assertEquals(400, totalGames, "전체 게임 수는 400이어야 합니다 (4명 * 100라운드)");
            
        } finally {
            System.setOut(originalOut);
        }
    }
    
    @Test
    @DisplayName("3. 최종 자금 검증")
    void testFinalMoney() {
        // given
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        
        try {
            // when
            Casino.main(new String[]{});
            
            // then
            String output = outputStream.toString();
            String[] lines = output.split("\n");
            
            int totalMoney = 0;
            int playerCount = 0;
            
            for (String line : lines) {
                // 자금이 포함된 라인 찾기 (예: "12,700원")
                if (line.matches(".*\\d+,\\d+원.*승.*패.*무.*")) {
                    playerCount++;
                    
                    // 금액 추출 (쉼표 제거)
                    String moneyStr = line.replaceAll(".*?(\\d+,\\d+)원.*", "$1");
                    moneyStr = moneyStr.replace(",", "");
                    totalMoney += Integer.parseInt(moneyStr);
                }
            }
            
            // 검증
            assertEquals(4, playerCount, "플레이어는 4명이어야 합니다");
            
            // 전체 자금은 초기 자금(40,000원) + 상금(100원 * 100라운드 = 10,000원)
            // 상금은 승자에게만 분배되므로 전체 합은 50,000원
            assertEquals(50000, totalMoney, "전체 자금의 합은 50,000원이어야 합니다");
            
        } finally {
            System.setOut(originalOut);
        }
    }
    
    @Test
    @DisplayName("4. 순위 정렬 검증")
    void testRankingSorting() {
        // given
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        
        try {
            // when
            Casino.main(new String[]{});
            
            // then
            String output = outputStream.toString();
            String[] lines = output.split("\n");
            
            int previousMoney = Integer.MAX_VALUE;
            boolean foundRanking = false;
            
            for (String line : lines) {
                // 순위가 포함된 라인 찾기
                if (line.matches(".*(🥇|🥈|🥉|😢).*\\d+,\\d+원.*")) {
                    foundRanking = true;
                    
                    // 금액 추출
                    String moneyStr = line.replaceAll(".*?(\\d+,\\d+)원.*", "$1");
                    moneyStr = moneyStr.replace(",", "");
                    int currentMoney = Integer.parseInt(moneyStr);
                    
                    // 내림차순 정렬 확인
                    assertTrue(currentMoney <= previousMoney, 
                        "순위는 자금 기준 내림차순이어야 합니다");
                    previousMoney = currentMoney;
                }
            }
            
            assertTrue(foundRanking, "순위 정보를 찾을 수 없습니다");
            
        } finally {
            System.setOut(originalOut);
        }
    }
    
    @Test
    @DisplayName("5. 메달 순서 검증")
    void testMedalOrder() {
        // given
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        
        try {
            // when
            Casino.main(new String[]{});
            
            // then
            String output = outputStream.toString();
            
            // 메달 순서 확인
            int goldIndex = output.indexOf("🥇");
            int silverIndex = output.indexOf("🥈");
            int bronzeIndex = output.indexOf("🥉");
            int cryIndex = output.indexOf("😢");
            
            assertTrue(goldIndex > 0, "금메달이 없습니다");
            assertTrue(silverIndex > goldIndex, "은메달이 금메달보다 먼저 나옵니다");
            assertTrue(bronzeIndex > silverIndex, "동메달이 은메달보다 먼저 나옵니다");
            assertTrue(cryIndex > bronzeIndex, "4위 표시가 동메달보다 먼저 나옵니다");
            
        } finally {
            System.setOut(originalOut);
        }
    }
}