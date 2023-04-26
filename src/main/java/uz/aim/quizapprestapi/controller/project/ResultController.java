package uz.aim.quizapprestapi.controller.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.aim.quizapprestapi.dtos.project.result.ResultCreateDTO;
import uz.aim.quizapprestapi.services.project.result.ResultService;
import uz.aim.quizapprestapi.validation.project.ResultValidation;

/**
 * @author : Abbosbek Murodov
 * @since : 26/04/23 / 15:14
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@RestController
@RequestMapping("/api/result")
public class ResultController {
    @Autowired
    private ResultService resultService;

    @PostMapping("/check")
    public ResponseEntity<?> result(@RequestBody ResultCreateDTO dto) {
        return ResponseEntity.ok(resultService.result(dto));
    }
}
