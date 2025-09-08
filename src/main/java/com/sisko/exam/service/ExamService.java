package com.sisko.exam.service;

import com.sisko.exam.enums.ExamStatus;
import com.sisko.exam.model.entity.ExamEntity;
import com.sisko.exam.repo.ExamAssignmentRepository;
import com.sisko.exam.model.entity.ExamAssignmentEntity;
import com.sisko.exam.repo.ExamQuestionRepository;
import com.sisko.exam.model.entity.ExamQuestionEntity;
import com.sisko.exam.repo.ExamRepository;
import com.sisko.exam.model.entity.QuestionEntity;
import com.sisko.exam.repo.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.Instant;


@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository examRepo;
    private final ExamQuestionRepository eqRepo;
    private final QuestionRepository questionRepo;
    private final ExamAssignmentRepository assignRepo;


    @Transactional
    public ExamEntity createExam(String name, String instructions, int durationMinutes) {
        ExamEntity e = ExamEntity.builder().name(name).instructions(instructions).durationMinutes(durationMinutes).build();
        return examRepo.save(e);
    }


    @Transactional
    public ExamQuestionEntity addQuestion(Long examId, Long questionId, double points, int orderIndex) {
        ExamEntity e = examRepo.findById(examId).orElseThrow();
        QuestionEntity q = questionRepo.findById(questionId).orElseThrow();
        ExamQuestionEntity eq = ExamQuestionEntity.builder().exam(e).question(q).points(points).orderIndex(orderIndex).build();
        return eqRepo.save(eq);
    }


    @Transactional
    public ExamEntity publish(Long examId, Instant start, Instant end) {
        ExamEntity e = examRepo.findById(examId).orElseThrow();
        e.setStatus(ExamStatus.PUBLISHED);
        e.setStartAt(start);
        e.setEndAt(end);
        return e;
    }


    @Transactional
    public ExamAssignmentEntity assign(Long examId, String audienceCode, Instant start, Instant end, int maxAttempts, String accessCode) {
        ExamEntity e = examRepo.findById(examId).orElseThrow();
        ExamAssignmentEntity a = ExamAssignmentEntity.builder().exam(e).audienceCode(audienceCode).startAt(start).endAt(end).maxAttempts(maxAttempts).accessCode(accessCode).build();
        return assignRepo.save(a);
    }
}
