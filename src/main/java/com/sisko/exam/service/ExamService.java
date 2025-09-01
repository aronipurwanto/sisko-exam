package com.sisko.exam.service;

import com.sisko.exam.model.*;
import com.sisko.exam.repo.*;
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
    public Exam createExam(String name, String instructions, int durationMinutes) {
        Exam e = Exam.builder().name(name).instructions(instructions).durationMinutes(durationMinutes).build();
        return examRepo.save(e);
    }


    @Transactional
    public ExamQuestion addQuestion(Long examId, Long questionId, double points, int orderIndex) {
        Exam e = examRepo.findById(examId).orElseThrow();
        Question q = questionRepo.findById(questionId).orElseThrow();
        ExamQuestion eq = ExamQuestion.builder().exam(e).question(q).points(points).orderIndex(orderIndex).build();
        return eqRepo.save(eq);
    }


    @Transactional
    public Exam publish(Long examId, Instant start, Instant end) {
        Exam e = examRepo.findById(examId).orElseThrow();
        e.setStatus(Exam.Status.PUBLISHED);
        e.setStartAt(start);
        e.setEndAt(end);
        return e;
    }


    @Transactional
    public ExamAssignment assign(Long examId, String audienceCode, Instant start, Instant end, int maxAttempts, String accessCode) {
        Exam e = examRepo.findById(examId).orElseThrow();
        ExamAssignment a = ExamAssignment.builder().exam(e).audienceCode(audienceCode).startAt(start).endAt(end).maxAttempts(maxAttempts).accessCode(accessCode).build();
        return assignRepo.save(a);
    }
}
