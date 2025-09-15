package com.sisko.exam.config;

import com.sisko.exam.enums.QuestionType;
import com.sisko.exam.enums.Role;
import com.sisko.exam.master.attempt_answer.model.AttemptAnswerEntity;
import com.sisko.exam.master.exam_assignment.model.ExamAssignmentEntity;
import com.sisko.exam.master.exam_attempt.model.ExamAttemptEntity;
import com.sisko.exam.master.question.model.QuestionEntity;
import com.sisko.exam.master.question.repository.QuestionRepository;
import com.sisko.exam.master.user.model.UserEntity;
import com.sisko.exam.master.exam.model.ExamEntity;
import com.sisko.exam.master.exam.repository.ExamRepository;
import com.sisko.exam.master.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.List;


@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final PasswordEncoder encoder;
    private final UserRepository userRepo;
    private final ExamRepository examRepo;
    private final QuestionRepository questionRepo;

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

        //exams
        ExamEntity math = ExamEntity.builder()
                .name("Mathematics Test")
                .instructions("Choose the true answer")
                .startAt(Instant.parse("2025-09-09T07:30:00Z"))
                .endAt(Instant.parse("2025-09-09T08:30:00Z"))
                .build();

        //exam assignments
        ExamAssignmentEntity XClass = ExamAssignmentEntity.builder()
                .exam(math)
                .groupLabel("X IPA 1")
                .startAt(Instant.parse("2025-09-09T07:30:00Z"))
                .endAt(Instant.parse("2025-09-09T08:30:00Z"))
                .accessCode("xxx")
                .audienceCode("X_IPA_1")
                .build();

        //question
        QuestionEntity mathQuestion01 = QuestionEntity.builder()
                .qtype(QuestionType.MCO)
                .stem("Name the odd numbers!")
                .build();

        //exam attempt
        ExamAttemptEntity diogo = ExamAttemptEntity.builder()
                .exam(math)
                .studentUsername("Diogo Dalot")
                .submittedAt(Instant.parse("2025-09-09T10:30:00Z"))
                .scoreTotal(90.50)
                .build();

        AttemptAnswerEntity diogoAttempt = AttemptAnswerEntity.builder()
                .examAttempt(diogo)
                .question(mathQuestion01)
                .answerText("1, 3, 5, 7, 9, 11")
                .score(10.00)
                .gradedBy("teacher math")
                .gradedAt(Instant.parse("2025-09-09T17:30:00Z"))
                .feedback("Don't give up!!")
                .build();

        List<AttemptAnswerEntity> attemptAnswerEntities = List.of(diogoAttempt);

        List<ExamAttemptEntity> examAttemptEntities = List.of(diogo);

        List<QuestionEntity> questionEntities = List.of(mathQuestion01);
        mathQuestion01.setAttemptAnswers(attemptAnswerEntities);

        List<ExamAssignmentEntity> examAssignments = List.of(XClass);

        //exam list
        List<ExamEntity> examEntities = List.of(math);
        math.setExamAssignments(examAssignments);
        math.setExamAttempts(examAttemptEntities);

        try {
            this.examRepo.saveAll(examEntities);
            this.questionRepo.saveAll(questionEntities);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
