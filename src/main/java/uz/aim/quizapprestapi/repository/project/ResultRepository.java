package uz.aim.quizapprestapi.repository.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.aim.quizapprestapi.domains.entity.project.test.Result;

/**
 * @author : Abbosbek Murodov
 * @since : 26/04/23 / 15:26
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
}
