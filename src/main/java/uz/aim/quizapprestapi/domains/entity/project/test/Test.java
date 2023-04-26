package uz.aim.quizapprestapi.domains.entity.project.test;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.aim.quizapprestapi.domains.Auditable;
import uz.aim.quizapprestapi.domains.entity.auth.User;
import uz.aim.quizapprestapi.domains.entity.project.quiz.Quiz;
import uz.aim.quizapprestapi.domains.entity.project.subject.Subject;
import uz.aim.quizapprestapi.domains.enums.project.Language;
import uz.aim.quizapprestapi.domains.enums.project.Level;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author : Abbosbek Murodov
 * @since : 26/04/23 / 11:50
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "test")
@EntityListeners(value = AuditingEntityListener.class)
public class Test extends Auditable {
    @ManyToOne
    private Subject subject;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Quiz quiz;
    private int questionsSize;
    @Enumerated(EnumType.STRING)
    private Level level;
    @Enumerated(EnumType.STRING)
    private Language language;
    private int solveTimeMinute;

    @Builder(builderMethodName = "childBuilder")
    public Test(Long id, Timestamp createdAt, Timestamp updatedAt, User createdBy, User updatedBy, boolean deleted, Subject subject, Quiz quiz, int questionsSize, Level level, Language language, int solveTimeMinute) {
        super(id, createdAt, updatedAt, createdBy, updatedBy, deleted);
        this.subject = subject;
        this.quiz = quiz;
        this.questionsSize = questionsSize;
        this.level = level;
        this.language = language;
        this.solveTimeMinute = solveTimeMinute;
    }
}
