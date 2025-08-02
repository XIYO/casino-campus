package campus.membercampusstudy;

import campus.membercampusstudy.entity.Member;
import campus.membercampusstudy.mapper.MemberMapperRef;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

/**
 * 간단한 MyBatis 테스트
 */
@SpringBootTest
@ActiveProfiles("ref")
@Transactional
public class SimpleMyBatisTest {
    
    @Autowired
    private MemberMapperRef memberMapperRef;
    
    @Test
    @DisplayName("MyBatis 단순 삽입 테스트")
    void testSimpleInsert() {
        // Given
        Member member = Member.builder()
                .email("simple-test@example.com")
                .name("간단테스트")
                .phone("010-1111-1111")
                .age(25)
                .gender(Member.Gender.MALE)
                .build();
        
        // When & Then
        try {
            memberMapperRef.insertMember(member);
            System.out.println("✅ MyBatis 삽입 성공: " + member.getId());
        } catch (Exception e) {
            System.out.println("❌ MyBatis 삽입 실패: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}