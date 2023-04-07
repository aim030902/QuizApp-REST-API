package uz.aim.quizapprestapi.dtos.project.quiz;

import lombok.*;
import uz.aim.quizapprestapi.domains.enums.project.Level;

/**
 * @author : Abbosbek Murodov
 * @since : 06/04/23 / 18:00
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class QuizCreateDTO {
    private Long subjectId;
    private Integer questionsCount;
    private String level;
    private String language;
}
