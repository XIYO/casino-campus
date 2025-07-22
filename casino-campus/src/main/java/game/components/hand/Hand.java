package game.components.hand;

import game.components.card.ICard;
import game.components.card.Rank;

import java.util.*;

/**
 * 핸드 클래스의 기본 구현
 * IHand 인터페이스를 구현합니다.
 * 
 * 구현이 필요한 메서드:
 * - evaluateHand() 메서드: 포커 족보 판정
 * - compareTo() 메서드: 핸드 비교
 * - 각종 족보 판정 헬퍼 메서드들
 */
public class Hand implements IHand {
    private List<ICard> cards;
    private static final int MAX_CARDS = 5;
    
    /**
     * Hand 생성자
     */
    public Hand() {
        this.cards = new ArrayList<>();
    }
    
    @Override
    public void addCard(ICard card) {
        if (card == null) {
            throw new IllegalArgumentException("카드는 null일 수 없습니다.");
        }
        if (cards.size() >= MAX_CARDS) {
            throw new IllegalStateException("핸드는 최대 " + MAX_CARDS + "장까지만 가질 수 있습니다.");
        }
        cards.add(card);
    }
    
    @Override
    public void addCards(List<ICard> cards) {
        if (cards == null) {
            throw new IllegalArgumentException("카드 리스트는 null일 수 없습니다.");
        }
        for (ICard card : cards) {
            addCard(card);
        }
    }
    
    @Override
    public boolean removeCard(ICard card) {
        return cards.remove(card);
    }
    
    @Override
    public ICard removeCardAt(int index) {
        return cards.remove(index);
    }
    
    @Override
    public List<ICard> getCards() {
        return new ArrayList<>(cards); // 방어적 복사
    }
    
    @Override
    public int getCardCount() {
        return cards.size();
    }
    
    @Override
    public boolean isEmpty() {
        return cards.isEmpty();
    }
    
    @Override
    public boolean contains(ICard card) {
        return cards.contains(card);
    }
    
    @Override
    public void clear() {
        cards.clear();
    }
    
    @Override
    public void sort() {
        Collections.sort(cards);
    }
    
    @Override
    public String toString() {
        return cards.toString();
    }
    
    @Override
    public HandRank evaluateHand() {
        if (cards.size() != 5) {
            throw new IllegalStateException("핸드는 정확히 5장이어야 평가할 수 있습니다.");
        }
        
        // TODO: 구현하세요
        // 힌트: 높은 족보부터 차례대로 확인
        // if (isRoyalFlush()) return HandRank.ROYAL_FLUSH;
        // if (isStraightFlush()) return HandRank.STRAIGHT_FLUSH;
        // if (isFourOfAKind()) return HandRank.FOUR_OF_A_KIND;
        // ... 계속 ...
        // return HandRank.HIGH_CARD;
        
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public int compareTo(IHand other) {
        // TODO: 구현하세요
        // 힌트:
        // HandRank myRank = this.evaluateHand();
        // HandRank otherRank = other.evaluateHand();
        // return Integer.compare(myRank.getScore(), otherRank.getScore());
        
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    // ===== 헬퍼 메서드들 =====
    
    /**
     * 로열 플러시인지 확인
     * @return 로열 플러시이면 true
     */
    private boolean isRoyalFlush() {
        // TODO: 구현하세요
        // 힌트: 10, J, Q, K, A가 모두 같은 무늬인지 확인
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * 스트레이트 플러시인지 확인
     * @return 스트레이트 플러시이면 true
     */
    private boolean isStraightFlush() {
        // TODO: 구현하세요
        // 힌트: isFlush() && isStraight()
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * 포카드인지 확인
     * @return 포카드이면 true
     */
    private boolean isFourOfAKind() {
        // TODO: 구현하세요
        // 힌트: 같은 랭크가 4개 있는지 확인
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * 풀하우스인지 확인
     * @return 풀하우스이면 true
     */
    private boolean isFullHouse() {
        // TODO: 구현하세요
        // 힌트: 3개 + 2개 조합인지 확인
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * 플러시인지 확인
     * @return 플러시이면 true
     */
    private boolean isFlush() {
        // TODO: 구현하세요
        // 힌트: 모든 카드가 같은 무늬인지 확인
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * 스트레이트인지 확인
     * @return 스트레이트이면 true
     */
    private boolean isStraight() {
        // TODO: 구현하세요
        // 힌트: 카드를 정렬한 후 연속된 숫자인지 확인
        // 주의: A-2-3-4-5도 스트레이트
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * 쓰리카드인지 확인
     * @return 쓰리카드이면 true
     */
    private boolean isThreeOfAKind() {
        // TODO: 구현하세요
        // 힌트: 같은 랭크가 3개 있는지 확인
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * 투페어인지 확인
     * @return 투페어이면 true
     */
    private boolean isTwoPair() {
        // TODO: 구현하세요
        // 힌트: 페어가 2개 있는지 확인
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * 원페어인지 확인
     * @return 원페어이면 true
     */
    private boolean isOnePair() {
        // TODO: 구현하세요
        // 힌트: 같은 랭크가 2개 있는지 확인
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * 각 랭크별 카드 개수를 계산
     * @return 랭크별 카드 개수 맵
     */
    private Map<Rank, Integer> getRankCounts() {
        Map<Rank, Integer> counts = new HashMap<>();
        for (ICard card : cards) {
            counts.put(card.getRank(), counts.getOrDefault(card.getRank(), 0) + 1);
        }
        return counts;
    }
}