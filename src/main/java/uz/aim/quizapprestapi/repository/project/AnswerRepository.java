package uz.aim.quizapprestapi.repository.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.aim.quizapprestapi.domains.entity.project.answer.Answer;
import uz.aim.quizapprestapi.domains.entity.project.subject.Subject;

import java.util.List;
import java.util.Optional;

/**
 * @author : Abbosbek Murodov
 * @since : 04/04/23 / 17:38
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByQuestionId(Long question_id);
}
