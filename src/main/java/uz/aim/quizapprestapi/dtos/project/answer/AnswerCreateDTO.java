package uz.aim.quizapprestapi.dtos.project.answer;

import lombok.*;
import uz.aim.quizapprestapi.domains.entity.project.question.Question;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

/**
 * @author : Abbosbek Murodov
 * @since : 05/04/23 / 15:23
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AnswerCreateDTO {
    private Long questionId;
    private String content;
    private boolean isRight;

    public AnswerCreateDTO(String content, boolean isRight) {
        this.content = content;
        this.isRight = isRight;
    }
}
