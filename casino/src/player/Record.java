package player;

public class Record {
    private int wins;
    private int losses;

    public Record() {
        this.wins = 0;
        this.losses = 0;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public void incrementWins() {
        this.wins++;
    }

    public void incrementLosses() {
        this.losses++;
    }

    @Override
    public String toString() {
        return "Wins: " + wins + ", Losses: " + losses;
    }
}