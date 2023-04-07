package uz.aim.quizapprestapi.services.project;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.aim.quizapprestapi.domains.entity.project.question.Question;
import uz.aim.quizapprestapi.domains.entity.project.quiz.Quiz;
import uz.aim.quizapprestapi.domains.entity.project.quiz.QuizQuestion;
import uz.aim.quizapprestapi.domains.enums.project.Level;
import uz.aim.quizapprestapi.dtos.project.quiz.QuizCreateDTO;
import uz.aim.quizapprestapi.dtos.project.quiz.QuizDTO;
import uz.aim.quizapprestapi.dtos.project.quiz.QuizDeleteDTO;
import uz.aim.quizapprestapi.exception.GenericNotFoundException;
import uz.aim.quizapprestapi.mapper.project.QuizMapper;
import uz.aim.quizapprestapi.repository.auth.UserRepository;
import uz.aim.quizapprestapi.repository.project.QuestionRepository;
import uz.aim.quizapprestapi.repository.project.QuizRepository;
import uz.aim.quizapprestapi.repository.project.SubjectRepository;
import uz.aim.quizapprestapi.validation.project.QuizValidation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author : Abbosbek Murodov
 * @since : 07/04/23 / 10:54
 * Project : QuizApp-REST-API / IntelliJ IDEA
 */

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuizMapper quizMapper;
    @Autowired
    private QuizValidation quizValidation;

    @Transactional
    public QuizDTO save(@NonNull QuizCreateDTO dto) {
        quizValidation.validateOnCreate(dto);
        Quiz createdQuiz = quizMapper.toEntity(dto);
        createdQuiz.setSubject(subjectRepository.findById(dto.getSubjectId()).get());
        List<QuizQuestion> quizQuestions = generateQuizQuestions(dto.getSubjectId(), createdQuiz.getLevel(), dto.getQuestionsCount());
        quizQuestions.forEach(quizQuestion -> quizQuestion.setQuiz(createdQuiz));
        createdQuiz.setQuestions(quizQuestions);
        Quiz savedQuiz = quizRepository.save(createdQuiz);
        savedQuiz.getQuestions().forEach(System.out::println);
        return quizMapper.toDTO(savedQuiz);
    }

    public void delete(@NonNull QuizDeleteDTO dto) {
        Quiz foundQuiz = getQuiz(dto.id());
        quizRepository.delete(foundQuiz);
    }
    public void deleteAll(@NonNull List<QuizDeleteDTO> dtoList) {
        dtoList.forEach(this::delete);
    }
    public void softDelete(@NonNull QuizDeleteDTO dto) {
        Quiz foundQuiz = getQuiz(dto.id());
        foundQuiz.setDeleted(true);
        quizRepository.save(foundQuiz);
    }
    public void softDeleteAll(@NonNull List<QuizDeleteDTO> dtoList) {
        dtoList.forEach(this::softDelete);
    }

    public QuizDTO get(@NonNull Long id) {
        Quiz quiz = getQuiz(id);
        quiz.getQuestions().forEach(System.out::println);
        return quizMapper.toDTO(quiz);
    }

    public List<QuizDTO> getAll(int page, int size) {
        return quizMapper.toDTOs(quizRepository.findAll(PageRequest.of(page, size)).getContent());
    }


    private List<QuizQuestion> generateQuizQuestions(Long subjectId, @NonNull Level level,  int count) {
        return switch (level) {
            case EASY -> generateEasyQuizQuestions(subjectId, count);
            case MEDIUM -> generateMediumQuizQuestions(subjectId, count);
            case HARD -> generateHardQuizQuestions(subjectId, count);
        };
    }

    private List<QuizQuestion> generateHardQuizQuestions(Long subjectId, int count) {
        return generateQuizQuestions(subjectId, count, 0.3, 0.3);
    }

    private List<QuizQuestion> generateMediumQuizQuestions(Long subjectId, int count) {
        return generateQuizQuestions(subjectId, count, 0.2, 0.3);
    }

    private List<QuizQuestion> generateEasyQuizQuestions(Long subjectId, int count) {
        return generateQuizQuestions(subjectId, count, 0.1, 0.2);
    }

    private List<QuizQuestion> generateQuizQuestions(Long subjectId, int count, double hardCount, double mediumCount) {

        Random random = new Random();

        List<Question> easyQuestionList = questionRepository.findBySubjectIdAndLevel(subjectId, Level.EASY);
        List<Question> mediumQuestionList = questionRepository.findBySubjectIdAndLevel(subjectId, Level.MEDIUM);
        List<Question> hardQuestionList = questionRepository.findBySubjectIdAndLevel(subjectId, Level.HARD);

        List<QuizQuestion> quizQuestionList = new ArrayList<>();
        IntStream intStream1 = random.ints((int)(count*hardCount), 0, hardQuestionList.size());
        IntStream intStream2 = random.ints((int)(count*mediumCount), 0, mediumQuestionList.size());
        IntStream intStream3 = random.ints( count - (int)(count*hardCount) - (int)(count*mediumCount), 0, easyQuestionList.size());

        intStream1.forEach(i -> quizQuestionList.add(QuizQuestion.builder().question(easyQuestionList.get(i)).build()));
        intStream2.forEach(i -> quizQuestionList.add(QuizQuestion.builder().question(mediumQuestionList.get(i)).build()));
        intStream3.forEach(i -> quizQuestionList.add(QuizQuestion.builder().question(hardQuestionList.get(i)).build()));

        return quizQuestionList;
    }

    private Quiz getQuiz(Long id) {
        return quizRepository.findQuizById(id).orElseThrow(() -> new GenericNotFoundException("Quiz not found by id -> " + id));
    }
}
