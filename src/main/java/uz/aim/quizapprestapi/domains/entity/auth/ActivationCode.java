package uz.aim.quizapprestapi.domains.entity.auth;

import javax.persistence.*;
import javax.validation.constraints.Future;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author : Abbosbek Murodov
 * @since : 04/04/23 / 17:11
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivationCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Future(message = "valid till must be future time")
    private LocalDateTime validTill;

    @Column(unique = true, nullable = false)
    private String activationLink;

    private boolean deleted;

    @Column(unique = true, nullable = false)
    private Long userId;
}
