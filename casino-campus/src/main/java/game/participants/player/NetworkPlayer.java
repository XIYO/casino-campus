package game.participants.player;

import network.INetworkCommunicator;

/**
 * 네트워크 플레이어 클래스의 기본 구현
 * Player 클래스를 상속받고 INetworkPlayer 인터페이스를 구현합니다.
 * 
 * 힌트:
 * - Player의 기능에 네트워크 통신 기능을 추가해야 합니다
 * - 고유 ID와 호스트 여부를 관리해야 합니다
 */
public class NetworkPlayer extends Player implements INetworkPlayer {
    // TODO: 추가 필드를 선언하세요
    // - INetworkCommunicator
    // - String playerId
    // - boolean isHost
    
    public NetworkPlayer(String name, int initialMoney, String playerId, boolean isHost) {
        super(name, initialMoney);
        // TODO: 구현하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public void setCommunicator(INetworkCommunicator communicator) {
        // TODO: 구현하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public String getPlayerId() {
        // TODO: 구현하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public boolean isHost() {
        // TODO: 구현하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public boolean isConnected() {
        // TODO: 구현하세요
        // 힌트: communicator가 null이 아닌지 확인하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public void sendMessage(String message) throws Exception {
        // TODO: 구현하세요
        // 힌트: communicator를 사용하여 메시지를 전송하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public void sendTurnData(Object turnData) throws Exception {
        // TODO: 구현하세요
        // 힌트: communicator를 사용하여 객체를 전송하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
}