package game.components.deck;

import game.components.card.CardRef;
import game.components.card.ICard;
import game.components.card.Rank;
import game.components.card.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 덱 참조 구현체 - IDeck 인터페이스의 완성된 구현
 * 
 * 이 클래스는 카드 게임에서 사용되는 표준 52장 덱을 나타내는 완성된 구현체입니다.
 * 4개 무늬 × 13개 랭크로 구성된 완전한 카드 덱을 제공합니다.
 * 
 * <p>주요 기능:</p>
 * <ul>
 *   <li>자동 초기화: 생성 시 52장 카드 자동 생성</li>
 *   <li>랜덤 셔플: Collections.shuffle()을 통한 무작위 섞기</li>
 *   <li>카드 추출: 덱에서 카드를 뽑아 제거</li>
 *   <li>상태 확인: 덱의 빈 상태 확인</li>
 * </ul>
 * 
 * <p>카지노 규칙 준수:</p>
 * <ul>
 *   <li>매 게임마다 새로운 덱 사용</li>
 *   <li>사용한 카드는 재사용하지 않음</li>
 *   <li>공정성과 보안을 위한 완전한 덱 보장</li>
 * </ul>
 * 
 * <p>사용 예시:</p>
 * <pre>
 * DeckRef deck = new DeckRef();  // 52장 자동 생성
 * deck.shuffle();                // 카드 섞기
 * Card card = deck.drawCard();   // 카드 한 장 뽑기
 * boolean empty = deck.isEmpty(); // 빈 덱 확인
 * </pre>
 * 
 * @author XIYO
 * @version 1.1
 * @since 2024-01-01
 */
public class DeckRef implements IDeck {
    
    private final List<ICard> cards = new ArrayList<>();
    
    // 인스턴스 초기화 블록 - 52장의 카드 생성
    {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new CardRef(suit, rank));
            }
        }
    }
    
    /**
     * 덱을 섞습니다.
     * 
     * 카드의 순서를 무작위로 변경합니다.
     * 셔플 후에도 덱의 카드 수는 변하지 않습니다.
     * 
     * <p>카지노 규칙:</p>
     * 새로운 덱은 사용 전에 반드시 섞어야 합니다.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }
    
    /**
     * 덱에서 카드를 한 장 뽑습니다.
     * 
     * 덱의 맨 위에서 카드를 한 장 뽑아 반환합니다.
     * 뽑은 카드는 덱에서 제거됩니다.
     * 
     * @return 뽑은 카드
     * @throws IllegalStateException 덱이 비어있을 때
     */
    public ICard drawCard() {
        if (isEmpty()) {
            throw new IllegalStateException("덱이 비어있습니다.");
        }
        return cards.remove(0);
    }
    
    /**
     * 덱이 비어있는지 확인합니다.
     * 
     * @return 덱이 비어있으면 true, 카드가 하나라도 있으면 false
     */
    public boolean isEmpty() {
        return cards.isEmpty();
    }
}