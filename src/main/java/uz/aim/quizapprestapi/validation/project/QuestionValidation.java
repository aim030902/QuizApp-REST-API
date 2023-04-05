package uz.aim.quizapprestapi.validation.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.aim.quizapprestapi.domains.entity.project.question.Question;
import uz.aim.quizapprestapi.domains.enums.project.Language;
import uz.aim.quizapprestapi.domains.enums.project.Level;
import uz.aim.quizapprestapi.dtos.project.question.QuestionCreateDTO;
import uz.aim.quizapprestapi.dtos.project.subject.SubjectCreateDTO;
import uz.aim.quizapprestapi.exception.GenericConflictException;
import uz.aim.quizapprestapi.exception.GenericNotFoundException;
import uz.aim.quizapprestapi.repository.auth.UserRepository;
import uz.aim.quizapprestapi.repository.project.QuestionRepository;
import uz.aim.quizapprestapi.repository.project.SubjectRepository;

import javax.validation.ValidationException;
import java.util.Arrays;
import java.util.List;

/**
 * @author : Abbosbek Murodov
 * @since : 04/04/23 / 17:37
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */
@Component
public class QuestionValidation {
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private QuestionRepository questionRepository;

    public void validateOnSave(QuestionCreateDTO dto) {
        subjectRepository.findById(dto.getSubjectId()).orElseThrow(() -> new GenericNotFoundException("Subject not found"));

        List<Question> questions = questionRepository.findBySubjectId(dto.getSubjectId());
        if (questions.stream().anyMatch(question -> question.getContent().equals(dto.getContent()))) {
            throw new GenericConflictException("This is question already exists");
        }

        if (Arrays.stream(Level.values()).noneMatch(level -> level.name().equals(dto.getLevel()))) {
            throw new GenericNotFoundException("Level not found");
        }
        if (Arrays.stream(Language.values()).noneMatch(language -> language.name().equals(dto.getLanguage()))) {
            throw new GenericNotFoundException("Language not found");
        }
    }
}
