package uz.aim.quizapprestapi.controller.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.aim.quizapprestapi.dtos.project.subject.SubjectCreateDTO;
import uz.aim.quizapprestapi.services.project.SubjectService;

/**
 * @author : Abbosbek Murodov
 * @since : 05/04/23 / 16:34
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@RestController
@RequestMapping("/api/subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody SubjectCreateDTO dto) {
        return ResponseEntity.ok(subjectService.save(dto));
    }
}
