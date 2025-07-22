package game.components.deck;

import game.components.card.ICard;

import java.util.ArrayList;
import java.util.List;

/**
 * 덱 클래스의 기본 구현
 * IDeck 인터페이스를 구현합니다.
 * 
 * 구현이 필요한 메서드:
 * - initializeDeck() 메서드: 52장의 카드 생성
 * - shuffle() 메서드: 카드 섞기
 * - drawCard() 메서드: 카드 한 장 뽑기
 * - drawCards() 메서드: 여러 장 뽑기
 * - reset() 메서드: 덱 초기화
 */
public class Deck implements IDeck {
    private List<ICard> cards;
    
    /**
     * Deck 생성자
     * 52장의 표준 플레잉 카드로 덱을 초기화합니다.
     */
    public Deck() {
        this.cards = new ArrayList<>();
        initializeDeck();
    }
    
    /**
     * 52장의 카드로 덱을 초기화합니다.
     */
    private void initializeDeck() {
        // TODO: 구현하세요
        // 힌트:
        // for (Suit suit : Suit.values()) {
        //     for (Rank rank : Rank.values()) {
        //         cards.add(new Card(suit, rank));
        //     }
        // }
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public void shuffle() {
        // TODO: 구현하세요
        // 힌트: Collections.shuffle(cards);
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public ICard drawCard() {
        // TODO: 구현하세요
        // 힌트:
        // 1. 카드가 남아있는지 확인 (isEmpty() 체크)
        // 2. 없으면 IllegalStateException 던지기
        // 3. 있으면 cards.remove(0) 또는 cards.remove(cards.size()-1)
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public List<ICard> drawCards(int count) {
        // TODO: 구현하세요
        // 힌트:
        // 1. count가 남은 카드 수보다 많은지 확인
        // 2. 결과를 담을 List<ICard> 생성
        // 3. count번 drawCard() 호출하여 리스트에 추가
        // 4. 리스트 반환
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public int getRemainingCards() {
        return cards.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(ICard card) {
        return false;
    }
}