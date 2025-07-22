package deck;

import card.Card;
import java.util.List;

/**
 * 카드 덱을 나타내는 인터페이스
 * 
 * 구현 요구사항:
 * 1. 52장의 카드로 구성된 표준 덱을 생성할 수 있어야 합니다
 * 2. 카드를 섞을 수 있어야 합니다
 * 3. 카드를 한 장씩 뽑을 수 있어야 합니다
 * 4. 남은 카드 수를 확인할 수 있어야 합니다
 */
public interface Deck {
    
    /**
     * 덱을 섞습니다
     * 카드의 순서를 무작위로 변경합니다
     */
    void shuffle();
    
    /**
     * 덱에서 카드를 한 장 뽑습니다
     * @return 뽑은 카드
     * @throws IllegalStateException 덱이 비어있을 때
     */
    Card drawCard();
    
    /**
     * 덱에서 여러 장의 카드를 뽑습니다
     * @param count 뽑을 카드 수
     * @return 뽑은 카드들의 리스트
     * @throws IllegalArgumentException count가 남은 카드 수보다 많을 때
     * @throws IllegalStateException 덱이 비어있을 때
     */
    List<Card> drawCards(int count);
    
    /**
     * 덱에 남은 카드 수를 반환합니다
     * @return 남은 카드 수
     */
    int getRemainingCards();
    
    /**
     * 덱이 비어있는지 확인합니다
     * @return 덱이 비어있으면 true
     */
    boolean isEmpty();
    
    /**
     * 덱을 초기 상태로 리셋합니다
     * 52장의 카드를 모두 다시 넣습니다
     */
    void reset();
    
    /**
     * 특정 카드가 덱에 남아있는지 확인합니다
     * @param card 확인할 카드
     * @return 카드가 덱에 있으면 true
     */
    boolean contains(Card card);
}