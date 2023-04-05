package uz.aim.quizapprestapi.validation.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.aim.quizapprestapi.dtos.auth.RegisterDTO;
import uz.aim.quizapprestapi.exception.GenericConflictException;
import uz.aim.quizapprestapi.repository.auth.UserRepository;

/**
 * @author : Abbosbek Murodov
 * @since : 02/04/23 / 11:29
 * Project : Trello-REST-API / IntelliJ IDEA
 */

@Component
public class AuthValidation {
    @Autowired
    private UserRepository userRepository;
    public void validateOnRegister(RegisterDTO dto) {
        boolean isValid = userRepository.existsByEmail(dto.email());
        if (isValid) {
            throw new GenericConflictException("This is email already exists");
        }
    }
}
