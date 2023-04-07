package uz.aim.quizapprestapi.controller.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.aim.quizapprestapi.dtos.project.quiz.QuizCreateDTO;
import uz.aim.quizapprestapi.dtos.project.quiz.QuizDeleteDTO;
import uz.aim.quizapprestapi.services.project.QuizService;

import java.util.List;

/**
 * @author : Abbosbek Murodov
 * @since : 07/04/23 / 12:54
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@RestController
@RequestMapping("/api/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody QuizCreateDTO dto) {
        return ResponseEntity.ok(quizService.save(dto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody QuizDeleteDTO dto) {
        quizService.delete(dto);
        return ResponseEntity.ok("Delete quiz");
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAll(@RequestBody List<QuizDeleteDTO> dtoList) {
        quizService.deleteAll(dtoList);
        return ResponseEntity.ok("Delete all quiz");
    }
    @DeleteMapping("/softDelete")
    public ResponseEntity<?> softDelete(@RequestBody QuizDeleteDTO dto) {
        quizService.softDelete(dto);
        return ResponseEntity.ok("Delete quiz");
    }
    @DeleteMapping("/softDeleteAll")
    public ResponseEntity<?> softDeleteAll(@RequestBody List<QuizDeleteDTO> dtoList) {
        quizService.softDeleteAll(dtoList);
        return ResponseEntity.ok("Delete all quiz");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return ResponseEntity.ok(quizService.get(id));
    }
    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(quizService.getAll(page, size));
    }


}
