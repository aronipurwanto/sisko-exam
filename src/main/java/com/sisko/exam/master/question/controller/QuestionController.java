package com.sisko.exam.master.question.controller;

import com.sisko.exam.base.BaseController;
import com.sisko.exam.base.Response;
import com.sisko.exam.master.question.model.QuestionReq;
import com.sisko.exam.master.question.model.QuestionRes;
import com.sisko.exam.master.question.service.QuestionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/question")
public class QuestionController extends BaseController<QuestionRes> {
    private final QuestionService questionService;

    @GetMapping
    public ResponseEntity<Response> get() {
        List<QuestionRes> result = questionService.get();
        return getResponse(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@PathVariable @NotBlank String id) {
        Optional<QuestionRes> result = questionService.getById(id);
        return getResponse(result);
    }

    @PostMapping
    public ResponseEntity<Response> post(@RequestBody @Valid QuestionReq request) {
        Optional<QuestionRes> result = questionService.save(request);
        return getResponse(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Response> patch(@RequestBody @Valid QuestionReq request, @PathVariable @NotBlank String id) {
        Optional<QuestionRes> result = questionService.update(request, id);
        return getResponse(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable @NotBlank String id) {
        Optional<QuestionRes> result = questionService.delete(id);
        return getResponse(result);
    }

}
