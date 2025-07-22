package card;

/**
 * Card 인터페이스 구현을 테스트하는 클래스
 * 
 * 학습자가 구현해야 할 것:
 * 1. Card 인터페이스를 구현하는 구체적인 클래스 (예: PlayingCard)
 * 2. 모든 테스트가 통과하도록 구현
 */
public class CardTest {
    
    public static void main(String[] args) {
        System.out.println("=== Card 구현 테스트 시작 ===\n");
        
        // TODO: Card 인터페이스를 구현한 클래스로 변경하세요
        // 예: Card card = new PlayingCard(Suit.SPADE, Rank.ACE);
        
        testCardCreation();
        testCardEquality();
        testCardValue();
        testCardToString();
        testAllCards();
        
        System.out.println("\n=== 모든 테스트 완료! ===");
    }
    
    /**
     * 카드 생성 테스트
     */
    private static void testCardCreation() {
        System.out.println("[테스트 1] 카드 생성");
        
        // TODO: 아래 주석을 해제하고 Card 구현체로 수정하세요
        /*
        Card spadeAce = // Card 구현체 생성
        Card heartKing = // Card 구현체 생성
        
        assert spadeAce.getSuit() == Suit.SPADE : "스페이드 에이스의 무늬가 올바르지 않습니다";
        assert spadeAce.getRank() == Rank.ACE : "스페이드 에이스의 랭크가 올바르지 않습니다";
        assert heartKing.getSuit() == Suit.HEART : "하트 킹의 무늬가 올바르지 않습니다";
        assert heartKing.getRank() == Rank.KING : "하트 킹의 랭크가 올바르지 않습니다";
        */
        
        System.out.println("✓ 카드 생성 테스트 통과");
    }
    
    /**
     * 카드 동일성 테스트
     */
    private static void testCardEquality() {
        System.out.println("[테스트 2] 카드 동일성");
        
        // TODO: 아래 주석을 해제하고 테스트하세요
        /*
        Card card1 = // 다이아몬드 10 생성
        Card card2 = // 다이아몬드 10 생성 (같은 카드)
        Card card3 = // 다이아몬드 9 생성 (다른 카드)
        
        assert card1.equals(card2) : "같은 무늬와 숫자의 카드는 동일해야 합니다";
        assert !card1.equals(card3) : "다른 카드는 동일하지 않아야 합니다";
        assert card1.hashCode() == card2.hashCode() : "같은 카드의 해시코드는 동일해야 합니다";
        */
        
        System.out.println("✓ 카드 동일성 테스트 통과");
    }
    
    /**
     * 카드 값 테스트
     */
    private static void testCardValue() {
        System.out.println("[테스트 3] 카드 값");
        
        // TODO: 아래 주석을 해제하고 테스트하세요
        /*
        Card two = // 클럽 2 생성
        Card ten = // 클럽 10 생성
        Card jack = // 클럽 J 생성
        Card queen = // 클럽 Q 생성
        Card king = // 클럽 K 생성
        Card ace = // 클럽 A 생성
        
        assert two.getValue() == 2 : "2의 값은 2여야 합니다";
        assert ten.getValue() == 10 : "10의 값은 10이어야 합니다";
        assert jack.getValue() == 11 : "J의 값은 11이어야 합니다";
        assert queen.getValue() == 12 : "Q의 값은 12여야 합니다";
        assert king.getValue() == 13 : "K의 값은 13이어야 합니다";
        assert ace.getValue() == 14 : "A의 값은 14여야 합니다";
        */
        
        System.out.println("✓ 카드 값 테스트 통과");
    }
    
    /**
     * 카드 문자열 표현 테스트
     */
    private static void testCardToString() {
        System.out.println("[테스트 4] 카드 문자열 표현");
        
        // TODO: 아래 주석을 해제하고 테스트하세요
        /*
        Card spadeAce = // 스페이드 A 생성
        Card heartTen = // 하트 10 생성
        
        System.out.println("스페이드 에이스: " + spadeAce);
        System.out.println("하트 10: " + heartTen);
        
        // toString()이 무늬 기호와 랭크를 포함하는지 확인
        assert spadeAce.toString().contains("♠") : "스페이드 기호가 포함되어야 합니다";
        assert spadeAce.toString().contains("A") : "A가 포함되어야 합니다";
        */
        
        System.out.println("✓ 카드 문자열 표현 테스트 통과");
    }
    
    /**
     * 52장의 카드가 모두 다른지 테스트
     */
    private static void testAllCards() {
        System.out.println("[테스트 5] 52장 카드 유일성");
        
        // TODO: 아래 주석을 해제하고 테스트하세요
        /*
        Set<Card> cardSet = new HashSet<>();
        
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                Card card = // 카드 생성
                cardSet.add(card);
            }
        }
        
        assert cardSet.size() == 52 : "52장의 카드가 모두 달라야 합니다";
        */
        
        System.out.println("✓ 52장 카드 유일성 테스트 통과");
    }
}