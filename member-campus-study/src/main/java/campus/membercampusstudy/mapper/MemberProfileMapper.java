package campus.membercampusstudy.mapper;

import campus.membercampusstudy.entity.MemberProfile;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * MyBatis 회원 프로필 매퍼 (학습용)
 * 
 * TODO: 이 인터페이스의 메서드들에 SQL 어노테이션을 추가하여 완성하세요.
 * 
 * 힌트:
 * - @Insert: 데이터 삽입용
 * - @Select: 데이터 조회용  
 * - @Update: 데이터 수정용
 * - @Delete: 데이터 삭제용
 * - @Results: 컬럼과 필드 매핑용
 * - @Options: 자동 생성 키 설정용
 * 
 * 테이블명: mybatismemberprofile
 * 시퀀스명: mybatis_profile_seq
 * 
 * @author XIYO
 * @version 1.0
 * @since 2025-08-02
 */
@Mapper
public interface MemberProfileMapper {
    
    /**
     * TODO 1: 프로필 등록 쿼리를 작성하세요
     * 힌트: INSERT INTO mybatismemberprofile (컬럼들...) VALUES (값들...)
     * 주의: id는 시퀀스(mybatis_profile_seq.NEXTVAL) 사용
     */
    // @Insert("여기에 SQL 작성")
    // @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insertProfile(MemberProfile profile);
    
    /**
     * TODO 2: 전체 프로필 조회 쿼리를 작성하세요
     * 힌트: SELECT * FROM mybatismemberprofile
     */
    // @Select("여기에 SQL 작성")
    // @Results({ /* 컬럼 매핑 */ })
    List<MemberProfile> findAllProfiles();
    
    /**
     * TODO 3: ID로 프로필 조회 쿼리를 작성하세요
     * 힌트: SELECT * FROM mybatismemberprofile WHERE id = #{id}
     */
    // @Select("여기에 SQL 작성") 
    // @Results({ /* 컬럼 매핑 */ })
    MemberProfile findProfileById(@Param("id") Long id);
    
    /**
     * TODO 4: 회원 ID로 프로필 조회 쿼리를 작성하세요
     * 힌트: SELECT * FROM mybatismemberprofile WHERE member_id = #{memberId}
     */
    // @Select("여기에 SQL 작성")
    // @Results({ /* 컬럼 매핑 */ })
    MemberProfile findProfileByMemberId(@Param("memberId") Long memberId);
    
    /**
     * TODO 5: 닉네임으로 프로필 조회 쿼리를 작성하세요
     * 힌트: SELECT * FROM mybatismemberprofile WHERE nickname = #{nickname}
     */
    // @Select("여기에 SQL 작성")
    // @Results({ /* 컬럼 매핑 */ })
    MemberProfile findProfileByNickname(@Param("nickname") String nickname);
    
    /**
     * TODO 6: 닉네임 중복 확인 쿼리를 작성하세요
     * 힌트: SELECT COUNT(*) FROM mybatismemberprofile WHERE nickname = #{nickname}
     */
    // @Select("여기에 SQL 작성")
    int countByNickname(@Param("nickname") String nickname);
    
    /**
     * TODO 7: 휴대전화번호로 프로필 조회 쿼리를 작성하세요
     * 힌트: SELECT * FROM mybatismemberprofile WHERE mobile_phone = #{mobilePhone}
     */
    // @Select("여기에 SQL 작성")
    // @Results({ /* 컬럼 매핑 */ })
    MemberProfile findProfileByMobilePhone(@Param("mobilePhone") String mobilePhone);
    
    /**
     * TODO 8: 닉네임으로 검색 쿼리를 작성하세요 (부분일치)
     * 힌트: SELECT * FROM mybatismemberprofile WHERE nickname LIKE CONCAT('%', #{nickname}, '%')
     */
    // @Select("여기에 SQL 작성")
    // @Results({ /* 컬럼 매핑 */ })
    List<MemberProfile> findByNicknameContaining(@Param("nickname") String nickname);
    
    /**
     * TODO 9: 이름으로 검색 쿼리를 작성하세요 (부분일치)
     * 힌트: SELECT * FROM mybatismemberprofile WHERE name LIKE CONCAT('%', #{name}, '%')
     */
    // @Select("여기에 SQL 작성")
    // @Results({ /* 컬럼 매핑 */ })
    List<MemberProfile> findByNameContaining(@Param("name") String name);
    
    /**
     * TODO 10: 우편번호로 검색 쿼리를 작성하세요
     * 힌트: SELECT * FROM mybatismemberprofile WHERE postal_code = #{postalCode}
     */
    // @Select("여기에 SQL 작성")
    // @Results({ /* 컬럼 매핑 */ })
    List<MemberProfile> findByPostalCode(@Param("postalCode") String postalCode);
    
    /**
     * TODO 11: 프로필 정보 수정 쿼리를 작성하세요
     * 힌트: UPDATE mybatismemberprofile SET 컬럼들... WHERE id = #{id}
     */
    // @Update("여기에 SQL 작성")
    void updateProfile(MemberProfile profile);
    
    /**
     * TODO 12: 프로필 삭제 쿼리를 작성하세요
     * 힌트: DELETE FROM mybatismemberprofile WHERE id = #{id}
     */
    // @Delete("여기에 SQL 작성")
    void deleteProfile(@Param("id") Long id);
}