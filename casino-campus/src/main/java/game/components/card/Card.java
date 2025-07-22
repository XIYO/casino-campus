package game.components.card;

/**
 * 카드 클래스의 기본 구현
 * ICard 인터페이스를 구현합니다.
 * 
 * 구현이 필요한 메서드:
 * - getValue() 메서드: 카드의 숫자 값 반환
 * - compareTo() 메서드: 카드 비교 로직
 * - toString() 메서드: 카드를 문자열로 표현
 * - equals() 메서드: 두 카드가 같은지 비교
 * - hashCode() 메서드: 해시코드 생성
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
    
    @Override
    public Suit getSuit() {
        return this.suit;
    }
    
    @Override
    public Rank getRank() {
        return this.rank;
    }

    @Override
    public int getValue() {
        // TODO: 구현하세요
        // 힌트: Rank enum이 이미 getValue() 메서드를 제공합니다!
        // return rank.getValue();
        
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int compareTo(ICard other) {
        // TODO: 구현하세요
        // 힌트: 
        // 1. 먼저 Rank로 비교 (enum의 선언 순서대로 비교됨)
        //    int rankCompare = this.rank.compareTo(other.getRank());
        // 2. Rank가 같으면 Suit로 비교
        //    if (rankCompare != 0) return rankCompare;
        //    return this.suit.compareTo(other.getSuit());
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public String toString() {
        // TODO: 구현하세요
        // 힌트: Suit의 getSymbol()과 Rank의 getSymbol()을 조합
        // 예: "A♠", "K♥" 형식
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public boolean equals(Object obj) {
        // TODO: 구현하세요
        // 힌트:
        // 1. null 체크
        // 2. 같은 객체인지 체크
        // 3. Card 타입인지 체크
        // 4. suit와 rank가 모두 같은지 확인
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public int hashCode() {
        // TODO: 구현하세요
        // 힌트: suit와 rank의 hashCode()를 조합
        // Objects.hash(suit, rank) 사용 가능
        throw new UnsupportedOperationException("Not implemented yet");
    }
}