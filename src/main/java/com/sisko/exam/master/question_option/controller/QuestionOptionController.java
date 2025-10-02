package com.sisko.exam.master.question_option.controller;

import com.sisko.exam.base.BaseController;
import com.sisko.exam.base.Response;
import com.sisko.exam.master.question_option.model.QuestionOptionReq;
import com.sisko.exam.master.question_option.model.QuestionOptionRes;
import com.sisko.exam.master.question_option.service.QuestionOptionService;
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
@RequestMapping("/v1/api/question-option")
public class QuestionOptionController extends BaseController<QuestionOptionRes> {
    private final QuestionOptionService questionOptionService;

    @GetMapping
    public ResponseEntity<Response> get() {
        List<QuestionOptionRes> result = questionOptionService.get();
        return getResponse(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@PathVariable @NotBlank String id) {
        Optional<QuestionOptionRes> result = questionOptionService.getById(id);
        return getResponse(result);
    }

    @PostMapping
    public ResponseEntity<Response> post(@RequestBody @Valid QuestionOptionReq request) {
        Optional<QuestionOptionRes> result = questionOptionService.save(request);
        return getResponse(result);
    }

    @PostMapping("/all")
    public ResponseEntity<Response> post(@RequestBody @Valid List<QuestionOptionReq> requests) {
        List<QuestionOptionRes> result = questionOptionService.saveAll(requests);
        return getResponse(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Response> patch(@RequestBody @Valid QuestionOptionReq request, @PathVariable @NotBlank String id) {
        Optional<QuestionOptionRes> result = questionOptionService.update(request, id);
        return getResponse(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable @NotBlank String id) {
        Optional<QuestionOptionRes> result = questionOptionService.delete(id);
        return getResponse(result);
    }
}