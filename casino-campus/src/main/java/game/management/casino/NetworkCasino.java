package game.management.casino;

import network.NetworkModeType;
import network.INetworkMode;
import network.INetworkCommunicator;
import game.participants.dealer.NetworkDealer;
import game.participants.player.NetworkPlayer;
import java.util.Scanner;

/**
 * 네트워크 카지노 메인 클래스
 * INetworkCasino 인터페이스를 구현합니다.
 * 
 * 힌트:
 * - 호스트 모드와 클라이언트 모드를 구분해서 처리해야 합니다
 * - 소켓 통신을 사용하여 네트워크 기능을 구현합니다
 */
public class NetworkCasino implements INetworkCasino {
    // TODO: 필드를 선언하세요
    // - GameState
    // - INetworkMode
    // - NetworkDealer (호스트용)
    // - NetworkPlayer (현재 플레이어)
    
    public static void main(String[] args) {
        System.out.println("🎰 라스베가스 드림 카지노 - 네트워크 버전 🎰");
        System.out.println("════════════════════════════════════════");
        
        Scanner scanner = new Scanner(System.in);
        
        // TODO: 구현하세요
        // 1. 사용자에게 모드 선택을 받으세요 (호스트/클라이언트)
        // 2. 플레이어 이름을 입력받으세요
        // 3. 호스트 모드: 포트 번호를 입력받고 서버를 시작하세요
        // 4. 클라이언트 모드: 호스트 주소와 포트를 입력받고 연결하세요
        
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public void start(NetworkModeType mode, String playerName) throws Exception {
        // TODO: 구현하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public void startAsHost(int port, int maxPlayers) throws Exception {
        // TODO: 구현하세요
        // 힌트:
        // 1. ServerSocket을 생성하여 포트를 열어두세요
        // 2. 클라이언트 연결을 기다리세요
        // 3. NetworkDealer를 생성하여 게임을 관리하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public void joinAsClient(String hostAddress, int port) throws Exception {
        // TODO: 구현하세요
        // 힌트:
        // 1. Socket을 사용하여 호스트에 연결하세요
        // 2. NetworkPlayer를 생성하세요
        // 3. 서버로부터 게임 정보를 받아 처리하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public void stop() {
        // TODO: 구현하세요
        // 힌트: 모든 네트워크 연결을 정리하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public GameState getGameState() {
        // TODO: 구현하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public int getConnectedPlayerCount() {
        // TODO: 구현하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
}