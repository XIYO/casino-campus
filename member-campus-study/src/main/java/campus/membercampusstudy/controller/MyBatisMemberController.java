package campus.membercampusstudy.controller;

import campus.membercampusstudy.dto.MemberDto;
import campus.membercampusstudy.service.IMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mybatis/members")
@RequiredArgsConstructor
@Slf4j
public class MyBatisMemberController {
    
    @Qualifier("myBatisMemberService")
    private final IMemberService memberService;
    
    @PostMapping
    public ResponseEntity<MemberDto.Response> createMember(@RequestBody MemberDto.CreateRequest request) {
        log.info("MyBatis 회원가입 요청: {}", request.getEmail());
        
        try {
            MemberDto.Response response = memberService.createMember(request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            log.error("MyBatis 회원가입 실패: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (UnsupportedOperationException e) {
            log.error("MyBatis 구현이 필요합니다: {}", e.getMessage());
            return ResponseEntity.status(501).build(); // Not Implemented
        }
    }
    
    @GetMapping
    public ResponseEntity<List<MemberDto.Response>> getAllMembers() {
        log.info("MyBatis 전체 회원 조회 요청");
        
        try {
            List<MemberDto.Response> members = memberService.getAllMembers();
            return ResponseEntity.ok(members);
        } catch (UnsupportedOperationException e) {
            log.error("MyBatis 구현이 필요합니다: {}", e.getMessage());
            return ResponseEntity.status(501).build(); // Not Implemented
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MemberDto.Response> getMemberById(@PathVariable Long id) {
        log.info("MyBatis 회원 조회 요청: ID {}", id);
        
        try {
            MemberDto.Response response = memberService.getMemberById(id);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            log.error("MyBatis 회원 조회 실패: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (UnsupportedOperationException e) {
            log.error("MyBatis 구현이 필요합니다: {}", e.getMessage());
            return ResponseEntity.status(501).build(); // Not Implemented
        }
    }
    
    @GetMapping("/email/{email}")
    public ResponseEntity<MemberDto.Response> getMemberByEmail(@PathVariable String email) {
        log.info("MyBatis 회원 조회 요청: Email {}", email);
        
        try {
            MemberDto.Response response = memberService.getMemberByEmail(email);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            log.error("MyBatis 회원 조회 실패: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (UnsupportedOperationException e) {
            log.error("MyBatis 구현이 필요합니다: {}", e.getMessage());
            return ResponseEntity.status(501).build(); // Not Implemented
        }
    }
    
    @GetMapping("/check-email/{email}")
    public ResponseEntity<Boolean> checkEmailExists(@PathVariable String email) {
        log.info("MyBatis 이메일 중복 확인 요청: {}", email);
        
        try {
            boolean exists = memberService.existsByEmail(email);
            return ResponseEntity.ok(exists);
        } catch (UnsupportedOperationException e) {
            log.error("MyBatis 구현이 필요합니다: {}", e.getMessage());
            return ResponseEntity.status(501).build(); // Not Implemented
        }
    }
}