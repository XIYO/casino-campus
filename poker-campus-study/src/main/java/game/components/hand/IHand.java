package game.components.hand;

import game.components.card.ICard;

import java.util.List;

/**
 * 플레이어의 손패 관리 동작을 정의하는 인터페이스
 * 
 * 이 인터페이스는 카드 게임에서 플레이어가 들고 있는 카드들(손패)의 관리 기능을 정의합니다.
 * 모든 손패 구현체는 이 인터페이스를 구현해야 합니다.
 * 
 * @author XIYO
 * @version 1.1
 * @since 2024-01-01
 */
public interface IHand extends Comparable<IHand> {
    /**
     * 손패에 카드를 추가합니다.
     * 
     * 카드는 손패의 끝에 추가됩니다.
     * 
     * @param card 추가할 카드
     * @throws IllegalArgumentException card가 null일 때
     */
    void add(ICard card);
    
    /**
     * 손패에 있는 모든 카드를 반환합니다.
     * 
     * 반환되는 리스트는 수정할 수 없는 읽기 전용 리스트입니다.
     * 원본 손패를 보호하기 위해 변경이 불가능한 리스트를 반환합니다.
     * 
     * @return 수정 불가능한 카드 리스트 (빈 손패일 경우 빈 리스트)
     */
    List<ICard> getCards();
    
    /**
     * 손패가 가득 찼는지 확인합니다.
     * 
     * @return 카드가 5장이면 true, 아니면 false
     */
    boolean isFull();
    
    /**
     * 손패를 정리합니다.
     * 
     * 현재 들고 있는 모든 카드를 버리고 빈 손이 됩니다.
     * 새로운 게임을 시작하거나 패를 교체할 때 사용합니다.
     */
    void clear();
    
    /**
     * 손패의 포커 순위를 평가합니다.
     * 
     * 5장의 카드로 이루어진 손패를 평가하여 포커 족보를 반환합니다.
     * 카드가 5장이 아닌 경우 예외를 발생시킵니다.
     * 
     * @return 평가된 포커 족보
     * @throws IllegalStateException 카드가 정확히 5장이 아닐 때
     */
    HandRank evaluate();
    
    /**
     * 손패를 공개하고 포커 점수를 반환합니다.
     * 
     * 현재 손패의 포커 족보를 평가하고 그에 해당하는 점수를 반환합니다.
     * 카드가 5장이 아닌 경우 예외를 발생시킵니다.
     * 
     * @return 포커 족보의 점수 (높을수록 강한 패)
     * @throws IllegalStateException 카드가 정확히 5장이 아닐 때
     */
    int open();
    
    /**
     * 손패를 문자열로 표현합니다.
     * 
     * 형식: "[카드1, 카드2, ..., 카드N]"
     * 빈 손패의 경우: "[]"
     * 
     * @return 손패의 문자열 표현
     */
    String toString();
}