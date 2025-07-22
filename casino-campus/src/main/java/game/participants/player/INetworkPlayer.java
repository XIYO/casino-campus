package game.participants.player;

import network.INetworkCommunicator;

/**
 * 네트워크 게임에 참여하는 플레이어를 위한 인터페이스
 * 기본 플레이어 기능에 네트워크 통신 기능을 추가합니다.
 */
public interface INetworkPlayer extends IPlayer {
    /**
     * 플레이어의 네트워크 통신 객체를 설정합니다.
     * 
     * @param communicator 네트워크 통신 객체
     */
    void setCommunicator(INetworkCommunicator communicator);
    
    /**
     * 플레이어의 고유 ID를 반환합니다.
     * 네트워크상에서 플레이어를 식별하는데 사용됩니다.
     * 
     * @return 플레이어의 고유 ID
     */
    String getPlayerId();
    
    /**
     * 플레이어가 호스트인지 확인합니다.
     * 
     * @return 호스트이면 true, 아니면 false
     */
    boolean isHost();
    
    /**
     * 네트워크 연결 상태를 확인합니다.
     * 
     * @return 연결되어 있으면 true, 아니면 false
     */
    boolean isConnected();
    
    /**
     * 다른 플레이어에게 메시지를 전송합니다.
     * 
     * @param message 전송할 메시지
     * @throws Exception 전송 실패 시 예외 발생
     */
    void sendMessage(String message) throws Exception;
    
    /**
     * 턴 정보를 네트워크로 전송합니다.
     * 
     * @param turnData 턴 데이터
     * @throws Exception 전송 실패 시 예외 발생
     */
    void sendTurnData(Object turnData) throws Exception;
}