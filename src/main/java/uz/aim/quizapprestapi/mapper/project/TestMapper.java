package uz.aim.quizapprestapi.mapper.project;

import org.mapstruct.*;
import uz.aim.quizapprestapi.domains.entity.project.test.Test;
import uz.aim.quizapprestapi.dtos.project.test.TestCreateDTO;
import uz.aim.quizapprestapi.dtos.project.test.TestDTO;

/**
 * @author : Abbosbek Murodov
 * @since : 26/04/23 / 12:12
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = {QuizMapper.class})
public interface TestMapper {
    Test toEntity(TestCreateDTO dto);
    @Mappings({
            @Mapping(target = "subjectId", source = "subject.id"),
            @Mapping(target = "createdBy", source = "createdBy.id"),
            @Mapping(target = "updatedBy", source = "updatedBy.id")
    })
    TestDTO toDTO(Test entity);

}
