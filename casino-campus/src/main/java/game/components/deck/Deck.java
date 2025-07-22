package game.components.deck;

import game.components.card.ICard;
import game.components.card.Card;
import game.components.card.Suit;
import game.components.card.Rank;
import java.util.List;
import java.util.ArrayList;

/**
 * 덱 클래스의 기본 구현
 * IDeck 인터페이스를 구현합니다.
 * 
 * 힌트:
 * - 52장의 카드를 생성해야 합니다
 * - Collections.shuffle()을 사용하여 섞을 수 있습니다
 * - 카드를 뽑을 때마다 덱에서 제거해야 합니다
 */
public class Deck implements IDeck {
    private List<ICard> cards;
    
    public Deck() {
        // TODO: 52장의 카드를 생성하여 cards 리스트에 추가하세요
        // 힌트: Suit.values()와 Rank.values()를 사용하여 모든 조합을 만드세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public void shuffle() {
        // TODO: 구현하세요
        // 힌트: Collections.shuffle(cards)를 사용하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public ICard drawCard() {
        // TODO: 구현하세요
        // 힌트: 카드가 없으면 예외를 던지세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public List<ICard> drawCards(int count) {
        // TODO: 구현하세요
        // 힌트: count만큼 drawCard()를 호출하여 리스트를 만드세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public int getRemainingCards() {
        // TODO: 구현하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public void reset() {
        // TODO: 구현하세요
        // 힌트: 새로운 52장의 카드로 덱을 다시 초기화하세요
        throw new UnsupportedOperationException("Not implemented yet");
    }
}