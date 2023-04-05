package uz.aim.quizapprestapi.configs.auditing;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import uz.aim.quizapprestapi.configs.security.user.UserDetails;
import uz.aim.quizapprestapi.domains.entity.auth.User;

import java.util.Optional;

public class SpringSecurityAuditAwareImpl implements AuditorAware<User> {
    @Override
    public Optional<User> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication == null
                || !authentication.isAuthenticated()
                || "anonymousUser".equals("" + authentication.getPrincipal()))){

            User user = ((UserDetails) authentication.getPrincipal()).authUser();
            return Optional.of(user);
        }
        return Optional.empty();
    }
}
