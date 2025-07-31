package game.components.hand;

import game.components.card.ICard;
import game.components.card.CardRef;
import game.components.card.Rank;
import game.components.card.Suit;

import java.util.*;

/**
 * 핸드 참조 구현체 - IHand 인터페이스의 완성된 구현
 * 
 * 이 클래스는 카드 게임에서 플레이어가 들고 있는 카드들(손패)를 나타내는 완성된 구현체입니다.
 * 최대 5장의 카드를 가질 수 있으며, 포커 족보를 평가할 수 있습니다.
 * 
 * <p>주요 기능:</p>
 * <ul>
 *   <li>카드 관리: 카드 추가, 전체 패 버리기</li>
 *   <li>족보 평가: 로열 플러시부터 하이카드까지 완전한 포커 족보 판정</li>
 *   <li>점수 계산: 족보별 점수를 반환하여 승부 판정 지원</li>
 *   <li>핸드 비교: 다른 핸드와의 강도 비교</li>
 *   <li>방어적 설계: null 카드 방지, 방어적 복사본 반환</li>
 * </ul>
 * 
 * <p>포커 족보 (높은 순서대로):</p>
 * <ul>
 *   <li>로열 플러시: 같은 무늬의 10-J-Q-K-A</li>
 *   <li>스트레이트 플러시: 같은 무늬의 연속된 5장</li>
 *   <li>포카드: 같은 랭크 4장</li>
 *   <li>풀하우스: 3장 + 2장 조합</li>
 *   <li>플러시: 같은 무늬 5장</li>
 *   <li>스트레이트: 연속된 5장 (A-2-3-4-5 백스트레이트 포함)</li>
 *   <li>쓰리카드: 같은 랭크 3장</li>
 *   <li>투페어: 페어 2개</li>
 *   <li>원페어: 페어 1개</li>
 *   <li>하이카드: 위 조건에 해당하지 않는 경우</li>
 * </ul>
 * 
 * <p>사용 예시:</p>
 * <pre>
 * HandRef hand = new HandRef();
 * hand.add(card1);  // 카드 추가
 * hand.add(card2);
 * // ... 5장까지 추가
 * HandRank rank = hand.evaluate();  // 족보 평가
 * int score = hand.open();          // 점수 반환
 * </pre>
 * 
 * @author XIYO
 * @version 1.1
 * @since 2024-01-01
 */
public class HandRef implements IHand {
    private List<ICard> cards  = new ArrayList<>();
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
        cards.add(card);
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
     * 로열 플러시인지 확인합니다.
     * 
     * 로열 플러시는 같은 무늬의 10-J-Q-K-A 조합입니다.
     * 포커에서 가장 강한 패입니다.
     * 
     * @return 로열 플러시이면 true, 아니면 false
     */
    private boolean isRoyalFlush() {
        
        if (!isFlush()) return false;
        
        Set<Rank> requiredRanks = new HashSet<>();
        requiredRanks.add(Rank.TEN);
        requiredRanks.add(Rank.JACK);
        requiredRanks.add(Rank.QUEEN);
        requiredRanks.add(Rank.KING);
        requiredRanks.add(Rank.ACE);
        
        Set<Rank> currentRanks = new HashSet<>();
        for (ICard card : cards) {
            currentRanks.add(card.getRank());
        }
        
        return currentRanks.equals(requiredRanks);
    }
    
    /**
     * 스트레이트 플러시인지 확인합니다.
     * 
     * 스트레이트 플러시는 같은 무늬의 연속된 5장 조합입니다.
     * 로열 플러시를 제외한 두 번째로 강한 패입니다.
     * 
     * @return 스트레이트 플러시이면 true, 아니면 false
     */
    private boolean isStraightFlush() {
        
        return isFlush() && isStraight();
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
     * 풀하우스인지 확인합니다.
     * 
     * 풀하우스는 같은 랭크 3장과 같은 랭크 2장의 조합입니다.
     * 예: AAA22, KKK33
     * 
     * @return 풀하우스이면 true, 아니면 false
     */
    private boolean isFullHouse() {
        
        Map<Rank, Integer> counts = getRankCounts();
        return counts.containsValue(3) && counts.containsValue(2);
    }
    
    /**
     * 플러시인지 확인
     * @return 플러시이면 true
     */
    private boolean isFlush() {
        Suit firstSuit = cards.get(0).getSuit();
        for (ICard card : cards) {
            if (card.getSuit() != firstSuit) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 스트레이트인지 확인합니다.
     * 
     * 스트레이트는 연속된 5장의 카드 조합입니다.
     * 백스트레이트(A-2-3-4-5)도 유효한 스트레이트입니다.
     * 
     * @return 스트레이트이면 true, 아니면 false
     */
    private boolean isStraight() {
        
        List<Integer> values = new ArrayList<>();
        for (ICard card : cards) {
            values.add(card.getValue());
        }
        Collections.sort(values);
        
        // 일반 스트레이트 체크
        boolean isNormalStraight = true;
        for (int i = 0; i < 4; i++) {
            if (values.get(i + 1) - values.get(i) != 1) {
                isNormalStraight = false;
                break;
            }
        }
        
        // 특수 케이스: A-2-3-4-5 (백스트레이트)
        boolean isAceLowStraight = values.equals(Arrays.asList(2, 3, 4, 5, 14));
        
        return isNormalStraight || isAceLowStraight;
    }
    
    /**
     * 쓰리카드인지 확인합니다.
     * 
     * 쓰리카드는 같은 랭크 3장을 가진 조합입니다.
     * 예: AAA45, KKK78
     * 
     * @return 쓰리카드이면 true, 아니면 false
     */
    private boolean isThreeOfAKind() {
        
        Map<Rank, Integer> counts = getRankCounts();
        return counts.containsValue(3);
    }
    
    /**
     * 투페어인지 확인합니다.
     * 
     * 투페어는 서로 다른 랭크의 페어 2개를 가진 조합입니다.
     * 예: AA33K, JJTT5
     * 
     * @return 투페어이면 true, 아니면 false
     */
    private boolean isTwoPair() {
        
        Map<Rank, Integer> counts = getRankCounts();
        int pairCount = 0;
        for (int count : counts.values()) {
            if (count == 2) {
                pairCount++;
            }
        }
        return pairCount == 2;
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
        for (ICard card : cards) {
            counts.put(card.getRank(), counts.getOrDefault(card.getRank(), 0) + 1);
        }
        return counts;
    }
}