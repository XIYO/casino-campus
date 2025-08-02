package campus.membercampusstudy.mapper;

import campus.membercampusstudy.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 회원 매퍼 인터페이스
 * <p>
 * MyBatis 매퍼의 공통 인터페이스입니다.
 * 학습용(MemberMapper)과 구현체(MemberMapperRef) 모두 이 인터페이스를 구현합니다.
 * IMember 인터페이스를 상속받아 JPA Repository와 일관된 인터페이스를 제공합니다.
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