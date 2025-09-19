package com.sisko.exam.master.exam_assignment.controller;

import com.sisko.exam.base.BaseController;
import com.sisko.exam.base.Response;
import com.sisko.exam.master.exam_assignment.model.ExamAssignmentReq;
import com.sisko.exam.master.exam_assignment.model.ExamAssignmentRes;
import com.sisko.exam.master.exam_assignment.service.ExamAssignmentService;
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
@RequestMapping("/v1/api/exam-assignment")
public class ExamAssignmentController extends BaseController<ExamAssignmentRes> {
    private final ExamAssignmentService examAssignmentService;

    @GetMapping
    public ResponseEntity<Response> get() {
        List<ExamAssignmentRes> result = examAssignmentService.get();
        return getResponse(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@PathVariable @NotBlank String id) {
        Optional<ExamAssignmentRes> result = examAssignmentService.getById(id);
        return getResponse(result);
    }

    @PostMapping
    public ResponseEntity<Response> post(@RequestBody @Valid ExamAssignmentReq request) {
        Optional<ExamAssignmentRes> result = examAssignmentService.save(request);
        return getResponse(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Response> patch(@RequestBody @Valid ExamAssignmentReq request, @PathVariable String id) {
        Optional<ExamAssignmentRes> result = examAssignmentService.update(request, id);
        return getResponse(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable @NotBlank String id) {
        Optional<ExamAssignmentRes> result = examAssignmentService.delete(id);
        return getResponse(result);
    }
}
