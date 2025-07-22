package game.components.card;

/**
 * 카드의 랭크(숫자/문자)를 나타내는 열거형
 * 
 * 표준 플레잉 카드의 13가지 랭크를 정의합니다.
 * 각 랭크는 고유한 값과 심볼을 가집니다.
 * 
 * <p>랭크 순서 (낮은 것부터 높은 것까지):</p>
 * <ol>
 *   <li>TWO (2) - 가장 낮은 카드</li>
 *   <li>THREE (3)</li>
 *   <li>FOUR (4)</li>
 *   <li>FIVE (5)</li>
 *   <li>SIX (6)</li>
 *   <li>SEVEN (7)</li>
 *   <li>EIGHT (8)</li>
 *   <li>NINE (9)</li>
 *   <li>TEN (10)</li>
 *   <li>JACK (11) - J</li>
 *   <li>QUEEN (12) - Q</li>
 *   <li>KING (13) - K</li>
 *   <li>ACE (14) - A, 가장 높은 카드</li>
 * </ol>
 * 
 * <p>참고: 일부 게임에서는 ACE를 1로 사용하기도 합니다.</p>
 * 
 * @author XIYO
 * @version 1.0
 * @since 2024-01-01
 */
public enum Rank {
    /**
     * 2 - 가장 낮은 랭크
     */
    TWO(2, "2"),
    
    /**
     * 3
     */
    THREE(3, "3"),
    
    /**
     * 4
     */
    FOUR(4, "4"),
    
    /**
     * 5
     */
    FIVE(5, "5"),
    
    /**
     * 6
     */
    SIX(6, "6"),
    
    /**
     * 7
     */
    SEVEN(7, "7"),
    
    /**
     * 8
     */
    EIGHT(8, "8"),
    
    /**
     * 9
     */
    NINE(9, "9"),
    
    /**
     * 10
     */
    TEN(10, "10"),
    
    /**
     * Jack - 잭 (J), 값은 11
     */
    JACK(11, "J"),
    
    /**
     * Queen - 퀸 (Q), 값은 12
     */
    QUEEN(12, "Q"),
    
    /**
     * King - 킹 (K), 값은 13
     */
    KING(13, "K"),
    
    /**
     * Ace - 에이스 (A), 값은 14 (가장 높은 카드)
     */
    ACE(14, "A");
    
    private final int value;
    private final String symbol;
    
    /**
     * Rank 열거형의 생성자
     * 
     * @param value 랭크의 숫자 값 (2-14)
     * @param symbol 랭크의 심볼 (2-10, J, Q, K, A)
     */
    Rank(int value, String symbol) {
        this.value = value;
        this.symbol = symbol;
    }
    
    /**
     * 랭크의 숫자 값을 반환합니다.
     * 
     * @return 랭크의 숫자 값 (2-14)
     */
    public int getValue() {
        return value;
    }
    
    /**
     * 랭크의 심볼을 반환합니다.
     * 
     * @return 랭크의 심볼 (예: "A", "K", "10")
     */
    public String getSymbol() {
        return symbol;
    }
    
    /**
     * 랭크를 문자열로 표현합니다.
     * 기본적으로 영문 이름을 반환합니다.
     * 
     * @return 랭크의 영문 이름 (예: "ACE", "KING")
     */
    @Override
    public String toString() {
        return name();
    }
}