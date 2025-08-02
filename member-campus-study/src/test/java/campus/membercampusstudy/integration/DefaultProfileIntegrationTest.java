package campus.membercampusstudy.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 기본 프로필 통합 테스트
 * 
 * 이 테스트는 기본 프로필(ref가 아닌 상태)에서 학습 목표가 달성되는지 검증합니다.
 * 기본 프로필에서는:
 * 1. 애플리케이션이 정상적으로 시작되어야 함 (컴파일 에러 없음)
 * 2. JPA 기능은 정상 작동해야 함
 * 3. MyBatis 기능은 런타임 에러가 발생해야 함 (학습자가 구현해야 함을 의미)
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("default")
class DefaultProfileIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * 기본 프로필에서 애플리케이션이 정상적으로 시작되는지 테스트
     * 
     * 예상 결과: 컨텍스트 로딩 성공 (컴파일 에러가 없어야 함)
     */
    @Test
    void testApplicationContext_WithDefaultProfile_ShouldLoad() {
        // Given & When: 스프링 컨텍스트가 로딩됨 (@SpringBootTest에 의해)
        
        // Then: TestRestTemplate이 정상적으로 주입되어야 함
        assertThat(restTemplate).isNotNull();
        
        // 이 테스트가 통과한다는 것은 애플리케이션이 컴파일 에러 없이 시작된다는 의미
        // 이는 학습 플랫폼의 핵심 요구사항 중 하나임
    }

    /**
     * 기본 프로필에서 JPA 기능이 정상적으로 작동하는지 테스트
     * 
     * 예상 결과: HTTP 200 OK (JPA는 프로필과 무관하게 항상 작동)
     */
    @Test
    void testJpaGetAllMembers_WithDefaultProfile_ShouldSucceed() {
        // Given: 기본 프로필이 활성화된 상태
        
        // When: JPA 전체 회원 조회 API 호출
        ResponseEntity<String> response = restTemplate.getForEntity(
            "/api/jpa/members", 
            String.class
        );
        
        // Then: 성공적으로 응답이 반환되어야 함 (200 OK)
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        
        // 응답 내용 검증 (더미 데이터 2명의 회원이 있어야 함)
        assertThat(response.getBody()).satisfiesAnyOf(
            body -> assertThat(body).contains("김철수"),  // 더미 데이터 확인
            body -> assertThat(body).contains("이영희"),  // 더미 데이터 확인
            body -> assertThat(body).contains("members")
        );
    }

    /**
     * 기본 프로필에서 MyBatis 전체 회원 조회가 런타임 에러를 발생시키는지 테스트
     * 
     * 예상 결과: HTTP 500 Internal Server Error 
     * (org.apache.ibatis.binding.BindingException: Invalid bound statement)
     * 
     * 이는 학습 플랫폼의 핵심 목표입니다:
     * - 학습자가 매퍼를 구현하지 않으면 런타임 에러가 발생
     * - 이를 통해 학습자는 자신이 구현해야 할 부분을 명확히 인식
     */
    @Test
    void testMyBatisGetAllMembers_WithDefaultProfile_ShouldFailWithRuntimeError() {
        // Given: 기본 프로필이 활성화된 상태 (MemberMapper는 구현되지 않음)
        
        // When: MyBatis 전체 회원 조회 API 호출
        ResponseEntity<String> response = restTemplate.getForEntity(
            "/api/mybatis/members", 
            String.class
        );
        
        // Then: 런타임 에러로 인한 500 에러가 발생해야 함
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        
        // 이 테스트가 통과한다는 것은 학습 플랫폼이 의도한 대로 작동한다는 의미:
        // 1. 애플리케이션은 시작되지만 (컴파일 에러 없음)
        // 2. 학습자가 구현하지 않은 부분에서 런타임 에러 발생
        // 3. 학습자는 에러 메시지를 통해 무엇을 구현해야 하는지 알 수 있음
    }

    /**
     * 기본 프로필에서 MyBatis ID로 회원 조회가 런타임 에러를 발생시키는지 테스트
     * 
     * 예상 결과: HTTP 500 Internal Server Error
     */
    @Test
    void testMyBatisGetMemberById_WithDefaultProfile_ShouldFailWithRuntimeError() {
        // Given: 기본 프로필이 활성화된 상태
        Long testId = 1L;
        
        // When: MyBatis ID로 회원 조회 API 호출
        ResponseEntity<String> response = restTemplate.getForEntity(
            "/api/mybatis/members/" + testId, 
            String.class
        );
        
        // Then: 런타임 에러로 인한 500 에러가 발생해야 함
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 기본 프로필에서 MyBatis 프로필 조회가 런타임 에러를 발생시키는지 테스트
     * 
     * 예상 결과: HTTP 500 Internal Server Error
     */
    @Test
    void testMyBatisGetProfileById_WithDefaultProfile_ShouldFailWithRuntimeError() {
        // Given: 기본 프로필이 활성화된 상태
        Long testMemberId = 1L;
        
        // When: MyBatis 프로필 조회 API 호출
        ResponseEntity<String> response = restTemplate.getForEntity(
            "/api/mybatis/members/" + testMemberId + "/profile", 
            String.class
        );
        
        // Then: 런타임 에러로 인한 500 에러가 발생해야 함
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 기본 프로필과 ref 프로필의 차이점을 명확히 보여주는 문서화 테스트
     * 
     * 이 테스트는 실제로는 실행되지 않지만, 학습 플랫폼의 동작 방식을 문서화합니다.
     */
    @Test
    void documentLearningPlatformBehavior() {
        // 학습 플랫폼의 핵심 동작 방식:
        
        // 1. 기본 프로필 (학습자 환경):
        //    - 애플리케이션 시작: ✅ 성공 (컴파일 에러 없음)
        //    - JPA 기능: ✅ 정상 작동
        //    - MyBatis 기능: ❌ 런타임 에러 (구현 필요)
        //    → 학습자는 MyBatis 매퍼를 구현해야 함을 인식
        
        // 2. ref 프로필 (참조 구현):
        //    - 애플리케이션 시작: ✅ 성공
        //    - JPA 기능: ✅ 정상 작동
        //    - MyBatis 기능: ✅ 정상 작동 (구현체 제공)
        //    → 학습자가 목표로 해야 할 완성된 상태
        
        // 3. 학습 과정:
        //    - 학습자는 기본 프로필에서 런타임 에러를 경험
        //    - 에러 메시지를 통해 구현해야 할 매퍼 메서드를 파악
        //    - MemberMapper와 ProfileMapper 인터페이스의 메서드들을 구현
        //    - 구현 완료 후 ref 프로필과 동일한 동작 달성
        
        assertThat(true).isTrue(); // 문서화 목적의 테스트
    }
}