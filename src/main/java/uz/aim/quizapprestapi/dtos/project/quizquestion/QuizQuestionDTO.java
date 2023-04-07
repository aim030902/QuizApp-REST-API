package uz.aim.quizapprestapi.dtos.project.quizquestion;

import uz.aim.quizapprestapi.dtos.project.question.QuestionDTO;

import java.sql.Timestamp;

/**
 * @author : Abbosbek Murodov
 * @since : 06/04/23 / 18:05
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

public record QuizQuestionDTO(Long quizId, Long id, QuestionDTO question, Long createdBy, Timestamp createdAt, Long updatedBy, Timestamp updatedAt) {

}
