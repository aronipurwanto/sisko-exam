package com.sisko.exam.master.exam_attempt.controller;

import com.sisko.exam.base.BaseController;
import com.sisko.exam.base.Response;
import com.sisko.exam.master.exam_attempt.model.ExamAttemptReq;
import com.sisko.exam.master.exam_attempt.model.ExamAttemptRes;
import com.sisko.exam.master.exam_attempt.service.ExamAttemptService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/exam-attempt")
public class ExamAttemptController extends BaseController<ExamAttemptRes> {
    private final ExamAttemptService examAttemptService;

    @GetMapping
    public ResponseEntity<Response> get() {
        List<ExamAttemptRes> result = examAttemptService.get();
        return getResponse(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@PathVariable String id) {
        Optional<ExamAttemptRes> result = examAttemptService.getById(id);
        return getResponse(result);
    }

    @PostMapping
    public ResponseEntity<Response> post(@Valid @RequestBody ExamAttemptReq request) {
        Optional<ExamAttemptRes> result = examAttemptService.save(request);
        return getResponse(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Response> patch(@Valid @RequestBody ExamAttemptReq request,
                                          @PathVariable String id) {
        Optional<ExamAttemptRes> result = examAttemptService.update(request, id);
        return getResponse(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable String id) {
        Optional<ExamAttemptRes> result = examAttemptService.delete(id);
        return getResponse(result);
    }
}