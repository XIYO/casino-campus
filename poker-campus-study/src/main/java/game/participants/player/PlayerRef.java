package game.participants.player;

import game.components.hand.HandRef;
import game.components.hand.IHand;

/**
 * 플레이어 참조 구현체 - IPlayer 인터페이스의 완성된 구현
 * 
 * 이 클래스는 카지노 게임에 참여하는 플레이어를 나타내는 완성된 구현체입니다.
 * 플레이어의 기본 정보, 자금 관리, 게임 전적을 추적합니다.
 * 
 * <p>주요 기능:</p>
 * <ul>
 *   <li>플레이어 정보: 이름, 자금 관리</li>
 *   <li>핸드 관리: 현재 손패 설정 및 조회</li>
 *   <li>자금 관리: 돈 추가/차감 (안전성 검증 포함)</li>
 *   <li>전적 추적: 승/패/무승부 기록 관리</li>
 *   <li>입력 검증: null 체크, 음수 방지</li>
 * </ul>
 * 
 * <p>안전성 보장:</p>
 * <ul>
 *   <li>음수 자금 차단: 음수 금액 추가/차감 방지</li>
 *   <li>잔액 부족 방지: 보유 자금보다 많은 차감 방지</li>
 *   <li>null 방지: 이름, 핸드 null 체크</li>
 * </ul>
 * 
 * <p>사용 예시:</p>
 * <pre>
 * PlayerRef player = new PlayerRef("김철수", 10000);
 * player.addMoney(5000);     // 자금 추가
 * player.removeMoney(3000);  // 자금 차감
 * player.recordWin();        // 승리 기록
 * System.out.println(player); // 상태 출력
 * </pre>
 * 
 * @author XIYO
 * @version 1.1
 * @since 2024-01-01
 */
public class PlayerRef implements IPlayer {
    private String name;
    private int money;
    private IHand hand;
    private int winCount;
    private int loseCount;
    private int drawCount;
    
    /**
     * PlayerRef 생성자
     * @param name 플레이어 이름
     * @param initialMoney 초기 자금
     */
    public PlayerRef(String name, int initialMoney) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("이름은 비어있을 수 없습니다.");
        }
        if (initialMoney < 0) {
            throw new IllegalArgumentException("초기 자금은 음수일 수 없습니다.");
        }
        
        this.name = name;
        this.money = initialMoney;
        this.hand = new HandRef();
        this.winCount = 0;
        this.loseCount = 0;
        this.drawCount = 0;
    }
    
    /**
     * 플레이어의 이름을 반환합니다.
     * 
     * @return 플레이어의 이름
     */
    public String getName() {
        return name;
    }
    
    /**
     * 플레이어의 현재 자금을 반환합니다.
     * 
     * @return 현재 보유 자금
     */
    public int getMoney() {
        return money;
    }
    
    /**
     * 플레이어에게 돈을 추가합니다.
     * 
     * @param amount 추가할 금액
     */
    public void addMoney(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("금액은 음수일 수 없습니다.");
        }
        money += amount;
    }
    
    /**
     * 플레이어로부터 돈을 차감합니다.
     * 
     * @param amount 차감할 금액
     * @return 차감 성공 여부 (잔액 부족시 false)
     */
    public boolean removeMoney(int amount) {
        if (amount < 0) {
            return false;
        }
        if (money >= amount) {
            money -= amount;
            return true;
        }
        return false;
    }
    
    /**
     * 플레이어의 현재 핸드를 반환합니다.
     * 
     * @return 플레이어의 핸드
     */
    public IHand getHand() {
        return hand;
    }
    
    /**
     * 플레이어의 핸드를 설정합니다.
     * 
     * @param hand 설정할 핸드
     */
    public void setHand(IHand hand) {
        if (hand == null) {
            throw new IllegalArgumentException("핸드는 null일 수 없습니다.");
        }
        this.hand = hand;
    }
    
    /**
     * 플레이어의 승리 횟수를 반환합니다.
     * 
     * @return 승리 횟수
     */
    public int getWinCount() {
        return winCount;
    }
    
    /**
     * 플레이어의 패배 횟수를 반환합니다.
     * 
     * @return 패배 횟수
     */
    public int getLoseCount() {
        return loseCount;
    }
    
    /**
     * 플레이어의 무승부 횟수를 반환합니다.
     * 
     * @return 무승부 횟수
     */
    public int getDrawCount() {
        return drawCount;
    }
    
    /**
     * 승리를 기록합니다.
     */
    public void recordWin() {
        winCount++;
    }
    
    /**
     * 패배를 기록합니다.
     */
    public void recordLose() {
        loseCount++;
    }
    
    /**
     * 무승부를 기록합니다.
     */
    public void recordDraw() {
        drawCount++;
    }
    
    @Override
    public String toString() {
        return String.format("%s (자금: %d원, 전적: %d승 %d패 %d무)", 
            name, money, winCount, loseCount, drawCount);
    }
}