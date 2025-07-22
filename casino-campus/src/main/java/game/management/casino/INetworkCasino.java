package casino;

import network.NetworkModeType;

/**
 * 네트워크 기능이 있는 카지노 게임의 메인 인터페이스
 * 호스트 모드와 클라이언트 모드로 실행할 수 있습니다.
 */
public interface INetworkCasino {
    /**
     * 카지노 게임을 시작합니다.
     * 
     * @param mode 네트워크 모드 (HOST 또는 CLIENT)
     * @param playerName 플레이어 이름
     * @throws Exception 게임 시작 실패 시 예외 발생
     */
    void start(NetworkModeType mode, String playerName) throws Exception;
    
    /**
     * 호스트 모드로 게임을 시작합니다.
     * 
     * @param port 서버 포트 번호
     * @param maxPlayers 최대 플레이어 수
     * @throws Exception 서버 시작 실패 시 예외 발생
     */
    void startAsHost(int port, int maxPlayers) throws Exception;
    
    /**
     * 클라이언트 모드로 게임에 참가합니다.
     * 
     * @param hostAddress 호스트 주소
     * @param port 호스트 포트 번호
     * @throws Exception 연결 실패 시 예외 발생
     */
    void joinAsClient(String hostAddress, int port) throws Exception;
    
    /**
     * 게임을 종료합니다.
     */
    void stop();
    
    /**
     * 현재 게임 상태를 반환합니다.
     * 
     * @return 게임 상태
     */
    GameState getGameState();
    
    /**
     * 현재 연결된 플레이어 수를 반환합니다.
     * 
     * @return 연결된 플레이어 수
     */
    int getConnectedPlayerCount();
}