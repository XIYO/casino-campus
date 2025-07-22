package card;

/**
 * 카드의 숫자(랭크)를 나타내는 열거형
 */
public enum Rank {
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(11, "J"),
    QUEEN(12, "Q"),
    KING(13, "K"),
    ACE(14, "A");
    
    private final int value;
    private final String symbol;
    
    Rank(int value, String symbol) {
        this.value = value;
        this.symbol = symbol;
    }
    
    /**
     * 카드의 숫자값을 반환합니다
     * @return 2-10은 그대로, J=11, Q=12, K=13, A=14
     */
    public int getValue() {
        return value;
    }
    
    /**
     * 카드의 기호를 반환합니다
     * @return 카드 기호 (2-10, J, Q, K, A)
     */
    public String getSymbol() {
        return symbol;
    }
    
    @Override
    public String toString() {
        return symbol;
    }
}