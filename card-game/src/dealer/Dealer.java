package dealer;

import common.Card;
import common.HandValue;
import player.Player;

import java.util.*;

public class Dealer {
    public static final int MAX_PLAYER = 4;
    public static final int MIN_PLAYER = 2;
    public static final int MAX_CARD = 5; // 42 / 플레이어 값 보다 작아야합니다. 높다면 에러가 발생합니다.
    public static final int PRIZE_MONEY = 100;

    // 각 플레이어에게 카드를 나눠주는 메서드 이름
    private Deck deck;
    private final List<Player> players;
    private final List<Player> winsHistory;

    private boolean isNewDeck;

    public static Dealer newDealer() {
        return new Dealer();
    }

    private Dealer() {
        this(4, 100);
    }

    public Dealer(int playerCount, int matchCount) {
        this.players = new ArrayList<>(playerCount);
        this.winsHistory = new ArrayList<>(matchCount);
    }

    /**
     * 새로운 게임을 시작시 덱을 교체합니다.
     */
    public void newGame() {
        deck = Deck.newDeck();
        isNewDeck = true;
    }

    public void enrollPlayer(Player player) {
        if (this.players.size() > Dealer.MAX_PLAYER) {
            String message = "플레이어는 " + Dealer.MAX_PLAYER + "명까지만 가능합니다.";
            throw new IllegalStateException(message);
        }

        this.players.add(player);
    }

    public void dealCard() {
        if (this.players.size() < Dealer.MIN_PLAYER) {
            String message = "플레이어가 " + Dealer.MIN_PLAYER + "명 이상이어야 합니다.";
            throw new IllegalStateException(message);
        }

        if (!isNewDeck) {
            String message = "덱이 준비되지 않았습니다. newGame() 메서드를 호출하세요.";
            throw new IllegalStateException(message);
        }

        // 돌아가면서 한장씩 총 5개의 카드를 나눠준다.
        for (int i = 0; i < Dealer.MAX_CARD; i++) {
            for (Player player : this.players) {
                Card drawnCard = deck.drawCard();
                player.receiveCard(drawnCard);
            }
        }

        isNewDeck = false;
    }

    public void cardOpen() {
        Player winningPlayer;

        // 01. 각 플레이어의 패를 확인하고 순위를 결정한다.
        List<Player> playerOrderedHandValue = new ArrayList<>(this.players.size());
        for (Player player : this.players) {
            SortedSet<Card> playerHand = player.openHand();
            // 플레이어가 점수 계산을 하지 않고, 딜러가 점수 계산을 해서 플레이어에게 전달한다.
            HandValue handValue = HandValue.evaluate(playerHand);
            player.setHandValue(handValue);

            // 플레이어의 점수를 기록한다.
            playerOrderedHandValue.add(player);
        }
        // 점수가 높은 순으로 정렬한다.
        playerOrderedHandValue.sort(Player.HAND_VALUE_ORDER);

        // 02. 승자를 결정한다.
        // 포커에서는 우승자 중 동점자가 있으면 그 게임은 무승부가 된다.
        Iterator<Player> iterator = playerOrderedHandValue.iterator();
        Player highestPlayer = iterator.next(); // 반드시 존재하기 떄문에 null 체크는 하지 않는다.
        Player nextPlayer = iterator.next(); // 두번째 플레이어도 무조건 존재한다.
        
        // 1등이 하이카드라면 비교할 필요도 없이 승자는 없다.
        if (highestPlayer.getHandValue().getTier() == HandValue.TierType.HIGH_CARD) {
            winningPlayer = null;
        }
        // 내림차순으로 정렬된 1, 2등이 동점이라면 무승부라서 승자도 없다.
        // 3등까지 가지 않아도 된다. 
        else if (highestPlayer.getHandValue().compareTo(nextPlayer.getHandValue()) == 0) {
            winningPlayer = null;
        } else {
            winningPlayer = highestPlayer;
        }

        // 03. 기록 남기기, 승자가 없다면 null을 기록한다.
        winsHistory.add(winningPlayer);

        // 04. 승자에게 상금을 주고, 패자에게는 패배 횟수를 기록한다.
        for (Player player : this.players) {
            if (player.equals(winningPlayer)) {
                player.prizeMoney(Dealer.PRIZE_MONEY);
                player.win();
            } else {
                player.lose();
            }
        }
    }

    /**
     * 마지막 스테이지의 승자
     */
    public Optional<Player> getLastMatchWinner() {
        Player lastWinner = this.winsHistory.get(this.winsHistory.size() - 1);

        if (lastWinner == null) {
            return Optional.empty();
        }

        return Optional.of(lastWinner);
    }

    /**
     * 게임의 최종 승자
     */
    public Optional<Player> getTotalStageWinner() {
        // 플레이어에서 정의한 승자 정렬 기준으로 최종 승자를 정한다.
        Player winner = Collections.min(this.players, Player.WIN_COUNT_ORDER);
        return Optional.of(winner);
    }

    public List<Player> getPlayers() {
        this.players.sort(Player.WIN_COUNT_ORDER);
        return this.players;
    }

    /**
     * 각 플레이어의 카드를 수거한다.
     */
    public void retrieveCard() {
        for (Player player : this.players)
            player.dropHands();
    }

    public void shuffle() {
        deck.shuffle();
    }
}