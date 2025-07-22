package game.components.hand;

/**
 * 포커 핸드의 순위를 나타내는 열거형
 * 
 * 각 핸드 순위는 고유한 점수를 가지며, 높은 점수일수록 강한 핸드입니다.
 * 
 * @author XIYO
 * @version 1.0
 * @since 2024-01-01
 */
public enum HandRank {
    /**
     * 하이카드 - 아무런 조합이 없는 경우
     * 가장 높은 카드로 승부
     */
    HIGH_CARD(100, "하이카드"),
    
    /**
     * 원페어 - 같은 숫자 2장
     * 예: A♠ A♥ K♦ Q♣ J♠
     */
    ONE_PAIR(200, "원페어"),
    
    /**
     * 투페어 - 같은 숫자 2장씩 2쌍
     * 예: K♠ K♥ Q♦ Q♣ J♠
     */
    TWO_PAIR(300, "투페어"),
    
    /**
     * 쓰리카드 - 같은 숫자 3장
     * 예: 7♠ 7♥ 7♦ K♣ Q♠
     */
    THREE_OF_A_KIND(400, "쓰리카드"),
    
    /**
     * 스트레이트 - 연속된 숫자 5장
     * 예: 5♠ 6♥ 7♦ 8♣ 9♠
     */
    STRAIGHT(500, "스트레이트"),
    
    /**
     * 플러시 - 같은 무늬 5장
     * 예: A♦ K♦ Q♦ J♦ 9♦
     */
    FLUSH(600, "플러시"),
    
    /**
     * 풀하우스 - 쓰리카드 + 원페어
     * 예: K♠ K♥ K♦ Q♣ Q♠
     */
    FULL_HOUSE(700, "풀하우스"),
    
    /**
     * 포카드 - 같은 숫자 4장
     * 예: 9♠ 9♥ 9♦ 9♣ K♠
     */
    FOUR_OF_A_KIND(800, "포카드"),
    
    /**
     * 스트레이트 플러시 - 같은 무늬의 연속된 숫자 5장
     * 예: 5♥ 6♥ 7♥ 8♥ 9♥
     */
    STRAIGHT_FLUSH(900, "스트레이트 플러시"),
    
    /**
     * 로열 플러시 - 10, J, Q, K, A의 스트레이트 플러시
     * 예: 10♠ J♠ Q♠ K♠ A♠
     */
    ROYAL_FLUSH(1000, "로열 플러시");
    
    private final int score;
    private final String koreanName;
    
    /**
     * HandRank 생성자
     * 
     * @param score 핸드의 점수
     * @param koreanName 한글 이름
     */
    HandRank(int score, String koreanName) {
        this.score = score;
        this.koreanName = koreanName;
    }
    
    /**
     * 핸드의 점수를 반환합니다.
     * 
     * @return 핸드의 점수
     */
    public int getScore() {
        return score;
    }
    
    /**
     * 핸드의 한글 이름을 반환합니다.
     * 
     * @return 한글 이름
     */
    public String getKoreanName() {
        return koreanName;
    }
    
    /**
     * 핸드의 문자열 표현을 반환합니다.
     * 
     * @return 핸드의 한글 이름
     */
    @Override
    public String toString() {
        return koreanName;
    }
}