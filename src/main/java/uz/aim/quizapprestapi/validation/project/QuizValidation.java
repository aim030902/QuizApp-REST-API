package uz.aim.quizapprestapi.validation.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.aim.quizapprestapi.domains.enums.project.Language;
import uz.aim.quizapprestapi.domains.enums.project.Level;
import uz.aim.quizapprestapi.dtos.project.quiz.QuizCreateDTO;
import uz.aim.quizapprestapi.exception.GenericNotFoundException;
import uz.aim.quizapprestapi.repository.auth.UserRepository;
import uz.aim.quizapprestapi.repository.project.QuizRepository;
import uz.aim.quizapprestapi.repository.project.SubjectRepository;

import java.util.Arrays;

/**
 * @author : Abbosbek Murodov
 * @since : 07/04/23 / 10:54
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@Component
public class QuizValidation {
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private UserRepository userRepository;

    public void validateOnCreate(QuizCreateDTO dto) {
        subjectRepository.findById(dto.getSubjectId()).orElseThrow(() -> new GenericNotFoundException("Subject not found"));
        if (Arrays.stream(Level.values()).noneMatch(level -> level.name().equals(dto.getLevel()))) {
            throw new GenericNotFoundException("Level not found");
        }
        if (Arrays.stream(Language.values()).noneMatch(language -> language.name().equals(dto.getLanguage()))) {
            throw new GenericNotFoundException("Language not found");
        }

    }
}
