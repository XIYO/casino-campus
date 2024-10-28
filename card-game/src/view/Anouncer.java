package view;

import common.Hand;
import player.Player;

import java.util.List;
import java.util.Optional;

public class Anouncer {
    public static void matchWinner(Player player) {
        String nickName = player.getNickName();
        Hand hand = player.openHand();
        System.out.println(nickName + "님이 " + hand.toDetailString() + "로 승리하셨습니다.");
    }

    public static void draw() {
        System.out.println("무승부 입니다.");
    }

    public static void stageWinner(Optional<Player> totalStageWinner) {
        if (totalStageWinner.isPresent()) {
            Player player = totalStageWinner.get();

            String message = String.format("%s님이 최종 승리하셨습니다. 상금 %d원, 전적 %d승 %d패",
                    player.getNickName(), player.getPoint(), player.getWins(), player.getLose());

            System.out.println(message);
        } else {
            System.out.println("무승부 입니다.");
        }
    }

    private static final String[] medals = {"🥇", "🥈", "🥉", "💩"};
    public static void showStageResult(List<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);

            String message = String.format("%s %s님의 상금 %d원, 전적 %d승 %d패",
                    medals[i], player.getNickName(), player.getPoint(), player.getWins(), player.getLose());

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
