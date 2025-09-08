package com.sisko.exam.web.controller;

import com.sisko.exam.master.exam.model.ExamEntity;
import com.sisko.exam.master.exam_question.model.ExamQuestionEntity;
import com.sisko.exam.service.ExamService;
import jakarta.validation.constraints.*;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.Instant;


record CreateExamReq(@NotBlank String name, String instructions, @Positive int durationMinutes) {}
record AddExamQuestionReq(@NotNull Long questionId, @Positive double points, @Positive int orderIndex) {}
record PublishExamReq(@NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant start,
                      @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant end) {}


@RestController
@RequestMapping("/api/exams")
@RequiredArgsConstructor
public class ExamController {
    private final ExamService examService;


    @PostMapping
    public ResponseEntity<ExamEntity> create(@RequestBody CreateExamReq req) {
        return ResponseEntity.ok(examService.createExam(req.name(), req.instructions(), req.durationMinutes()));
    }


    @PostMapping("/{examId}/questions")
    public ResponseEntity<ExamQuestionEntity> addQuestion(@PathVariable Long examId, @RequestBody AddExamQuestionReq req) {
        return ResponseEntity.ok(examService.addQuestion(examId, req.questionId(), req.points(), req.orderIndex()));
    }


    @PostMapping("/{examId}/publish")
    public ResponseEntity<ExamEntity> publish(@PathVariable Long examId, @RequestBody PublishExamReq req) {
        return ResponseEntity.ok(examService.publish(examId, req.start(), req.end()));
    }
}
