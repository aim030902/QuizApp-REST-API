package uz.aim.quizapprestapi.services.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.aim.quizapprestapi.domains.entity.project.answer.Answer;
import uz.aim.quizapprestapi.dtos.project.answer.AnswerCreateDTO;
import uz.aim.quizapprestapi.dtos.project.answer.AnswerDTO;
import uz.aim.quizapprestapi.mapper.project.AnswerMapper;
import uz.aim.quizapprestapi.repository.project.AnswerRepository;
import uz.aim.quizapprestapi.repository.project.QuestionRepository;
import uz.aim.quizapprestapi.validation.project.AnswerValidation;

/**
 * @author : Abbosbek Murodov
 * @since : 05/04/23 / 15:51
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerMapper answerMapper;
    @Autowired
    private AnswerValidation answerValidation;

    public AnswerDTO save(AnswerCreateDTO dto) {
        answerValidation.validateOnSave(dto);
        Answer createdAnswer = answerMapper.toEntity(dto);
        createdAnswer.setQuestion(questionRepository.findById(dto.getQuestionId()).get());
        return answerMapper.toDTO(answerRepository.save(createdAnswer));
    }
}
