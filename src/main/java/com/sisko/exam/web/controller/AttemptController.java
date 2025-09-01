package com.sisko.exam.web.controller;

import com.sisko.exam.model.AttemptAnswer;
import com.sisko.exam.model.ExamAttempt;
import com.sisko.exam.service.AttemptService;
import com.sisko.exam.web.dto.AttemptDTOs;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/attempts")
@RequiredArgsConstructor
@Validated
public class AttemptController {


    private static final Logger log = LoggerFactory.getLogger(AttemptController.class);


    private final AttemptService attemptService;


    @PostMapping
    public ResponseEntity<AttemptDTOs.StartAttemptResp> start(@RequestBody @Valid AttemptDTOs.StartAttemptReq req, Authentication auth) {
        ExamAttempt att = attemptService.startAttempt(req.examId(), req.assignmentId(), auth.getName(), req.attemptNo());
        log.info("Attempt started id={} by {}", att.getId(), auth.getName());
        return ResponseEntity.ok(new AttemptDTOs.StartAttemptResp(att.getId(), att.getStatus().name()));
    }


    @PostMapping("/{attemptId}/essay")
    public ResponseEntity<Long> answerEssay(@PathVariable Long attemptId, @RequestBody @Valid AttemptDTOs.AnswerEssayReq req) {
        AttemptAnswer aa = attemptService.answerEssay(attemptId, req.questionId(), req.text());
        log.info("Essay answered aaId={}", aa.getId());
        return ResponseEntity.ok(aa.getId());
    }


    @PostMapping("/{attemptId}/single")
    public ResponseEntity<Long> answerSingle(@PathVariable Long attemptId, @RequestBody @Valid AttemptDTOs.AnswerSingleReq req) {
        AttemptAnswer aa = attemptService.answerSingleMcq(attemptId, req.questionId(), req.optionId());
        log.info("Single MCQ answered aaId={}", aa.getId());
        return ResponseEntity.ok(aa.getId());
    }


    @PostMapping("/{attemptId}/multi")
    public ResponseEntity<Long> answerMulti(@PathVariable Long attemptId, @RequestBody @Valid AttemptDTOs.AnswerMultiReq req) {
        AttemptAnswer aa = attemptService.answerMulti(attemptId, req.questionId(), req.optionIds());
        log.info("Multi MCQ answered aaId={}", aa.getId());
        return ResponseEntity.ok(aa.getId());
    }


    @PostMapping("/{attemptId}/submit")
    public ResponseEntity<AttemptDTOs.SubmitResp> submit(@PathVariable Long attemptId) {
        ExamAttempt att = attemptService.submit(attemptId);
        log.info("Attempt submitted id={}, score={}", att.getId(), att.getScoreTotal());
        return ResponseEntity.ok(new AttemptDTOs.SubmitResp(att.getId(), att.getScoreTotal(), att.getStatus().name()));
    }
}
