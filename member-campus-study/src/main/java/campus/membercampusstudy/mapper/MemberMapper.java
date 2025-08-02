package campus.membercampusstudy.mapper;

import campus.membercampusstudy.entity.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * MyBatis 회원 매퍼 인터페이스 (학습용)
 * <p>
 * 테이블: {@code mybatismember}<br>
 * 시퀀스: {@code mybatis_member_seq}
 * 
 * @author XIYO
 * @since 2025-08-02
 */
@Mapper
public interface MemberMapper extends IMemberMapper {
    
    // TODO: @Insert 어노테이션으로 회원 등록 쿼리 작성
    void insertMember(Member member);
    
    // TODO: @Select 어노테이션으로 전체 회원 조회 쿼리 작성
    List<Member> findAllMembers();
    
    // TODO: @Select 어노테이션으로 ID 조회 쿼리 작성
    Member findMemberById(@Param("id") Long id);
    
    // TODO: @Select 어노테이션으로 이메일 조회 쿼리 작성
    Member findMemberByEmail(@Param("email") String email);
    
    // TODO: @Select 어노테이션으로 이메일 중복 확인 쿼리 작성
    int countByEmail(@Param("email") String email);
    
    // TODO: @Select 어노테이션으로 이름 검색 쿼리 작성 (부분일치)
    List<Member> findByNameContaining(@Param("name") String name);
    
    // TODO: @Select 어노테이션으로 나이 범위 검색 쿼리 작성
    List<Member> findByAgeBetween(@Param("minAge") Integer minAge, @Param("maxAge") Integer maxAge);
    
    // TODO: @Select 어노테이션으로 성별 검색 쿼리 작성
    List<Member> findByGender(@Param("gender") String gender);
    
    // TODO: @Update 어노테이션으로 회원 정보 수정 쿼리 작성
    void updateMember(Member member);
    
    // TODO: @Delete 어노테이션으로 회원 삭제 쿼리 작성
    void deleteMember(@Param("id") Long id);
}