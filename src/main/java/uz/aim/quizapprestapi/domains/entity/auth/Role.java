package uz.aim.quizapprestapi.domains.entity.auth;

import javax.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import uz.aim.quizapprestapi.domains.enums.auth.Permission;
import uz.aim.quizapprestapi.domains.enums.auth.RoleCode;

import java.util.Set;

/**
 * @author : Abbosbek Murodov
 * @since : 04/04/23 / 16:58
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleCode code;
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)

    private Set<Permission> permissions;
    private boolean deleted;

    @Override
    public String getAuthority() {
        return "ROLE_" + this.name;
    }
}
