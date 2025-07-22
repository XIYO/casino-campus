package game.management.casino;

import network.NetworkModeType;
import game.participants.player.Player;
import game.participants.dealer.Dealer;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 카지노 메인 클래스
 * 기본 로컬 게임 실행을 담당합니다.
 * 
 * 힌트:
 * - 4명의 플레이어로 게임을 진행합니다
 * - 100라운드를 자동으로 진행합니다
 * - 최종 결과를 출력합니다
 */
public class Casino {
    private static final int INITIAL_MONEY = 10000;
    private static final int PRIZE_AMOUNT = 100;
    private static final int TOTAL_ROUNDS = 100;
    private static final int PLAYER_COUNT = 4;
    
    public static void main(String[] args) {
        System.out.println("🎰 라스베가스 드림 카지노 🎰");
        System.out.println("════════════════════════════════════════");
        
        // TODO: 구현하세요
        // 1. 4명의 플레이어를 생성하세요
        // 2. 딜러를 생성하세요
        // 3. 100라운드 게임을 진행하세요
        // 4. 최종 결과를 출력하세요
        
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * 최종 결과를 출력합니다.
     * 
     * @param players 플레이어 목록
     */
    private static void printFinalResults(List<Player> players) {
        // TODO: 구현하세요
        // 힌트: 플레이어들을 자금 순으로 정렬하여 출력하세요
        System.out.println("\n🎰 라스베가스 드림 카지노 - 베타 테스트 결과 🎰");
        System.out.println("════════════════════════════════════════");
        
        // 예시 출력:
        // 🥇 1위: 럭키가이 - 12,700원 (27승 65패 8무)
        // 🥈 2위: 포커마스터 - 12,600원 (26승 66패 8무)
        // 🥉 3위: 초보자 - 12,200원 (22승 70패 8무)
        // 😢 4위: 불운한자 - 11,700원 (17승 75패 8무)
        
        throw new UnsupportedOperationException("Not implemented yet");
    }
}