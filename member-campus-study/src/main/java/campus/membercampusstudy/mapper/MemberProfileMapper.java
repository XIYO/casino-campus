package campus.membercampusstudy.mapper;

import campus.membercampusstudy.entity.MemberProfile;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * MyBatis 회원 프로필 매퍼 인터페이스 (학습용)
 * <p>
 * 테이블: {@code mybatismemberprofile}<br>
 * 시퀀스: {@code mybatis_profile_seq}
 * 
 * @author XIYO
 * @since 2025-08-02
 */
@Mapper
public interface MemberProfileMapper {
    
    // TODO: @Insert와 @Options 어노테이션으로 프로필 등록 쿼리 작성
    void insertProfile(MemberProfile profile);
    
    // TODO: @Select와 @Results 어노테이션으로 전체 프로필 조회 쿼리 작성
    List<MemberProfile> findAllProfiles();
    
    // TODO: @Select와 @Results 어노테이션으로 ID 조회 쿼리 작성
    MemberProfile findProfileById(@Param("id") Long id);
    
    // TODO: @Select와 @Results 어노테이션으로 회원 ID 조회 쿼리 작성
    MemberProfile findProfileByMemberId(@Param("memberId") Long memberId);
    
    // TODO: @Select와 @Results 어노테이션으로 닉네임 조회 쿼리 작성
    MemberProfile findProfileByNickname(@Param("nickname") String nickname);
    
    // TODO: @Select 어노테이션으로 닉네임 중복 확인 쿼리 작성
    int countByNickname(@Param("nickname") String nickname);
    
    // TODO: @Select와 @Results 어노테이션으로 휴대전화번호 조회 쿼리 작성
    MemberProfile findProfileByMobilePhone(@Param("mobilePhone") String mobilePhone);
    
    // TODO: @Select와 @Results 어노테이션으로 닉네임 검색 쿼리 작성 (부분일치)
    List<MemberProfile> findByNicknameContaining(@Param("nickname") String nickname);
    
    // TODO: @Select와 @Results 어노테이션으로 이름 검색 쿼리 작성 (부분일치)
    List<MemberProfile> findByNameContaining(@Param("name") String name);
    
    // TODO: @Select와 @Results 어노테이션으로 우편번호 검색 쿼리 작성
    List<MemberProfile> findByPostalCode(@Param("postalCode") String postalCode);
    
    // TODO: @Update 어노테이션으로 프로필 정보 수정 쿼리 작성
    void updateProfile(MemberProfile profile);
    
    // TODO: @Delete 어노테이션으로 프로필 삭제 쿼리 작성
    void deleteProfile(@Param("id") Long id);
}