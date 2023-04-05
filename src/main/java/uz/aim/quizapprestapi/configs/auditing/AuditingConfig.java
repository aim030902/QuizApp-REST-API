package uz.aim.quizapprestapi.configs.auditing;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import uz.aim.quizapprestapi.domains.entity.auth.User;

@Configuration
@EnableJpaAuditing
public class AuditingConfig {
    @Bean
    public AuditorAware<User> auditorProvider() {
        return new SpringSecurityAuditAwareImpl();
    }

}
