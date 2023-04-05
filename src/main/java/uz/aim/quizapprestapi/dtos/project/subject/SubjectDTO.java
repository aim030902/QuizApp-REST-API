package uz.aim.quizapprestapi.dtos.project.subject;

import uz.aim.quizapprestapi.dtos.project.question.QuestionDTO;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author : Abbosbek Murodov
 * @since : 05/04/23 / 15:14
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

public record SubjectDTO(Long id, String name, String description, List<QuestionDTO> questionDTOS, Long createdBy, Timestamp createdAt, Long updatedBy, Timestamp updatedAt, boolean deleted) {

}
