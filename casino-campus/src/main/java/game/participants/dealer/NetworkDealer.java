package game.participants.dealer;

import network.INetworkCommunicator;
import game.participants.player.INetworkPlayer;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * 네트워크 딜러 클래스의 기본 구현
 * Dealer 클래스를 상속받고 INetworkDealer 인터페이스를 구현합니다.
 * 
 * 힌트:
 * - 네트워크 플레이어들을 관리해야 합니다
 * - 모든 플레이어에게 게임 상태를 브로드캐스트해야 합니다
 */
public class NetworkDealer extends Dealer implements INetworkDealer {
    // TODO: 추가 필드를 선언하세요
    // - INetworkCommunicator
    // - Map<String, INetworkPlayer> 연결된 플레이어 관리
    
    public NetworkDealer() {
        super();
        // TODO: 구현하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public void setCommunicator(INetworkCommunicator communicator) {
        // TODO: 구현하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public void notifyGameStart() throws Exception {
        // TODO: 구현하세요
        // 힌트: 모든 플레이어에게 게임 시작 메시지를 브로드캐스트하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public void notifyRoundStart(int roundNumber) throws Exception {
        // TODO: 구현하세요
        // 힌트: 라운드 시작 정보를 브로드캐스트하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public void notifyRoundResult(List<INetworkPlayer> winners) throws Exception {
        // TODO: 구현하세요
        // 힌트: 승자 정보를 브로드캐스트하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public void notifyGameEnd() throws Exception {
        // TODO: 구현하세요
        // 힌트: 게임 종료와 최종 결과를 브로드캐스트하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public void addNetworkPlayer(INetworkPlayer player) {
        // TODO: 구현하세요
        // 힌트: Map에 플레이어를 추가하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public void removeNetworkPlayer(String playerId) {
        // TODO: 구현하세요
        // 힌트: Map에서 플레이어를 제거하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public int getConnectedPlayerCount() {
        // TODO: 구현하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
}