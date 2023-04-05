package uz.aim.quizapprestapi.mapper.project;

import org.mapstruct.*;
import uz.aim.quizapprestapi.domains.entity.project.question.Question;
import uz.aim.quizapprestapi.dtos.project.question.QuestionCreateDTO;
import uz.aim.quizapprestapi.dtos.project.question.QuestionDTO;

/**
 * @author : Abbosbek Murodov
 * @since : 05/04/23 / 15:43
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {SubjectMapper.class}, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface QuestionMapper {
    Question toEntity(QuestionCreateDTO dto);
    @Mappings(value = {
            @Mapping(target = "createdBy", source = "createdBy.id"),
            @Mapping(target = "updatedBy", source = "updatedBy.id")
    })
    QuestionDTO toDTO(Question entity);
}
