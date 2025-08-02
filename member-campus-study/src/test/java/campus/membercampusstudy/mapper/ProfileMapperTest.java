package campus.membercampusstudy.mapper;

import campus.membercampusstudy.entity.Member;
import campus.membercampusstudy.entity.Profile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * MemberProfileMapper 테스트 클래스
 * <p>
 * MyBatis SQL 쿼리 구현이 올바른지 검증합니다.
 * 
 * @author XIYO
 * @since 2025-08-02
 */
@SpringBootTest
@ActiveProfiles("ref")
@Transactional
class ProfileMapperTest {

    @Autowired
    private MemberMapperRef memberMapper;
    
    @Autowired
    private ProfileMapperRef profileMapper; // 참고 구현체로 테스트

    @Test
    void insertProfile_성공() {
        // Given
        Member member = Member.builder()
            .email("test@test.com").name("홍길동").phone("010-1234-5678")
            .age(25).gender(Member.Gender.MALE).build();
        memberMapper.insertMember(member);
        
        Profile profile = Profile.createWithMember(
            member, "길동이", "홍길동", "profile.jpg", 
            "12345", "서울시 강남구", "역삼동 123-45", "010-1234-5678", "메모"
        );
        
        // When
        profileMapper.insertProfile(profile);
        
        // Then
        assertThat(profile.getId()).isNotNull();
    }

    @Test
    void findAllProfiles_성공() {
        // Given
        Member member1 = Member.builder()
            .email("test1@test.com").name("홍길동").phone("010-1111-1111")
            .age(25).gender(Member.Gender.MALE).build();
        Member member2 = Member.builder()
            .email("test2@test.com").name("김영희").phone("010-2222-2222")
            .age(23).gender(Member.Gender.FEMALE).build();
        memberMapper.insertMember(member1);
        memberMapper.insertMember(member2);
        
        Profile profile1 = Profile.createWithMember(member1, "길동이", "홍길동", null, null, null, null, null, null);
        Profile profile2 = Profile.createWithMember(member2, "영희", "김영희", null, null, null, null, null, null);
        profileMapper.insertProfile(profile1);
        profileMapper.insertProfile(profile2);
        
        // When
        List<Profile> profiles = profileMapper.findAllProfiles();
        
        // Then
        assertThat(profiles).hasSize(2);
    }

    @Test
    void findProfileById_성공() {
        // Given
        Member member = Member.builder()
            .email("test@test.com").name("홍길동").phone("010-1234-5678")
            .age(25).gender(Member.Gender.MALE).build();
        memberMapper.insertMember(member);
        
        Profile profile = Profile.createWithMember(member, "길동이", "홍길동", null, null, null, null, null, null);
        profileMapper.insertProfile(profile);
        
        // When
        Profile found = profileMapper.findProfileById(profile.getId());
        
        // Then
        assertThat(found).isNotNull();
        assertThat(found.getNickname()).isEqualTo("길동이");
    }

    @Test
    void findProfileByMemberId_성공() {
        // Given
        Member member = Member.builder()
            .email("test@test.com").name("홍길동").phone("010-1234-5678")
            .age(25).gender(Member.Gender.MALE).build();
        memberMapper.insertMember(member);
        
        Profile profile = Profile.createWithMember(member, "길동이", "홍길동", null, null, null, null, null, null);
        profileMapper.insertProfile(profile);
        
        // When
        Profile found = profileMapper.findProfileByMemberId(member.getId());
        
        // Then
        assertThat(found).isNotNull();
        assertThat(found.getNickname()).isEqualTo("길동이");
    }

    @Test
    void findProfileByNickname_성공() {
        // Given
        Member member = Member.builder()
            .email("test@test.com").name("홍길동").phone("010-1234-5678")
            .age(25).gender(Member.Gender.MALE).build();
        memberMapper.insertMember(member);
        
        Profile profile = Profile.createWithMember(member, "길동이", "홍길동", null, null, null, null, null, null);
        profileMapper.insertProfile(profile);
        
        // When
        Profile found = profileMapper.findProfileByNickname("길동이");
        
        // Then
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("홍길동");
    }

