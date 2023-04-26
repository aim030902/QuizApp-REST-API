package uz.aim.quizapprestapi.dtos.project.result;

import lombok.*;

/**
 * @author : Abbosbek Murodov
 * @since : 26/04/23 / 16:33
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class QA {
    private Long questionId;
    private Long answerId;
}
