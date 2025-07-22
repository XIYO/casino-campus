package game.components.hand;

import game.components.card.ICard;
import game.components.card.Rank;
import game.components.card.Suit;
import java.util.List;
import java.util.ArrayList;

/**
 * 핸드 클래스의 기본 구현
 * IHand 인터페이스를 구현합니다.
 * 
 * 힌트:
 * - 포커 족보를 판정하는 로직을 구현해야 합니다
 * - 카드 5장을 관리해야 합니다
 * - 각 족보별 판정 메소드를 만들면 도움이 됩니다
 */
public class Hand implements IHand {
    private List<ICard> cards;
    
    public Hand() {
        this.cards = new ArrayList<>();
    }
    
    @Override
    public void addCard(ICard card) {
        // TODO: 구현하세요
        // 힌트: 5장을 초과하면 예외를 던지세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public List<ICard> getCards() {
        // TODO: 구현하세요
        // 힌트: 방어적 복사를 고려하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public void clear() {
        // TODO: 구현하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public HandRank evaluateHand() {
        // TODO: 구현하세요
        // 힌트: 가장 높은 족보부터 순서대로 확인하세요
        // 1. 로열 플러시 확인
        // 2. 스트레이트 플러시 확인
        // 3. 포카드 확인
        // ... 등등
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public int compareTo(IHand other) {
        // TODO: 구현하세요
        // 힌트: HandRank의 점수로 비교하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    // TODO: 각 족보를 판정하는 헬퍼 메소드들을 구현하세요
    // 예시:
    // private boolean isRoyalFlush() { ... }
    // private boolean isStraightFlush() { ... }
    // private boolean isFourOfAKind() { ... }
    // 등등...
}