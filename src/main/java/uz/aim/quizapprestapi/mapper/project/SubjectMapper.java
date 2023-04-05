package uz.aim.quizapprestapi.mapper.project;

import org.mapstruct.*;
import uz.aim.quizapprestapi.domains.entity.project.subject.Subject;
import uz.aim.quizapprestapi.dtos.project.subject.SubjectCreateDTO;
import uz.aim.quizapprestapi.dtos.project.subject.SubjectDTO;

/**
 * @author : Abbosbek Murodov
 * @since : 05/04/23 / 15:46
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface SubjectMapper {
    Subject toEntity(SubjectCreateDTO dto);

    @Mappings(value = {
            @Mapping(target = "createdBy", source = "createdBy.id"),
            @Mapping(target = "updatedBy", source = "updatedBy.id")
    })
    SubjectDTO toDTO(Subject entity);
}
