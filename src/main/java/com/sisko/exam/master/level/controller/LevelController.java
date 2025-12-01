package com.sisko.exam.master.level.controller;

import com.sisko.exam.base.BaseController;
import com.sisko.exam.base.Response;
import com.sisko.exam.master.level.mapper.LevelMapper;
import com.sisko.exam.master.level.model.LevelReq;
import com.sisko.exam.master.level.model.LevelRes;
import com.sisko.exam.master.level.service.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/level")
public class LevelController extends BaseController<LevelRes> {
    private final LevelService levelService;

    @GetMapping
    public ResponseEntity<Response> get() {
        return getResponse(this.levelService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@PathVariable String id) {
        return getResponse(this.levelService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Response> post(@RequestBody @Validated LevelReq request) {
        return getResponse(this.levelService.save(request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Response> patch(@PathVariable String id, @RequestBody @Validated LevelReq request) {
        return getResponse(this.levelService.update(request, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable String id) {
        return getResponse(this.levelService.delete(id));
    }

}
