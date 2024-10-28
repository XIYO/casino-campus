package player;

import common.Card;
import common.HandValue;

import java.util.*;

public class Player {
    private static final Set<String> nickNames = new HashSet<>(); // 닉네임 중복 확인을 위한 데이터 저장소

    private final String nickName; // 이름
    private final SortedSet<Card> hand; // 패, 의도적으로, 정렬된 데이터란것을 알리기 위해 SortedSet을 사용
    private int money; // 총 획득 상금

    private int wins; // 전적, 총 승리 횟수
    private int lose; // 전적, 총 패배 횟수
    private HandValue handValue;

    public static Player newPlayer(String nickName) {
        return new Player(nickName);
    }

    private Player(String nickName) {
        // 규칙4, 닉네임은 고유해야한다.
        if (nickNames.contains(nickName))
            throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");

        // 규칙4, 닉네임의 길이는 20자를 넘기지 못한다.
        if (nickName.length() > 20)
            throw new IllegalArgumentException("닉네임은 20자 이하여야 합니다.");

        hand = new TreeSet<>(); // SortedSet 의 구현체 TreeSet 를 생성
        handValue = HandValue.HIGH_CARD; // 기본값은 하이카드, 쓰레기 값

        this.nickName = nickName;
        this.money = 10_000; // 일만
        
        nickNames.add(nickName); // 중복 데이터에 등록
    }

    public String getNickName() {
        return nickName;
    }

    public int getMoney() {
        return money;
    }

    public int getWins() {
        return wins;
    }

    public int getLose() {
        return lose;
    }

    public void win() {
        wins++;
    }

    public void lose() {
        lose++;
    }

    public void prizeMoney(int prize) {
        this.money += prize;
    }

    public SortedSet<Card> openHand() {
        return hand;
    }

    public void receiveCard(Card card) {
        hand.add(card);
    }

    public void dropHands() {
        hand.clear();
    }

    // 1. 승리 횟수 기준 내림차순, 패배 횟수 오름차순, 닉네임 오름차순 정렬 Comparator
    public static final Comparator<Player> WIN_COUNT_ORDER = new WinCountComparator();

    private static class WinCountComparator implements Comparator<Player> {
        @Override
        public int compare(Player p1, Player p2) {
            int winsCompare = Integer.compare(p2.wins, p1.wins); // 승리 내림차순
            int loseCompare = Integer.compare(p1.lose, p2.lose); // 패배 오름차순
            int nickNameCompare = String.CASE_INSENSITIVE_ORDER.compare(p1.nickName, p2.nickName); // 닉네임 오름차순

            if (winsCompare != 0) return winsCompare;
            if (loseCompare != 0) return loseCompare;
            return nickNameCompare;
        }
    }

    // 2. 핸드 밸류 기준 정렬 Comparator (내림차순)
    public static final Comparator<Player> HAND_VALUE_ORDER = new HandValueComparator();

    private static class HandValueComparator implements Comparator<Player> {
        @Override
        public int compare(Player p1, Player p2) {
            return p1.handValue.compareTo(p2.handValue); // 핸드 밸류 내림차순
        }
    }

    public void setHandValue(HandValue handValue) {
        this.handValue = handValue;
    }

    public HandValue getHandValue() {
        return handValue;
    }

    public String toString() {
        return nickName;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Player player = (Player) obj;
        return nickName.equals(player.nickName);
    }

    public int hashCode() {
        return Objects.hash(nickName);
    }
}
