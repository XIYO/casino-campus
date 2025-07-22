package card;

/**
 * 카드의 무늬를 나타내는 열거형
 */
public enum Suit {
    SPADE("♠", "스페이드"),
    HEART("♥", "하트"),
    DIAMOND("♦", "다이아몬드"),
    CLUB("♣", "클럽");
    
    private final String symbol;
    private final String koreanName;
    
    Suit(String symbol, String koreanName) {
        this.symbol = symbol;
        this.koreanName = koreanName;
    }
    
    /**
     * 무늬의 기호를 반환합니다
     * @return 무늬 기호 (♠, ♥, ♦, ♣)
     */
    public String getSymbol() {
        return symbol;
    }
    
    /**
     * 무늬의 한글 이름을 반환합니다
     * @return 무늬의 한글 이름
     */
    public String getKoreanName() {
        return koreanName;
    }
    
    @Override
    public String toString() {
        return symbol;
    }
}