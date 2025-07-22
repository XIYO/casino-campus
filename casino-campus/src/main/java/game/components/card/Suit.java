package game.components.card;

/**
 * 카드의 무늬를 나타내는 열거형
 * 
 * 표준 플레잉 카드의 4가지 무늬를 정의합니다.
 * 각 무늬는 고유한 기호와 한글 이름을 가집니다.
 * 
 * <p>무늬 순서 (일반적인 게임에서의 우선순위):</p>
 * <ol>
 *   <li>SPADES (♠) - 스페이드</li>
 *   <li>HEARTS (♥) - 하트</li>
 *   <li>DIAMONDS (♦) - 다이아몬드</li>
 *   <li>CLUBS (♣) - 클럽</li>
 * </ol>
 * 
 * @author XIYO
 * @version 1.0
 * @since 2024-01-01
 */
public enum Suit {
    /**
     * 스페이드 (♠)
     * 검은색 무늬로, 창 모양을 나타냅니다.
     */
    SPADES("♠", "스페이드"),
    
    /**
     * 하트 (♥)
     * 빨간색 무늬로, 하트 모양을 나타냅니다.
     */
    HEARTS("♥", "하트"),
    
    /**
     * 다이아몬드 (♦)
     * 빨간색 무늬로, 다이아몬드 모양을 나타냅니다.
     */
    DIAMONDS("♦", "다이아몬드"),
    
    /**
     * 클럽 (♣)
     * 검은색 무늬로, 클로버 모양을 나타냅니다.
     */
    CLUBS("♣", "클럽");
    
    private final String symbol;
    private final String koreanName;
    
    /**
     * Suit 열거형의 생성자
     * 
     * @param symbol 무늬 기호 (♠, ♥, ♦, ♣)
     * @param koreanName 무늬의 한글 이름
     */
    Suit(String symbol, String koreanName) {
        this.symbol = symbol;
        this.koreanName = koreanName;
    }
    
    /**
     * 무늬의 기호를 반환합니다.
     * 
     * @return 무늬 기호 (예: "♠")
     */
    public String getSymbol() {
        return symbol;
    }
    
    /**
     * 무늬의 한글 이름을 반환합니다.
     * 
     * @return 무늬의 한글 이름 (예: "스페이드")
     */
    public String getKoreanName() {
        return koreanName;
    }
    
    /**
     * 무늬를 문자열로 표현합니다.
     * 기본적으로 영문 이름을 반환합니다.
     * 
     * @return 무늬의 영문 이름 (예: "SPADES")
     */
    @Override
    public String toString() {
        return name();
    }
}