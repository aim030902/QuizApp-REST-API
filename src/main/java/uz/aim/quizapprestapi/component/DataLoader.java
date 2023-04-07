package uz.aim.quizapprestapi.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.aim.quizapprestapi.domains.entity.auth.Role;
import uz.aim.quizapprestapi.domains.entity.auth.User;
import uz.aim.quizapprestapi.domains.entity.project.answer.Answer;
import uz.aim.quizapprestapi.domains.entity.project.question.Question;
import uz.aim.quizapprestapi.domains.entity.project.subject.Subject;
import uz.aim.quizapprestapi.domains.enums.auth.Permission;
import uz.aim.quizapprestapi.domains.enums.auth.RoleCode;
import uz.aim.quizapprestapi.domains.enums.auth.UserStatus;
import uz.aim.quizapprestapi.domains.enums.project.Language;
import uz.aim.quizapprestapi.domains.enums.project.Level;
import uz.aim.quizapprestapi.repository.auth.RoleRepository;
import uz.aim.quizapprestapi.repository.auth.UserRepository;
import uz.aim.quizapprestapi.repository.project.SubjectRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author : Abbosbek Murodov
 * @since : 02/04/23 / 11:30
 * Project : Trello-REST-API / IntelliJ IDEA
 */

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private SubjectRepository subjectRepository;


    @Value("${spring.sql.init.mode}")
    private String initialMode;

    @Override
    public void run(String... args) throws Exception {
        if (initialMode.equals("always")) {
            Role roleAdmin = Role
                    .builder()
                    .name("ADMIN")
                    .code(RoleCode.ROLE_ADMIN)
                    .permissions(Set.of(Permission.values()))
                    .build();
            Role roleUser = Role
                    .builder()
                    .name("USER")
                    .code(RoleCode.ROLE_USER)
                    .permissions(Set.of(Permission.values()))
                    .build();

            User admin = User
                    .builder()
                    .email("admin@gmail.com")
                    .fullName("admin")
                    .password(passwordEncoder.encode("123"))
                    .roles(Set.of(roleAdmin, roleUser))
                    .status(UserStatus.ACTIVE)
                    .build();


//            User user = User
//                    .builder()
//                    .fullName("User")
//                    .username("user")
//                    .password(passwordEncoder.encode("123"))
//                    .email("user@gmail.com")
//                    .roles(Set.of(roleUser))
//                    .build();

            Subject subject0 = Subject
                    .childBuilder()
                    .name("Subject 0")
                    .description("Subject 0 is great program language")
                    .createdBy(admin)
                    .build();
            subject0.setQuestions(generateQuestion(subject0, admin));



            roleRepository.saveAll(new ArrayList<>(List.of(roleAdmin, roleUser)));
            userRepository.save(admin);
            subjectRepository.save(subject0);

        }
    }

    public List<Question> generateQuestion(Subject subject, User admin) {
        List<Question> questions = new ArrayList<>();
        for (int i=1; i<=20; i++) {
            questions.add(
                    Question
                            .childBuilder()
                            .subject(subject)
                            .content("Question 0." + i)
                            .level(Level.EASY)
                            .language(Language.ENGLISH)
                            .createdBy(admin)
                            .build()
            );
        }
        for (int i=1; i<=20; i++) {
            questions.add(
                    Question
                            .childBuilder()
                            .subject(subject)
                            .content("Question 0." + i)
                            .level(Level.MEDIUM)
                            .language(Language.ENGLISH)
                            .createdBy(admin)
                            .build()
            );
        }
        for (int i=1; i<=20; i++) {
            questions.add(
                    Question
                            .childBuilder()
                            .subject(subject)
                            .content("Question 0." + i)
                            .level(Level.HARD)
                            .language(Language.ENGLISH)
                            .createdBy(admin)
                            .build()
            );
        }
        generateAndSetAnswersToQuestion(questions, admin);
        return questions;
    }

    public void generateAndSetAnswersToQuestion(List<Question> questions, User admin) {
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            question.setAnswers(
                    List.<Answer>of(
                            Answer.childBuilder().question(question).content("Answer 0." + i + "1").isRight(true).createdBy(admin).build(),
                            Answer.childBuilder().question(question).content("Answer 0." + i + "2").isRight(false).createdBy(admin).build(),
                            Answer.childBuilder().question(question).content("Answer 0" + i + "3").isRight(false).createdBy(admin).build()
                    )
            );
        }
    }
}


/*

 */