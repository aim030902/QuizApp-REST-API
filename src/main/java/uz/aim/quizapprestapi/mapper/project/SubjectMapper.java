package uz.aim.quizapprestapi.mapper.project;

import org.mapstruct.*;
import uz.aim.quizapprestapi.domains.entity.project.subject.Subject;
import uz.aim.quizapprestapi.dtos.project.subject.SubjectCreateDTO;
import uz.aim.quizapprestapi.dtos.project.subject.SubjectDTO;
import uz.aim.quizapprestapi.dtos.project.subject.SubjectUpdateDTO;

import java.util.List;

/**
 * @author : Abbosbek Murodov
 * @since : 05/04/23 / 15:46
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = {QuestionMapper.class})
public interface SubjectMapper {
    Subject toEntity(SubjectCreateDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Subject partialUpdate(SubjectUpdateDTO blogDTO, @MappingTarget Subject subject);

    @Mappings(value = {
            @Mapping(target = "createdBy", source = "createdBy.id"),
            @Mapping(target = "updatedBy", source = "updatedBy.id"),
            @Mapping(target = "questionDTOS", source = "questions")
    })
    SubjectDTO toDTO(Subject entity);

    List<SubjectDTO> toDTOs(List<Subject> subjects);
}
