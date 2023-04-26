package uz.aim.quizapprestapi.dtos.project.result;

import lombok.*;

import java.util.List;

/**
 * @author : Abbosbek Murodov
 * @since : 26/04/23 / 15:07
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResultCreateDTO {
    private Long solverId;
    private Long testId;
    private List<QA> answerList;

}
