package ua.testing.periodicals.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

/**
 * The Thymeleaf config.
 */
@Configuration
public class ThymeleafConfig {

    /**
     * Spring security dialect getting.
     *
     * @return the spring security dialect
     */
    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }
}