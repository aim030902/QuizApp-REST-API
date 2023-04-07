package uz.aim.quizapprestapi.services.project;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.aim.quizapprestapi.domains.entity.project.answer.Answer;
import uz.aim.quizapprestapi.domains.entity.project.question.Question;
import uz.aim.quizapprestapi.dtos.project.answer.AnswerCreateDTO;
import uz.aim.quizapprestapi.dtos.project.answer.AnswerDTO;
import uz.aim.quizapprestapi.dtos.project.answer.AnswerDeleteDTO;
import uz.aim.quizapprestapi.dtos.project.answer.AnswerUpdateDTO;
import uz.aim.quizapprestapi.dtos.project.subject.SubjectUpdateDTO;
import uz.aim.quizapprestapi.exception.GenericNotFoundException;
import uz.aim.quizapprestapi.mapper.project.AnswerMapper;
import uz.aim.quizapprestapi.repository.project.AnswerRepository;
import uz.aim.quizapprestapi.repository.project.QuestionRepository;
import uz.aim.quizapprestapi.validation.project.AnswerValidation;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public List<AnswerDTO> saveAll(List<AnswerCreateDTO> dtoList) {
        List<AnswerDTO> answerDTOS = new ArrayList<>();
        dtoList.forEach(dto -> answerDTOS.add(save(dto)));
        return answerDTOS;
    }

    public AnswerDTO update(AnswerUpdateDTO dto) {
        Answer foundAnswer = getAnswer(dto.id());
        answerValidation.validateOnUpdate(dto);
        return answerMapper.toDTO(answerRepository.save(partialUpdate(foundAnswer, dto)));
    }

    public void delete(@NonNull AnswerDeleteDTO dto) {
        Answer foundAnswer = getAnswer(dto.id());
        answerRepository.delete(foundAnswer);
    }

    @Transactional
    public void deleteAll(@NonNull List<AnswerDeleteDTO> dtoList) {
        dtoList.forEach(this::delete);
    }

    public void softDelete(@NonNull AnswerDeleteDTO dto) {
        Answer foundAnswer = getAnswer(dto.id());
        foundAnswer.setDeleted(true);
        answerRepository.save(foundAnswer);
    }

    @Transactional
    public void softDeleteAll(@NonNull List<AnswerDeleteDTO> dtoList) {
        dtoList.forEach(this::softDelete);
    }

    public AnswerDTO get(@NonNull Long id) {
        Answer foundAnswer = getAnswer(id);
        return answerMapper.toDTO(foundAnswer);
    }

    public List<AnswerDTO> getAll(int page, int size) {
        List<Answer> answers = answerRepository.findAll(PageRequest.of(page, size)).getContent();
        return answerMapper.toDTOs(answers);
    }




    private Answer partialUpdate(Answer answer, AnswerUpdateDTO dto) {
        if (Objects.nonNull(dto.questionId())) {
            Question foundSubject = questionRepository.findById(dto.questionId()).orElseThrow(() -> new GenericNotFoundException("Subject not found"));
            answer.setQuestion(foundSubject);
        }
        if (Objects.nonNull(dto.content())) {
            answer.setContent(dto.content());
        }
        if (Objects.nonNull(dto.isRight()) && dto.isRight()) {
            List<Answer> answers = answerRepository.findByQuestionId(answer.getQuestion().getId());
            answers.forEach(ans -> ans.setIsRight(false));
            answer.setIsRight(true);
        }
        return answer;
    }

    private Answer getAnswer(@NotNull Long id) {
        return answerRepository.findById(id).orElseThrow(() -> new GenericNotFoundException("Answer not found by id -> " + id));
    }
}
