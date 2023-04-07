package uz.aim.quizapprestapi.repository.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.aim.quizapprestapi.domains.entity.project.quiz.Quiz;
import uz.aim.quizapprestapi.domains.entity.project.quiz.QuizQuestion;

/**
 * @author : Abbosbek Murodov
 * @since : 07/04/23 / 10:55
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@Repository
public interface QuizQuestionRepository extends JpaRepository<QuizQuestion, Long > {
}
