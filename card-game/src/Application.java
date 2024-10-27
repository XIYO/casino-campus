import dealer.Dealer;
import player.Player;
import view.Anouncer;

import java.util.Optional;

public class Application {
    public static void main(String[] args) {
        // 딜러 생성
        Dealer dealer = Dealer.newDealer();

        // 딜러가 플레이어들을 등록한다.
        dealer.enrollPlayer(Player.newPlayer("고니"));
        dealer.enrollPlayer(Player.newPlayer("평경장"));
        dealer.enrollPlayer(Player.newPlayer("짝귀"));
        dealer.enrollPlayer(Player.newPlayer("아귀"));

        // 포커 100판 진행 💀
        for (int i = 0; i < 100; i++) {
            // 0. 새로운 게임을 시작한다. (덱을 준비한다)
            dealer.newGame();

            // 1. 딜러가 카드를 나눠준다.
            dealer.dealCard();

            // 2. 딜러가 모든 플레이어의 카드를 오픈한다.
            dealer.cardOpen();

            // 3. 매치 승자 확인
            Optional<Player> optionalPlayer = dealer.getLastMatchWinner();
            Anouncer.openWinner(optionalPlayer);

            // 4. 게임이 끝나서 카드를 수거한다.
            dealer.retrieveCard();
        }

        // 5. 100판이 끝나면 최종 승자를 발표한다.
        Anouncer.stageWinner(dealer.getTotalStageWinner());

        // 6. 게임이 끝나면 플레이어들의 전적을 출력한다.
        Anouncer.showStageResult(dealer.getPlayers());
    }
}