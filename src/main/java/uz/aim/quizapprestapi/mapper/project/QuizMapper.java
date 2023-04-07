package uz.aim.quizapprestapi.mapper.project;

import org.mapstruct.*;
import org.springframework.data.domain.Page;
import uz.aim.quizapprestapi.domains.entity.project.quiz.Quiz;
import uz.aim.quizapprestapi.dtos.project.quiz.QuizCreateDTO;
import uz.aim.quizapprestapi.dtos.project.quiz.QuizDTO;
import uz.aim.quizapprestapi.mapper.auth.UserMapper;

import java.util.List;

/**
 * @author : Abbosbek Murodov
 * @since : 06/04/23 / 18:12
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = {UserMapper.class, SubjectMapper.class, QuizQuestionMapper.class})
public interface QuizMapper {
    Quiz toEntity(QuizCreateDTO dto);

    @Mappings(value = {
            @Mapping(target = "createdBy", source = "createdBy.id"),
            @Mapping(target = "updatedBy", source = "updatedBy.id"),
            @Mapping(target = "subjectId", source = "subject.id")
    })
    QuizDTO toDTO(Quiz entity);

    List<QuizDTO> toDTOs(List<Quiz> content);
}
