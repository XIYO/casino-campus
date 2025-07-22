package hand;

import card.Card;
import java.util.List;

/**
 * 플레이어의 손패를 나타내는 인터페이스
 * 
 * 구현 요구사항:
 * 1. 카드를 추가할 수 있어야 합니다
 * 2. 카드를 제거할 수 있어야 합니다
 * 3. 현재 손에 있는 카드들을 확인할 수 있어야 합니다
 * 4. 손에 있는 카드 수를 확인할 수 있어야 합니다
 */
public interface Hand {
    
    /**
     * 손에 카드를 추가합니다
     * @param card 추가할 카드
     * @throws IllegalArgumentException card가 null일 때
     */
    void addCard(Card card);
    
    /**
     * 손에 여러 카드를 한 번에 추가합니다
     * @param cards 추가할 카드들
     * @throws IllegalArgumentException cards가 null이거나 null을 포함할 때
     */
    void addCards(List<Card> cards);
    
    /**
     * 손에서 특정 카드를 제거합니다
     * @param card 제거할 카드
     * @return 카드가 제거되면 true, 없으면 false
     */
    boolean removeCard(Card card);
    
    /**
     * 손에서 특정 위치의 카드를 제거합니다
     * @param index 제거할 카드의 위치 (0부터 시작)
     * @return 제거된 카드
     * @throws IndexOutOfBoundsException 잘못된 인덱스일 때
     */
    Card removeCardAt(int index);
    
    /**
     * 손에 있는 모든 카드를 반환합니다
     * @return 카드 리스트의 복사본 (수정해도 원본에 영향 없음)
     */
    List<Card> getCards();
    
    /**
     * 손에 있는 카드 수를 반환합니다
     * @return 카드 수
     */
    int getCardCount();
    
    /**
     * 손이 비어있는지 확인합니다
     * @return 비어있으면 true
     */
    boolean isEmpty();
    
    /**
     * 특정 카드가 손에 있는지 확인합니다
     * @param card 확인할 카드
     * @return 카드가 있으면 true
     */
    boolean contains(Card card);
    
    /**
     * 손에 있는 모든 카드를 제거합니다
     */
    void clear();
    
    /**
     * 손에 있는 카드들을 정렬합니다
     * 무늬 순서: SPADE < HEART < DIAMOND < CLUB
     * 같은 무늬 내에서는 숫자 순서로 정렬
     */
    void sort();
    
    /**
     * 손패를 문자열로 표현합니다
     * @return 예: "[♠A, ♥10, ♦K]"
     */
    String toString();
}