package game.components.hand;

import game.components.card.Card;
import game.components.card.ICard;
import game.components.card.Rank;
import game.components.card.Suit;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * 플레이어의 손패를 나타내는 클래스
 * 
 * 이 클래스는 카드 게임에서 플레이어가 들고 있는 카드들(손패)의 관리 기능을 정의합니다.
 * 손패는 가변적이며, 게임 진행에 따라 카드를 추가하거나 제거할 수 있습니다.
 * 
 * <p>구현 요구사항:</p>
 * <ul>
 *   <li>카드를 추가할 수 있어야 합니다</li>
 *   <li>전체 패를 버릴 수 있어야 합니다 (clear)</li>
 *   <li>카드 수에 제한이 없어야 합니다 (게임 규칙은 별도로 처리)</li>
 *   <li>카드 리스트의 순서를 유지해야 합니다</li>
 *   <li>getCards()는 방어적 복사본을 반환해야 합니다</li>
 *   <li>null 카드는 허용하지 않아야 합니다</li>
 * </ul>
 * 
 * <p>사용 예시:</p>
 * <pre>
 * Hand hand = new Hand();
 * hand.add(card1);
 * hand.add(card2);
 * hand.clear();  // 패를 버리고 새로 시작
 * hand.add(card3);
 * </pre>
 * 
 * 구현이 필요한 메서드:
 * - evaluate() 메서드: 포커 족보 판정
 * - open() 메서드: 패를 공개하고 점수 반환
 * - compareTo() 메서드: 핸드 비교
 * - 각종 족보 판정 헬퍼 메서드들
 * 
 * @author XIYO
 * @version 1.1
 * @since 2024-01-01
 */
public class Hand implements IHand {
    private List<Card> cards  = new ArrayList<>();
    private static final int MAX_CARDS = 5;
    
    /**
     * 손패에 카드를 추가합니다.
     * 
     * 카드는 손패의 끝에 추가됩니다.
     * 
     * @param card 추가할 카드
     * @throws IllegalArgumentException card가 null일 때
     */
    public void add(ICard card) {
        if (card == null) {
            throw new IllegalArgumentException("카드는 null일 수 없습니다.");
        }
        if (isFull()) {
            throw new IllegalStateException("핸드는 최대 " + MAX_CARDS + "장까지만 가질 수 있습니다.");
        }
        cards.add((Card) card);
    }
    
    /**
     * 손패에 있는 모든 카드를 반환합니다.
     * 
     * 반환되는 리스트는 수정할 수 없는 읽기 전용 리스트입니다.
     * 원본 손패를 보호하기 위해 변경이 불가능한 리스트를 반환합니다.
     * 
     * @return 수정 불가능한 카드 리스트 (빈 손패일 경우 빈 리스트)
     */
    public List<ICard> getCards() {
        return List.copyOf(cards);
    }
    
    /**
     * 손패가 가득 찼는지 확인합니다.
     * 
     * @return 카드가 5장이면 true, 아니면 false
     */
    public boolean isFull() {
        return cards.size() == MAX_CARDS;
    }
    
    
    /**
     * 손패를 정리합니다.
     * 
     * 현재 들고 있는 모든 카드를 버리고 빈 손이 됩니다.
     * 새로운 게임을 시작하거나 패를 교체할 때 사용합니다.
     */
    public void clear() {
        cards.clear();
    }
    
    
    /**
     * 손패를 문자열로 표현합니다.
     * 
     * 형식: "[카드1, 카드2, ..., 카드N]"
     * 빈 손패의 경우: "[]"
     * 
     * @return 손패의 문자열 표현
     */
    @Override
    public String toString() {
        return cards.toString();
    }
    
    /**
     * 손패의 포커 순위를 평가합니다.
     * 
     * 5장의 카드로 이루어진 손패를 평가하여 포커 족보를 반환합니다.
     * 카드가 5장이 아닌 경우 예외를 발생시킵니다.
     * 
     * @return 평가된 포커 족보
     * @throws IllegalStateException 카드가 정확히 5장이 아닐 때
     */
    public HandRank evaluate() {
        if (cards.size() != 5) {
            throw new IllegalStateException("핸드는 정확히 5장이어야 평가할 수 있습니다.");
        }
        
        // 높은 족보부터 차례대로 확인
        if (isRoyalFlush()) return HandRank.ROYAL_FLUSH;
        if (isStraightFlush()) return HandRank.STRAIGHT_FLUSH;
        if (isFourOfAKind()) return HandRank.FOUR_OF_A_KIND;
        if (isFullHouse()) return HandRank.FULL_HOUSE;
        if (isFlush()) return HandRank.FLUSH;
        if (isStraight()) return HandRank.STRAIGHT;
        if (isThreeOfAKind()) return HandRank.THREE_OF_A_KIND;
        if (isTwoPair()) return HandRank.TWO_PAIR;
        if (isOnePair()) return HandRank.ONE_PAIR;
        
        return HandRank.HIGH_CARD;
    }
    
