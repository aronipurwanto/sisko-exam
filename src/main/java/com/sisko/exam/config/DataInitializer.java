package com.sisko.exam.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sisko.exam.master.course.model.CourseEntity;
import com.sisko.exam.master.course.repository.CourseRepository;
import com.sisko.exam.master.exam.model.ExamEntity;
import com.sisko.exam.master.exam.repository.ExamRepository;
import com.sisko.exam.master.exam_assignment.model.ExamAssignmentEntity;
import com.sisko.exam.master.exam_assignment.repository.ExamAssignmentRepository;
import com.sisko.exam.master.exam_question.model.ExamQuestionEntity;
import com.sisko.exam.master.exam_question.repository.ExamQuestionRepository;
import com.sisko.exam.master.level.model.LevelEntity;
import com.sisko.exam.master.level.repository.LevelRepository;
import com.sisko.exam.master.question.model.QuestionEntity;
import com.sisko.exam.master.question.repository.QuestionRepository;
import com.sisko.exam.master.question_option.model.QuestionOptionEntity;
import com.sisko.exam.master.question_option.repository.QuestionOptionRepository;
import com.sisko.exam.master.user.model.UserEntity;
import com.sisko.exam.master.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.InputStream;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final LevelRepository levelRepository;
    private final ExamRepository examRepository;
    private final ExamAssignmentRepository examAssignmentRepository;
    private final QuestionRepository questionRepository;
    private final QuestionOptionRepository questionOptionRepository;
    private final ExamQuestionRepository examQuestionRepository;
    private final PasswordEncoder passwordEncoder;
    private final ResourceLoader resourceLoader;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            loadData("UserEntity", new TypeReference<List<UserEntity>>() {}, (List<UserEntity> data) -> {
                data.forEach(user -> user.setPassword(passwordEncoder.encode(user.getPassword())));
                userRepository.saveAll(data);
            });
        }
        if (courseRepository.count() == 0) {
            loadData("CourseEntity", new TypeReference<List<CourseEntity>>() {}, courseRepository::saveAll);
        }
        if (levelRepository.count() == 0) {
            loadData("LevelEntity", new TypeReference<List<LevelEntity>>() {}, levelRepository::saveAll);
        }
        if (examRepository.count() == 0) {
            loadData("ExamEntity", new TypeReference<List<ExamEntity>>() {}, examRepository::saveAll);
        }
        if (examAssignmentRepository.count() == 0) {
            loadData("ExamAssignmentEntity", new TypeReference<List<ExamAssignmentEntity>>() {}, examAssignmentRepository::saveAll);
        }
        if (questionRepository.count() == 0) {
            loadData("QuestionEntity", new TypeReference<List<QuestionEntity>>() {}, questionRepository::saveAll);
        }
        if (questionOptionRepository.count() == 0) {
            loadData("QuestionOptionEntity", new TypeReference<List<QuestionOptionEntity>>() {}, questionOptionRepository::saveAll);
        }
        if (examQuestionRepository.count() == 0) {
            loadData("ExamQuestionEntity", new TypeReference<List<ExamQuestionEntity>>() {}, examQuestionRepository::saveAll);
        }
    }

    private <T> void loadData(String className, TypeReference<List<T>> typeReference, DataSaver<T> saver) {
        try {
            Resource resource = resourceLoader.getResource("classpath:json/" + className + ".json");
            InputStream inputStream = resource.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            List<T> data = mapper.readValue(inputStream, typeReference);
            saver.save(data);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load data for " + className, e);
        }
    }

    @FunctionalInterface
    interface DataSaver<T> {
        void save(List<T> data);
    }
}