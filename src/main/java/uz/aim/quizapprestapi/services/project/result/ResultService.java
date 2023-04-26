package uz.aim.quizapprestapi.services.project.result;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.aim.quizapprestapi.domains.entity.auth.User;
import uz.aim.quizapprestapi.domains.entity.project.answer.Answer;
import uz.aim.quizapprestapi.domains.entity.project.quiz.Quiz;
import uz.aim.quizapprestapi.domains.entity.project.quiz.QuizQuestion;
import uz.aim.quizapprestapi.domains.entity.project.test.Result;
import uz.aim.quizapprestapi.domains.entity.project.test.Test;
import uz.aim.quizapprestapi.dtos.project.result.QA;
import uz.aim.quizapprestapi.dtos.project.result.ResultCreateDTO;
import uz.aim.quizapprestapi.dtos.project.result.ResultDTO;
import uz.aim.quizapprestapi.exception.GenericNotFoundException;
import uz.aim.quizapprestapi.repository.auth.UserRepository;
import uz.aim.quizapprestapi.repository.project.ResultRepository;
import uz.aim.quizapprestapi.repository.project.TestRepository;
import uz.aim.quizapprestapi.validation.project.ResultValidation;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

/**
 * @author : Abbosbek Murodov
 * @since : 26/04/23 / 15:25
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@Service
public class ResultService {
    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResultValidation resultValidation;

    public ResultDTO result(@NonNull ResultCreateDTO dto) {
        resultValidation.validateOnChecking(dto);
        Result createResult = toEntity(dto);
        return toDTO(resultRepository.save(createResult));
    }

    private int trueAnswerSize(Quiz quiz, List<QA> qas) {
        int count = 0;
        for (QA qa : qas) {
            for (QuizQuestion question : quiz.getQuestions()) {
                if (qa.getQuestionId().equals(question.getId())) {
                    for (Answer answer : question.getQuestion().getAnswers()) {
                        if (answer.getIsRight()) {
                            if (answer.getId().equals(qa.getAnswerId())) {
                                count++;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    private Result toEntity(ResultCreateDTO dto) {
        Test foundTest = testRepository.findById(dto.getTestId()).orElseThrow(() -> new GenericNotFoundException("Test not found"));
        User foundUser = userRepository.findById(dto.getSolverId()).orElseThrow(() -> new GenericNotFoundException("User not found"));
        int trueAnswerSize = trueAnswerSize(foundTest.getQuiz(), dto.getAnswerList());

        return Result
                .builder()
                .solver(foundUser)
                .test(foundTest)
                .questionsSize(foundTest.getQuestionsSize())
                .trueAnswerCount(trueAnswerSize)
                .solveTimeMinute(foundTest.getSolveTimeMinute())
                .startDate(foundTest.getCreatedAt())
                .endDate(Timestamp.from(Instant.now()))
                .completed(true)
                .build();
    }

    public ResultDTO toDTO(Result entity) {
        return ResultDTO
                .builder()
                .id(entity.getId())
                .solverId(entity.getSolver().getId())
                .testId(entity.getTest().getId())
                .questionsSize(entity.getQuestionsSize())
                .trueAnswerSize(entity.getTrueAnswerCount())
                .solveTimeMinute(entity.getSolveTimeMinute())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .complete(entity.isCompleted())
                .build();
    }

}
