package uz.aim.quizapprestapi.repository.project;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.aim.quizapprestapi.domains.entity.project.test.Test;

/**
 * @author : Abbosbek Murodov
 * @since : 26/04/23 / 11:48
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

public interface TestRepository extends JpaRepository<Test, Long> {

}
