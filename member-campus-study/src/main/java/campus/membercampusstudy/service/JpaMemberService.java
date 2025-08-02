package campus.membercampusstudy.service;

import campus.membercampusstudy.dto.MemberDto;
import campus.membercampusstudy.entity.Member;
import campus.membercampusstudy.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * JPA 기반 회원 서비스 구현체
 * 
 * 이 클래스는 JPA를 사용하여 회원 관리 기능을 구현합니다.
 * Spring Data JPA Repository를 활용하여 데이터베이스 연산을 처리합니다.
 * 
 * @author XIYO
 * @version 1.0
 * @since 2025-08-02
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class JpaMemberService implements IMemberService {
    
    private final MemberRepository memberRepository;
    
    @Transactional
    public MemberDto.Response createMember(MemberDto.CreateRequest request) {
        log.info("JPA로 회원 생성 시도: {}", request.getEmail());
        
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다: " + request.getEmail());
        }
        
        Member member = request.toEntity();
        Member savedMember = memberRepository.save(member);
        
        log.info("JPA로 회원 생성 완료: ID {}", savedMember.getId());
        return MemberDto.Response.from(savedMember);
    }
    
    public List<MemberDto.Response> getAllMembers() {
        log.info("JPA로 전체 회원 조회");
        
        return memberRepository.findAll()
                .stream()
                .map(MemberDto.Response::from)
                .collect(Collectors.toList());
    }
    
    public MemberDto.Response getMemberById(Long id) {
        log.info("JPA로 회원 조회: ID {}", id);
        
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다: ID " + id));
        
        return MemberDto.Response.from(member);
    }
    
    public MemberDto.Response getMemberByEmail(String email) {
        log.info("JPA로 회원 조회: Email {}", email);
        
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다: Email " + email));
        
        return MemberDto.Response.from(member);
    }
    
    public boolean existsByEmail(String email) {
        log.info("JPA로 이메일 중복 검사: {}", email);
        
        return memberRepository.existsByEmail(email);
    }
}