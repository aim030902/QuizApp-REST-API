package uz.aim.quizapprestapi.dtos.project.question;

/**
 * @author : Abbosbek Murodov
 * @since : 06/04/23 / 14:24
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

public record QuestionUpdateDTO(Long id, Long subjectId, String content, String level, String language) {
}
