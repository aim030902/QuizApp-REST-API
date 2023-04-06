package uz.aim.quizapprestapi.services.project;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.aim.quizapprestapi.domains.entity.project.subject.Subject;
import uz.aim.quizapprestapi.dtos.project.subject.SubjectCreateDTO;
import uz.aim.quizapprestapi.dtos.project.subject.SubjectDTO;
import uz.aim.quizapprestapi.dtos.project.subject.SubjectUpdateDTO;
import uz.aim.quizapprestapi.exception.GenericNotFoundException;
import uz.aim.quizapprestapi.mapper.project.SubjectMapper;
import uz.aim.quizapprestapi.repository.project.SubjectRepository;
import uz.aim.quizapprestapi.validation.project.SubjectValidation;

import java.util.List;

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

    public SubjectDTO save(@NonNull SubjectCreateDTO dto) {
        subjectValidation.validateOnSave(dto);
        Subject createdSubject = subjectMapper.toEntity(dto);
        createdSubject.getQuestions().forEach(question -> question.setSubject(createdSubject));
        createdSubject.getQuestions().forEach(question -> question.getAnswers().forEach(answer -> answer.setQuestion(question)));
        return subjectMapper.toDTO(subjectRepository.save(createdSubject));
    }

    public SubjectDTO update(@NonNull SubjectUpdateDTO dto) {
        subjectValidation.validateOnUpdate(dto);
        Subject foundSubject = subjectRepository.findById(dto.id()).orElseThrow(() -> new GenericNotFoundException("This is subject not found by id -> " + dto.id()));
        return subjectMapper.toDTO(subjectRepository.save(subjectMapper.partialUpdate(dto, foundSubject)));
    }

    public void delete(@NonNull Long id) {
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new GenericNotFoundException("This is subject not found by id -> " + id));
        subjectRepository.delete(subject);
    }

    public void softDelete(@NonNull Long id) {
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new GenericNotFoundException("This is subject not found by id -> " + id));
        subject.setDeleted(true);
        subjectRepository.save(subject);
    }

    public SubjectDTO get(@NonNull Long id) {
        return subjectMapper.toDTO(subjectRepository.findById(id).orElseThrow(() -> new GenericNotFoundException("Subject not found by id -> " + id)));
    }

    public List<SubjectDTO> getAll(int page, int size) {
        List<Subject> subjects = subjectRepository.findAll(PageRequest.of(page, size)).getContent();
        return subjectMapper.toDTOs(subjects);
    }
}
