package uz.aim.quizapprestapi.validation.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.aim.quizapprestapi.domains.entity.project.answer.Answer;
import uz.aim.quizapprestapi.dtos.project.answer.AnswerCreateDTO;
import uz.aim.quizapprestapi.exception.GenericConflictException;
import uz.aim.quizapprestapi.exception.GenericNotFoundException;
import uz.aim.quizapprestapi.repository.project.AnswerRepository;
import uz.aim.quizapprestapi.repository.project.QuestionRepository;

import java.util.List;

/**
 * @author : Abbosbek Murodov
 * @since : 05/04/23 / 15:53
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@Component
public class AnswerValidation {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;

    public void validateOnSave(AnswerCreateDTO dto) {
        questionRepository.findById(dto.getQuestionId()).orElseThrow(() -> new GenericNotFoundException("Question not found"));
        List<Answer> answers = answerRepository.findByQuestionId(dto.getQuestionId());
        if (answers.stream().anyMatch(answer -> answer.getContent().equals(dto.getContent()))) {
            throw new GenericConflictException("This is answer already exists");
        }
        if (dto.isRight()) {
            if (answers.stream().anyMatch(Answer::isRight)) {
                throw new GenericConflictException("True answer already exists !");
            }
        }
    }
}
