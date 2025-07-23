package game.management.casino;

import game.participants.player.Player;

import java.util.List;

/**
 * 카지노 메인 클래스
 * 기본 로컬 게임 실행을 담당합니다.
 * 
 * 구현이 필요한 메서드:
 * - main() 메서드: 게임 실행 로직
 * - printFinalResults() 메서드: 최종 결과 출력
 */
public class Casino {
    private static final int INITIAL_MONEY = 10000;
    private static final int PRIZE_AMOUNT = 100;
    private static final int TOTAL_ROUNDS = 100;
    private static final int PLAYER_COUNT = 4;
    
    private static final String[] PLAYER_NAMES = {
        "럭키가이", "포커마스터", "초보자", "불운한자"
    };
    
    public static void main(String[] args) {
        System.out.println("🎰 라스베가스 드림 카지노 🎰");
        System.out.println("════════════════════════════════════════");
        System.out.println("게임을 시작합니다!");
        System.out.println("플레이어 수: " + PLAYER_COUNT + "명");
        System.out.println("총 라운드: " + TOTAL_ROUNDS + "라운드");
        System.out.println("초기 자금: " + INITIAL_MONEY + "원");
        System.out.println("라운드당 상금: " + PRIZE_AMOUNT + "원\n");
        
        // TODO: 구현하세요
        // 
        // 🎯 구현 순서:
        // 1. 플레이어 리스트 생성하기
        //    - ArrayList를 사용하여 Player 타입의 리스트를 생성해야 합니다
        //    - import java.util.ArrayList; 가 필요합니다
        // 
        // 2. 4명의 플레이어 생성하기
        //    - PLAYER_NAMES 배열에 있는 이름을 사용합니다
        //    - 각 플레이어는 초기 자금 INITIAL_MONEY(10000원)를 가집니다
        //    - Player 클래스의 생성자는 (이름, 초기자금) 형태입니다
        //    - for-each 문을 사용하면 배열의 각 이름을 쉽게 처리할 수 있습니다
        // 
        // 3. 딜러 객체 생성하기
        //    - Dealer 클래스를 import 해야 합니다
        //    - 딜러는 게임을 진행하는 역할을 합니다
        // 
        // 4. 게임 진행하기
        //    - dealer의 playGame 메서드를 호출합니다
        //    - 매개변수로 플레이어 리스트와 총 라운드 수(TOTAL_ROUNDS)를 전달합니다
        //    - 이 메서드가 100라운드의 게임을 자동으로 진행합니다
        // 
        // 5. 최종 결과 출력하기
        //    - printFinalResults 메서드를 호출하여 게임 결과를 표시합니다
        //    - 이 메서드는 아래에 정의되어 있습니다
        
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * 최종 결과를 출력합니다.
     * 
     * @param players 플레이어 목록
     */
    private static void printFinalResults(List<? extends Player> players) {
        System.out.println("\n🎰 라스베가스 드림 카지노 - 베타 테스트 결과 🎰");
        System.out.println("════════════════════════════════════════");
        
        // TODO: 구현하세요
        // 
        // 🎯 구현 순서:
        // 1. 정렬을 위한 새로운 리스트 생성
        //    - 원본 리스트를 변경하지 않기 위해 새로운 ArrayList를 만듭니다
        //    - 생성자에 players를 전달하면 복사본이 만들어집니다
        // 
        // 2. 플레이어를 자금 기준으로 정렬
        //    - List의 sort 메서드를 사용합니다
        //    - 람다식을 사용하여 정렬 기준을 정의합니다: (p1, p2) -> ...
        //    - 각 플레이어의 getMoney() 메서드로 현재 자금을 확인합니다
        //    - Integer.compare(p2.getMoney(), p1.getMoney())로 내림차순 정렬
        //    - 주의: p2를 먼저 쓰면 내림차순, p1을 먼저 쓰면 오름차순입니다
        // 
        // 3. 메달 배열 준비
        //    - 1위: 🥇, 2위: 🥈, 3위: 🥉, 4위: 😢
        //    - String 배열로 메달들을 저장합니다
        // 
        // 4. 순위별로 결과 출력
        //    - for문을 사용하여 정렬된 플레이어들을 순회합니다
        //    - 인덱스 i를 사용하여 메달과 순위를 결정합니다
        //    - System.out.printf를 사용하여 포맷팅된 출력을 만듭니다
        //    - 출력 형식: "메달 순위: 이름 - 자금 (승 패 무)"
        //    - Player 클래스의 메서드들:
        //      * getName(): 플레이어 이름
        //      * getMoney(): 현재 보유 자금
        //      * getWinCount(): 승리 횟수
        //      * getLoseCount(): 패배 횟수
        //      * getDrawCount(): 무승부 횟수
        //    - %,d는 숫자에 천 단위 구분자를 추가합니다 (예: 10,000)
        // 
        // 5. 추가 통계 정보 출력 (선택사항)
        //    - 총 라운드 수, 총 상금 등을 추가로 출력하면 좋습니다
        
        throw new UnsupportedOperationException("Not implemented yet");
    }
}