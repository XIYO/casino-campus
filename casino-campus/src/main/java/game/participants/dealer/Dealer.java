package game.participants.dealer;

import game.components.deck.Deck;
import game.components.deck.IDeck;
import game.participants.player.IPlayer;

import java.util.List;

/**
 * 딜러 클래스의 기본 구현
 * IDealer 인터페이스를 구현합니다.
 * 
 * 구현이 필요한 메서드:
 * - startNewGame() 메서드: 새 게임 시작
 * - dealCards() 메서드: 카드 분배
 * - determineWinners() 메서드: 승자 판정
 * - distributePrize() 메서드: 상금 분배
 * - playGame() 메서드: 전체 게임 진행
 */
public class Dealer implements IDealer {
    private IDeck deck;
    private static final int CARDS_PER_PLAYER = 5;
    private static final int PRIZE_PER_ROUND = 100;
    
    /**
     * Dealer 생성자
     */
    public Dealer() {
        this.deck = new Deck();
    }
    
    @Override
    public void startNewGame() {
        // TODO: 구현하세요
        // 힌트:
        // 1. deck.reset()으로 덱 초기화
        // 2. deck.shuffle()로 카드 섞기
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public void dealCards(List<? extends IPlayer> players) {
        // TODO: 구현하세요
        // 힌트:
        // 1. 각 플레이어의 핸드를 clear()
        // 2. 각 플레이어에게 CARDS_PER_PLAYER장씩 분배
        // 3. deck.drawCard()를 사용하여 카드를 뽑고
        // 4. player.getHand().addCard()로 추가
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public List<? extends IPlayer> determineWinners(List<? extends IPlayer> players) {
        // TODO: 구현하세요
        // 힌트:
        // 1. 최고 점수를 가진 플레이어들을 찾기
        // 2. List<IPlayer> winners = new ArrayList<>();
        // 3. 각 플레이어의 hand.evaluateHand()로 핸드 순위 확인
        // 4. 가장 높은 점수를 가진 플레이어들을 winners에 추가
        // 5. 동점자가 있을 수 있음을 고려
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public void distributePrize(List<? extends IPlayer> winners, int prizeAmount) {
        // TODO: 구현하세요
        // 힌트:
        // 1. 각 승자에게 prizeAmount만큼 돈 추가
        // 2. winner.addMoney(prizeAmount) 사용
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public void playGame(List<? extends IPlayer> players, int rounds) {
        if (players == null || players.isEmpty()) {
            throw new IllegalArgumentException("플레이어가 없습니다.");
        }
        if (rounds <= 0) {
            throw new IllegalArgumentException("라운드 수는 양수여야 합니다.");
        }
        
        // TODO: 구현하세요
        // 힌트:
        // for (int round = 1; round <= rounds; round++) {
        //     1. startNewGame()으로 새 게임 시작
        //     2. dealCards(players)로 카드 분배
        //     3. List<? extends IPlayer> winners = determineWinners(players)
        //     4. distributePrize(winners, PRIZE_PER_ROUND)
        //     5. 기록 업데이트:
        //        - 승자들: winner.recordWin()
        //        - 패자들: loser.recordLose()
        //        - 동점자가 여러명이면: 모두 recordDraw()
        // }
        throw new UnsupportedOperationException("Not implemented yet");
    }
}