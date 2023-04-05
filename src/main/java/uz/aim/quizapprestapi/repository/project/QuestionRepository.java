package uz.aim.quizapprestapi.repository.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.aim.quizapprestapi.domains.entity.project.question.Question;

import java.util.List;

/**
 * @author : Abbosbek Murodov
 * @since : 04/04/23 / 17:38
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findBySubjectId(Long subject_id);
}
