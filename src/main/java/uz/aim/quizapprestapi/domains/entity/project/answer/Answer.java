package uz.aim.quizapprestapi.domains.entity.project.answer;

import javax.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.aim.quizapprestapi.domains.Auditable;
import uz.aim.quizapprestapi.domains.entity.project.question.Question;

/**
 * @author : Abbosbek Murodov
 * @since : 04/04/23 / 17:07
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "answer")
@EntityListeners(value = AuditingEntityListener.class)
public class Answer extends Auditable {
    @Column(nullable = false)
    private String content;
    @ManyToOne
    private Question question;
    private boolean isRight;
}
