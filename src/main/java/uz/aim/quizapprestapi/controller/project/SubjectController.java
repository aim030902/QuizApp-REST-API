package uz.aim.quizapprestapi.controller.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.aim.quizapprestapi.dtos.project.subject.SubjectCreateDTO;
import uz.aim.quizapprestapi.dtos.project.subject.SubjectDeleteDTO;
import uz.aim.quizapprestapi.dtos.project.subject.SubjectUpdateDTO;
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

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody SubjectUpdateDTO dto) {
        return ResponseEntity.ok(subjectService.update(dto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody SubjectDeleteDTO dto) {
        subjectService.delete(dto);
        return ResponseEntity.ok("Delete subject");
    }

    @DeleteMapping("/softDelete")
    public ResponseEntity<?> softDelete(@RequestBody SubjectDeleteDTO dto) {
        subjectService.softDelete(dto);
        return ResponseEntity.ok("Delete subject");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return ResponseEntity.ok(subjectService.get(id));
    }

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(subjectService.getAll(page, size));
    }
}
