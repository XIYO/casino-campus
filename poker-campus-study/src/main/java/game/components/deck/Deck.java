package game.components.deck;

import game.components.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 카드 덱을 나타내는 클래스
 * 
 * 이 클래스는 카드 게임에서 사용되는 카드 덱의 기본 동작을 정의합니다.
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
 * Deck deck = new Deck();
 * deck.shuffle();
 * 
 * // 게임 진행
 * Card card = deck.drawCard();
 * 
 * // 게임 종료 후 덱은 폐기
 * // 다음 게임을 위해 새 덱 생성 필요
 * deck = new Deck();
 * deck.shuffle();
 * </pre>
 * 
 * 구현이 필요한 부분:
 * - cards 필드 초기화: 52장의 카드 생성
 * - shuffle() 메서드: 카드 섞기
 * - drawCard() 메서드: 카드 한 장 뽑기
 * - isEmpty() 메서드: 덱이 비어있는지 확인
 * 
 * @author XIYO
 * @version 1.1
 * @since 2024-01-01
 */
public class Deck implements IDeck {
    // TODO: 구현하세요 - 인스턴스 초기화 블록 추가
    // 
    // 🎯 구현 순서:
    // 1. 인스턴스 초기화 블록을 만들어야 합니다
    //    - 초기화 블록은 { }로 감싸진 코드 영역입니다
    //    - 클래스 내부에 필드 선언 아래에 작성합니다
    //    - 예: { /* 여기에 초기화 코드 작성 */ }
    // 
    // 2. 52장의 카드를 생성하는 로직
    //    - 바깥 반복문: Suit.values()로 모든 무늬 순회 (SPADES, HEARTS, DIAMONDS, CLUBS)
    //    - 안쪽 반복문: Rank.values()로 모든 랭크 순회 (TWO부터 ACE까지)
    //    - 각 조합에 대해 new Card(suit, rank)로 카드 생성
    //    - cards.add()로 리스트에 추가
    // 
    // 3. 세부 구현 힌트
    //    - for-each 문법 사용: for (Suit suit : Suit.values())
    //    - 각 무늬마다 13장의 카드가 생성되어야 합니다
    //    - 총 4 × 13 = 52장이 만들어집니다
    // 
    // 테스트 실패 시 확인사항:
    // - "덱은 52장의 카드를 가져야 합니다" 에러: 반복문이 잘못되었거나 추가를 빼먹었습니다
    // - "빈 컬렉션입니다" 경고: 초기화 블록을 만들지 않았습니다
    // - NullPointerException: Card 생성자에 null을 전달했습니다
    
    private final List<Card> cards = new ArrayList<>();
    
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
        throw new UnsupportedOperationException("TODO: Collections.shuffle(cards)를 사용하여 덱의 카드들을 무작위로 섞으세요. " +
                "java.util.Collections 클래스를 import하고 Collections.shuffle() 메서드에 cards 리스트를 전달하면 됩니다. " +
                "이 메서드는 카드의 순서만 바꾸고 카드 수는 변경하지 않습니다.");
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
    public Card drawCard() {
        throw new UnsupportedOperationException("TODO: 1) isEmpty()로 덱이 비어있는지 확인하고, 비어있으면 IllegalStateException(\"덱이 비어있습니다\")을 던지세요. " +
                "2) 덱에 카드가 있으면 cards.remove(0)를 사용해 첫 번째 카드를 제거하고 반환하세요. " +
                "또는 cards.remove(cards.size()-1)로 마지막 카드를 제거해도 됩니다.");
    }
    
    /**
     * 덱이 비어있는지 확인합니다.
     * 
     * @return 덱이 비어있으면 true, 카드가 하나라도 있으면 false
     */
    public boolean isEmpty() {
        throw new UnsupportedOperationException("TODO: cards.isEmpty()를 호출하여 카드 리스트가 비어있는지 확인하고 그 결과를 반환하세요. " +
                "List의 isEmpty() 메서드는 리스트에 요소가 없으면 true, 하나라도 있으면 false를 반환합니다.");
    }
}