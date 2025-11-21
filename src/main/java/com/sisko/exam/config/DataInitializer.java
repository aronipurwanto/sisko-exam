package com.sisko.exam.config;

import com.sisko.exam.enums.QuestionAnswerPolicy;
import com.sisko.exam.enums.QuestionType;
import com.sisko.exam.enums.Role;
import com.sisko.exam.master.attempt_answer.model.AttemptAnswerEntity;
import com.sisko.exam.master.course.model.CourseEntity;
import com.sisko.exam.master.course.repository.CourseRepository;
import com.sisko.exam.master.exam_assignment.model.ExamAssignmentEntity;
import com.sisko.exam.master.exam_assignment.repository.ExamAssignmentRepository;
import com.sisko.exam.master.exam_attempt.model.ExamAttemptEntity;
import com.sisko.exam.master.exam_attempt.repository.ExamAttemptRepository;
import com.sisko.exam.master.exam_question.model.ExamQuestionEntity;
import com.sisko.exam.master.exam_question.repository.ExamQuestionRepository;
import com.sisko.exam.master.level.model.LevelEntity;
import com.sisko.exam.master.level.repository.LevelRepository;
import com.sisko.exam.master.question.model.QuestionEntity;
import com.sisko.exam.master.question.repository.QuestionRepository;
import com.sisko.exam.master.question_option.model.QuestionOptionEntity;
import com.sisko.exam.master.question_option.repository.QuestionOptionRepository;
import com.sisko.exam.master.user.model.UserEntity;
import com.sisko.exam.master.exam.model.ExamEntity;
import com.sisko.exam.master.exam.repository.ExamRepository;
import com.sisko.exam.master.user.repository.UserRepository;
import com.sisko.exam.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;


