package game.participants.dealer;

import game.participants.player.IPlayer;

import java.util.List;

/**
 * 딜러의 기본 동작을 정의하는 인터페이스입니다.
 * 딜러는 게임을 진행하고, 카드를 나누고, 승자를 결정하는 역할을 합니다.
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
    void dealCards(List<? extends IPlayer> players);
    
    /**
     * 라운드의 승자를 결정합니다.
     * 
     * @param players 참가 플레이어 목록
     * @return 승자 목록 (동점일 경우 여러 명)
     */
    List<? extends IPlayer> determineWinners(List<? extends IPlayer> players);
    
    /**
     * 승자들에게 상금을 분배합니다.
     * 
     * @param winners 승자 목록
     * @param prizeAmount 각 승자가 받을 상금
     */
    void distributePrize(List<? extends IPlayer> winners, int prizeAmount);
    
    /**
     * 전체 게임을 진행합니다.
     * 
     * @param players 참가 플레이어 목록
     * @param rounds 진행할 라운드 수
     */
    void playGame(List<? extends IPlayer> players, int rounds);
}