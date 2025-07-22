package game.components.hand;

import game.components.card.ICard;
import java.util.List;

/**
 * 플레이어의 손패를 나타내는 인터페이스
 * 
 * 이 인터페이스는 카드 게임에서 플레이어가 들고 있는 카드들(손패)의 관리 기능을 정의합니다.
 * 손패는 가변적이며, 게임 진행에 따라 카드를 추가하거나 제거할 수 있습니다.
 * 
 * <p>구현 요구사항:</p>
 * <ul>
 *   <li>카드를 추가/제거할 수 있어야 합니다</li>
 *   <li>카드 수에 제한이 없어야 합니다 (게임 규칙은 별도로 처리)</li>
 *   <li>카드 리스트의 순서를 유지해야 합니다</li>
 *   <li>getCards()는 방어적 복사본을 반환해야 합니다</li>
 *   <li>null 카드는 허용하지 않아야 합니다</li>
 * </ul>
 * 
 * <p>사용 예시:</p>
 * <pre>
 * IHand hand = new PlayerHand();
 * hand.addCard(card1);
 * hand.addCards(Arrays.asList(card2, card3));
 * hand.sort();
 * System.out.println(hand);  // "[2♠, 3♠, A♥]"
 * </pre>
 * 
 * @author KDT Campus
 * @version 1.0
 * @since 2024-01-01
 */
public interface IHand extends Comparable<IHand> {
    
    /**
     * 손패에 카드를 추가합니다.
     * 
     * 카드는 손패의 끝에 추가됩니다.
     * 
     * @param card 추가할 카드
     * @throws IllegalArgumentException card가 null일 때
     */
    void addCard(ICard card);
    
    /**
     * 손패에 여러 카드를 한 번에 추가합니다.
     * 
     * 카드들은 리스트의 순서대로 손패의 끝에 추가됩니다.
     * 
     * @param cards 추가할 카드들의 리스트
     * @throws IllegalArgumentException cards가 null이거나, null 카드를 포함할 때
     */
    void addCards(List<ICard> cards);
    
    /**
     * 손패에서 특정 카드를 제거합니다.
     * 
     * 해당 카드가 여러 장 있을 경우, 첫 번째로 발견된 카드만 제거합니다.
     * 
     * @param card 제거할 카드
     * @return 카드가 제거되면 true, 해당 카드가 없으면 false
     */
    boolean removeCard(ICard card);
    
    /**
     * 손패에서 특정 위치의 카드를 제거합니다.
     * 
     * @param index 제거할 카드의 위치 (0부터 시작)
     * @return 제거된 카드
     * @throws IndexOutOfBoundsException 인덱스가 범위를 벗어날 때
     */
    ICard removeCardAt(int index);
    
    /**
     * 손패에 있는 모든 카드를 반환합니다.
     * 
     * 반환되는 리스트는 방어적 복사본이어야 합니다.
     * 즉, 반환된 리스트를 수정해도 원본 손패에는 영향이 없어야 합니다.
     * 
     * @return 카드 리스트의 복사본 (빈 손패일 경우 빈 리스트)
     */
    List<ICard> getCards();
    
    /**
     * 손패에 있는 카드 수를 반환합니다.
     * 
     * @return 카드 수 (0 이상)
     */
    int getCardCount();
    
    /**
     * 손패가 비어있는지 확인합니다.
     * 
     * @return 손패가 비어있으면 true, 카드가 하나라도 있으면 false
     */
    boolean isEmpty();
    
    /**
     * 특정 카드가 손패에 있는지 확인합니다.
     * 
     * @param card 확인할 카드
     * @return 카드가 있으면 true, 없으면 false
     */
    boolean contains(ICard card);
    
    /**
     * 손패에 있는 모든 카드를 제거합니다.
     * 
     * 이 메서드 호출 후 손패는 빈 상태가 됩니다.
     */
    void clear();
    
    /**
     * 손패의 카드들을 정렬합니다.
     * 
     * <p>정렬 규칙:</p>
     * <ol>
     *   <li>무늬 순서: SPADES < HEARTS < DIAMONDS < CLUBS</li>
     *   <li>같은 무늬 내에서는 랭크 순서: 2 < 3 < ... < K < A</li>
     * </ol>
     * 
     * <p>예시: [A♥, 2♠, K♣, 3♠] → [2♠, 3♠, A♥, K♣]</p>
     */
    void sort();
    
    /**
     * 손패를 문자열로 표현합니다.
     * 
     * 형식: "[카드1, 카드2, ..., 카드N]"
     * 빈 손패의 경우: "[]"
     * 
     * @return 손패의 문자열 표현
     */
    @Override
    String toString();
    
    /**
     * 손패의 포커 핸드 순위를 평가합니다.
     * 
     * 5장의 카드로 이루어진 손패를 평가하여 포커 핸드 순위를 반환합니다.
     * 카드가 5장이 아닌 경우 예외를 발생시킵니다.
     * 
     * @return 평가된 핸드 순위
     * @throws IllegalStateException 카드가 정확히 5장이 아닐 때
     */
    HandRank evaluateHand();
}