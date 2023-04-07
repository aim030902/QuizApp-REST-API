package uz.aim.quizapprestapi.dtos.project.quiz;

import uz.aim.quizapprestapi.domains.enums.project.Language;
import uz.aim.quizapprestapi.domains.enums.project.Level;
import uz.aim.quizapprestapi.dtos.project.quizquestion.QuizQuestionDTO;
import uz.aim.quizapprestapi.dtos.project.subject.SubjectDTO;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author : Abbosbek Murodov
 * @since : 06/04/23 / 18:00
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

public record QuizDTO(Long id, Long subjectId, int questionsCount, List<QuizQuestionDTO> questions, Level level, Language language, Long createdBy, Timestamp createdAt, Long updatedBy, Timestamp updatedAt, boolean deleted) {

}
