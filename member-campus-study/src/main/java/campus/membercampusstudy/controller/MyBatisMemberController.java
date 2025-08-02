package campus.membercampusstudy.controller;

import campus.membercampusstudy.entity.Member;
import campus.membercampusstudy.entity.MemberProfile;
import campus.membercampusstudy.mapper.MemberMapperRef;
import campus.membercampusstudy.mapper.MemberProfileMapperRef;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "MyBatis 회원 관리", description = "MyBatis Mapper를 직접 사용한 회원 관리 API")
@RestController
@RequestMapping("/api/mybatis/members")
@RequiredArgsConstructor
@Profile("ref")  // ref 프로파일에서만 활성화
@Slf4j
public class MyBatisMemberController {
    
    private final MemberMapperRef memberMapper;
    private final MemberProfileMapperRef memberProfileMapper;
    
    @Operation(summary = "회원 가입", description = "새로운 회원을 등록합니다")
    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        log.info("MyBatis 회원가입 요청: {}", member.getEmail());
        
        // 이메일 중복 확인
        if (memberMapper.countByEmail(member.getEmail()) > 0) {
            return ResponseEntity.badRequest().build();
        }
        
        memberMapper.insertMember(member);
        return ResponseEntity.ok(member);
    }
    
    @Operation(summary = "전체 회원 조회", description = "등록된 모든 회원 목록을 조회합니다")
    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        log.info("MyBatis 전체 회원 조회 요청");
        List<Member> members = memberMapper.findAllMembers();
        return ResponseEntity.ok(members);
    }
    
    @Operation(summary = "회원 상세 조회", description = "ID로 특정 회원을 조회합니다")
    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        log.info("MyBatis 회원 상세 조회 요청: {}", id);
        Member member = memberMapper.findMemberById(id);
        return member != null ? ResponseEntity.ok(member) : ResponseEntity.notFound().build();
    }
    
    @Operation(summary = "이메일로 회원 조회", description = "이메일로 회원을 조회합니다")
    @GetMapping("/email/{email}")
    public ResponseEntity<Member> getMemberByEmail(@PathVariable String email) {
        log.info("MyBatis 이메일 회원 조회 요청: {}", email);
        Member member = memberMapper.findMemberByEmail(email);
        return member != null ? ResponseEntity.ok(member) : ResponseEntity.notFound().build();
    }
    
    @Operation(summary = "이메일 중복 확인", description = "이메일 중복 여부를 확인합니다")
    @GetMapping("/email/{email}/exists")
    public ResponseEntity<Boolean> checkEmailExists(@PathVariable String email) {
        log.info("MyBatis 이메일 중복 확인: {}", email);
        boolean exists = memberMapper.countByEmail(email) > 0;
        return ResponseEntity.ok(exists);
    }
    
    @Operation(summary = "회원 탈퇴", description = "회원을 삭제합니다")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        log.info("MyBatis 회원 탈퇴 요청: {}", id);
        
        Member member = memberMapper.findMemberById(id);
        if (member == null) {
            return ResponseEntity.notFound().build();
        }
        
        // 프로필 먼저 삭제
        memberProfileMapper.deleteProfileByMemberId(id);
        // 회원 삭제
        memberMapper.deleteMember(id);
        
        return ResponseEntity.ok().build();
    }
    
    @Operation(summary = "프로필 등록/수정", description = "회원 프로필을 등록하거나 수정합니다")
    @PostMapping("/{id}/profile")
    public ResponseEntity<MemberProfile> saveProfile(@PathVariable Long id, @RequestBody MemberProfile profile) {
        log.info("MyBatis 프로필 등록/수정 요청: {}", id);
        
        Member member = memberMapper.findMemberById(id);
        if (member == null) {
            return ResponseEntity.notFound().build();
        }
        
        // 회원 정보 설정
        profile.setMember(member);
        
        // 기존 프로필이 있는지 확인
        MemberProfile existingProfile = memberProfileMapper.findProfileByMemberId(id);
        if (existingProfile != null) {
            profile.setId(existingProfile.getId());
            memberProfileMapper.updateProfile(profile);
        } else {
            memberProfileMapper.insertProfile(profile);
        }
        
        return ResponseEntity.ok(profile);
    }
    
    @Operation(summary = "프로필 조회", description = "회원의 프로필을 조회합니다")
    @GetMapping("/{id}/profile")
    public ResponseEntity<MemberProfile> getProfile(@PathVariable Long id) {
        log.info("MyBatis 프로필 조회 요청: {}", id);
        
        MemberProfile profile = memberProfileMapper.findProfileByMemberId(id);
        return profile != null ? ResponseEntity.ok(profile) : ResponseEntity.notFound().build();
    }
}