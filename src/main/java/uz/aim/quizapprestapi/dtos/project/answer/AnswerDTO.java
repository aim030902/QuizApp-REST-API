package uz.aim.quizapprestapi.dtos.project.answer;

import uz.aim.quizapprestapi.dtos.project.question.QuestionDTO;

import java.sql.Timestamp;

/**
 * @author : Abbosbek Murodov
 * @since : 05/04/23 / 15:33
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

public record AnswerDTO(QuestionDTO questionDTO, Long id, String content, boolean isRight, Long createdBy, Timestamp createdAt, Long updatedBy, Timestamp updatedAt, boolean deleted) {
}
