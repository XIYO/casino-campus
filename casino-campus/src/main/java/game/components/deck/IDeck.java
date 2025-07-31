package game.components.deck;

import game.components.card.ICard;

/**
 * 카드 덱의 기본 동작을 정의하는 인터페이스
 * 
 * 이 인터페이스는 카드 게임에서 사용되는 카드 덱의 기본 동작을 정의합니다.
 * 모든 덱 구현체는 이 인터페이스를 구현해야 합니다.
 * 
 * @author XIYO
 * @version 1.1
 * @since 2024-01-01
 */
public interface IDeck {
    /**
     * 덱을 섞습니다.
     * 
     * 카드의 순서를 무작위로 변경합니다.
     * 셔플 후에도 덱의 카드 수는 변하지 않습니다.
     */
    void shuffle();
    
    /**
     * 덱에서 카드를 한 장 뽑습니다.
     * 
     * 덱의 맨 위에서 카드를 한 장 뽑아 반환합니다.
     * 뽑은 카드는 덱에서 제거됩니다.
     * 
     * @return 뽑은 카드
     * @throws IllegalStateException 덱이 비어있을 때
     */
    ICard drawCard();
    
    /**
     * 덱이 비어있는지 확인합니다.
     * 
     * @return 덱이 비어있으면 true, 카드가 하나라도 있으면 false
     */
    boolean isEmpty();
}