package campus.membercampusstudy.mapper;

import campus.membercampusstudy.entity.MemberProfile;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * MyBatis 회원 프로필 매퍼 인터페이스 (참고 구현체)
 * <p>
 * 테이블: {@code mybatismemberprofile}<br>
 * 시퀀스: {@code mybatis_profile_seq}
 * 
 * @author XIYO
 * @since 2025-08-02
 */
@Mapper
public interface MemberProfileMapperRef {
    
    /**
     * 프로필 등록
     */
    @Insert("""
            INSERT INTO mybatismemberprofile (id, member_id, nickname, name, profile_image_url, postal_code, address, address_detail, mobile_phone, memo, created_at, updated_at)
            VALUES (mybatis_profile_seq.NEXTVAL, #{member.id}, #{nickname}, #{name}, #{profileImageUrl}, #{postalCode}, #{address}, #{addressDetail}, #{mobilePhone}, #{memo}, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insertProfile(MemberProfile profile);
    
    /**
     * 전체 프로필 조회
     */
    @Select("SELECT * FROM mybatismemberprofile")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "member.id", column = "member_id"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "name", column = "name"),
            @Result(property = "profileImageUrl", column = "profile_image_url"),
            @Result(property = "postalCode", column = "postal_code"),
            @Result(property = "address", column = "address"),
            @Result(property = "addressDetail", column = "address_detail"),
            @Result(property = "mobilePhone", column = "mobile_phone"),
            @Result(property = "memo", column = "memo"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    List<MemberProfile> findAllProfiles();
    
    /**
     * ID로 프로필 조회
     */
    @Select("SELECT * FROM mybatismemberprofile WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "member.id", column = "member_id"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "name", column = "name"),
            @Result(property = "profileImageUrl", column = "profile_image_url"),
            @Result(property = "postalCode", column = "postal_code"),
            @Result(property = "address", column = "address"),
            @Result(property = "addressDetail", column = "address_detail"),
            @Result(property = "mobilePhone", column = "mobile_phone"),
            @Result(property = "memo", column = "memo"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    MemberProfile findProfileById(@Param("id") Long id);
    
    /**
     * 회원 ID로 프로필 조회
     */
    @Select("SELECT * FROM mybatismemberprofile WHERE member_id = #{memberId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "member.id", column = "member_id"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "name", column = "name"),
            @Result(property = "profileImageUrl", column = "profile_image_url"),
            @Result(property = "postalCode", column = "postal_code"),
            @Result(property = "address", column = "address"),
            @Result(property = "addressDetail", column = "address_detail"),
            @Result(property = "mobilePhone", column = "mobile_phone"),
            @Result(property = "memo", column = "memo"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    MemberProfile findProfileByMemberId(@Param("memberId") Long memberId);
    
    /**
     * 닉네임으로 프로필 조회
     */
    @Select("SELECT * FROM mybatismemberprofile WHERE nickname = #{nickname}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "member.id", column = "member_id"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "name", column = "name"),
            @Result(property = "profileImageUrl", column = "profile_image_url"),
            @Result(property = "postalCode", column = "postal_code"),
            @Result(property = "address", column = "address"),
            @Result(property = "addressDetail", column = "address_detail"),
            @Result(property = "mobilePhone", column = "mobile_phone"),
            @Result(property = "memo", column = "memo"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    MemberProfile findProfileByNickname(@Param("nickname") String nickname);
    
    /**
     * 닉네임 중복 확인
     */
    @Select("SELECT COUNT(*) FROM mybatismemberprofile WHERE nickname = #{nickname}")
    int countByNickname(@Param("nickname") String nickname);
    
    /**
     * 휴대전화번호로 프로필 조회
     */
    @Select("SELECT * FROM mybatismemberprofile WHERE mobile_phone = #{mobilePhone}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "member.id", column = "member_id"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "name", column = "name"),
            @Result(property = "profileImageUrl", column = "profile_image_url"),
            @Result(property = "postalCode", column = "postal_code"),
            @Result(property = "address", column = "address"),
            @Result(property = "addressDetail", column = "address_detail"),
            @Result(property = "mobilePhone", column = "mobile_phone"),
            @Result(property = "memo", column = "memo"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    MemberProfile findProfileByMobilePhone(@Param("mobilePhone") String mobilePhone);
    
    /**
     * 닉네임으로 검색 (부분일치)
     */
    @Select("SELECT * FROM mybatismemberprofile WHERE nickname LIKE CONCAT('%', #{nickname}, '%')")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "member.id", column = "member_id"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "name", column = "name"),
            @Result(property = "profileImageUrl", column = "profile_image_url"),
            @Result(property = "postalCode", column = "postal_code"),
            @Result(property = "address", column = "address"),
            @Result(property = "addressDetail", column = "address_detail"),
            @Result(property = "mobilePhone", column = "mobile_phone"),
            @Result(property = "memo", column = "memo"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    List<MemberProfile> findByNicknameContaining(@Param("nickname") String nickname);
    
    /**
     * 이름으로 검색 (부분일치)
     */
    @Select("SELECT * FROM mybatismemberprofile WHERE name LIKE CONCAT('%', #{name}, '%')")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "member.id", column = "member_id"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "name", column = "name"),
            @Result(property = "profileImageUrl", column = "profile_image_url"),
            @Result(property = "postalCode", column = "postal_code"),
            @Result(property = "address", column = "address"),
            @Result(property = "addressDetail", column = "address_detail"),
            @Result(property = "mobilePhone", column = "mobile_phone"),
            @Result(property = "memo", column = "memo"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    List<MemberProfile> findByNameContaining(@Param("name") String name);
    
    /**
     * 우편번호로 검색
     */
    @Select("SELECT * FROM mybatismemberprofile WHERE postal_code = #{postalCode}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "member.id", column = "member_id"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "name", column = "name"),
            @Result(property = "profileImageUrl", column = "profile_image_url"),
            @Result(property = "postalCode", column = "postal_code"),
            @Result(property = "address", column = "address"),
            @Result(property = "addressDetail", column = "address_detail"),
            @Result(property = "mobilePhone", column = "mobile_phone"),
            @Result(property = "memo", column = "memo"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    List<MemberProfile> findByPostalCode(@Param("postalCode") String postalCode);
    
    /**
     * 프로필 정보 수정
     */
    @Update("""
            UPDATE mybatismemberprofile 
            SET nickname = #{nickname}, name = #{name}, profile_image_url = #{profileImageUrl}, 
                postal_code = #{postalCode}, address = #{address}, address_detail = #{addressDetail}, 
                mobile_phone = #{mobilePhone}, memo = #{memo}, updated_at = CURRENT_TIMESTAMP 
            WHERE id = #{id}
            """)
    void updateProfile(MemberProfile profile);
    
    /**
     * 프로필 삭제
     */
    @Delete("DELETE FROM mybatismemberprofile WHERE id = #{id}")
    void deleteProfile(@Param("id") Long id);
}