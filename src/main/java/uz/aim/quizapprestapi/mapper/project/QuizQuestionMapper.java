package uz.aim.quizapprestapi.mapper.project;

import org.mapstruct.*;
import uz.aim.quizapprestapi.domains.entity.project.quiz.QuizQuestion;
import uz.aim.quizapprestapi.dtos.project.quizquestion.QuizQuestionDTO;

/**
 * @author : Abbosbek Murodov
 * @since : 06/04/23 / 18:13
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = {QuestionMapper.class})

public interface QuizQuestionMapper {
    @Mappings(value = {
            @Mapping(target = "createdBy", source = "createdBy.id"),
            @Mapping(target = "updatedBy", source = "updatedBy.id"),
            @Mapping(target = "quizId", source = "quiz.id")
    })
    QuizQuestionDTO toDTO(QuizQuestion entity);
}
