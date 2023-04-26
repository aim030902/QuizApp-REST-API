package uz.aim.quizapprestapi.domains.entity.project.test;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.aim.quizapprestapi.domains.entity.auth.User;
import uz.aim.quizapprestapi.domains.entity.project.quiz.Quiz;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author : Abbosbek Murodov
 * @since : 08/04/23 / 14:30
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "result")
@EntityListeners(value = AuditingEntityListener.class)
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User solver;
    @OneToOne
    private Test test;
    private int solveTimeMinute;
    private int questionsSize;
    private int trueAnswerCount;
    private Timestamp startDate;
    private Timestamp endDate;
    private boolean completed;

}
