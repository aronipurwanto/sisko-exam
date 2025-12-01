package com.sisko.exam.master.attempt_answer.controller;

import com.sisko.exam.base.BaseController;
import com.sisko.exam.base.Response;
import com.sisko.exam.master.attempt_answer.model.AttemptAnswerReq;
import com.sisko.exam.master.attempt_answer.model.AttemptAnswerRes;
import com.sisko.exam.master.attempt_answer.service.AttemptAnswerService;
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
@RequestMapping("/api/v1/attempt-answer")
public class AttemptAnswerController extends BaseController<AttemptAnswerRes> {
    private final AttemptAnswerService attemptAnswerService;

    @GetMapping
    public ResponseEntity<Response> get() {
        List<AttemptAnswerRes> result = attemptAnswerService.get();
        return getResponse(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@PathVariable @NotBlank String id) {
        Optional<AttemptAnswerRes> result = attemptAnswerService.getById(id);
        return getResponse(result);
    }

    @PostMapping
    public ResponseEntity<Response> post(@RequestBody @Valid AttemptAnswerReq request) {
        Optional<AttemptAnswerRes> result = attemptAnswerService.save(request);
        return getResponse(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Response> patch(@RequestBody @Valid AttemptAnswerReq request, @PathVariable @NotBlank String id) {
        Optional<AttemptAnswerRes> result = attemptAnswerService.update(request, id);
        return getResponse(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable @NotBlank String id) {
        Optional<AttemptAnswerRes> result = attemptAnswerService.delete(id);
        return getResponse(result);
    }
}