package com.sisko.exam.master.attempt_answer_option.controller;

import com.sisko.exam.base.BaseController;
import com.sisko.exam.base.Response;
import com.sisko.exam.master.attempt_answer_option.model.AttemptAnswerOptionReq;
import com.sisko.exam.master.attempt_answer_option.model.AttemptAnswerOptionRes;
import com.sisko.exam.master.attempt_answer_option.service.AttemptAnswerOptionService;
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
@RequestMapping("/v1/api/attempt-answer-option")
public class AttemptAnswerOptionController extends BaseController<AttemptAnswerOptionRes> {
    private final AttemptAnswerOptionService service;

    @GetMapping
    public ResponseEntity<Response> get() {
        List<AttemptAnswerOptionRes> result = this.service.get();
        return getResponse(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@PathVariable @NotBlank String id) {
        Optional<AttemptAnswerOptionRes> result = this.service.getById(id);
        return getResponse(result);
    }

    @PostMapping
    public ResponseEntity<Response> post(@RequestBody @Valid AttemptAnswerOptionReq req) {
        Optional<AttemptAnswerOptionRes> result = this.service.save(req);
        return getResponse(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Response> patch(@PathVariable @NotBlank String id, @RequestBody @Valid AttemptAnswerOptionReq req) {
        Optional<AttemptAnswerOptionRes> result = this.service.update(req, id);
        return getResponse(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable @NotBlank String id) {
        Optional<AttemptAnswerOptionRes> result = this.service.delete(id);
        return getResponse(result);
    }
}