package campus.membercampusstudy.mapper;

import campus.membercampusstudy.entity.Profile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 회원 프로필 매퍼 인터페이스
 * <p>
 * MyBatis 프로필 매퍼의 공통 인터페이스입니다.
 * 학습용(ProfileMapper)과 구현체(ProfileMapperRef) 모두 이 인터페이스를 구현합니다.
 * IProfile 인터페이스를 상속받아 JPA Repository와 일관된 인터페이스를 제공합니다.
 */
@Mapper
public interface ProfileMapper extends IProfileMapper {
    
    // TODO: @Insert 어노테이션으로 프로필 등록 쿼리 작성
    void insertProfile(Profile profile);
    
    // TODO: @Select와 @Results 어노테이션으로 전체 프로필 조회 쿼리 작성
    List<Profile> findAllProfiles();
    
    // TODO: @Select와 @Results 어노테이션으로 ID 조회 쿼리 작성
    Profile findProfileById(@Param("id") Long id);
    
    // TODO: @Select와 @Results 어노테이션으로 회원 ID 조회 쿼리 작성
    Profile findProfileByMemberId(@Param("memberId") Long memberId);
    
    // TODO: @Select 어노테이션으로 회원 ID 존재 확인 쿼리 작성
    int countByMemberId(@Param("memberId") Long memberId);
    
    // TODO: @Select와 @Results 어노테이션으로 닉네임 검색 쿼리 작성 (부분일치)
    List<Profile> findProfilesByNicknameContaining(@Param("nickname") String nickname);
    
    // TODO: @Select와 @Results 어노테이션으로 주소 검색 쿼리 작성 (부분일치)
    List<Profile> findProfilesByAddressContaining(@Param("address") String address);
    
    // TODO: @Select와 @Results 어노테이션으로 우편번호 검색 쿼리 작성
    List<Profile> findProfilesByPostalCode(@Param("postalCode") String postalCode);
    
    // TODO: @Update 어노테이션으로 프로필 정보 수정 쿼리 작성
    void updateProfile(Profile profile);
    
    // TODO: @Delete 어노테이션으로 프로필 삭제 쿼리 작성
    void deleteProfile(@Param("id") Long id);
    
    // TODO: @Delete 어노테이션으로 회원 ID로 프로필 삭제 쿼리 작성
    void deleteProfileByMemberId(@Param("memberId") Long memberId);
}