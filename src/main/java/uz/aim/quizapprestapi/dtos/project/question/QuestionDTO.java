package uz.aim.quizapprestapi.dtos.project.question;

import uz.aim.quizapprestapi.domains.enums.project.Language;
import uz.aim.quizapprestapi.domains.enums.project.Level;
import uz.aim.quizapprestapi.dtos.project.subject.SubjectDTO;
import uz.aim.quizapprestapi.dtos.project.answer.AnswerDTO;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author : Abbosbek Murodov
 * @since : 05/04/23 / 15:16
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

public record QuestionDTO(Long id, SubjectDTO subjectDTO, String content, List<AnswerDTO> answerDTOS, Level level, Language language, Long createdBy, Timestamp createdAt, Long updatedBy, Timestamp updatedAt, boolean deleted) {
}
