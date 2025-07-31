package game.participants.dealer;

import game.components.deck.DeckRef;
import game.components.hand.HandRef;
import game.participants.player.IPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * 딜러 참조 구현체 - IDealer 인터페이스의 완성된 구현
 * 
 * 이 클래스는 카지노 게임의 딜러를 나타내는 완성된 구현체입니다.
 * 게임 진행, 카드 분배, 승부 판정, 상금 분배의 모든 역할을 담당합니다.
 * 
 * <p>주요 기능:</p>
 * <ul>
 *   <li>게임 관리: 새 게임 시작, 덱 초기화 및 셔플</li>
 *   <li>카드 분배: 플레이어들에게 공정한 카드 분배</li>
 *   <li>승부 판정: 포커 족보에 따른 승자 결정</li>
 *   <li>상금 분배: 승자들에게 상금 지급</li>
 *   <li>게임 진행: 전체 게임 라운드 관리</li>
 * </ul>
 * 
 * <p>게임 규칙:</p>
 * <ul>
 *   <li>각 플레이어에게 5장씩 카드 분배</li>
 *   <li>가장 높은 족보를 가진 플레이어 승리</li>
 *   <li>동점 시 모든 동점자가 승리</li>
 *   <li>전체 동점 시 무승부 (상금 없음)</li>
 *   <li>승자당 100원 상금 지급</li>
 * </ul>
 * 
 * <p>게임 진행 순서:</p>
 * <ol>
 *   <li>새 덱 생성 및 셔플</li>
 *   <li>각 플레이어에게 5장씩 카드 분배</li>
 *   <li>모든 플레이어 핸드 공개</li>
 *   <li>족보 비교하여 승자 결정</li>
 *   <li>승부 결과 출력 및 기록</li>
 *   <li>상금 분배</li>
 * </ol>
 * 
 * <p>사용 예시:</p>
 * <pre>
 * DealerRef dealer = new DealerRef();
 * List&lt;Player&gt; players = Arrays.asList(player1, player2);
 * dealer.playGame(players, 10);  // 10라운드 게임 진행
 * </pre>
 * 
 * @author XIYO
 * @version 1.1
 * @since 2024-01-01
 */
public class DealerRef implements IDealer {
    private DeckRef deck;
    private static final int CARDS_PER_PLAYER = 5;
    private static final int PRIZE_PER_ROUND = 100;
    
    /**
     * DealerRef 생성자
     */
    public DealerRef() {
        this.deck = new DeckRef();
    }
    
    /**
     * 새로운 게임을 시작합니다.
     * 덱을 초기화하고 셔플합니다.
     */
    public void startNewGame() {
        // 새로운 덱 생성
        deck = new DeckRef();
        deck.shuffle();
    }
    
    /**
     * 플레이어들에게 카드를 분배합니다.
     * 
     * @param players 카드를 받을 플레이어 목록
     */
    public void dealCards(List<? extends IPlayer> players) {
        // 모든 플레이어의 핸드를 초기화
        for (IPlayer player : players) {
            player.setHand(new HandRef());
        }
        
        // 각 플레이어에게 5장씩 분배
        for (int i = 0; i < CARDS_PER_PLAYER; i++) {
            for (IPlayer player : players) {
                player.getHand().add(deck.drawCard());
            }
        }
    }
    
    /**
     * 라운드의 승자를 결정합니다.
     * 
     * @param players 참가 플레이어 목록
     * @return 승자 목록 (동점일 경우 여러 명)
     */
    public List<? extends IPlayer> determineWinners(List<? extends IPlayer> players) {
        List<IPlayer> winners = new ArrayList<>();
        int highestScore = 0;
        
        // 최고 점수 찾기
        for (IPlayer player : players) {
            int score = player.getHand().open();
            if (score > highestScore) {
                highestScore = score;
            }
        }
        
        // 최고 점수를 가진 모든 플레이어 찾기
        for (IPlayer player : players) {
            if (player.getHand().open() == highestScore) {
                winners.add(player);
            }
        }
        
        return winners;
    }
    
    /**
     * 승자들에게 상금을 분배합니다.
     * 
     * @param winners 승자 목록
     * @param prizeAmount 각 승자가 받을 상금
     */
    public void distributePrize(List<? extends IPlayer> winners, int prizeAmount) {
        for (IPlayer winner : winners) {
            winner.addMoney(prizeAmount);
        }
    }
    
    /**
     * 전체 게임을 진행합니다.
     * 
     * @param players 참가 플레이어 목록
     * @param rounds 진행할 라운드 수
     */
    public void playGame(List<? extends IPlayer> players, int rounds) {
        if (players == null || players.isEmpty()) {
            throw new IllegalArgumentException("플레이어가 없습니다.");
        }
        if (rounds <= 0) {
            throw new IllegalArgumentException("라운드 수는 양수여야 합니다.");
        }
        
        for (int round = 1; round <= rounds; round++) {
            System.out.println("\n=== 라운드 " + round + " ===");
            
            // 새 게임 시작
            startNewGame();
            
            // 카드 분배
            dealCards(players);
            
            // 각 플레이어의 핸드 출력
            System.out.println("플레이어 핸드:");
            for (IPlayer player : players) {
                System.out.println(player.getName() + ": " + player.getHand() + 
                    " (" + player.getHand().evaluate() + ")");
            }
            
            // 승자 판정
            List<? extends IPlayer> winners = determineWinners(players);
            
            // 결과 출력 및 기록 업데이트
            if (winners.size() == players.size()) {
                // 모든 플레이어가 동점 - 무승부
                System.out.println("\n결과: 무승부!");
                System.out.println("상금: 없음");
                for (IPlayer player : players) {
                    player.recordDraw();
                }
                // 무승부 시에는 상금 분배 없음
            } else {
                // 승자 출력
                System.out.println("\n승자:");
                for (IPlayer winner : winners) {
                    System.out.println("  🏆 " + winner.getName() + " - " + 
                        winner.getHand().evaluate() + " (+" + PRIZE_PER_ROUND + "원)");
                }
                
                // 승자와 패자 기록
                for (IPlayer player : players) {
                    if (winners.contains(player)) {
                        player.recordWin();
                    } else {
                        player.recordLose();
                    }
                }
                // 승자에게만 상금 분배
                distributePrize(winners, PRIZE_PER_ROUND);
            }
        }
    }
}