    /**
     * 손패를 공개하고 포커 점수를 반환합니다.
     * 
     * 현재 손패의 포커 족보를 평가하고 그에 해당하는 점수를 반환합니다.
     * 카드가 5장이 아닌 경우 예외를 발생시킵니다.
     * 
     * @return 포커 족보의 점수 (높을수록 강한 패)
     * @throws IllegalStateException 카드가 정확히 5장이 아닐 때
     */
    public int open() {
        return evaluate().getScore();
    }
    
    public int compareTo(IHand other) {
        return Integer.compare(this.open(), other.open());
    }
    
    // ===== 헬퍼 메서드들 =====
    
    /**
     * 로열 플러시인지 확인
     * @return 로열 플러시이면 true
     */
    private boolean isRoyalFlush() {
        throw new UnsupportedOperationException("TODO: 로열 플러시 판정을 구현하세요. 먼저 isFlush()로 플러시인지 확인한 후, 10-J-Q-K-A 조합인지 확인하세요. Set<Rank>를 사용하여 필수 랭크(TEN, JACK, QUEEN, KING, ACE)와 현재 카드의 랭크를 비교하세요.");
    }
    
    /**
     * 스트레이트 플러시인지 확인
     * @return 스트레이트 플러시이면 true
     */
    private boolean isStraightFlush() {
        throw new UnsupportedOperationException("TODO: 스트레이트 플러시 판정을 구현하세요. 플러시이면서 동시에 스트레이트인 경우입니다. isFlush() && isStraight() 조건을 모두 만족해야 합니다.");
    }
    
    /**
     * 포카드인지 확인
     * @return 포카드이면 true
     */
    private boolean isFourOfAKind() {
        Map<Rank, Integer> counts = getRankCounts();
        return counts.containsValue(4);
    }
    
    /**
     * 풀하우스인지 확인
     * @return 풀하우스이면 true
     */
    private boolean isFullHouse() {
        throw new UnsupportedOperationException("TODO: 풀하우스 판정을 구현하세요. 같은 랭크 3장과 다른 랭크 2장의 조합입니다. getRankCounts()를 사용하여 랭크별 개수를 구한 후, 3개와 2개 값이 모두 존재하는지 확인하세요(containsValue(3) && containsValue(2)).");
    }
    
    /**
     * 플러시인지 확인
     * @return 플러시이면 true
     */
    private boolean isFlush() {
        Suit firstSuit = cards.get(0).getSuit();
        for (Card card : cards) {
            if (card.getSuit() != firstSuit) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 스트레이트인지 확인
     * @return 스트레이트이면 true
     */
    private boolean isStraight() {
        throw new UnsupportedOperationException("TODO: 스트레이트 판정을 구현하세요. 연속된 5장의 카드입니다. 카드 값들을 정렬한 후 연속 여부를 확인하세요. 특별히 A-2-3-4-5(백스트레이트) 케이스도 처리해야 합니다. Collections.sort()로 정렬 후 인접한 값들의 차이가 1인지 확인하고, 백스트레이트는 [2,3,4,5,14] 패턴을 체크하세요.");
    }
    
    /**
     * 쓰리카드인지 확인
     * @return 쓰리카드이면 true
     */
    private boolean isThreeOfAKind() {
        throw new UnsupportedOperationException("TODO: 쓰리카드 판정을 구현하세요. 같은 랭크의 카드가 3장 있는지 확인합니다. getRankCounts()로 랭크별 개수를 구한 후, 3개의 값이 존재하는지 확인하세요(containsValue(3)).");
    }
    
    /**
     * 투페어인지 확인
     * @return 투페어이면 true
     */
    private boolean isTwoPair() {
        throw new UnsupportedOperationException("TODO: 투페어 판정을 구현하세요. 서로 다른 랭크의 페어가 2개 있는지 확인합니다. getRankCounts()로 랭크별 개수를 구한 후, 값이 2인 랭크가 정확히 2개 있는지 확인하세요. for문으로 counts.values()를 순회하며 count==2인 경우를 세어주세요.");
    }
    
    /**
     * 원페어인지 확인
     * @return 원페어이면 true
     */
    private boolean isOnePair() {
        Map<Rank, Integer> counts = getRankCounts();
        return counts.containsValue(2);
    }
    
    /**
     * 각 랭크별 카드 개수를 계산
     * @return 랭크별 카드 개수 맵
     */
    private Map<Rank, Integer> getRankCounts() {
        Map<Rank, Integer> counts = new EnumMap<>(Rank.class);
        for (Card card : cards) {
            counts.put(card.getRank(), counts.getOrDefault(card.getRank(), 0) + 1);
        }
        return counts;
    }
}