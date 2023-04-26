package uz.aim.quizapprestapi.services.project;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.aim.quizapprestapi.domains.entity.project.quiz.Quiz;
import uz.aim.quizapprestapi.domains.entity.project.test.Test;
import uz.aim.quizapprestapi.dtos.project.quiz.QuizCreateDTO;
import uz.aim.quizapprestapi.dtos.project.test.TestCreateDTO;
import uz.aim.quizapprestapi.dtos.project.test.TestDTO;
import uz.aim.quizapprestapi.mapper.project.TestMapper;
import uz.aim.quizapprestapi.repository.project.SubjectRepository;
import uz.aim.quizapprestapi.repository.project.TestRepository;
import uz.aim.quizapprestapi.validation.project.TestValidation;

/**
 * @author : Abbosbek Murodov
 * @since : 26/04/23 / 11:48
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@Service
public class TestService {
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private TestMapper testMapper;
    @Autowired
    private TestValidation testValidation;
    @Autowired
    private QuizService quizService;

    public TestDTO save(@NonNull TestCreateDTO dto) {
        testValidation.validateOnSave(dto);
        Test createTest = testMapper.toEntity(dto);
        createTest.setQuiz(generateQuiz(dto));
        createTest.setSubject(subjectRepository.findById(dto.getSubjectId()).get());
        Test savedTest = testRepository.save(createTest);
        return testMapper.toDTO(savedTest);
    }

    private Quiz generateQuiz(TestCreateDTO dto) {
        return quizService.generateQuiz(
                QuizCreateDTO
                        .builder()
                        .subjectId(dto.getSubjectId())
                        .questionsCount(dto.getQuestionsSize())
                        .level(dto.getLevel())
                        .language(dto.getLanguage())
                        .build()
        );
    }
}
