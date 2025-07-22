package network;

/**
 * 네트워크 게임 모드의 타입을 정의하는 열거형
 */
public enum NetworkModeType {
    /**
     * 호스트 모드: 게임 방을 열고 다른 플레이어들의 접속을 기다립니다.
     * 딜러 역할을 자동으로 수행합니다.
     */
    HOST("호스트"),
    
    /**
     * 클라이언트 모드: 호스트가 연 게임 방에 참가합니다.
     */
    CLIENT("클라이언트");
    
    private final String displayName;
    
    NetworkModeType(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}