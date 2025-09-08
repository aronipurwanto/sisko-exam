package com.sisko.exam.web.controller;

import com.sisko.exam.master.exam_assignment.model.ExamAssignmentEntity;
import com.sisko.exam.service.ExamService;
import jakarta.validation.constraints.*;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.Instant;


record AssignReq(@NotNull Long examId,
                 @NotBlank String audienceCode,
                 @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant start,
                 @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant end,
                 @Positive int maxAttempts,
                 String accessCode) {}


@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssignmentController {
    private final ExamService examService;


    @PostMapping
    public ResponseEntity<ExamAssignmentEntity> assign(@RequestBody AssignReq req) {
        return ResponseEntity.ok(examService.assign(req.examId(), req.audienceCode(), req.start(), req.end(), req.maxAttempts(), req.accessCode()));
    }
}
