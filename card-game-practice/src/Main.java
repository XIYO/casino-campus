/**
 * 카드 게임 연습 프로젝트의 메인 클래스
 * 
 * 이 프로젝트는 카드 게임의 기본 구성 요소들을 구현하는 연습 프로젝트입니다.
 * Card, Deck, Hand 인터페이스를 구현하여 간단한 카드 게임을 만들어보세요.
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("=== 카드 게임 연습 프로젝트 ===");
        System.out.println();
        System.out.println("이 프로젝트에서 구현해야 할 것:");
        System.out.println("1. Card 인터페이스 구현 - 카드 한 장을 표현");
        System.out.println("2. Deck 인터페이스 구현 - 52장의 카드 덱 관리");
        System.out.println("3. Hand 인터페이스 구현 - 플레이어의 손패 관리");
        System.out.println();
        System.out.println("각 패키지의 test 폴더에 있는 테스트 클래스를 실행하여");
        System.out.println("구현이 올바른지 확인할 수 있습니다.");
        System.out.println();
        
        // TODO: 구현 후 아래 주석을 해제하여 간단한 시연을 해보세요
        /*
        System.out.println("=== 구현 시연 ===");
        
        // 1. 덱 생성 및 셔플
        Deck deck = // Deck 구현체 생성
        System.out.println("새 덱 생성: " + deck.getRemainingCards() + "장");
        
        deck.shuffle();
        System.out.println("덱을 섞었습니다.");
        
        // 2. 두 명의 플레이어에게 5장씩 카드 분배
        Hand player1 = // Hand 구현체 생성
        Hand player2 = // Hand 구현체 생성
        
        System.out.println("\n카드 분배 중...");
        for (int i = 0; i < 5; i++) {
            player1.addCard(deck.drawCard());
            player2.addCard(deck.drawCard());
        }
        
        // 3. 각 플레이어의 손패 확인
        System.out.println("\n플레이어 1의 손패 (" + player1.getCardCount() + "장):");
        System.out.println(player1);
        
        System.out.println("\n플레이어 2의 손패 (" + player2.getCardCount() + "장):");
        System.out.println(player2);
        
        System.out.println("\n남은 카드: " + deck.getRemainingCards() + "장");
        
        // 4. 손패 정렬
        player1.sort();
        player2.sort();
        
        System.out.println("\n=== 정렬 후 ===");
        System.out.println("플레이어 1: " + player1);
        System.out.println("플레이어 2: " + player2);
        */
    }
}