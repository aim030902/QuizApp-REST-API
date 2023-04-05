package uz.aim.quizapprestapi.services.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.aim.quizapprestapi.domains.entity.project.question.Question;
import uz.aim.quizapprestapi.dtos.project.question.QuestionCreateDTO;
import uz.aim.quizapprestapi.dtos.project.question.QuestionDTO;
import uz.aim.quizapprestapi.mapper.project.QuestionMapper;
import uz.aim.quizapprestapi.repository.project.QuestionRepository;
import uz.aim.quizapprestapi.repository.project.SubjectRepository;
import uz.aim.quizapprestapi.validation.project.QuestionValidation;

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
}

