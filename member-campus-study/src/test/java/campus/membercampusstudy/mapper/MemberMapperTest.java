package campus.membercampusstudy.mapper;

import campus.membercampusstudy.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * MemberMapper 테스트 클래스
 * <p>
 * MyBatis SQL 쿼리 구현이 올바른지 검증합니다.
 * 
 * @author XIYO
 * @since 2025-08-02
 */
@SpringBootTest
@ActiveProfiles("ref")
@Transactional
class MemberMapperTest {

    @Autowired
    private MemberMapperRef memberMapper; // 참고 구현체로 테스트

    @Test
    void insertMember_성공() {
        // Given
        Member member = Member.builder()
            .email("test@test.com")
            .name("홍길동")
            .phone("010-1234-5678")
            .age(25)
            .gender(Member.Gender.MALE)
            .build();
        
        // When
        memberMapper.insertMember(member);
        
        // Then
        assertThat(member.getId()).isNotNull();
    }

    @Test
    void findAllMembers_성공() {
        // Given
        Member member1 = Member.builder()
            .email("test1@test.com").name("홍길동").phone("010-1111-1111")
            .age(25).gender(Member.Gender.MALE).build();
        Member member2 = Member.builder()
            .email("test2@test.com").name("김영희").phone("010-2222-2222")
            .age(23).gender(Member.Gender.FEMALE).build();
        memberMapper.insertMember(member1);
        memberMapper.insertMember(member2);
        
        // When
        List<Member> members = memberMapper.findAllMembers();
        
        // Then
        assertThat(members).hasSize(2);
    }

    @Test
    void findMemberById_성공() {
        // Given
        Member member = Member.builder()
            .email("test@test.com").name("홍길동").phone("010-1234-5678")
            .age(25).gender(Member.Gender.MALE).build();
        memberMapper.insertMember(member);
        
        // When
        Member found = memberMapper.findMemberById(member.getId());
        
        // Then
        assertThat(found).isNotNull();
        assertThat(found.getEmail()).isEqualTo("test@test.com");
    }

    @Test
    void findMemberByEmail_성공() {
        // Given
        Member member = Member.builder()
            .email("test@test.com").name("홍길동").phone("010-1234-5678")
            .age(25).gender(Member.Gender.MALE).build();
        memberMapper.insertMember(member);
        
        // When
        Member found = memberMapper.findMemberByEmail("test@test.com");
        
        // Then
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("홍길동");
    }

    @Test
    void countByEmail_성공() {
        // Given
        Member member = Member.builder()
            .email("test@test.com").name("홍길동").phone("010-1234-5678")
            .age(25).gender(Member.Gender.MALE).build();
        memberMapper.insertMember(member);
        
        // When
        int count = memberMapper.countByEmail("test@test.com");
        
        // Then
        assertThat(count).isEqualTo(1);
    }

    @Test
    void findByNameContaining_성공() {
        // Given
        Member member1 = Member.builder()
            .email("test1@test.com").name("홍길동").phone("010-1111-1111")
            .age(25).gender(Member.Gender.MALE).build();
        Member member2 = Member.builder()
            .email("test2@test.com").name("홍영수").phone("010-2222-2222")
            .age(23).gender(Member.Gender.MALE).build();
        memberMapper.insertMember(member1);
        memberMapper.insertMember(member2);
        
        // When
        List<Member> members = memberMapper.findByNameContaining("홍");
        
        // Then
        assertThat(members).hasSize(2);
    }

    @Test
    void findByAgeBetween_성공() {
        // Given
        Member member1 = Member.builder()
            .email("test1@test.com").name("홍길동").phone("010-1111-1111")
            .age(25).gender(Member.Gender.MALE).build();
        Member member2 = Member.builder()
            .email("test2@test.com").name("김영희").phone("010-2222-2222")
            .age(30).gender(Member.Gender.FEMALE).build();
        memberMapper.insertMember(member1);
        memberMapper.insertMember(member2);
        
        // When
        List<Member> members = memberMapper.findByAgeBetween(20, 27);
        
        // Then
        assertThat(members).hasSize(1);
        assertThat(members.get(0).getName()).isEqualTo("홍길동");
    }

    @Test
    void findByGender_성공() {
        // Given
        Member member1 = Member.builder()
            .email("test1@test.com").name("홍길동").phone("010-1111-1111")
            .age(25).gender(Member.Gender.MALE).build();
        Member member2 = Member.builder()
            .email("test2@test.com").name("김영희").phone("010-2222-2222")
            .age(23).gender(Member.Gender.FEMALE).build();
        memberMapper.insertMember(member1);
        memberMapper.insertMember(member2);
        
        // When
        List<Member> members = memberMapper.findByGender("MALE");
        
        // Then
        assertThat(members).hasSize(1);
        assertThat(members.get(0).getName()).isEqualTo("홍길동");
    }

    @Test
    void updateMember_성공() {
        // Given
        Member member = Member.builder()
            .email("test@test.com").name("홍길동").phone("010-1234-5678")
            .age(25).gender(Member.Gender.MALE).build();
        memberMapper.insertMember(member);
        
        // When
        member.setName("김길동");
        member.setPhone("010-9999-9999");
        member.setAge(26);
        memberMapper.updateMember(member);
        
        // Then
        Member updated = memberMapper.findMemberById(member.getId());
        assertThat(updated.getName()).isEqualTo("김길동");
        assertThat(updated.getAge()).isEqualTo(26);
    }

    @Test
    void deleteMember_성공() {
        // Given
        Member member = Member.builder()
            .email("test@test.com").name("홍길동").phone("010-1234-5678")
            .age(25).gender(Member.Gender.MALE).build();
        memberMapper.insertMember(member);
        
        // When
        memberMapper.deleteMember(member.getId());
        
        // Then
        Member deleted = memberMapper.findMemberById(member.getId());
        assertThat(deleted).isNull();
    }
}