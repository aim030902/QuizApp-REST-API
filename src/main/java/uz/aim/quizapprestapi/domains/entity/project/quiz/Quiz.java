package uz.aim.quizapprestapi.domains.entity.project.quiz;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.aim.quizapprestapi.domains.Auditable;
import uz.aim.quizapprestapi.domains.entity.auth.User;
import uz.aim.quizapprestapi.domains.entity.project.question.Question;
import uz.aim.quizapprestapi.domains.entity.project.subject.Subject;
import uz.aim.quizapprestapi.domains.enums.project.Language;
import uz.aim.quizapprestapi.domains.enums.project.Level;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author : Abbosbek Murodov
 * @since : 06/04/23 / 17:26
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "quiz")
@EntityListeners(value = AuditingEntityListener.class)
public class Quiz extends Auditable {

    @ManyToOne
    private Subject subject;
    private int questionsCount;
    @OneToMany(mappedBy = "quiz", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<QuizQuestion> questions;
    @Enumerated(EnumType.STRING)
    private Level level;
    @Enumerated(EnumType.STRING)
    private Language language;

    @Builder(builderMethodName = "childBuilder")
    public Quiz(Long id, Timestamp createdAt, Timestamp updatedAt, User createdBy, User updatedBy, boolean deleted, Subject subject, int questionsCount, List<QuizQuestion> questions, Level level, Language language) {
        super(id, createdAt, updatedAt, createdBy, updatedBy, deleted);
        this.subject = subject;
        this.questionsCount = questionsCount;
        this.questions = questions;
        this.level = level;
        this.language = language;
    }
}