@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final PasswordEncoder encoder;
    private final UserRepository userRepo;
    private final ExamRepository examRepo;
    private final QuestionRepository questionRepo;
    private final QuestionOptionRepository questionOptionRepo;
    private final ExamAttemptRepository examAttemptRepo;
    private final ExamAssignmentRepository examAssignmentRepo;
    private final ExamQuestionRepository examQuestionRepo;
    private final LevelRepository levelRepo;
    private final CourseRepository courseRepo;

    @Override
    public void run(String... args) throws Exception {
        setUserRepo();
        initTables();
    }

    private void setUserRepo() {
        if (!userRepo.findAll().isEmpty()) return;

        if (userRepo.findByEmail("student1@example.com").isEmpty()) {
            userRepo.save(UserEntity.builder()
                            .username("student1")
                            .email("student1@example.com")
                            .password(encoder.encode("secret"))
                            .fullName("Student One")
                            .role(Role.STUDENT)
                    .build());
        }

        if (userRepo.findByEmail("teacher1@example.com").isEmpty()) {
            userRepo.save(UserEntity.builder()
                            .username("teacher1")
                            .email("teacher1@example.com")
                            .password(encoder.encode("secret"))
                            .fullName("Teacher One")
                            .role(Role.TEACHER)
                    .build());
        }
    }

    private void initTables() {
        if (!examRepo.findAll().isEmpty()) return;

        CourseEntity mathCourse = CourseEntity.builder()
                .id("f9c389d3712f49218eec74e8d764a8d5")
                .name("Math")
                .code("math")
                .build();

        LevelEntity a7 = LevelEntity.builder()
                .id("5d04c8b77e584461a4b35ac851b79fe8")
                .code("XII_A")
                .name("Level 7A")
                .build();

        //exams
        ExamEntity math = ExamEntity.builder()
                .id("5a3b8b1289a14ff7b3a32129ba5a1b52")
                .name("Mathematics Test")
                .course(mathCourse)
                .level(a7)
                .instructions("Choose the true answer")
                .startAt(LocalDateTime.parse("2026-06-09T07:30:00"))
                .endAt(LocalDateTime.parse("2026-06-09T08:30:00"))
                .build();

        //exam assignments
        ExamAssignmentEntity XClass = ExamAssignmentEntity.builder()
                .id("73d8ff83c88949e5995dceaa1a463152")
                .exam(math)
                .groupLabel("X IPA 1")
                .startAt(LocalDateTime.parse("2026-06-09T07:30:00"))
                .endAt(LocalDateTime.parse("2026-06-09T08:30:00"))
                .accessCode("xxx")
                .audienceCode("X_IPA_1")
                .build();

        //question
        QuestionEntity mathQuestion01 = QuestionEntity.builder()
                .id("aef4891244004d8cac0a4b9a50365d09")
                .qtype(QuestionType.MCO)
                .questionAnswerPolicy(QuestionAnswerPolicy.SINGLE)
                .stem("Name the odd numbers!")
                .pointsDefault(1.0)
                .build();

        QuestionOptionEntity option01 = QuestionOptionEntity.builder()
                .id("0a423d8410654979ab76590caf5f9a62")
                .question(mathQuestion01)
                .label("A")
                .content("1, 3, 5, 7, 9")
                .correct(true)
                .orderIndex(100)
                .build();

        QuestionOptionEntity option02 = QuestionOptionEntity.builder()
                .id("c8076e005b5b43c3ac704f4ba4a1b5e9")
                .question(mathQuestion01)
                .label("B")
                .content("2, 4, 6, 8, 10")
                .correct(false)
                .orderIndex(20)
                .build();

        //exam attempt
        ExamAttemptEntity diogo = ExamAttemptEntity.builder()
                .id("f8f08206876a4bceac98ebe5ce5b1d51")
                .exam(math)
                .studentUsername("Diogo Dalot")
                .startedAt(LocalDateTime.parse("2026-06-09T07:30:00"))
                .submittedAt(LocalDateTime.parse("2026-06-09T08:30:00"))
                .scoreTotal(90.50)
                .build();

        ExamQuestionEntity mathMathQuestion1 = ExamQuestionEntity.builder()
                .id("090f45ba6b3949a58e5899902b439ffc")
                .exam(math)
                .question(mathQuestion01)
                .points(10.0)
                .required(true)
                .orderIndex(1)
                .build();

        AttemptAnswerEntity diogoAttempt = AttemptAnswerEntity.builder()
                .id("78d9b2009d0d49f7ac1c65b568095d40")
                .examAttempt(diogo)
                .examQuestion(mathMathQuestion1)
                .answerText("1, 3, 5, 7, 9, 11")
                .score(10.00)
                .gradedBy("teacher math")
                .gradedAt(Instant.parse("2025-09-09T17:30:00Z"))
                .feedback("Don't give up!!")
                .build();

        List<CourseEntity> courses = List.of(mathCourse);

        List<LevelEntity> levels = List.of(a7);

        List<AttemptAnswerEntity> attemptAnswerEntities = List.of(diogoAttempt);

        List<ExamQuestionEntity> examQuestionEntities = List.of(mathMathQuestion1);

        List<ExamAttemptEntity> examAttemptEntities = List.of(diogo);

        List<QuestionOptionEntity> questionOptionEntities = List.of(option01, option02);

        List<QuestionEntity> questionEntities = List.of(mathQuestion01);
        //mathQuestion01.setAttemptAnswers(attemptAnswerEntities);
        mathQuestion01.setQuestionOptions(questionOptionEntities);

        List<ExamAssignmentEntity> examAssignments = List.of(XClass);

        //exam list
        List<ExamEntity> examEntities = List.of(math);
       // math.setExamAssignments(examAssignments);
        //math.setExamAttempts(examAttemptEntities);

        try {
            this.courseRepo.saveAll(courses);
            this.levelRepo.saveAll(levels);
            this.examRepo.saveAll(examEntities);
            this.questionRepo.saveAll(questionEntities);
            this.questionOptionRepo.saveAll(questionOptionEntities);
            this.examAssignmentRepo.saveAll(examAssignments);
            this.examQuestionRepo.saveAll(examQuestionEntities);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
