package uz.aim.quizapprestapi.dtos.project.answer;

/**
 *  @author : Abbosbek Murodov
 *  @since  : 06/04/23 / 15:01
 *  Project : QuizApp-REST-API / IntelliJ IDEA
*/

public record AnswerUpdateDTO(Long id, Long questionId, String content, Boolean isRight) {
}
