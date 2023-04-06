package uz.aim.quizapprestapi.validation.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.aim.quizapprestapi.dtos.project.answer.AnswerCreateDTO;
import uz.aim.quizapprestapi.dtos.project.question.QuestionCreateDTO;
import uz.aim.quizapprestapi.dtos.project.subject.SubjectCreateDTO;
import uz.aim.quizapprestapi.dtos.project.subject.SubjectUpdateDTO;
import uz.aim.quizapprestapi.exception.GenericConflictException;
import uz.aim.quizapprestapi.repository.auth.UserRepository;
import uz.aim.quizapprestapi.repository.project.SubjectRepository;

import javax.validation.ValidationException;
import java.util.*;

/**
 * @author : Abbosbek Murodov
 * @since : 04/04/23 / 17:37
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */
@Component
public class SubjectValidation {
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private UserRepository userRepository;
    public void validateOnSave(SubjectCreateDTO dto) {
        if (subjectRepository.existsByName(dto.getName())) {
            throw new GenericConflictException("This is subject already exists");
        }

        if (!(Objects.isNull(dto.getQuestions()) || dto.getQuestions().isEmpty())) {
            List<String> list = new ArrayList<>();
            dto.getQuestions().forEach(question -> list.add(question.getContent()));
            if (hasDuplicate(list)) {
                throw new GenericConflictException("There are duplicate questions !");
            }
            list.clear();
            for (QuestionCreateDTO dtoQuestion : dto.getQuestions()) {
                if (!(Objects.isNull(dtoQuestion.getAnswers()) || dtoQuestion.getAnswers().isEmpty())) {
                    dtoQuestion.getAnswers().forEach(dtoAnswer -> list.add(dtoAnswer.getContent()));
                    if (hasDuplicate(list)) {
                        throw new GenericConflictException("There are duplicate answers to one question !");
                    }
                }
                list.clear();
            }
            List<Boolean> booleanList = new ArrayList<>();
            for (QuestionCreateDTO dtoQuestion : dto.getQuestions()) {
                if (!(Objects.isNull(dtoQuestion.getAnswers()) || dtoQuestion.getAnswers().isEmpty())) {
                    for (AnswerCreateDTO answerCreateDTO : dtoQuestion.getAnswers()) {
                        if (answerCreateDTO.getIsRight()) {
                            booleanList.add(true);
                            if (booleanList.size() > 1) {
                                throw new GenericConflictException("One question has many correct answers");
                            }
                        }
                    }
                    booleanList.clear();
                }
            }
        }

    }

    public void validateOnUpdate(SubjectUpdateDTO dto) {
        if (subjectRepository.existsByName(dto.name())) {
            throw new GenericConflictException("This is subject name already exists");
        }
    }

    private <T> boolean hasDuplicate(List<T> list) {
        Set<T> set = new HashSet<>(list);
        return !(set.size() == list.size());
    }
}
