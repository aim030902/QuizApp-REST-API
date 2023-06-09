package uz.aim.quizapprestapi.domains.entity.project.answer;

import javax.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.aim.quizapprestapi.domains.Auditable;
import uz.aim.quizapprestapi.domains.entity.auth.User;
import uz.aim.quizapprestapi.domains.entity.project.question.Question;

import java.sql.Timestamp;

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
    @Builder.Default
    private Boolean isRight = false;

    @Builder(builderMethodName = "childBuilder")
    public Answer(Long id, Timestamp createdAt, Timestamp updatedAt, User createdBy, User updatedBy, boolean deleted, String content, Question question, Boolean isRight) {
        super(id, createdAt, updatedAt, createdBy, updatedBy, deleted);
        this.content = content;
        this.question = question;
        this.isRight = isRight;
    }

}
