package uz.aim.quizapprestapi.dtos.project.question;

import lombok.*;
import uz.aim.quizapprestapi.dtos.project.answer.AnswerCreateDTO;

import java.util.List;

/**
 * @author : Abbosbek Murodov
 * @since : 05/04/23 / 15:13
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class QuestionCreateDTO {
    private Long subjectId;
    private String content;
    private List<AnswerCreateDTO> answers;
    private String level;
    private String language;

    public QuestionCreateDTO(String content, List<AnswerCreateDTO> answers, String level, String language) {
        this.content = content;
        this.answers = answers;
        this.level = level;
        this.language = language;
    }

    public QuestionCreateDTO(Long subjectId, String content, String level, String language) {
        this.subjectId = subjectId;
        this.content = content;
        this.level = level;
        this.language = language;
    }



}
