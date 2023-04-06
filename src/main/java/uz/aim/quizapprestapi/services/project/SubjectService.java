package uz.aim.quizapprestapi.services.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.aim.quizapprestapi.domains.entity.project.subject.Subject;
import uz.aim.quizapprestapi.dtos.project.subject.SubjectCreateDTO;
import uz.aim.quizapprestapi.dtos.project.subject.SubjectDTO;
import uz.aim.quizapprestapi.mapper.project.SubjectMapper;
import uz.aim.quizapprestapi.repository.project.SubjectRepository;
import uz.aim.quizapprestapi.validation.project.SubjectValidation;

/**
 * @author : Abbosbek Murodov
 * @since : 05/04/23 / 16:26
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private SubjectValidation subjectValidation;
    @Autowired
    private SubjectMapper subjectMapper;

    public SubjectDTO save(SubjectCreateDTO dto) {
        subjectValidation.validateOnSave(dto);
        Subject createdSubject = subjectMapper.toEntity(dto);
        createdSubject.getQuestions().forEach(question -> question.setSubject(createdSubject));
        createdSubject.getQuestions().forEach(question -> question.getAnswers().forEach(answer -> answer.setQuestion(question)));
        return subjectMapper.toDTO(subjectRepository.save(createdSubject));
    }
}
