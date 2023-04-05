package uz.aim.quizapprestapi.dtos.project.subject;

import lombok.*;
import uz.aim.quizapprestapi.dtos.project.question.QuestionCreateDTO;

import java.util.List;

/**
 * @author : Abbosbek Murodov
 * @since : 05/04/23 / 15:11
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SubjectCreateDTO {
    private String name;
    private String description;
    private List<QuestionCreateDTO> questions;

    public SubjectCreateDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
