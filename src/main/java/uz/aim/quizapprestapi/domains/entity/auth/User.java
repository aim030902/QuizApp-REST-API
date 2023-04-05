package uz.aim.quizapprestapi.domains.entity.auth;

import javax.persistence.*;
import lombok.*;
import uz.aim.quizapprestapi.domains.enums.auth.UserStatus;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author : Abbosbek Murodov
 * @since : 04/04/23 / 16:54
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false)
    private String password;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status = UserStatus.NOT_ACTIVE;
    @ManyToMany(targetEntity = Role.class, cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;
    private int loginTryCount;
    private LocalDateTime lastLoginTime;
    private boolean deleted;

    public boolean isActive() {
        return UserStatus.ACTIVE.equals(this.status);
    }
}
