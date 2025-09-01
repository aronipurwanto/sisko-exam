package com.sisko.exam.service;

import com.sisko.exam.model.*;
import com.sisko.exam.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AttemptService {
    private final ExamAttemptRepository attemptRepo;
    private final AttemptAnswerRepository answerRepo;
    private final AttemptAnswerOptionRepository aaoRepo;
    private final QuestionRepository questionRepo;
    private final QuestionOptionRepository optionRepo;
    private final GradingService gradingService;

    @Transactional
    public ExamAttempt startAttempt(Long examId, Long assignmentId, String username, int attemptNo) {
        ExamAttempt att = ExamAttempt.builder()
                .examId(examId)
                .assignmentId(assignmentId)
                .studentUsername(username)
                .attemptNo(attemptNo)
                .status(ExamAttempt.Status.IN_PROGRESS)
                .build();
        return attemptRepo.save(att);
    }


    @Transactional
    public AttemptAnswer answerEssay(Long attemptId, Long questionId, String text) {
        ExamAttempt att = attemptRepo.findById(attemptId).orElseThrow();
        Question q = questionRepo.findById(questionId).orElseThrow();
        AttemptAnswer aa = answerRepo.save(AttemptAnswer.builder()
                .attempt(att).question(q).answerText(text).build());
        return aa;
    }

    @Transactional
    public AttemptAnswer answerSingleMcq(Long attemptId, Long questionId, Long optionId) {
        ExamAttempt att = attemptRepo.findById(attemptId).orElseThrow();
        Question q = questionRepo.findById(questionId).orElseThrow();
        QuestionOption opt = optionRepo.findById(optionId).orElseThrow();
        AttemptAnswer aa = answerRepo.save(AttemptAnswer.builder()
                .attempt(att).question(q).selectedOption(opt).build());
        return aa;
    }

    @Transactional
    public AttemptAnswer answerMulti(Long attemptId, Long questionId, java.util.List<Long> optionIds) {
        ExamAttempt att = attemptRepo.findById(attemptId).orElseThrow();
        Question q = questionRepo.findById(questionId).orElseThrow();
        AttemptAnswer aa = answerRepo.save(AttemptAnswer.builder()
                .attempt(att).question(q).build());
        for (Long oid : optionIds) {
            QuestionOption opt = optionRepo.findById(oid).orElseThrow();
            AttemptAnswerOption sel = AttemptAnswerOption.builder()
                    .attemptAnswer(aa).option(opt).build();
            aaoRepo.save(sel);
            aa.getSelectedOptions().add(sel);
        }
        return aa;
    }

    @Transactional
    public ExamAttempt submit(Long attemptId) {
        ExamAttempt att = attemptRepo.findById(attemptId).orElseThrow();
        double total = 0.0;
        for (AttemptAnswer aa : att.getAnswers()) {
            Question q = aa.getQuestion();
            boolean correct = false;
            switch (q.getAnswerPolicy()) {
                case SINGLE -> correct = gradingService.isSingleCorrect(aa);
                case MULTI_ALL -> correct = gradingService.isMultiAllCorrect(aa);
                case MULTI_PARTIAL -> {
// simple partial example: ratio of correct picks / total correct if no wrong selected
                    java.util.Set<Long> correctIds = q.getOptions().stream().filter(QuestionOption::isCorrect).map(QuestionOption::getId).collect(java.util.stream.Collectors.toSet());
                    java.util.Set<Long> selIds = aa.getSelectedOptions().stream().map(a -> a.getOption().getId()).collect(java.util.stream.Collectors.toSet());
                    if (!selIds.isEmpty() && selIds.stream().allMatch(correctIds::contains)) {
                        aa.setScore(q.getPointsDefault() * ((double) selIds.size() / (double) correctIds.size()));
                    } else {
                        aa.setScore(0.0);
                    }
                    aa.setGradedAt(Instant.now());
                    continue; // skip default scoring below
                }
            }
            aa.setScore(correct ? q.getPointsDefault() : 0.0);
            aa.setGradedAt(Instant.now());
        }
        for (AttemptAnswer aa : att.getAnswers()) {
            if (aa.getScore() != null) total += aa.getScore();
        }
        att.setScoreTotal(total);
        att.setSubmittedAt(Instant.now());
        att.setStatus(ExamAttempt.Status.SUBMITTED);
        return att;
    }
}
