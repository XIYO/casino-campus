package game.components.card;

/**
 * 카드 한 장을 나타내는 인터페이스
 * 
 * 이 인터페이스는 카드 게임에서 사용되는 카드 한 장의 기본 동작을 정의합니다.
 * 모든 카드는 무늬(Suit)와 랭크(Rank)를 가지며, 불변(immutable) 객체로 구현되어야 합니다.
 * 
 * <p>구현 요구사항:</p>
 * <ul>
 *   <li>카드는 생성 후 상태가 변경되지 않아야 합니다 (불변 객체)</li>
 *   <li>같은 무늬와 랭크를 가진 카드는 동일한 것으로 간주되어야 합니다</li>
 *   <li>equals()와 hashCode()는 일관성 있게 구현되어야 합니다</li>
 *   <li>toString()은 사람이 읽기 쉬운 형태로 카드를 표현해야 합니다</li>
 * </ul>
 * 
 * <p>사용 예시:</p>
 * <pre>
 * ICard card = new PlayingCard(Suit.HEARTS, Rank.ACE);
 * System.out.println(card);  // "A♥" 출력
 * System.out.println(card.getValue());  // 14 출력
 * </pre>
 * 
 * @author KDT Campus
 * @version 1.0
 * @since 2024-01-01
 */
public interface ICard extends Comparable<ICard> {
    
    /**
     * 카드의 무늬를 반환합니다.
     * 
     * @return 카드의 무늬 (SPADES, HEARTS, DIAMONDS, CLUBS 중 하나)
     */
    Suit getSuit();
    
    /**
     * 카드의 랭크를 반환합니다.
     * 
     * @return 카드의 랭크 (TWO부터 ACE까지)
     */
    Rank getRank();
    
    /**
     * 카드의 숫자 값을 반환합니다.
     * 
     * <p>값 규칙:</p>
     * <ul>
     *   <li>숫자 카드 (2-10): 해당 숫자 그대로</li>
     *   <li>JACK: 11</li>
     *   <li>QUEEN: 12</li>
     *   <li>KING: 13</li>
     *   <li>ACE: 14 (가장 높은 값)</li>
     * </ul>
     * 
     * <p>참고: 게임에 따라 ACE의 값이 1일 수도 있습니다. 
     * 이 인터페이스에서는 ACE를 가장 높은 카드로 취급합니다.</p>
     * 
     * @return 카드의 숫자 값 (2-14)
     */
    int getValue();
    
    /**
     * 카드를 문자열로 표현합니다.
     * 
     * <p>표현 형식:</p>
     * <ul>
     *   <li>랭크 심볼 + 무늬 기호</li>
     *   <li>예: "A♠", "10♥", "K♦", "2♣"</li>
     * </ul>
     * 
     * @return 카드의 문자열 표현
     */
    @Override
    String toString();
    
    /**
     * 다른 객체와 이 카드가 같은지 비교합니다.
     * 
     * <p>두 카드가 같으려면:</p>
     * <ul>
     *   <li>같은 무늬(Suit)를 가져야 합니다</li>
     *   <li>같은 랭크(Rank)를 가져야 합니다</li>
     * </ul>
     * 
     * @param obj 비교할 객체
     * @return 같은 무늬와 랭크를 가지면 true, 그렇지 않으면 false
     */
    @Override
    boolean equals(Object obj);
    
    /**
     * 카드의 해시 코드를 반환합니다.
     * 
     * <p>해시 코드는 equals()와 일관성을 유지해야 합니다:</p>
     * <ul>
     *   <li>두 카드가 equals()로 같다면, hashCode()도 같아야 합니다</li>
     *   <li>무늬와 랭크를 기반으로 해시 코드를 생성해야 합니다</li>
     * </ul>
     * 
     * @return 카드의 해시 코드
     */
    @Override
    int hashCode();
}