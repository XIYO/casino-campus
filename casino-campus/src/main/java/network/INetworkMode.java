package network;

/**
 * 네트워크 게임 모드를 정의하는 인터페이스
 * 호스트 모드와 클라이언트 모드를 구분합니다.
 */
public interface INetworkMode {
    /**
     * 네트워크 연결을 시작합니다.
     * 호스트: 서버를 열고 클라이언트 연결을 기다립니다.
     * 클라이언트: 호스트 서버에 연결을 시도합니다.
     * 
     * @throws Exception 연결 실패 시 예외 발생
     */
    void start() throws Exception;
    
    /**
     * 네트워크 연결을 종료합니다.
     */
    void stop();
    
    /**
     * 현재 연결 상태를 확인합니다.
     * 
     * @return 연결되어 있으면 true, 아니면 false
     */
    boolean isConnected();
    
    /**
     * 현재 모드의 타입을 반환합니다.
     * 
     * @return 네트워크 모드 타입 (HOST 또는 CLIENT)
     */
    NetworkModeType getModeType();
}