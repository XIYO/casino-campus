package dealer;

import common.Card;

import java.util.*;

/**
 * 덱의 접근은 딜러만 할 수 있도로 제약하기 위해 접근 제어자를 default 로 설정
 */
class Deck {
    /**
     * 덱을 구성하는 카드들
     * 재사용이 불가능 하도록 카드를 뽑을 때마다 제거되도록 큐로 구성
     */
    private final List<Card> cards;

    /**
     * 프라이빗으로 선언하여 외부에서 생성을 못하도록 제약
     * 새로운 덱을 생성합니다.
     * 생성과 동시에 카드들을 섞습니다.
     */
    private Deck() {
        cards = new ArrayList<>();

        // 덱에 넣을 카드 순서대로 생성
        List<Card> cardCase = new ArrayList<>(52); // 동적 확장을 할 필요 없도록 처음부터 필요 갯수 만큼 생성
        for (Card.Suit suit : Card.Suit.values())
            for (Card.Rank rank : Card.Rank.values())
                cardCase.add(new Card(suit, rank));

        this.cards.addAll(cardCase);
    }

    /**
     * 덱을 만들어서 제공합니다.
     */
    static Deck newDeck() {
        return new Deck();
    }

    /**
     * 덱에서 카드를 한 장 뽑습니다.
     */
    public Card drawCard() {
        if (cards.isEmpty())
            throw new IllegalStateException("더 이상 카드가 없습니다.");
        return cards.removeLast();
    }

    /**
     * 딜러만 덱을 섞을 수 있습니다.
     */
    void shuffle() {
        Collections.shuffle(this.cards);
    }
}