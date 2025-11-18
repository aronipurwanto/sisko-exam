package com.sisko.exam.service;

import com.sisko.exam.enums.ExamStatus;
import com.sisko.exam.master.exam.model.ExamEntity;
import com.sisko.exam.master.exam_assignment.repository.ExamAssignmentRepository;
import com.sisko.exam.master.exam_assignment.model.ExamAssignmentEntity;
import com.sisko.exam.master.exam_question.repository.ExamQuestionRepository;
import com.sisko.exam.master.exam_question.model.ExamQuestionEntity;
import com.sisko.exam.master.exam.repository.ExamRepository;
import com.sisko.exam.master.question.model.QuestionEntity;
import com.sisko.exam.master.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;

@Service("examServiceEx")
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
    public ExamQuestionEntity addQuestion(String  examId, String questionId, double points, int orderIndex) {
        ExamEntity e = examRepo.findById(examId).orElseThrow();
        QuestionEntity q = questionRepo.findById(questionId).orElseThrow();
        ExamQuestionEntity eq = ExamQuestionEntity.builder().exam(e).question(q).points(points).orderIndex(orderIndex).build();
        return eqRepo.save(eq);
    }


    @Transactional
    public ExamEntity publish(String  examId, LocalDateTime start, LocalDateTime end) {
        ExamEntity e = examRepo.findById(examId).orElseThrow();
        e.setStatus(ExamStatus.PUBLISHED);
        e.setStartAt(start);
        e.setEndAt(end);
        return e;
    }


    @Transactional
    public ExamAssignmentEntity assign(String examId, String audienceCode, LocalDateTime start, LocalDateTime end, int maxAttempts, String accessCode) {
        ExamEntity e = examRepo.findById(examId).orElseThrow();
        ExamAssignmentEntity a = ExamAssignmentEntity.builder().exam(e).audienceCode(audienceCode).startAt(start).endAt(end).maxAttempts(maxAttempts).accessCode(accessCode).build();
        return assignRepo.save(a);
    }
}
