package campus.membercampusstudy.controller;

import campus.membercampusstudy.dto.MemberDto;
import campus.membercampusstudy.service.IMemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "MyBatis 회원 관리", description = "MyBatis Mapper를 사용한 회원 관리 API")
@RestController
@RequestMapping("/api/mybatis/members")
@Slf4j
public class MyBatisMemberController {
    
    private final IMemberService memberService;
    
    public MyBatisMemberController(@Qualifier("myBatisMemberService") IMemberService memberService) {
        this.memberService = memberService;
    }
    
    @Operation(summary = "회원 가입", description = "새로운 회원을 등록합니다 (MyBatis 구현)")
    @ApiResponse(responseCode = "200", description = "회원가입 성공", 
                content = @Content(schema = @Schema(implementation = MemberDto.Response.class)))
    @ApiResponse(responseCode = "400", description = "잘못된 요청 (이메일 중복 등)")
    @ApiResponse(responseCode = "501", description = "구현 필요")
    @PostMapping
    public ResponseEntity<MemberDto.Response> createMember(
            @Parameter(description = "회원가입 정보", required = true)
            @RequestBody MemberDto.CreateRequest request) {
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
    
    @Operation(summary = "전체 회원 조회", description = "등록된 모든 회원 목록을 조회합니다 (MyBatis 구현)")
    @ApiResponse(responseCode = "200", description = "조회 성공",
                content = @Content(schema = @Schema(implementation = MemberDto.Response.class)))
    @ApiResponse(responseCode = "501", description = "구현 필요")
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
    
    @Operation(summary = "ID로 회원 조회", description = "회원 ID로 특정 회원을 조회합니다 (MyBatis 구현)")
    @ApiResponse(responseCode = "200", description = "조회 성공",
                content = @Content(schema = @Schema(implementation = MemberDto.Response.class)))
    @ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음")
    @ApiResponse(responseCode = "501", description = "구현 필요")
    @GetMapping("/{id}")
    public ResponseEntity<MemberDto.Response> getMemberById(
            @Parameter(description = "회원 ID", required = true) @PathVariable Long id) {
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
    
    @Operation(summary = "이메일로 회원 조회", description = "이메일 주소로 특정 회원을 조회합니다 (MyBatis 구현)")
    @ApiResponse(responseCode = "200", description = "조회 성공",
                content = @Content(schema = @Schema(implementation = MemberDto.Response.class)))
    @ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음")
    @ApiResponse(responseCode = "501", description = "구현 필요")
    @GetMapping("/email/{email}")
    public ResponseEntity<MemberDto.Response> getMemberByEmail(
            @Parameter(description = "이메일 주소", required = true) @PathVariable String email) {
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
    
    @Operation(summary = "이메일 중복 확인", description = "이메일 주소의 중복 여부를 확인합니다 (MyBatis 구현)")
    @ApiResponse(responseCode = "200", description = "확인 완료",
                content = @Content(schema = @Schema(implementation = Boolean.class)))
    @ApiResponse(responseCode = "501", description = "구현 필요")
    @GetMapping("/check-email/{email}")
    public ResponseEntity<Boolean> checkEmailExists(
            @Parameter(description = "확인할 이메일 주소", required = true) @PathVariable String email) {
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