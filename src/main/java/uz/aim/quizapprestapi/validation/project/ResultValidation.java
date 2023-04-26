package uz.aim.quizapprestapi.validation.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.aim.quizapprestapi.domains.entity.project.test.Test;
import uz.aim.quizapprestapi.dtos.project.result.ResultCreateDTO;
import uz.aim.quizapprestapi.exception.GenericNotFoundException;
import uz.aim.quizapprestapi.repository.auth.UserRepository;
import uz.aim.quizapprestapi.repository.project.QuizRepository;
import uz.aim.quizapprestapi.repository.project.TestRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

/**
 * @author : Abbosbek Murodov
 * @since : 26/04/23 / 15:28
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@Component
public class ResultValidation {
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private UserRepository userRepository;

    public void validateOnChecking(ResultCreateDTO dto) {

        Test foundTest = testRepository.findById(dto.getTestId()).orElseThrow(() -> new GenericNotFoundException("Test not found by id -> " + dto.getTestId()));

        if (foundTest.getCreatedAt().toLocalDateTime().plus(foundTest.getSolveTimeMinute(), ChronoUnit.MINUTES).isAfter(LocalDateTime.now())) {
            throw new RuntimeException("Test time is over");
        }
        if (!userRepository.existsById(dto.getSolverId())) {
            throw new GenericNotFoundException("User not found by id -> " + dto.getSolverId());
        }

    }
}
