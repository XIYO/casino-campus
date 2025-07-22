package game.components.card;

/**
 * 카드 클래스의 기본 구현
 * ICard 인터페이스를 구현합니다.
 * 
 * 힌트:
 * - Suit와 Rank를 필드로 가져야 합니다
 * - equals()와 hashCode()를 적절히 구현해야 합니다
 * - compareTo()를 구현하여 카드를 정렬할 수 있어야 합니다
 */
public class Card implements ICard {
    // TODO: 필드를 선언하세요
    
    // TODO: 생성자를 구현하세요
    
    @Override
    public Suit getSuit() {
        // TODO: 구현하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public Rank getRank() {
        // TODO: 구현하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int getValue() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int compareTo(ICard other) {
        // TODO: 구현하세요
        // 힌트: 먼저 Rank로 비교하고, 같으면 Suit로 비교
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public String toString() {
        // TODO: 구현하세요
        // 예: "♠A", "♥K" 형식으로 반환
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    // TODO: equals()와 hashCode()를 구현하세요
}