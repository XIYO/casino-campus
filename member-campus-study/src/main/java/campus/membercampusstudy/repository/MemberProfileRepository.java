package campus.membercampusstudy.repository;

import campus.membercampusstudy.entity.MemberProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * JPA 회원 프로필 리포지토리 (완성 구현체)
 * 
 * 이 리포지토리는 JPA를 사용한 회원 프로필 데이터 접근을 담당합니다.
 * JPA Repository를 상속받아 기본 CRUD 기능을 자동으로 제공받습니다.
 * 
 * @author XIYO
 * @version 1.0
 * @since 2025-08-02
 */
@Repository
public interface MemberProfileRepository extends JpaRepository<MemberProfile, Long> {
    
    /**
     * 회원 ID로 프로필 조회
     */
    Optional<MemberProfile> findByMemberId(Long memberId);
    
    /**
     * 회원 ID로 프로필 존재 여부 확인
     */
    boolean existsByMemberId(Long memberId);
    
    /**
     * 닉네임으로 프로필 조회
     */
    Optional<MemberProfile> findByNickname(String nickname);
    
    /**
     * 닉네임 중복 확인
     */
    boolean existsByNickname(String nickname);
    
    /**
     * 닉네임으로 프로필 검색 (부분일치)
     */
    List<MemberProfile> findByNicknameContaining(String nickname);
    
    /**
     * 이름으로 프로필 검색 (부분일치)
     */
    List<MemberProfile> findByNameContaining(String name);
    
    /**
     * 우편번호로 프로필 검색
     */
    List<MemberProfile> findByPostalCode(String postalCode);
    
    /**
     * 주소로 프로필 검색 (부분일치)
     */
    List<MemberProfile> findByAddressContaining(String address);
    
    /**
     * 휴대전화번호로 프로필 조회
     */
    Optional<MemberProfile> findByMobilePhone(String mobilePhone);
    
    /**
     * 휴대전화번호 중복 확인
     */
    boolean existsByMobilePhone(String mobilePhone);
    
    /**
     * 프로필 이미지가 있는 프로필 조회
     */
    List<MemberProfile> findByProfileImageUrlIsNotNull();
    
    /**
     * 메모가 있는 프로필 조회
     */
    List<MemberProfile> findByMemoIsNotNull();
    
    /**
     * 닉네임과 이름으로 프로필 검색 (부분일치)
     */
    List<MemberProfile> findByNicknameContainingAndNameContaining(String nickname, String name);
}