package uz.aim.quizapprestapi.mapper.project;

import org.mapstruct.*;
import uz.aim.quizapprestapi.domains.entity.project.question.Question;
import uz.aim.quizapprestapi.domains.entity.project.subject.Subject;
import uz.aim.quizapprestapi.dtos.project.question.QuestionCreateDTO;
import uz.aim.quizapprestapi.dtos.project.question.QuestionDTO;
import uz.aim.quizapprestapi.dtos.project.question.QuestionUpdateDTO;
import uz.aim.quizapprestapi.dtos.project.subject.SubjectUpdateDTO;

import java.util.List;

/**
 * @author : Abbosbek Murodov
 * @since : 05/04/23 / 15:43
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {SubjectMapper.class, AnswerMapper.class}, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface QuestionMapper {
    Question toEntity(QuestionCreateDTO dto);
    @Mappings(value = {
            @Mapping(target = "createdBy", source = "createdBy.id"),
            @Mapping(target = "updatedBy", source = "updatedBy.id"),
            @Mapping(target = "answerDTOS", source = "answers"),
            @Mapping(target = "subjectId", source = "subject.id")
    })
    QuestionDTO toDTO(Question entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Question partialUpdate(QuestionUpdateDTO blogDTO, @MappingTarget Question question);

    List<QuestionDTO> toDTOs(List<Question> questions);
}
