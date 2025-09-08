package com.sisko.exam.web.controller;

import com.sisko.exam.enums.QuestionAnswerPolicy;
import com.sisko.exam.enums.QuestionType;
import com.sisko.exam.model.entity.QuestionEntity;
import com.sisko.exam.model.entity.QuestionOptionEntity;
import com.sisko.exam.repo.QuestionRepository;
import com.sisko.exam.service.QuestionService;
import com.sisko.exam.web.dto.QuestionDTOs;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {
    private static final Logger log = LoggerFactory.getLogger(QuestionController.class);
    private final QuestionService questionService;
    private final QuestionRepository questionRepo;


    @PostMapping
    public ResponseEntity<QuestionDTOs.QuestionResp> create(@RequestBody @Valid QuestionDTOs.CreateQuestionReq req) {
        QuestionEntity q = QuestionEntity.builder()
                .qtype(req.qtype())
                .answerPolicy(req.answerPolicy())
                .stem(req.stem())
                .pointsDefault(req.pointsDefault())
                .build();
        List<QuestionOptionEntity> options = req.options() == null ? java.util.List.of() : req.options().stream()
                .map(o -> QuestionOptionEntity.builder().label(o.label()).content(o.content()).correct(o.correct()).build())
                .toList();
        QuestionEntity saved = questionService.createQuestion(q, options);
        log.info("Created question id={}", saved.getId());
        return ResponseEntity.ok(new QuestionDTOs.QuestionResp(saved.getId(), saved.getStem(), saved.getQtype(), saved.getAnswerPolicy(), saved.getPointsDefault()));
    }


    @GetMapping
    public Page<QuestionEntity> list(
            @RequestParam(required = false) QuestionType qtype,
            @RequestParam(required = false) QuestionAnswerPolicy policy,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Specification<QuestionEntity> spec = Specification.where(null);
        if (qtype != null) spec = spec.and((root, q, cb) -> cb.equal(root.get("qtype"), qtype));
        if (policy != null) spec = spec.and((root, q, cb) -> cb.equal(root.get("answerPolicy"), policy));
        if (keyword != null && !keyword.isBlank()) spec = spec.and((root, q, cb) -> cb.like(cb.lower(root.get("stem")), "%" + keyword.toLowerCase() + "%"));
        return questionRepo.findAll(spec, pageable);
    }


    @GetMapping("/{id}")
    public QuestionEntity get(@PathVariable Long id) { return questionRepo.findById(id).orElseThrow(); }


    @PutMapping("/{id}")
    public QuestionEntity update(@PathVariable Long id, @RequestBody @Valid QuestionDTOs.CreateQuestionReq req) {
        QuestionEntity q = questionRepo.findById(id).orElseThrow();
        q.setQtype(req.qtype());
        q.setAnswerPolicy(req.answerPolicy());
        q.setStem(req.stem());
        q.setPointsDefault(req.pointsDefault());
// Options update strategy: replace all (simple)
        q.getQuestionOptions().clear();
        int idx = 1;
        for (QuestionDTOs.OptionReq o : req.options()) {
            QuestionOptionEntity qo = QuestionOptionEntity.builder().question(q).label(o.label()).content(o.content()).correct(o.correct()).orderIndex(idx++).build();
            q.getQuestionOptions().add(qo);
        }
        return questionRepo.save(q);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        questionRepo.deleteById(id); // soft delete via @SQLDelete
        return ResponseEntity.noContent().build();
    }
}
