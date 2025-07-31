package game.participants.dealer;

import game.participants.player.Player;
import java.util.List;

/**
 * 딜러의 기본 동작을 정의하는 인터페이스
 * 
 * 이 인터페이스는 카드 게임에서 딜러의 기본 동작을 정의합니다.
 * 딜러는 게임을 진행하고, 카드를 나누고, 승자를 결정하는 역할을 합니다.
 * 모든 딜러 구현체는 이 인터페이스를 구현해야 합니다.
 * 
 * @author XIYO
 * @version 1.1
 * @since 2024-01-01
 */
public interface IDealer {
    /**
     * 새로운 게임을 시작합니다.
     * 덱을 초기화하고 셔플합니다.
     */
    void startNewGame();
    
    /**
     * 플레이어들에게 카드를 분배합니다.
     * 
     * @param players 카드를 받을 플레이어 목록
     */
    void dealCards(List<? extends Player> players);
    
    /**
     * 라운드의 승자를 결정합니다.
     * 
     * @param players 참가 플레이어 목록
     * @return 승자 목록 (동점일 경우 여러 명)
     */
    List<? extends Player> determineWinners(List<? extends Player> players);
    
    /**
     * 승자들에게 상금을 분배합니다.
     * 
     * @param winners 승자 목록
     * @param prizeAmount 각 승자가 받을 상금
     */
    void distributePrize(List<? extends Player> winners, int prizeAmount);
    
    /**
     * 전체 게임을 진행합니다.
     * 
     * @param players 참가 플레이어 목록
     * @param rounds 진행할 라운드 수
     */
    void playGame(List<? extends Player> players, int rounds);
}