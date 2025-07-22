package network;

/**
 * 네트워크 통신을 위한 인터페이스
 * 호스트와 클라이언트 간의 메시지 송수신을 담당합니다.
 */
public interface INetworkCommunicator {
    /**
     * 메시지를 전송합니다.
     * 
     * @param message 전송할 메시지
     * @throws Exception 전송 실패 시 예외 발생
     */
    void sendMessage(String message) throws Exception;
    
    /**
     * 객체를 전송합니다.
     * 
     * @param object 전송할 객체 (Serializable이어야 함)
     * @throws Exception 전송 실패 시 예외 발생
     */
    void sendObject(Object object) throws Exception;
    
    /**
     * 메시지를 수신합니다.
     * 
     * @return 수신한 메시지
     * @throws Exception 수신 실패 시 예외 발생
     */
    String receiveMessage() throws Exception;
    
    /**
     * 객체를 수신합니다.
     * 
     * @return 수신한 객체
     * @throws Exception 수신 실패 시 예외 발생
     */
    Object receiveObject() throws Exception;
    
    /**
     * 모든 연결된 클라이언트에게 메시지를 브로드캐스트합니다.
     * (호스트 전용 기능)
     * 
     * @param message 브로드캐스트할 메시지
     * @throws Exception 전송 실패 시 예외 발생
     */
    void broadcastMessage(String message) throws Exception;
}