import game.participants.dealer.DealerRef;
import game.participants.dealer.IDealer;
import game.participants.player.PlayerRef;
import game.participants.player.IPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Ref 구현체를 사용한 카지노 게임의 메인 엔트리 포인트
 * 
 * 이 클래스는 완성된 참조 구현체들(Ref 클래스들)만을 사용하여 게임을 실행합니다.
 * 학생들이 자신의 구현체와 참조 구현체의 동작을 비교할 수 있도록 합니다.
 */
public class MainRef {
    private static final int INITIAL_MONEY = 10000;
    private static final int TOTAL_ROUNDS = 100;
    private static final int PLAYER_COUNT = 4;
    
    private static final String[] PLAYER_NAMES = {
        "럭키가이", "포커마스터", "초보자", "불운한자"
    };
    
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("   🎰 라스베가스 드림 카지노 (Ref) 🎰   ");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println();
        System.out.println("참조 구현체로 로컬 게임을 시작합니다.");
        System.out.println("플레이어 수: " + PLAYER_COUNT + "명");
        System.out.println("총 라운드: " + TOTAL_ROUNDS + "라운드");
        System.out.println("초기 자금: " + INITIAL_MONEY + "원\n");
        
        // IPlayer 인터페이스 타입으로 리스트 생성
        List<IPlayer> players = new ArrayList<>();
        
        // 4명의 플레이어 생성 (PlayerRef 구현체 사용)
        for (String name : PLAYER_NAMES) {
            players.add(new PlayerRef(name, INITIAL_MONEY));
        }
        
        // IDealer 인터페이스 타입으로 DealerRef 객체 생성
        IDealer dealer = new DealerRef();
        
        // 게임 진행
        dealer.playGame(players, TOTAL_ROUNDS);
        
        // 최종 결과 출력
        printFinalResults(players);
    }
    
    /**
     * 최종 결과를 출력합니다.
     * 
     * @param players 플레이어 목록
     */
    private static void printFinalResults(List<IPlayer> players) {
        System.out.println("\n🎰 라스베가스 드림 카지노 (Ref) - 최종 결과 🎰");
        System.out.println("════════════════════════════════════════");
        
        // 정렬을 위한 새로운 리스트 생성
        List<IPlayer> sortedPlayers = new ArrayList<>(players);
        
        // 플레이어를 자금 기준으로 내림차순 정렬
        sortedPlayers.sort((p1, p2) -> Integer.compare(p2.getMoney(), p1.getMoney()));
        
        // 메달 배열
        String[] medals = {"🥇", "🥈", "🥉", "😢"};
        
        // 순위별로 결과 출력
        for (int i = 0; i < sortedPlayers.size(); i++) {
            IPlayer player = sortedPlayers.get(i);
            System.out.printf("%s %d위: %s - %,d원 (%d승 %d패 %d무)\n",
                medals[i], 
                i + 1,
                player.getName(),
                player.getMoney(),
                player.getWinCount(),
                player.getLoseCount(),
                player.getDrawCount()
            );
        }
        System.out.println("════════════════════════════════════════");
        System.out.println("✨ 참조 구현체를 사용한 게임이 완료되었습니다! ✨");
    }
}