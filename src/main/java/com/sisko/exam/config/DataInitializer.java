package com.sisko.exam.config;

import com.sisko.exam.enums.Role;
import com.sisko.exam.master.user.model.User;
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

    @Override
    public void run(String... args) throws Exception {
        setUserRepo();
        initExamAttempt();
    }

    private void setUserRepo() {
        if (!userRepo.findAll().isEmpty()) return;

        if (userRepo.findByEmail("student1@example.com").isEmpty()) {
            userRepo.save(User.builder()
                    .email("student1@example.com")
                    .passwordHash(encoder.encode("secret"))
                    .fullName("Student One")
                    .role(Role.STUDENT)
                    .build());
        }

        if (userRepo.findByEmail("teacher1@example.com").isEmpty()) {
            userRepo.save(User.builder()
                    .email("teacher1@example.com")
                    .passwordHash(encoder.encode("secret"))
                    .fullName("Teacher One")
                    .role(Role.TEACHER)
                    .build());
        }
    }

    private void initExamAttempt() {
        if (!examRepo.findAll().isEmpty()) return;

        ExamEntity test = ExamEntity.builder()
                .name("Test Exam")
                .instructions("Test Instructions")
                .startAt(Instant.now())
                .endAt(Instant.now())
                .build();

        List<ExamEntity> examEntities = List.of(test);

        try {
            this.examRepo.saveAll(examEntities);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
