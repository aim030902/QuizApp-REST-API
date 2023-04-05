package uz.aim.quizapprestapi.validation.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.aim.quizapprestapi.dtos.project.subject.SubjectCreateDTO;
import uz.aim.quizapprestapi.exception.GenericConflictException;
import uz.aim.quizapprestapi.repository.auth.UserRepository;
import uz.aim.quizapprestapi.repository.project.SubjectRepository;

import javax.validation.ValidationException;

/**
 * @author : Abbosbek Murodov
 * @since : 04/04/23 / 17:37
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */
@Component
public class SubjectValidation {
    @Autowired
    private SubjectRepository repository;
    @Autowired
    private UserRepository userRepository;
    public void validateOnSave(SubjectCreateDTO dto) {
        if (repository.existsByName(dto.getName())) {
            throw new GenericConflictException("This is subject already exists");
        }

    }
}
