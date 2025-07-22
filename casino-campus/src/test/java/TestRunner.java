// 기본 패키지 (테스트 유틸리티)

import java.util.Scanner;

/**
 * 테스트 실행을 위한 헬퍼 클래스
 * 학생들이 쉽게 테스트를 실행할 수 있도록 도와줍니다.
 */
public class TestRunner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║        🧪 테스트 실행기 🧪             ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println();
        System.out.println("테스트할 클래스를 선택하세요:");
        System.out.println("1. Card 테스트");
        System.out.println("2. Deck 테스트");
        System.out.println("3. Hand 테스트 (포커 족보)");
        System.out.println("4. Player 테스트");
        System.out.println("5. Dealer 테스트");
        System.out.println("6. Casino 테스트 (전체 게임)");
        System.out.println("7. 모든 테스트 실행");
        System.out.print("\n선택 (1-7): ");
        
        try {
            int choice = scanner.nextInt();
            System.out.println();
            
            switch (choice) {
                case 1:
                    runTest("game.components.card.CardTest");
                    break;
                case 2:
                    runTest("game.components.deck.DeckTest");
                    break;
                case 3:
                    runTest("game.components.hand.HandTest");
                    break;
                case 4:
                    runTest("game.participants.player.PlayerTest");
                    break;
                case 5:
                    runTest("game.participants.dealer.DealerTest");
                    break;
                case 6:
                    runTest("game.management.casino.CasinoTest");
                    break;
                case 7:
                    runAllTests();
                    break;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        } catch (Exception e) {
            System.err.println("오류가 발생했습니다: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
    
    private static void runTest(String testClassName) {
        System.out.println("=== " + testClassName + " 실행 중 ===");
        System.out.println();
        
        try {
            // JUnit 테스트 실행
            org.junit.runner.JUnitCore junit = new org.junit.runner.JUnitCore();
            org.junit.runner.Result result = junit.run(Class.forName(testClassName));
            
            // 결과 출력
            System.out.println("\n=== 테스트 결과 ===");
            System.out.println("실행된 테스트: " + result.getRunCount());
            System.out.println("성공: " + (result.getRunCount() - result.getFailureCount()));
            System.out.println("실패: " + result.getFailureCount());
            
            if (result.wasSuccessful()) {
                System.out.println("\n✅ 모든 테스트 통과!");
            } else {
                System.out.println("\n❌ 실패한 테스트:");
                result.getFailures().forEach(failure -> {
                    System.out.println("- " + failure.getTestHeader());
                    System.out.println("  원인: " + failure.getMessage());
                });
            }
        } catch (ClassNotFoundException e) {
            System.err.println("테스트 클래스를 찾을 수 없습니다: " + testClassName);
            System.err.println("테스트 파일이 컴파일되었는지 확인하세요.");
        } catch (Exception e) {
            System.err.println("테스트 실행 중 오류: " + e.getMessage());
        }
    }
    
    private static void runAllTests() {
        String[] testClasses = {
            "game.components.card.CardTest",
            "game.components.deck.DeckTest",
            "game.components.hand.HandTest",
            "game.participants.player.PlayerTest",
            "game.participants.dealer.DealerTest",
            "game.management.casino.CasinoTest"
        };
        
        System.out.println("=== 전체 테스트 실행 ===\n");
        
        int totalTests = 0;
        int totalPassed = 0;
        
        for (String testClass : testClasses) {
            System.out.println("\n--- " + testClass + " ---");
            try {
                org.junit.runner.JUnitCore junit = new org.junit.runner.JUnitCore();
                org.junit.runner.Result result = junit.run(Class.forName(testClass));
                
                int passed = result.getRunCount() - result.getFailureCount();
                totalTests += result.getRunCount();
                totalPassed += passed;
                
                System.out.println("결과: " + passed + "/" + result.getRunCount() + " 통과");
            } catch (Exception e) {
                System.out.println("실행 실패: " + e.getMessage());
            }
        }
        
        System.out.println("\n=== 전체 결과 ===");
        System.out.println("총 테스트: " + totalTests);
        System.out.println("통과: " + totalPassed);
        System.out.println("실패: " + (totalTests - totalPassed));
        System.out.println("성공률: " + (totalTests > 0 ? (totalPassed * 100 / totalTests) : 0) + "%");
    }
}