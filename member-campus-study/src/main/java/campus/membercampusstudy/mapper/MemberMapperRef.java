package campus.membercampusstudy.mapper;

import campus.membercampusstudy.entity.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * MyBatis 회원 매퍼 참고 구현체
 * <p>
 * 테이블: {@code mybatismember}<br>
 * 시퀀스: {@code mybatis_member_seq}
 * 
 * @author XIYO
 * @since 2025-08-02
 */
@Mapper
public interface MemberMapperRef {
    
    /**
     * 회원 등록
     */
    @Insert("""
            INSERT INTO mybatismember (id, email, name, phone, age, gender, created_at, updated_at)
            VALUES (mybatis_member_seq.NEXTVAL, #{email}, #{name}, #{phone}, #{age}, #{gender}, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insertMember(Member member);
    
    /**
     * 전체 회원 조회
     */
    @Select("SELECT * FROM mybatismember")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "email", column = "email"),
            @Result(property = "name", column = "name"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "age", column = "age"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    List<Member> findAllMembers();
    
    /**
     * ID로 회원 조회
     */
    @Select("SELECT * FROM mybatismember WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "email", column = "email"),
            @Result(property = "name", column = "name"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "age", column = "age"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    Member findMemberById(@Param("id") Long id);
    
    /**
     * 이메일로 회원 조회
     */
    @Select("SELECT * FROM mybatismember WHERE email = #{email}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "email", column = "email"),
            @Result(property = "name", column = "name"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "age", column = "age"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    Member findMemberByEmail(@Param("email") String email);
    
    /**
     * 이메일 중복 확인
     */
    @Select("SELECT COUNT(*) FROM mybatismember WHERE email = #{email}")
    int countByEmail(@Param("email") String email);
    
    /**
     * 이름으로 검색 (부분일치)
     */
    @Select("SELECT * FROM mybatismember WHERE name LIKE CONCAT('%', #{name}, '%')")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "email", column = "email"),
            @Result(property = "name", column = "name"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "age", column = "age"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    List<Member> findByNameContaining(@Param("name") String name);
    
    /**
     * 나이 범위로 검색
     */
    @Select("SELECT * FROM mybatismember WHERE age BETWEEN #{minAge} AND #{maxAge}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "email", column = "email"),
            @Result(property = "name", column = "name"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "age", column = "age"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    List<Member> findByAgeBetween(@Param("minAge") Integer minAge, @Param("maxAge") Integer maxAge);
    
    /**
     * 성별로 검색
     */
    @Select("SELECT * FROM mybatismember WHERE gender = #{gender}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "email", column = "email"),
            @Result(property = "name", column = "name"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "age", column = "age"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    List<Member> findByGender(@Param("gender") String gender);
    
    /**
     * 회원 정보 수정
     */
    @Update("""
            UPDATE mybatismember 
            SET name = #{name}, phone = #{phone}, age = #{age}, gender = #{gender}, updated_at = CURRENT_TIMESTAMP 
            WHERE id = #{id}
            """)
    void updateMember(Member member);
    
    /**
     * 회원 삭제
     */
    @Delete("DELETE FROM mybatismember WHERE id = #{id}")
    void deleteMember(@Param("id") Long id);
}