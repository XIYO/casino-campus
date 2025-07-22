package game.participants.dealer;

import network.INetworkCommunicator;
import game.participants.player.INetworkPlayer;
import java.util.List;

/**
 * 네트워크 게임의 딜러 인터페이스
 * 호스트 모드에서 자동으로 게임을 진행합니다.
 */
public interface INetworkDealer extends IDealer {
    /**
     * 네트워크 통신 객체를 설정합니다.
     * 
     * @param communicator 네트워크 통신 객체
     */
    void setCommunicator(INetworkCommunicator communicator);
    
    /**
     * 연결된 모든 플레이어에게 게임 시작을 알립니다.
     * 
     * @throws Exception 통신 실패 시 예외 발생
     */
    void notifyGameStart() throws Exception;
    
    /**
     * 연결된 모든 플레이어에게 라운드 시작을 알립니다.
     * 
     * @param roundNumber 라운드 번호
     * @throws Exception 통신 실패 시 예외 발생
     */
    void notifyRoundStart(int roundNumber) throws Exception;
    
    /**
     * 연결된 모든 플레이어에게 라운드 결과를 알립니다.
     * 
     * @param winners 승자 목록
     * @throws Exception 통신 실패 시 예외 발생
     */
    void notifyRoundResult(List<INetworkPlayer> winners) throws Exception;
    
    /**
     * 연결된 모든 플레이어에게 게임 종료를 알립니다.
     * 
     * @throws Exception 통신 실패 시 예외 발생
     */
    void notifyGameEnd() throws Exception;
    
    /**
     * 플레이어의 연결을 관리합니다.
     * 
     * @param player 추가할 플레이어
     */
    void addNetworkPlayer(INetworkPlayer player);
    
    /**
     * 플레이어의 연결을 제거합니다.
     * 
     * @param playerId 제거할 플레이어의 ID
     */
    void removeNetworkPlayer(String playerId);
    
    /**
     * 현재 연결된 플레이어 수를 반환합니다.
     * 
     * @return 연결된 플레이어 수
     */
    int getConnectedPlayerCount();
}