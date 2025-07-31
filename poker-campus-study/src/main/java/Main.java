import game.management.poker.PokerRoom;

/**
 * 포커 게임의 메인 엔트리 포인트
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║     🃏 포커 캠퍼스 스터디룸 🃏      ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println();
        System.out.println("로컬 게임을 시작합니다.");
        System.out.println("4명의 플레이어와 100라운드를 진행합니다.\n");
        
        // PokerRoom 클래스의 main 메소드 호출
        PokerRoom.main(new String[]{});
    }
}