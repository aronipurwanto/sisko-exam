package com.sisko.exam.config;

import com.sisko.exam.model.Role;
import com.sisko.exam.model.User;
import com.sisko.exam.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final PasswordEncoder encoder;
    private final UserRepository userRepo;

//    @Bean
//    CommandLineRunner seedUsers(UserRepository repo) {
//        return args -> {
//            if (repo.findByEmail("teacher1@example.com").isEmpty()) {
//                repo.save(User.builder()
//                        .email("teacher1@example.com")
//                        .passwordHash(encoder.encode("secret"))
//                        .fullName("Teacher One")
//                        .role(Role.TEACHER)
//                        .build());
//            }
//            if (repo.findByEmail("student1@example.com").isEmpty()) {
//                repo.save(User.builder()
//                        .email("student1@example.com")
//                        .passwordHash(encoder.encode("secret"))
//                        .fullName("Student One")
//                        .role(Role.STUDENT)
//                        .build());
//            }
//        };
//    }

    @Override
    public void run(String... args) throws Exception {
        setUserRepo();
    }

    private void setUserRepo() {
        if (!userRepo.findAll().isEmpty()) {
            return;
        }

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
}
