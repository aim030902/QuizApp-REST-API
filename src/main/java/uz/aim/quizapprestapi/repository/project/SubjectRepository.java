package uz.aim.quizapprestapi.repository.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.aim.quizapprestapi.domains.entity.project.subject.Subject;

import java.util.Optional;

/**
 * @author : Abbosbek Murodov
 * @since : 04/04/23 / 17:38
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    boolean existsByName(String name);
    Optional<Subject> findByName(String name);
}
