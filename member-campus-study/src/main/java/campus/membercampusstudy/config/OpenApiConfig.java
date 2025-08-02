package campus.membercampusstudy.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * OpenAPI 설정 클래스
 * <p>
 * Swagger UI 문서 생성을 위한 설정
 * 
 * @author XIYO
 * @since 2025-08-02
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI memberCampusOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Member Campus Study API")
                        .description("JPA와 MyBatis 쿼리 학습을 위한 회원 관리 API")
                        .version("1.0.0")
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("개발 서버")));
    }
}