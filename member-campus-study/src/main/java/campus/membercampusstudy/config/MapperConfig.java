package campus.membercampusstudy.config;

import campus.membercampusstudy.mapper.IMemberMapper;
import campus.membercampusstudy.mapper.MemberMapper;
import campus.membercampusstudy.mapper.MemberMapperRef;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

/**
 * 매퍼 구성 설정
 * <p>
 * 프로파일에 따라 다른 매퍼 구현체를 제공합니다.
 */
@Configuration
public class MapperConfig {
    
    /**
     * ref 프로파일: 완전히 구현된 매퍼
     */
    @Bean("memberMapperImpl")
    @Primary
    @Profile("ref")
    @ConditionalOnMissingBean(name = "memberMapperImpl")
    public IMemberMapper memberMapperRef(MemberMapperRef memberMapperRef) {
        return memberMapperRef;
    }
    
    /**
     * 기본 프로파일: 학습용 매퍼 (구현되지 않음)
     */
    @Bean("memberMapperImpl")
    @Primary
    @Profile("!ref")
    @ConditionalOnMissingBean(name = "memberMapperImpl")
    public IMemberMapper memberMapperDefault(MemberMapper memberMapper) {
        return memberMapper;
    }
}