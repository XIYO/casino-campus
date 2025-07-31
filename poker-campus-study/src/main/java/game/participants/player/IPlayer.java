package game.participants.player;

import game.components.hand.IHand;

/**
 * 플레이어의 기본 동작을 정의하는 인터페이스
 * 
 * 이 인터페이스는 카드 게임에서 플레이어의 기본 동작을 정의합니다.
 * 모든 플레이어 구현체는 이 인터페이스를 구현해야 합니다.
 * 
 * @author XIYO
 * @version 1.1
 * @since 2024-01-01
 */
public interface IPlayer {
    /**
     * 플레이어의 이름을 반환합니다.
     * 
     * @return 플레이어의 이름
     */
    String getName();
    
    /**
     * 플레이어의 현재 자금을 반환합니다.
     * 
     * @return 현재 보유 자금
     */
    int getMoney();
    
    /**
     * 플레이어에게 돈을 추가합니다.
     * 
     * @param amount 추가할 금액
     * @throws IllegalArgumentException amount가 음수일 때
     */
    void addMoney(int amount);
    
    /**
     * 플레이어로부터 돈을 차감합니다.
     * 
     * @param amount 차감할 금액
     * @return 차감 성공 여부 (잔액 부족시 false)
     */
    boolean removeMoney(int amount);
    
    /**
     * 플레이어의 현재 핸드를 반환합니다.
     * 
     * @return 플레이어의 핸드
     */
    IHand getHand();
    
    /**
     * 플레이어의 핸드를 설정합니다.
     * 
     * @param hand 설정할 핸드
     * @throws IllegalArgumentException hand가 null일 때
     */
    void setHand(IHand hand);
    
    /**
     * 플레이어의 승리 횟수를 반환합니다.
     * 
     * @return 승리 횟수
     */
    int getWinCount();
    
    /**
     * 플레이어의 패배 횟수를 반환합니다.
     * 
     * @return 패배 횟수
     */
    int getLoseCount();
    
    /**
     * 플레이어의 무승부 횟수를 반환합니다.
     * 
     * @return 무승부 횟수
     */
    int getDrawCount();
    
    /**
     * 승리를 기록합니다.
     */
    void recordWin();
    
    /**
     * 패배를 기록합니다.
     */
    void recordLose();
    
    /**
     * 무승부를 기록합니다.
     */
    void recordDraw();
    
    /**
     * 플레이어를 문자열로 표현합니다.
     * 
     * @return 플레이어의 문자열 표현
     */
    String toString();
}