package uz.aim.quizapprestapi.controller.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.aim.quizapprestapi.dtos.project.question.QuestionCreateDTO;
import uz.aim.quizapprestapi.dtos.project.question.QuestionDeleteDTO;
import uz.aim.quizapprestapi.dtos.project.question.QuestionUpdateDTO;
import uz.aim.quizapprestapi.dtos.project.subject.SubjectCreateDTO;
import uz.aim.quizapprestapi.services.project.QuestionService;

import java.util.List;

/**
 * @author : Abbosbek Murodov
 * @since : 05/04/23 / 16:34
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@RestController
@RequestMapping("/api/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody QuestionCreateDTO dto) {
        return ResponseEntity.ok(questionService.save(dto));
    }

    @PostMapping("/saveAll")
    public ResponseEntity<?> saveAll(@RequestBody List<QuestionCreateDTO> dtoList) {
        return ResponseEntity.ok(questionService.saveAll(dtoList));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody QuestionUpdateDTO dto) {
        return ResponseEntity.ok(questionService.update(dto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody QuestionDeleteDTO dto) {
        questionService.delete(dto);
        return ResponseEntity.ok("Delete question");
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAll(@RequestBody List<QuestionDeleteDTO> dtoList) {
        questionService.deleteAll(dtoList);
        return ResponseEntity.ok("Delete all questions");
    }

    @DeleteMapping("/softDelete")
    public ResponseEntity<?> softDelete(@RequestBody QuestionDeleteDTO dto) {
        questionService.softDelete(dto);
        return ResponseEntity.ok("Delete question");
    }
    @DeleteMapping("/softDeleteAll")
    public ResponseEntity<?> softDeleteAll(@RequestBody List<QuestionDeleteDTO> dtoList) {
        questionService.softDeleteAll(dtoList);
        return ResponseEntity.ok("Delete all questions");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return ResponseEntity.ok(questionService.get(id));
    }

    @GetMapping()
    public ResponseEntity<?> getAll(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(questionService.getAll(page, size));
    }
}
