package uz.aim.quizapprestapi.domains.entity.project.question;

import javax.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.aim.quizapprestapi.domains.Auditable;
import uz.aim.quizapprestapi.domains.entity.project.subject.Subject;
import uz.aim.quizapprestapi.domains.entity.project.answer.Answer;
import uz.aim.quizapprestapi.domains.enums.project.Language;
import uz.aim.quizapprestapi.domains.enums.project.Level;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Abbosbek Murodov
 * @since : 04/04/23 / 17:01
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "question")
@ToString
@EntityListeners(value = AuditingEntityListener.class)
public class Question extends Auditable {
    @ManyToOne
    private Subject subject;
    @Column(nullable = false)
    private String content;
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Builder.Default
    private List<Answer> answers = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private Level level;
    @Enumerated(EnumType.STRING)
    private Language language;

}
