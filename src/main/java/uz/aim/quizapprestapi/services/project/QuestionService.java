package uz.aim.quizapprestapi.services.project;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.aim.quizapprestapi.domains.entity.project.question.Question;
import uz.aim.quizapprestapi.domains.entity.project.subject.Subject;
import uz.aim.quizapprestapi.dtos.project.question.QuestionCreateDTO;
import uz.aim.quizapprestapi.dtos.project.question.QuestionDTO;
import uz.aim.quizapprestapi.dtos.project.question.QuestionUpdateDTO;
import uz.aim.quizapprestapi.exception.GenericNotFoundException;
import uz.aim.quizapprestapi.mapper.project.QuestionMapper;
import uz.aim.quizapprestapi.repository.project.QuestionRepository;
import uz.aim.quizapprestapi.repository.project.SubjectRepository;
import uz.aim.quizapprestapi.validation.project.QuestionValidation;

import java.util.List;
import java.util.Objects;

/**
 * @author : Abbosbek Murodov
 * @since : 05/04/23 / 16:14
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private QuestionValidation questionValidation;
    @Autowired
    private QuestionMapper questionMapper;

    public QuestionDTO save(QuestionCreateDTO dto) {
        questionValidation.validateOnSave(dto);
        Question createdQuestion = questionMapper.toEntity(dto);
        createdQuestion.setSubject(subjectRepository.findById(dto.getSubjectId()).get());
        createdQuestion.getAnswers().forEach(answer -> answer.setQuestion(createdQuestion));
        return questionMapper.toDTO(questionRepository.save(createdQuestion));
    }

    public QuestionDTO update(QuestionUpdateDTO dto) {
        Question foundQuestion = questionRepository.findById(dto.id()).orElseThrow(() -> new GenericNotFoundException("Question not found by id -> " + dto.id()));
        updateSubjectInQuestionBySubjectId(foundQuestion, dto.subjectId());
        questionValidation.validateOnUpdate(dto);
        return questionMapper.toDTO(questionRepository.save(questionMapper.partialUpdate(dto, foundQuestion)));
    }

    public void delete(@NonNull Long id) {
        Question foundQuestion = questionRepository.findById(id).orElseThrow(() -> new GenericNotFoundException("Question not found by id -> " + id));
        questionRepository.delete(foundQuestion);
    }

    public void softDelete(@NonNull Long id) {
        Question foundQuestion = questionRepository.findById(id).orElseThrow(() -> new GenericNotFoundException("Question not found by id -> " + id));
        foundQuestion.setDeleted(true);
        questionRepository.save(foundQuestion);
    }

    public QuestionDTO get(@NonNull Long id) {
        Question foundQuestion = questionRepository.findById(id).orElseThrow(() -> new GenericNotFoundException("Question not found"));
        return questionMapper.toDTO(foundQuestion);
    }

    public List<QuestionDTO> getAll(int page, int size) {
        List<Question> questions = questionRepository.findAll(PageRequest.of(page, size)).getContent();
        return questionMapper.toDTOs(questions);
    }

    private void updateSubjectInQuestionBySubjectId(Question question, Long subjectId) {
        if (Objects.nonNull(subjectId)) {
            Subject foundSubject = subjectRepository.findById(subjectId).orElseThrow(() -> new GenericNotFoundException("Subject not found"));
            question.setSubject(foundSubject);
        }
    }
}

