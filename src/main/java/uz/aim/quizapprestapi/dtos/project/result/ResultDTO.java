package uz.aim.quizapprestapi.dtos.project.result;

import lombok.Builder;

import java.sql.Timestamp;

/**
 * @author : Abbosbek Murodov
 * @since : 26/04/23 / 15:27
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@Builder
public record ResultDTO(Long id, Long solverId, Long testId, int questionsSize, int trueAnswerSize, int solveTimeMinute, Timestamp startDate, Timestamp endDate, boolean complete) {
}
