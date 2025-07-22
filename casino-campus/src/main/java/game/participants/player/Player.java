package game.participants.player;

import game.components.hand.IHand;
import game.components.hand.Hand;

/**
 * 플레이어 클래스의 기본 구현
 * IPlayer 인터페이스를 구현합니다.
 * 
 * 힌트:
 * - 이름, 자금, 핸드를 필드로 가져야 합니다
 * - 승/패/무 기록을 관리해야 합니다
 */
public class Player implements IPlayer {
    // TODO: 필드를 선언하세요
    
    public Player(String name, int initialMoney) {
        // TODO: 구현하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public String getName() {
        // TODO: 구현하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public int getMoney() {
        // TODO: 구현하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public void addMoney(int amount) {
        // TODO: 구현하세요
        // 힌트: 음수 금액은 허용하지 않아야 합니다
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public boolean removeMoney(int amount) {
        // TODO: 구현하세요
        // 힌트: 잔액이 부족하면 false를 반환하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public IHand getHand() {
        // TODO: 구현하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public void setHand(IHand hand) {
        // TODO: 구현하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public int getWinCount() {
        // TODO: 구현하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public int getLoseCount() {
        // TODO: 구현하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public int getDrawCount() {
        // TODO: 구현하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public void recordWin() {
        // TODO: 구현하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public void recordLose() {
        // TODO: 구현하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public void recordDraw() {
        // TODO: 구현하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
}