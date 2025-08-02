package campus.membercampusstudy.config;

import campus.membercampusstudy.service.IMemberService;
import campus.membercampusstudy.service.JpaMemberService;
import campus.membercampusstudy.service.MyBatisMemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 서비스 빈 설정 클래스
 * 
 * JPA와 MyBatis 서비스를 각각 빈으로 등록합니다.
 * JPA 서비스가 기본(@Primary)으로 설정됩니다.
 * 
 * @author XIYO
 * @version 1.0
 * @since 2025-08-02
 */
@Configuration
public class ServiceConfig {
    
    /**
     * JPA 서비스 (기본)
     */
    @Bean
    @Primary
    public IMemberService jpaMemberService(JpaMemberService jpaMemberService) {
        return jpaMemberService;
    }
    
    /**
     * MyBatis 서비스
     */
    @Bean("myBatisMemberService")
    public IMemberService myBatisMemberService(MyBatisMemberService myBatisMemberService) {
        return myBatisMemberService;
    }
}