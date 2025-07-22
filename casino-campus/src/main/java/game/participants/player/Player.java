package game.participants.player;

import game.components.hand.Hand;
import game.components.hand.IHand;

/**
 * 플레이어 클래스의 기본 구현
 * IPlayer 인터페이스를 구현합니다.
 * 
 * 구현이 필요한 메서드:
 * - addMoney() 메서드: 돈 추가 (음수 체크)
 * - removeMoney() 메서드: 돈 차감 (잔액 체크)
 */
public class Player implements IPlayer {
    private String name;
    private int money;
    private IHand hand;
    private int winCount;
    private int loseCount;
    private int drawCount;
    
    /**
     * Player 생성자
     * @param name 플레이어 이름
     * @param initialMoney 초기 자금
     */
    public Player(String name, int initialMoney) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("이름은 비어있을 수 없습니다.");
        }
        if (initialMoney < 0) {
            throw new IllegalArgumentException("초기 자금은 음수일 수 없습니다.");
        }
        
        this.name = name;
        this.money = initialMoney;
        this.hand = new Hand();
        this.winCount = 0;
        this.loseCount = 0;
        this.drawCount = 0;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public int getMoney() {
        return money;
    }
    
    @Override
    public void addMoney(int amount) {
        // TODO: 구현하세요
        // 힌트:
        // 1. amount가 음수인지 체크
        // 2. 음수면 IllegalArgumentException 던지기
        // 3. 양수면 money에 추가
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public boolean removeMoney(int amount) {
        // TODO: 구현하세요
        // 힌트:
        // 1. amount가 음수인지 체크 (음수면 false)
        // 2. money >= amount인지 체크
        // 3. 충분하면 차감하고 true 반환
        // 4. 부족하면 false 반환
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public IHand getHand() {
        return hand;
    }
    
    @Override
    public void setHand(IHand hand) {
        if (hand == null) {
            throw new IllegalArgumentException("핸드는 null일 수 없습니다.");
        }
        this.hand = hand;
    }
    
    @Override
    public int getWinCount() {
        return winCount;
    }
    
    @Override
    public int getLoseCount() {
        return loseCount;
    }
    
    @Override
    public int getDrawCount() {
        return drawCount;
    }
    
    @Override
    public void recordWin() {
        winCount++;
    }
    
    @Override
    public void recordLose() {
        loseCount++;
    }
    
    @Override
    public void recordDraw() {
        drawCount++;
    }
    
    @Override
    public String toString() {
        return String.format("%s (자금: %d원, 전적: %d승 %d패 %d무)", 
            name, money, winCount, loseCount, drawCount);
    }
}