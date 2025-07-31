package game.components.card;

/**
 * 카드 한 장을 나타내는 클래스
 * 
 * 이 클래스는 카드 게임에서 사용되는 카드 한 장의 기본 동작을 정의합니다.
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
 * Card card = new Card(Suit.HEARTS, Rank.ACE);
 * System.out.println(card);  // "A♥" 출력
 * System.out.println(card.getValue());  // 14 출력
 * </pre>
 * 
 * 구현이 필요한 메서드:
 * - getValue(): 카드의 포커 순위 값 반환
 * - compareTo(Card other): 카드 비교
 * - toString(): 카드 문자열 표현
 * - equals(Object obj): 동등성 비교
 * - hashCode(): 해시 코드 생성
 * 
 * @author XIYO
 * @version 1.1
 * @since 2024-01-01
 */
public class Card implements ICard {
    private final Suit suit;
    private final Rank rank;
    
    /**
     * Card 생성자
     * @param suit 카드의 무늬
     * @param rank 카드의 숫자/문자
     */
    public Card(Suit suit, Rank rank) {
        if (suit == null || rank == null) {
            throw new IllegalArgumentException("Suit와 Rank는 null일 수 없습니다.");
        }
        this.suit = suit;
        this.rank = rank;
    }
    
    /**
     * 카드의 무늬를 반환합니다.
     * 
     * @return 카드의 무늬 (SPADES, HEARTS, DIAMONDS, CLUBS 중 하나)
     */
    public Suit getSuit() {
        return this.suit;
    }
    
    /**
     * 카드의 랭크를 반환합니다.
     * 
     * @return 카드의 랭크 (TWO부터 ACE까지)
     */
    public Rank getRank() {
        return this.rank;
    }

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
    public int getValue() {
        throw new UnsupportedOperationException("TODO: 카드의 포커 순위 값을 반환하세요. rank 필드의 getValue() 메서드를 호출하여 2(TWO)부터 14(ACE)까지의 값을 반환합니다");
    }

    @Override
    public int compareTo(ICard other) {
        throw new UnsupportedOperationException("TODO: 카드의 랭크를 먼저 비교하고, 랭크가 같으면 무늬를 비교하세요. this.rank.compareTo(other.getRank())로 랭크 비교 후, 결과가 0이면 this.suit.compareTo(other.getSuit())로 무늬를 비교합니다. this가 더 크면 양수, 작으면 음수, 같으면 0을 반환합니다");
    }
    
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
    public String toString() {
        throw new UnsupportedOperationException("TODO: 카드를 '랭크심볼+무늬기호' 형식의 문자열로 반환하세요. rank.getSymbol() + suit.getSymbol()을 사용하여 'A♠', '10♥', 'K♦' 같은 형태로 만드세요");
    }
    
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
    public boolean equals(Object obj) {
        throw new UnsupportedOperationException("TODO: 객체의 동등성을 비교하세요. 1) obj가 null이면 false 반환, 2) this == obj이면 true 반환, 3) obj instanceof Card가 아니면 false 반환, 4) Card로 캐스팅 후 this.suit.equals(other.suit) && this.rank.equals(other.rank) 결과를 반환하세요");
    }
    
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
    public int hashCode() {
        throw new UnsupportedOperationException("TODO: 해시 코드를 생성하여 반환하세요. java.util.Objects.hash(suit, rank) 메서드를 사용하거나, 31 * suit.hashCode() + rank.hashCode() 수식을 사용하여 suit과 rank 필드를 모두 포함한 해시값을 계산하세요");
    }
}