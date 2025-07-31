package game.participants.player;

import game.components.hand.Hand;
import game.components.hand.IHand;

/**
 * 플레이어의 기본 동작을 정의하는 클래스
 * 
 * 이 클래스는 포커 게임에서 플레이어의 상태와 행동을 관리합니다.
 * 
 * 주요 기능:
 * - 자금 관리: addMoney(), removeMoney() 메서드로 베팅과 상금 지급 처리
 * - 핸드 관리: Hand 객체를 통한 카드 보유 상태 관리
 * - 게임 기록: 승/패/무승부 통계 관리
 * 
 * 구현이 필요한 메서드:
 * - addMoney() 메서드: 돈 추가 (음수 검증, 오버플로우 방지)
 * - removeMoney() 메서드: 돈 차감 (음수 검증, 잔액 부족 처리)
 * 
 * 유효성 검증:
 * - 이름: null 또는 빈 문자열 불허
 * - 초기 자금: 음수 불허
 * - 핸드: null 불허
 * - 모든 자금 조작: 음수 및 오버플로우 검증 필수
 * 
 * @author XIYO
 * @version 1.2
 * @since 2024-01-01
 */
public class Player implements IPlayer {
    private String name;
    private int money;
    private Hand hand;
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
     * @throws IllegalArgumentException amount가 음수인 경우
     */
    public void addMoney(int amount) {
        throw new UnsupportedOperationException(
            "TODO: addMoney() 메서드를 구현하세요.\n" +
            "1. amount가 음수(<0)인지 확인하고, 음수면 IllegalArgumentException을 던지세요\n" +
            "   - 예외 메시지: \"추가할 금액은 음수일 수 없습니다. 입력값: \" + amount\n" +
            "2. amount가 0 이상이면 money 필드에 더하세요 (this.money += amount)\n" +
            "3. 자금 관리 검증: 추가 후 money가 Integer.MAX_VALUE를 초과하지 않도록 주의하세요"
        );
    }
    
    /**
     * 플레이어로부터 돈을 차감합니다.
     * 
     * @param amount 차감할 금액
     * @return 차감 성공 여부 (잔액 부족시 false)
     */
    public boolean removeMoney(int amount) {
        throw new UnsupportedOperationException(
            "TODO: removeMoney() 메서드를 구현하세요.\n" +
            "1. amount가 음수(<0)인지 확인하고, 음수면 false를 반환하세요\n" +
            "2. 현재 보유 자금(money)이 차감할 금액(amount) 이상인지 확인하세요 (money >= amount)\n" +
            "3. 자금이 충분하면:\n" +
            "   - money에서 amount를 차감하세요 (this.money -= amount)\n" +
            "   - true를 반환하세요\n" +
            "4. 자금이 부족하면 false를 반환하세요 (money는 변경하지 않음)\n" +
            "5. 베팅 관리: 이 메서드는 베팅 시 자금 차감에 사용됩니다"
        );
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
     * @throws IllegalArgumentException hand가 null인 경우
     */
    public void setHand(IHand hand) {
        if (hand == null) {
            throw new IllegalArgumentException("핸드는 null일 수 없습니다.");
        }
        this.hand = (Hand) hand;
        // 구현 가이드:
        // 1. 이 메서드는 이미 완성되어 있습니다
        // 2. null 검증 후 핸드를 설정하는 기본적인 setter 메서드입니다
        // 3. 게임 시작 시 새로운 핸드로 교체할 때 사용됩니다
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
     * 게임 결과 처리 시 호출됩니다.
     */
    public void recordWin() {
        winCount++;
        // 구현 가이드:
        // 1. 이 메서드는 이미 완성되어 있습니다
        // 2. 승리 횟수를 1 증가시키는 간단한 메서드입니다
        // 3. 게임 엔진에서 승부 결정 후 호출됩니다
        // 4. 통계 관리: 전체 게임 기록을 추적하는 용도입니다
    }
    
    /**
     * 패배를 기록합니다.
     * 게임 결과 처리 시 호출됩니다.
     */
    public void recordLose() {
        loseCount++;
        // 구현 가이드:
        // 1. 이 메서드는 이미 완성되어 있습니다
        // 2. 패배 횟수를 1 증가시키는 간단한 메서드입니다
        // 3. 게임 엔진에서 승부 결정 후 호출됩니다
        // 4. 통계 관리: 플레이어의 승률 계산에 사용됩니다
    }
    
    /**
     * 무승부를 기록합니다.
     * 게임 결과 처리 시 호출됩니다.
     */
    public void recordDraw() {
        drawCount++;
        // 구현 가이드:
        // 1. 이 메서드는 이미 완성되어 있습니다
        // 2. 무승부 횟수를 1 증가시키는 간단한 메서드입니다
        // 3. 게임 엔진에서 승부 결정 후 호출됩니다
        // 4. 통계 관리: 전체 게임 기록 완성도를 높입니다
    }
    
    /**
     * 플레이어의 정보를 문자열로 반환합니다.
     * 
     * @return "이름 (자금: xxx원, 전적: x승 x패 x무)" 형식의 문자열
     */
    @Override
    public String toString() {
        return String.format("%s (자금: %d원, 전적: %d승 %d패 %d무)", 
            name, money, winCount, loseCount, drawCount);
        // 구현 가이드:
        // 1. 이 메서드는 이미 완성되어 있습니다
        // 2. 플레이어의 상태를 한눈에 볼 수 있는 요약 정보를 제공합니다
        // 3. 디버깅과 게임 상태 출력에 유용합니다
        // 4. String.format()을 사용하여 깔끔한 형식으로 출력합니다
    }
}