package game.components.card;

/**
 * 카드의 기본 동작을 정의하는 인터페이스
 * 
 * 이 인터페이스는 카드 게임에서 사용되는 카드 한 장의 기본 동작을 정의합니다.
 * 모든 카드 구현체는 이 인터페이스를 구현해야 합니다.
 * 
 * @author XIYO
 * @version 1.1
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
     * 카드의 포커 게임에서의 순위 값을 반환합니다.
     * 
     * <p>포커에서 카드 강도를 비교하기 위한 숫자 값:</p>
     * <ul>
     *   <li>TWO (2): 2 (가장 약한 카드)</li>
     *   <li>THREE (3): 3</li>
     *   <li>FOUR (4): 4</li>
     *   <li>FIVE (5): 5</li>
     *   <li>SIX (6): 6</li>
     *   <li>SEVEN (7): 7</li>
     *   <li>EIGHT (8): 8</li>
     *   <li>NINE (9): 9</li>
     *   <li>TEN (10): 10</li>
     *   <li>JACK (J): 11</li>
     *   <li>QUEEN (Q): 12</li>
     *   <li>KING (K): 13</li>
     *   <li>ACE (A): 14 (가장 강한 카드)</li>
     * </ul>
     * 
     * <p>이 값은 카드를 비교할 때 사용됩니다. 
     * 예: ACE(14) > KING(13) > QUEEN(12) > ... > TWO(2)</p>
     * 
     * @return 카드의 포커 순위 값 (2-14)
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
    int hashCode();
}