import dealer.Dealer;
import player.Player;
import announcer.Announcer;

public class Casino {
    public static void main(String[] args) {
        // 🎩 딜러 입장
        Dealer dealer = Announcer.enrollDealer(Dealer::newDealer);

        // 👥 플레이어 입장
        Announcer.standbyStage(() -> {
            Announcer.enrollPlayer(dealer.enrollPlayer(Player.newPlayer("고니")));
            Announcer.enrollPlayer(dealer.enrollPlayer(Player.newPlayer("평경장")));
            Announcer.enrollPlayer(dealer.enrollPlayer(Player.newPlayer("짝귀")));
            Announcer.enrollPlayer(dealer.enrollPlayer(Player.newPlayer("아귀")));
        });

        // 💀 포커 100판 진행
        Announcer.playStage(() -> {
            dealer.runDeathGame(() -> {
                Announcer.newGame(dealer::newGame); // 🎲 새로운 게임을 시작한다
                Announcer.cardShuffle(dealer::shuffle); // 🔄 카드를 섞는다
                Announcer.dealCard(dealer::dealCard); // 🃏 카드를 나눠준다
                Announcer.handOpen(dealer::handOpen); // 👀 카드를 오픈한다
                Announcer.matchResult(dealer.getLatestMatch()); // 📊 매치 결과를 출력한다
                Announcer.openWinner(dealer.getLastMatchWinner()); // 🏆 매치 승자를 발표한다
                Announcer.endGame(dealer::retrieveCard); // 🔚 게임을 종료한다
            });
        });

        // 🏁 스테이지 결과 발표
        Announcer.endStage(() -> {
            Announcer.stageWinner(dealer.getTotalStageWinner()); // 🏆 스테이지 승자를 발표한다
            Announcer.showStageResult(dealer.getPlayers()); // 📈 스테이지 결과 출력
        });
    }
}
