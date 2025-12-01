package com.sisko.exam.master.course.controller;

import com.sisko.exam.base.BaseController;
import com.sisko.exam.base.Response;
import com.sisko.exam.master.course.model.CourseReq;
import com.sisko.exam.master.course.model.CourseRes;
import com.sisko.exam.master.course.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/course")
public class CourseController extends BaseController<CourseRes> {
    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<Response> get() {
        return getResponse(this.courseService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@PathVariable String id) {
        return getResponse(this.courseService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Response> post(@Valid @RequestBody CourseReq request) {
        return getResponse(this.courseService.save(request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Response> patch(@PathVariable String id, @Valid @RequestBody CourseReq request) {
        return getResponse(this.courseService.update(request, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable String id) {
        return getResponse(this.courseService.delete(id));
    }
}
