package uz.aim.quizapprestapi.controller.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.aim.quizapprestapi.dtos.project.answer.AnswerCreateDTO;
import uz.aim.quizapprestapi.dtos.project.answer.AnswerDeleteDTO;
import uz.aim.quizapprestapi.dtos.project.answer.AnswerUpdateDTO;
import uz.aim.quizapprestapi.dtos.project.question.QuestionCreateDTO;
import uz.aim.quizapprestapi.services.project.AnswerService;
import uz.aim.quizapprestapi.services.project.QuestionService;

import java.util.List;

/**
 * @author : Abbosbek Murodov
 * @since : 05/04/23 / 16:34
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@RestController
@RequestMapping("/api/answer")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody AnswerCreateDTO dto) {
        return ResponseEntity.ok(answerService.save(dto));
    }

    @PostMapping("/saveAll")
    public ResponseEntity<?> saveAll(@RequestBody List<AnswerCreateDTO> dtoList) {
        return ResponseEntity.ok(answerService.saveAll(dtoList));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody AnswerUpdateDTO dto) {
        return ResponseEntity.ok(answerService.update(dto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody AnswerDeleteDTO dto) {
        answerService.delete(dto);
        return ResponseEntity.ok("Delete answer");
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAll(@RequestBody List<AnswerDeleteDTO> dtoList) {
        answerService.deleteAll(dtoList);
        return ResponseEntity.ok("Delete all answers");
    }

    @DeleteMapping("/softDelete")
    public ResponseEntity<?> softDelete(@RequestBody AnswerDeleteDTO dto) {
        answerService.softDelete(dto);
        return ResponseEntity.ok("Delete answer");
    }

    @DeleteMapping("/softDeleteAll")
    public ResponseEntity<?> softDeleteAll(@RequestBody List<AnswerDeleteDTO> dtoList) {
        answerService.softDeleteAll(dtoList);
        return ResponseEntity.ok("Delete all answer");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return ResponseEntity.ok(answerService.get(id));
    }

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(answerService.getAll(page, size));
    }

}
