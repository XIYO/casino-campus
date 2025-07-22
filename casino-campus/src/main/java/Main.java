import game.management.casino.Casino;
import game.management.casino.NetworkCasino;
import java.util.Scanner;

/**
 * 카지노 게임의 메인 엔트리 포인트
 * 로컬 게임과 네트워크 게임을 선택할 수 있습니다.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║     🎰 라스베가스 드림 카지노 🎰      ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println();
        System.out.println("게임 모드를 선택하세요:");
        System.out.println("1. 로컬 게임 (혼자 연습하기)");
        System.out.println("2. 네트워크 게임 (친구들과 함께)");
        System.out.println("3. 종료");
        System.out.print("\n선택 (1-3): ");
        
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기
            
            switch (choice) {
                case 1:
                    runLocalGame();
                    break;
                case 2:
                    runNetworkGame(scanner);
                    break;
                case 3:
                    System.out.println("게임을 종료합니다. 안녕히 가세요!");
                    break;
                default:
                    System.out.println("잘못된 선택입니다. 프로그램을 종료합니다.");
            }
        } catch (Exception e) {
            System.err.println("오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
    
    /**
     * 로컬 게임을 실행합니다.
     */
    private static void runLocalGame() {
        System.out.println("\n=== 로컬 게임 시작 ===");
        System.out.println("4명의 플레이어와 100라운드를 진행합니다.\n");
        
        // Casino 클래스의 main 메소드 호출
        Casino.main(new String[]{});
    }
    
    /**
     * 네트워크 게임을 실행합니다.
     */
    private static void runNetworkGame(Scanner scanner) {
        System.out.println("\n=== 네트워크 게임 ===");
        System.out.println("1. 방 만들기 (호스트)");
        System.out.println("2. 방 참가하기 (클라이언트)");
        System.out.print("\n선택 (1-2): ");
        
        int mode = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기
        
        if (mode == 1) {
            runAsHost(scanner);
        } else if (mode == 2) {
            runAsClient(scanner);
        } else {
            System.out.println("잘못된 선택입니다.");
        }
    }
    
    /**
     * 호스트 모드로 게임을 시작합니다.
     */
    private static void runAsHost(Scanner scanner) {
        System.out.println("\n=== 호스트 모드 ===");
        System.out.print("플레이어 이름을 입력하세요: ");
        String playerName = scanner.nextLine();
        
        System.out.print("포트 번호를 입력하세요 (기본값: 8888): ");
        String portStr = scanner.nextLine();
        int port = portStr.isEmpty() ? 8888 : Integer.parseInt(portStr);
        
        System.out.print("최대 플레이어 수 (2-4명, 기본값: 4): ");
        String maxStr = scanner.nextLine();
        int maxPlayers = maxStr.isEmpty() ? 4 : Integer.parseInt(maxStr);
        
        try {
            NetworkCasino casino = new NetworkCasino();
            System.out.println("\n서버를 시작합니다...");
            System.out.println("다른 플레이어들이 다음 주소로 접속할 수 있습니다:");
            System.out.println("주소: localhost (또는 귀하의 IP 주소)");
            System.out.println("포트: " + port);
            System.out.println("\n플레이어를 기다리는 중... (0/" + maxPlayers + ")");
            
            casino.startAsHost(port, maxPlayers);
        } catch (Exception e) {
            System.err.println("호스트 시작 실패: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * 클라이언트 모드로 게임에 참가합니다.
     */
    private static void runAsClient(Scanner scanner) {
        System.out.println("\n=== 클라이언트 모드 ===");
        System.out.print("플레이어 이름을 입력하세요: ");
        String playerName = scanner.nextLine();
        
        System.out.print("호스트 주소를 입력하세요 (기본값: localhost): ");
        String host = scanner.nextLine();
        if (host.isEmpty()) host = "localhost";
        
        System.out.print("포트 번호를 입력하세요 (기본값: 8888): ");
        String portStr = scanner.nextLine();
        int port = portStr.isEmpty() ? 8888 : Integer.parseInt(portStr);
        
        try {
            NetworkCasino casino = new NetworkCasino();
            System.out.println("\n서버에 연결 중...");
            casino.joinAsClient(host, port);
            System.out.println("게임 방에 참가했습니다!");
            System.out.println("다른 플레이어를 기다리는 중...");
        } catch (Exception e) {
            System.err.println("연결 실패: " + e.getMessage());
            System.err.println("호스트가 게임을 시작했는지 확인해주세요.");
        }
    }
}