package card;

/**
 * 카드 한 장을 나타내는 인터페이스
 * 
 * 구현 요구사항:
 * 1. 카드는 무늬(Suit)와 숫자(Rank)를 가져야 합니다
 * 2. 카드는 불변(immutable) 객체여야 합니다
 * 3. 같은 무늬와 숫자를 가진 카드는 동일한 것으로 간주되어야 합니다
 */
public interface Card {
    
    /**
     * 카드의 무늬를 반환합니다
     * @return 카드의 무늬 (SPADE, HEART, DIAMOND, CLUB)
     */
    Suit getSuit();
    
    /**
     * 카드의 숫자를 반환합니다
     * @return 카드의 숫자 (2-10, J, Q, K, A)
     */
    Rank getRank();
    
    /**
     * 카드의 숫자값을 반환합니다
     * @return 2-10은 그대로, J=11, Q=12, K=13, A=14
     */
    int getValue();
    
    /**
     * 카드를 문자열로 표현합니다
     * @return 예: "♠A", "♥10", "♦K"
     */
    String toString();
    
    /**
     * 다른 카드와 같은지 비교합니다
     * @param obj 비교할 객체
     * @return 같은 무늬와 숫자를 가지면 true
     */
    boolean equals(Object obj);
    
    /**
     * 해시코드를 반환합니다
     * @return 무늬와 숫자를 기반으로 한 해시코드
     */
    int hashCode();
}