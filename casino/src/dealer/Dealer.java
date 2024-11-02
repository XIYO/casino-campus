package dealer;

import common.Card;
import common.Hand;
import player.Player;

import java.util.*;

public class Dealer {
    public static final int MAX_PLAYER = 4;
    public static final int MIN_PLAYER = 2;
    public static final int MAX_CARD = 5;
    public static final int PRIZE_POINT = 100;

    // 각 플레이어에게 카드를 나눠주는 메서드 이름
    private Deck deck;
    private final List<Player> players;
    private final List<Player> winsHistory;
    private final List<Map<String, String>> matchHistory;

    private boolean isNewDeck;
    private boolean isShuffle = false;

    public static Dealer newDealer() {
        System.out.println("🎩 딜러가 입장하였습니다."); // 딜러 입장
        return new Dealer();
    }

    public Dealer() {
        this.players = new ArrayList<>();
        this.winsHistory = new ArrayList<>();
        this.matchHistory = new ArrayList<>();
    }

    /**
     * 새로운 게임을 시작시 덱을 교체합니다.
     */
    public void newGame() {
        deck = Deck.newDeck();
        isNewDeck = true;
        System.out.println("🎲 새로운 게임을 시작합니다."); // 새 게임 시작
    }

    public void enrollPlayer(Player player) {
        if (this.players.size() > Dealer.MAX_PLAYER) {
            String message = "⚠️ 플레이어는 " + Dealer.MAX_PLAYER + "명까지만 가능합니다.";
            throw new IllegalStateException(message);
        }

        this.players.add(player);
        System.out.println("👋🏻 " + player.getNickName() + "님이 참가하셨습니다."); // 플레이어 입장
    }

    public void dealCard() {
        if (this.players.size() < Dealer.MIN_PLAYER) {
            String message = "⚠️ 플레이어가 " + Dealer.MIN_PLAYER + "명 이상이어야 합니다.";
            throw new IllegalStateException(message);
        }

        if (!isNewDeck) {
            String message = "⚠️ 덱이 준비되지 않았습니다. newGame() 메서드를 호출하세요.";
            throw new IllegalStateException(message);
        }

        if (!isShuffle) {
            String message = "⚠️ 덱이 섞이지 않았습니다. shuffle() 메서드를 호출하세요.";
            throw new IllegalStateException(message);
        }

        // 돌아가면서 한장씩 총 5개의 카드를 나눠준다.
        for (int i = 0; i < Dealer.MAX_CARD; i++) {
            for (Player player : this.players) {
                Card drawnCard = deck.drawCard();
                player.receiveCard(drawnCard);
            }
        }

        System.out.println("🃏 카드를 나눠주었습니다."); // 카드 배분 완료
        isNewDeck = false;
    }

    public void handOpen() {
        System.out.println("🔍 모든 플레이어의 패를 오픈합니다."); // 패 오픈

        // 00. 모든 플레이어의 패를 오픈한다.
        this.players.forEach(Player::openHand);

        // 01. 각 플레이어의 패를 확인하고 순위를 결정한다.
        this.players.sort(Player.HAND_ORDER); // 패 점수가 높은 순으로 정렬한다.

        // 02. 승자를 결정한다.
        Iterator<Player> iterator = this.players.iterator();
        Player highestPlayer = iterator.next(); // 반드시 존재하기 떄문에 null 체크는 하지 않는다.
        Player nextPlayer = iterator.next(); // 두번째 플레이어도 무조건 존재한다.

        // 1등이 하이카드라면 비교할 필요도 없이 승자는 없다.
        Player winningPlayer;
        if (highestPlayer.getHand().getTier() == Hand.Tier.HIGH_CARD) {
            winningPlayer = null;
        }
        else if (highestPlayer.getHand().compareTo(nextPlayer.getHand()) == 0) {
            winningPlayer = null;
        } else {
            winningPlayer = highestPlayer;
        }

        // 03. 기록 남기기, 승자가 없다면 null을 기록한다.
        this.winsHistory.add(winningPlayer);

        // 04. 승자에게 상금을 주고, 패자에게는 패배 횟수를 기록한다.
        Map<String, String> matchRecord = new HashMap<>();
        for (Player player : this.players) {
            if (player.equals(winningPlayer)) {
                player.prizePoint(Dealer.PRIZE_POINT);
                player.win();
            } else if (winningPlayer == null) {
                player.draw();
            } else {
                player.lose();
            }
            matchRecord.put(player.toString(), player.getHand().toString());
        }
        this.matchHistory.add(matchRecord);
    }

    public Map<String, String> getLatestMatch() {
        return this.matchHistory.getLast();
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
            player.dropHand();
        System.out.println("♻️ 모든 플레이어의 카드를 수거했습니다."); // 카드 수거 완료
    }

    public void shuffle() {
        isShuffle = true;
        deck.shuffle();
        System.out.println("🔄 카드를 섞었습니다."); // 카드 셔플 완료
    }
}
