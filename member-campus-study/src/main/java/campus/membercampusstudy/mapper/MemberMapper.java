package campus.membercampusstudy.mapper;

import campus.membercampusstudy.entity.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * MyBatis 회원 매퍼 (학습용 - SQL 쿼리 구현 필요)
 * 
 * TODO: 아래 메서드들에 MyBatis 어노테이션을 추가하여 SQL 쿼리를 완성하세요.
 * 
 * 사용할 어노테이션:
 * - @Insert: INSERT 쿼리
 * - @Select: SELECT 쿼리  
 * - @Update: UPDATE 쿼리
 * - @Delete: DELETE 쿼리
 * - @Results: 결과 매핑
 * - @Result: 컬럼-필드 매핑
 * - @Param: 파라미터 바인딩
 * 
 * @author XIYO
 * @version 1.0
 * @since 2025-08-02
 */
@Mapper
public interface MemberMapper {
    
    // TODO 1: 회원 등록 쿼리를 작성하세요
    // 힌트: INSERT INTO mybatismember (컬럼들...) VALUES (값들...)
    // 힌트: mybatis_member_seq.NEXTVAL 사용
    void insertMember(Member member);
    
    // TODO 2: 전체 회원 조회 쿼리를 작성하세요
    // 힌트: SELECT * FROM mybatismember
    // 힌트: @Results로 컬럼 매핑 필요
    List<Member> findAllMembers();
    
    // TODO 3: ID로 회원 조회 쿼리를 작성하세요
    // 힌트: SELECT * FROM mybatismember WHERE id = #{id}
    Member findMemberById(@Param("id") Long id);
    
    // TODO 4: 이메일로 회원 조회 쿼리를 작성하세요
    // 힌트: SELECT * FROM mybatismember WHERE email = #{email}
    Member findMemberByEmail(@Param("email") String email);
    
    // TODO 5: 이메일 중복 확인 쿼리를 작성하세요
    // 힌트: SELECT COUNT(*) FROM mybatismember WHERE email = #{email}
    int countByEmail(@Param("email") String email);
    
    // TODO 6: 추가 검색 쿼리들을 구현하세요 (도전 과제)
    
    // TODO 6-1: 이름으로 검색 (부분일치)
    // 힌트: WHERE name LIKE CONCAT('%', #{name}, '%')
    // List<Member> findByNameContaining(@Param("name") String name);
    
    // TODO 6-2: 나이 범위로 검색
    // 힌트: WHERE age BETWEEN #{minAge} AND #{maxAge}
    // List<Member> findByAgeBetween(@Param("minAge") Integer minAge, @Param("maxAge") Integer maxAge);
    
    // TODO 6-3: 성별로 검색
    // 힌트: WHERE gender = #{gender}
    // List<Member> findByGender(@Param("gender") String gender);
    
    // TODO 7: 회원 정보 수정 쿼리를 작성하세요
    // 힌트: UPDATE mybatismember SET name=#{name}, phone=#{phone}... WHERE id=#{id}
    // void updateMember(Member member);
    
    // TODO 8: 회원 삭제 쿼리를 작성하세요
    // 힌트: DELETE FROM mybatismember WHERE id = #{id}
    // void deleteMember(@Param("id") Long id);
}