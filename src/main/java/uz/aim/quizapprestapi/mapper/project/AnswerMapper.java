package uz.aim.quizapprestapi.mapper.project;

import org.mapstruct.*;
import uz.aim.quizapprestapi.domains.entity.project.answer.Answer;
import uz.aim.quizapprestapi.dtos.project.answer.AnswerCreateDTO;
import uz.aim.quizapprestapi.dtos.project.answer.AnswerDTO;

/**
 * @author : Abbosbek Murodov
 * @since : 05/04/23 / 15:39
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {QuestionMapper.class}, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AnswerMapper {
    Answer toEntity(AnswerCreateDTO dto);

    @Mappings(value = {
            @Mapping(target = "createdBy", source = "createdBy.id"),
            @Mapping(target = "updatedBy", source = "updatedBy.id"),
            @Mapping(target = "questionId", source = "question.id")
    })
    AnswerDTO toDTO(Answer entity);
}
