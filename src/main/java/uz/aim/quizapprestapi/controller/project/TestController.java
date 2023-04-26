package uz.aim.quizapprestapi.controller.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.aim.quizapprestapi.dtos.project.test.TestCreateDTO;
import uz.aim.quizapprestapi.services.project.TestService;

/**
 * @author : Abbosbek Murodov
 * @since : 26/04/23 / 11:46
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@RestController
@RequestMapping("/api/test")
public class TestController {
    @Autowired
    private TestService testService;

    @PostMapping("/save")
    public ResponseEntity<?> save(TestCreateDTO dto) {
        return ResponseEntity.ok(testService.save(dto));
    }


}