    @Test
    void countByNickname_성공() {
        // Given
        Member member = Member.builder()
            .email("test@test.com").name("홍길동").phone("010-1234-5678")
            .age(25).gender(Member.Gender.MALE).build();
        memberMapper.insertMember(member);
        
        Profile profile = Profile.createWithMember(member, "길동이", "홍길동", null, null, null, null, null, null);
        profileMapper.insertProfile(profile);
        
        // When
        int count = profileMapper.countByNickname("길동이");
        
        // Then
        assertThat(count).isEqualTo(1);
    }

    @Test
    void findProfileByMobilePhone_성공() {
        // Given
        Member member = Member.builder()
            .email("test@test.com").name("홍길동").phone("010-1234-5678")
            .age(25).gender(Member.Gender.MALE).build();
        memberMapper.insertMember(member);
        
        Profile profile = Profile.createWithMember(
            member, "길동이", "홍길동", null, null, null, null, "010-9999-9999", null
        );
        profileMapper.insertProfile(profile);
        
        // When
        Profile found = profileMapper.findProfileByMobilePhone("010-9999-9999");
        
        // Then
        assertThat(found).isNotNull();
        assertThat(found.getNickname()).isEqualTo("길동이");
    }

    @Test
    void findByNicknameContaining_성공() {
        // Given
        Member member1 = Member.builder()
            .email("test1@test.com").name("홍길동").phone("010-1111-1111")
            .age(25).gender(Member.Gender.MALE).build();
        Member member2 = Member.builder()
            .email("test2@test.com").name("김영희").phone("010-2222-2222")
            .age(23).gender(Member.Gender.FEMALE).build();
        memberMapper.insertMember(member1);
        memberMapper.insertMember(member2);
        
        Profile profile1 = Profile.createWithMember(member1, "길동이", "홍길동", null, null, null, null, null, null);
        Profile profile2 = Profile.createWithMember(member2, "길순이", "김영희", null, null, null, null, null, null);
        profileMapper.insertProfile(profile1);
        profileMapper.insertProfile(profile2);
        
        // When
        List<Profile> profiles = profileMapper.findByNicknameContaining("길");
        
        // Then
        assertThat(profiles).hasSize(2);
    }

    @Test
    void findByPostalCode_성공() {
        // Given
        Member member = Member.builder()
            .email("test@test.com").name("홍길동").phone("010-1234-5678")
            .age(25).gender(Member.Gender.MALE).build();
        memberMapper.insertMember(member);
        
        Profile profile = Profile.createWithMember(
            member, "길동이", "홍길동", null, "12345", "서울시", null, null, null
        );
        profileMapper.insertProfile(profile);
        
        // When
        List<Profile> profiles = profileMapper.findByPostalCode("12345");
        
        // Then
        assertThat(profiles).hasSize(1);
        assertThat(profiles.get(0).getAddress()).isEqualTo("서울시");
    }

    @Test
    void updateProfile_성공() {
        // Given
        Member member = Member.builder()
            .email("test@test.com").name("홍길동").phone("010-1234-5678")
            .age(25).gender(Member.Gender.MALE).build();
        memberMapper.insertMember(member);
        
        Profile profile = Profile.createWithMember(member, "길동이", "홍길동", null, null, null, null, null, null);
        profileMapper.insertProfile(profile);
        
        // When
        profile.updateProfile("새길동", "홍길동", "new.jpg", "54321", "부산시", "해운대", "010-9999-9999", "새메모");
        profileMapper.updateProfile(profile);
        
        // Then
        Profile updated = profileMapper.findProfileById(profile.getId());
        assertThat(updated.getNickname()).isEqualTo("새길동");
        assertThat(updated.getPostalCode()).isEqualTo("54321");
    }

    @Test
    void deleteProfile_성공() {
        // Given
        Member member = Member.builder()
            .email("test@test.com").name("홍길동").phone("010-1234-5678")
            .age(25).gender(Member.Gender.MALE).build();
        memberMapper.insertMember(member);
        
        Profile profile = Profile.createWithMember(member, "길동이", "홍길동", null, null, null, null, null, null);
        profileMapper.insertProfile(profile);
        
        // When
        profileMapper.deleteProfile(profile.getId());
        
        // Then
        Profile deleted = profileMapper.findProfileById(profile.getId());
        assertThat(deleted).isNull();
    }
}