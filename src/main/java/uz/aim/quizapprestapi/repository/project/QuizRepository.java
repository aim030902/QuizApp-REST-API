package uz.aim.quizapprestapi.repository.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.aim.quizapprestapi.domains.entity.project.question.Question;
import uz.aim.quizapprestapi.domains.entity.project.quiz.Quiz;

import java.util.List;
import java.util.Optional;

/**
 * @author : Abbosbek Murodov
 * @since : 07/04/23 / 10:55
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long > {
    Optional<Quiz> findQuizById(Long id);
}
