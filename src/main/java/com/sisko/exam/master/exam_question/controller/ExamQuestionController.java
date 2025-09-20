package com.sisko.exam.master.exam_question.controller;

import com.sisko.exam.base.BaseController;
import com.sisko.exam.base.Response;
import com.sisko.exam.master.exam_question.model.ExamQuestionReq;
import com.sisko.exam.master.exam_question.model.ExamQuestionRes;
import com.sisko.exam.master.exam_question.service.ExamQuestionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/v1/api/exam-question")
public class ExamQuestionController extends BaseController<ExamQuestionRes> {
    private final ExamQuestionService examQuestionService;

    @GetMapping
    public ResponseEntity<Response> get() {
        List<ExamQuestionRes> result = examQuestionService.get();
        return getResponse(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@PathVariable @NotBlank String id) {
        Optional<ExamQuestionRes> result = examQuestionService.getById(id);
        return getResponse(result);
    }

    @PostMapping
    public ResponseEntity<Response> post(@RequestBody @Valid ExamQuestionReq request) {
        Optional<ExamQuestionRes> result = examQuestionService.save(request);
        return getResponse(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Response> patch(@RequestBody @Valid ExamQuestionReq request, @PathVariable @NotBlank String id) {
        Optional<ExamQuestionRes> result = examQuestionService.update(request, id);
        return getResponse(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable @NotBlank String id) {
        Optional<ExamQuestionRes> result = examQuestionService.delete(id);
        return getResponse(result);
    }
}
