package campus.membercampusstudy.service;

import campus.membercampusstudy.dto.MemberDto;

import java.util.List;

/**
 * 회원 관리 서비스의 기본 동작을 정의하는 인터페이스
 * 
 * 이 인터페이스는 회원 가입, 조회 등의 기본 동작을 정의합니다.
 * 모든 회원 서비스 구현체는 이 인터페이스를 구현해야 합니다.
 * 
 * @author XIYO
 * @version 1.0
 * @since 2025-08-02
 */
public interface IMemberService {
    
    /**
     * 새로운 회원을 등록합니다.
     * 
     * @param request 회원가입 요청 정보
     * @return 등록된 회원 정보
     * @throws IllegalArgumentException 이메일이 이미 존재하는 경우
     */
    MemberDto.Response createMember(MemberDto.CreateRequest request);
    
    /**
     * 모든 회원 목록을 조회합니다.
     * 
     * @return 전체 회원 목록
     */
    List<MemberDto.Response> getAllMembers();
    
    /**
     * ID로 회원을 조회합니다.
     * 
     * @param id 회원 ID
     * @return 조회된 회원 정보
     * @throws IllegalArgumentException 회원을 찾을 수 없는 경우
     */
    MemberDto.Response getMemberById(Long id);
    
    /**
     * 이메일로 회원을 조회합니다.
     * 
     * @param email 회원 이메일
     * @return 조회된 회원 정보
     * @throws IllegalArgumentException 회원을 찾을 수 없는 경우
     */
    MemberDto.Response getMemberByEmail(String email);
    
    /**
     * 이메일 중복 여부를 확인합니다.
     * 
     * @param email 확인할 이메일
     * @return 중복 여부 (true: 중복, false: 사용 가능)
     */
    boolean existsByEmail(String email);
}