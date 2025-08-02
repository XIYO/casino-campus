package campus.membercampusstudy.integration;

import campus.membercampusstudy.entity.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ref 프로필 통합 테스트
 * 
 * 이 테스트는 ref 프로필에서 MyBatis 기능이 정상적으로 작동하는지 검증합니다.
 * ref 프로필에서는 MemberMapperRef와 ProfileMapperRef가 활성화되어 모든 기능이 작동해야 합니다.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("ref")
class ProfileBasedIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;
    
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * ref 프로필에서 MyBatis 전체 회원 조회가 정상적으로 작동하는지 테스트
     * 
     * 예상 결과: HTTP 200 OK (초기 상태이므로 빈 배열 또는 정상 응답)
     */
    @Test
    void testMyBatisGetAllMembers_WithRefProfile_ShouldSucceed() {
        // Given: ref 프로필이 활성화된 상태
        
        // When: MyBatis 전체 회원 조회 API 호출
        ResponseEntity<String> response = restTemplate.getForEntity(
            "/api/mybatis/members", 
            String.class
        );
        
        // Then: 성공적으로 응답이 반환되어야 함 (200 OK)
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        
        // 응답 내용 검증 (초기 상태이므로 빈 배열 "[]" 예상)
        assertThat(response.getBody()).satisfiesAnyOf(
            body -> assertThat(body).contains("[]"),
            body -> assertThat(body).contains("members")
        );
    }

    /**
     * ref 프로필에서 MyBatis ID로 회원 조회가 정상적으로 작동하는지 테스트
     * 
     * 예상 결과: HTTP 404 NOT FOUND (존재하지 않는 ID이므로)
     */
    @Test
    void testMyBatisGetMemberById_WithRefProfile_ShouldReturnNotFound() {
        // Given: ref 프로필이 활성화된 상태
        Long nonExistentId = 999L;
        
        // When: MyBatis ID로 회원 조회 API 호출
        ResponseEntity<String> response = restTemplate.getForEntity(
            "/api/mybatis/members/" + nonExistentId, 
            String.class
        );
        
        // Then: 존재하지 않는 ID이므로 404 반환
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    /**
     * ref 프로필에서 MyBatis 프로필 조회가 정상적으로 작동하는지 테스트
     * 
     * 예상 결과: HTTP 404 NOT FOUND (존재하지 않는 회원 ID이므로)
     */
    @Test
    void testMyBatisGetProfileById_WithRefProfile_ShouldReturnNotFound() {
        // Given: ref 프로필이 활성화된 상태
        Long nonExistentMemberId = 999L;
        
        // When: MyBatis 프로필 조회 API 호출 (존재하지 않는 회원 ID로)
        ResponseEntity<String> response = restTemplate.getForEntity(
            "/api/mybatis/members/" + nonExistentMemberId + "/profile", 
            String.class
        );
        
        // Then: 존재하지 않는 회원 ID이므로 404 반환
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    /**
     * ref 프로필에서 JPA 기능도 정상적으로 작동하는지 테스트
     * 
     * 예상 결과: HTTP 200 OK (JPA는 프로필과 무관하게 항상 작동)
     */
    @Test
    void testJpaGetAllMembers_WithRefProfile_ShouldSucceed() {
        // Given: ref 프로필이 활성화된 상태
        
        // When: JPA 전체 회원 조회 API 호출
        ResponseEntity<String> response = restTemplate.getForEntity(
            "/api/jpa/members", 
            String.class
        );
        
        // Then: 성공적으로 응답이 반환되어야 함 (200 OK)
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        
        // 응답 내용 검증 (초기 상태이므로 빈 배열 "[]" 예상)
        assertThat(response.getBody()).satisfiesAnyOf(
            body -> assertThat(body).contains("[]"),
            body -> assertThat(body).contains("members")
        );
    }

    /**
     * ref 프로필에서 애플리케이션이 정상적으로 시작되는지 테스트
     * 
     * 예상 결과: 컨텍스트 로딩 성공
     */
    @Test
    void testApplicationContext_WithRefProfile_ShouldLoad() {
        // Given & When: 스프링 컨텍스트가 로딩됨 (@SpringBootTest에 의해)
        
        // Then: TestRestTemplate이 정상적으로 주입되어야 함
        assertThat(restTemplate).isNotNull();
        assertThat(objectMapper).isNotNull();
    }
}