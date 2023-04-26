package uz.aim.quizapprestapi.validation.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.aim.quizapprestapi.domains.enums.project.Language;
import uz.aim.quizapprestapi.domains.enums.project.Level;
import uz.aim.quizapprestapi.dtos.project.test.TestCreateDTO;
import uz.aim.quizapprestapi.exception.GenericNotFoundException;
import uz.aim.quizapprestapi.repository.project.SubjectRepository;

import java.util.Arrays;

/**
 * @author : Abbosbek Murodov
 * @since : 26/04/23 / 12:20
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@Component
public class TestValidation {
    @Autowired
    private SubjectRepository subjectRepository;
    public void validateOnSave(TestCreateDTO dto) {
        if (!subjectRepository.existsById(dto.getSubjectId())) {
            throw new GenericNotFoundException("Subject not found!");
        }
        if (Arrays.stream(Level.values()).noneMatch(level -> level.name().equals(dto.getLevel()))) {
            throw new GenericNotFoundException("Level not found!");
        }
        if (Arrays.stream(Language.values()).noneMatch(language -> language.name().equals(dto.getLanguage()))) {
            throw new GenericNotFoundException("Language not found");
        }
    }
}
