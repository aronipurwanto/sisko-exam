package com.sisko.exam.master.exam.controller;

import com.sisko.exam.base.BaseController;
import com.sisko.exam.base.Response;
import com.sisko.exam.master.exam.model.ExamReq;
import com.sisko.exam.master.exam.model.ExamRes;
import com.sisko.exam.master.exam.service.ExamService;
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
@RequestMapping("/api/v1/exam")
public class ExamController extends BaseController<ExamRes> {
    private final ExamService examService;

    @GetMapping
    public ResponseEntity<Response> get() {
        List<ExamRes> result = examService.get();
        return getResponse(result);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<Response> getByCourseId(@PathVariable("courseId") String courseId) {
        return getResponse(this.examService.getByCourseId(courseId));
    }

    @GetMapping("/level/{levelId}")
    public ResponseEntity<Response> getByLevelId(@PathVariable("levelId") String levelId) {
        return getResponse(this.examService.getByLevelId(levelId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@PathVariable @NotBlank String id) {
        Optional<ExamRes> result = examService.getById(id);
        return getResponse(result);
    }

    @PostMapping
    public ResponseEntity<Response> post(@RequestBody @Valid ExamReq request) {
        Optional<ExamRes> result = examService.save(request);
        return getResponse(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Response> patch(@RequestBody @Valid ExamReq request, @PathVariable @NotBlank String id) {
        Optional<ExamRes> result = examService.update(request, id);
        return getResponse(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable @NotBlank String id) {
        Optional<ExamRes> result = examService.delete(id);
        return getResponse(result);
    }
}
