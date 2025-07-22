package game.participants.dealer;

import game.components.deck.IDeck;
import game.components.deck.Deck;
import game.participants.player.IPlayer;
import game.components.hand.Hand;
import java.util.List;
import java.util.ArrayList;

/**
 * 딜러 클래스의 기본 구현
 * IDealer 인터페이스를 구현합니다.
 * 
 * 힌트:
 * - 덱을 관리해야 합니다
 * - 카드 분배 로직을 구현해야 합니다
 * - 승자 판정 로직을 구현해야 합니다
 */
public class Dealer implements IDealer {
    private IDeck deck;
    
    public Dealer() {
        // TODO: 구현하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public void startNewGame() {
        // TODO: 구현하세요
        // 힌트: 덱을 초기화하고 섞으세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public void dealCards(List<? extends IPlayer> players) {
        // TODO: 구현하세요
        // 힌트: 각 플레이어에게 5장씩 카드를 나누세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public List<? extends IPlayer> determineWinners(List<? extends IPlayer> players) {
        // TODO: 구현하세요
        // 힌트: 각 플레이어의 핸드를 비교하여 최고의 핸드를 가진 플레이어를 찾으세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public void distributePrize(List<? extends IPlayer> winners, int prizeAmount) {
        // TODO: 구현하세요
        // 힌트: 각 승자에게 상금을 분배하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public void playGame(List<? extends IPlayer> players, int rounds) {
        // TODO: 구현하세요
        // 힌트: rounds만큼 게임을 반복하세요
        // 1. startNewGame()
        // 2. dealCards()
        // 3. determineWinners()
        // 4. distributePrize()
        // 5. 플레이어 기록 업데이트
        throw new UnsupportedOperationException("Not implemented yet");
    }
}