package uz.aim.quizapprestapi.domains.entity.project.quiz;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.aim.quizapprestapi.domains.Auditable;
import uz.aim.quizapprestapi.domains.entity.auth.User;
import uz.aim.quizapprestapi.domains.entity.project.question.Question;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * @author : Abbosbek Murodov
 * @since : 06/04/23 / 17:46
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "quiz_question")
@EntityListeners(value = AuditingEntityListener.class)
public class QuizQuestion extends Auditable {
    @ManyToOne
    private Quiz quiz;
    @ManyToOne
    private Question question;

    @Builder(builderMethodName = "childBuilder")
    public QuizQuestion(Long id, Timestamp createdAt, Timestamp updatedAt, User createdBy, User updatedBy, boolean deleted, Quiz quiz, Question question) {
        super(id, createdAt, updatedAt, createdBy, updatedBy, deleted);
        this.quiz = quiz;
        this.question = question;
    }
}
