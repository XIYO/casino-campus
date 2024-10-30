package player;

class Record {
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

    /**
     * 값의 증가는 같은 패키지에 있는 클래스에서만 가능하도록 제한합니다.
     */
    void incrementWins() {
        this.wins++;
    }

    /**
     * 값의 증가는 같은 패키지에 있는 클래스에서만 가능하도록 제한합니다.
     */
    void incrementLosses() {
        this.losses++;
    }

    @Override
    public String toString() {
        return "Wins: " + wins + ", Losses: " + losses;
    }
}