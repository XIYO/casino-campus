package view;

import common.HandValue;
import player.Player;

import java.util.List;
import java.util.Optional;
import java.util.SortedSet;

public class Anouncer {
    public static void matchWinner(Player player) {
        String nickName = player.getNickName();
        HandValue handValue = player.getHandValue();
        System.out.println(nickName + "님이 " + handValue + "로 승리하셨습니다.");
    }

    public static void draw() {
        System.out.println("무승부 입니다.");
    }

    public static void showHand(SortedSet<Player> sortedPlayers) {
        for (Player player : sortedPlayers) {
            String nickName = player.getNickName();
            HandValue handValue = player.getHandValue();
            System.out.println(nickName + "님의 패는 " + handValue + " 입니다.");
        }
    }

    public static void stageWinner(Optional<Player> totalStageWinner) {
        if (totalStageWinner.isPresent()) {
            String nickName = totalStageWinner.get().getNickName();
            int money = totalStageWinner.get().getMoney();
            int wins = totalStageWinner.get().getWins();
            int lose = totalStageWinner.get().getLose();

            String message = String.format("%s님이 최종 승리하셨습니다. 상금 %d원, 전적 %d승 %d패",
                    nickName, money, wins, lose);

            System.out.println(message);

        } else {
            System.out.println("무승부 입니다.");
        }
    }

    private static final String[] medals = {"🥇", "🥈", "🥉", "💩"};
    public static void showStageResult(List<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            String nickName = player.getNickName();
            int money = player.getMoney();
            int wins = player.getWins();
            int lose = player.getLose();

            String message = String.format("%s %s님의 상금 %d원, 전적 %d승 %d패",
                    medals[i], nickName, money, wins, lose);

            System.out.println(message);

        }
    }

    public static void openWinner(Optional<Player> optionalPlayer) {
        if (optionalPlayer.isPresent()) {
            Anouncer.matchWinner(optionalPlayer.get());
        } else {
            Anouncer.draw();
        }
    }
}
