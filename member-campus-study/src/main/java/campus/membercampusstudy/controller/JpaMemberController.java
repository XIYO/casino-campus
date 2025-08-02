package campus.membercampusstudy.controller;

import campus.membercampusstudy.entity.Member;
import campus.membercampusstudy.entity.MemberProfile;
import campus.membercampusstudy.repository.MemberRepository;
import campus.membercampusstudy.repository.MemberProfileRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "JPA 회원 관리", description = "JPA Repository를 직접 사용한 회원 관리 API")
@RestController
@RequestMapping("/api/jpa/members")
@RequiredArgsConstructor
@Slf4j
public class JpaMemberController {
    
    private final MemberRepository memberRepository;
    private final MemberProfileRepository memberProfileRepository;
    
    @Operation(summary = "회원 가입", description = "새로운 회원을 등록합니다")
    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        log.info("JPA 회원가입 요청: {}", member.getEmail());
        
        // 이메일 중복 확인
        if (memberRepository.existsByEmail(member.getEmail())) {
            return ResponseEntity.badRequest().build();
        }
        
        Member savedMember = memberRepository.save(member);
        return ResponseEntity.ok(savedMember);
    }
    
    @Operation(summary = "전체 회원 조회", description = "등록된 모든 회원 목록을 조회합니다")
    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        log.info("JPA 전체 회원 조회 요청");
        List<Member> members = memberRepository.findAll();
        return ResponseEntity.ok(members);
    }
    
    @Operation(summary = "회원 상세 조회", description = "ID로 특정 회원을 조회합니다")
    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        log.info("JPA 회원 상세 조회 요청: {}", id);
        Optional<Member> member = memberRepository.findById(id);
        return member.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    @Operation(summary = "이메일로 회원 조회", description = "이메일로 회원을 조회합니다")
    @GetMapping("/email/{email}")
    public ResponseEntity<Member> getMemberByEmail(@PathVariable String email) {
        log.info("JPA 이메일 회원 조회 요청: {}", email);
        Optional<Member> member = memberRepository.findByEmail(email);
        return member.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    @Operation(summary = "이메일 중복 확인", description = "이메일 중복 여부를 확인합니다")
    @GetMapping("/email/{email}/exists")
    public ResponseEntity<Boolean> checkEmailExists(@PathVariable String email) {
        log.info("JPA 이메일 중복 확인: {}", email);
        boolean exists = memberRepository.existsByEmail(email);
        return ResponseEntity.ok(exists);
    }
    
    @Operation(summary = "회원 탈퇴", description = "회원을 삭제합니다")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        log.info("JPA 회원 탈퇴 요청: {}", id);
        
        if (!memberRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        // 프로필 먼저 삭제
        memberProfileRepository.deleteByMemberId(id);
        // 회원 삭제
        memberRepository.deleteById(id);
        
        return ResponseEntity.ok().build();
    }
    
    @Operation(summary = "프로필 등록/수정", description = "회원 프로필을 등록하거나 수정합니다")
    @PostMapping("/{id}/profile")
    public ResponseEntity<MemberProfile> saveProfile(@PathVariable Long id, @RequestBody MemberProfile profile) {
        log.info("JPA 프로필 등록/수정 요청: {}", id);
        
        if (!memberRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        // 회원 정보 설정
        Member member = memberRepository.findById(id).orElseThrow();
        profile.setMember(member);
        
        MemberProfile savedProfile = memberProfileRepository.save(profile);
        return ResponseEntity.ok(savedProfile);
    }
    
    @Operation(summary = "프로필 조회", description = "회원의 프로필을 조회합니다")
    @GetMapping("/{id}/profile")
    public ResponseEntity<MemberProfile> getProfile(@PathVariable Long id) {
        log.info("JPA 프로필 조회 요청: {}", id);
        
        Optional<MemberProfile> profile = memberProfileRepository.findByMemberId(id);
        return profile.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
}