package game.components.deck;

import game.components.card.ICard;

import java.util.List;

/**
 * 카드 덱을 나타내는 인터페이스
 * 
 * 이 인터페이스는 카드 게임에서 사용되는 카드 덱의 기본 동작을 정의합니다.
 * 표준 덱은 52장의 카드(4개 무늬 × 13개 랭크)로 구성됩니다.
 * 
 * <p>구현 요구사항:</p>
 * <ul>
 *   <li>새 덱은 52장의 카드를 모두 포함해야 합니다</li>
 *   <li>카드를 뽑으면 덱에서 제거되어야 합니다</li>
 *   <li>셔플은 카드의 순서를 무작위로 섞어야 합니다</li>
 *   <li>한 번 사용한 덱은 폐기하고 새 덱을 생성해야 합니다</li>
 *   <li>적절한 예외 처리를 해야 합니다</li>
 * </ul>
 * 
 * <p>카지노 실무 규칙:</p>
 * <ul>
 *   <li>매 게임마다 새로운 덱 사용</li>
 *   <li>사용한 카드는 재사용하지 않음 (보안 및 공정성)</li>
 *   <li>카드 카운팅 방지를 위해 여러 덱을 함께 사용</li>
 *   <li>플라스틱 및 봉인된 새 덱 사용</li>
 * </ul>
 * 
 * <p>사용 예시:</p>
 * <pre>
 * // 매 게임마다 새 덱 생성
 * IDeck deck = new StandardDeck();
 * deck.shuffle();
 * 
 * // 게임 진행
 * ICard card = deck.drawCard();
 * List&lt;ICard&gt; hand = deck.drawCards(5);
 * 
 * // 게임 종료 후 덱은 폐기
 * // 다음 게임을 위해 새 덱 생성 필요
 * deck = new StandardDeck();
 * deck.shuffle();
 * </pre>
 * 
 * @author XIYO
 * @version 1.0
 * @since 2024-01-01
 */
public interface IDeck {
    
    /**
     * 덱을 섞습니다.
     * 
     * 카드의 순서를 무작위로 변경합니다.
     * 셔플 후에도 덱의 카드 수는 변하지 않습니다.
     * 
     * <p>구현 힌트:</p>
     * <ul>
     *   <li>Collections.shuffle() 메서드를 활용할 수 있습니다</li>
     *   <li>Fisher-Yates 알고리즘을 직접 구현할 수도 있습니다</li>
     * </ul>
     * 
     * <p>카지노 규칙:</p>
     * 새로운 덱은 사용 전에 반드시 섞어야 합니다.
     */
    void shuffle();
    
    /**
     * 덱에서 카드를 한 장 뽑습니다.
     * 
     * 덱의 맨 위에서 카드를 한 장 뽑아 반환합니다.
     * 뽑은 카드는 덱에서 제거됩니다.
     * 
     * @return 뽑은 카드
     * @throws IllegalStateException 덱이 비어있을 때
     */
    ICard drawCard();
    
    /**
     * 덱에서 여러 장의 카드를 뽑습니다.
     * 
     * 지정한 수만큼 카드를 뽑아 리스트로 반환합니다.
     * 뽑은 카드들은 덱에서 제거됩니다.
     * 
     * @param count 뽑을 카드 수 (양수여야 함)
     * @return 뽑은 카드들의 리스트
     * @throws IllegalArgumentException count가 0 이하이거나 남은 카드 수보다 많을 때
     * @throws IllegalStateException 덱이 비어있을 때
     */
    List<ICard> drawCards(int count);
    
    /**
     * 덱에 남은 카드 수를 반환합니다.
     * 
     * @return 남은 카드 수 (0-52)
     */
    int getRemainingCards();
    
    /**
     * 덱이 비어있는지 확인합니다.
     * 
     * @return 덱이 비어있으면 true, 카드가 하나라도 있으면 false
     */
    boolean isEmpty();
    
    /**
     * 특정 카드가 덱에 남아있는지 확인합니다.
     * 
     * @param card 확인할 카드
     * @return 카드가 덱에 있으면 true, 없으면 false
     * @throws IllegalArgumentException card가 null일 때
     */
    boolean contains(ICard card);
}