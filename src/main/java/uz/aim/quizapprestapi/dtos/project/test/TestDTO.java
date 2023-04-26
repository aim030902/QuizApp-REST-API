package uz.aim.quizapprestapi.dtos.project.test;

import uz.aim.quizapprestapi.domains.enums.project.Language;
import uz.aim.quizapprestapi.domains.enums.project.Level;
import uz.aim.quizapprestapi.dtos.project.quiz.QuizDTO;

import java.sql.Timestamp;

/**
 * @author : Abbosbek Murodov
 * @since : 26/04/23 / 12:03
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

public record TestDTO(Long id, Long subjectId, QuizDTO quiz, int questionsSize, Level level, Language language, int solveTimeMinute, Long createdBy, Timestamp createdAt, Long updatedBy, Timestamp updatedAt) {
}
