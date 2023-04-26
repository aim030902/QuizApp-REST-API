package uz.aim.quizapprestapi.dtos.project.test;

import lombok.*;

/**
 * @author : Abbosbek Murodov
 * @since : 26/04/23 / 11:41
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TestCreateDTO {
    private Long subjectId;
    private int questionsSize;
    private String level;
    private String language;
    private int solveTimeMinute;
}
