package com.sisko.exam.service;

import com.sisko.exam.enums.ExamAttemptStatus;
import com.sisko.exam.master.attempt_answer.model.AttemptAnswerEntity;
import com.sisko.exam.master.attempt_answer.repository.AttemptAnswerRepository;
import com.sisko.exam.master.attempt_answer_option.repository.AttemptAnswerOptionRepository;
import com.sisko.exam.master.attempt_answer_option.model.AttemptAnswerOptionEntity;
import com.sisko.exam.master.exam_attempt.repository.ExamAttemptRepository;
import com.sisko.exam.master.exam_attempt.model.ExamAttemptEntity;
import com.sisko.exam.master.question.model.QuestionEntity;
import com.sisko.exam.master.question_option.repository.QuestionOptionRepository;
import com.sisko.exam.master.question_option.model.QuestionOptionEntity;
import com.sisko.exam.master.question.repository.QuestionRepository;
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
    public ExamAttemptEntity startAttempt(String username, int attemptNo) {
        ExamAttemptEntity att = ExamAttemptEntity.builder()
                .studentUsername(username)
                .attemptNo(attemptNo)
                .status(ExamAttemptStatus.IN_PROGRESS)
                .build();
        return attemptRepo.save(att);
    }


    @Transactional
    public AttemptAnswerEntity answerEssay(Long attemptId, Long questionId, String text) {
        ExamAttemptEntity att = attemptRepo.findById(attemptId).orElseThrow();
        QuestionEntity q = questionRepo.findById(questionId).orElseThrow();
        AttemptAnswerEntity aa = answerRepo.save(AttemptAnswerEntity.builder()
                .examAttempt(att).question(q).answerText(text).build());
        return aa;
    }

    @Transactional
    public AttemptAnswerEntity answerSingleMcq(Long attemptId, Long questionId, Long optionId) {
        ExamAttemptEntity att = attemptRepo.findById(attemptId).orElseThrow();
        QuestionEntity q = questionRepo.findById(questionId).orElseThrow();
        QuestionOptionEntity opt = optionRepo.findById(optionId).orElseThrow();
        AttemptAnswerEntity aa = answerRepo.save(AttemptAnswerEntity.builder()
                .examAttempt(att).question(q).questionOption(opt).build());
        return aa;
    }

    @Transactional
    public AttemptAnswerEntity answerMulti(Long attemptId, Long questionId, java.util.List<Long> optionIds) {
        ExamAttemptEntity att = attemptRepo.findById(attemptId).orElseThrow();
        QuestionEntity q = questionRepo.findById(questionId).orElseThrow();
        AttemptAnswerEntity aa = answerRepo.save(AttemptAnswerEntity.builder()
                .examAttempt(att).question(q).build());
        for (Long oid : optionIds) {
            QuestionOptionEntity opt = optionRepo.findById(oid).orElseThrow();
            AttemptAnswerOptionEntity sel = AttemptAnswerOptionEntity.builder()
                    .attemptAnswer(aa).questionOption(opt).build();
            aaoRepo.save(sel);
            aa.getAttemptAnswerOptions().add(sel);
        }
        return aa;
    }

    @Transactional
    public ExamAttemptEntity submit(Long attemptId) {
        ExamAttemptEntity att = attemptRepo.findById(attemptId).orElseThrow();
        double total = 0.0;
        for (AttemptAnswerEntity aa : att.getAttemptAnswers()) {
            QuestionEntity q = aa.getQuestion();
            boolean correct = false;
            switch (q.getAnswerPolicy()) {
                case SINGLE -> correct = gradingService.isSingleCorrect(aa);
                case MULTI_ALL -> correct = gradingService.isMultiAllCorrect(aa);
                case MULTI_PARTIAL -> {
// simple partial example: ratio of correct picks / total correct if no wrong selected
                    java.util.Set<Long> correctIds = q.getQuestionOptions().stream().filter(QuestionOptionEntity::isCorrect).map(QuestionOptionEntity::getId).collect(java.util.stream.Collectors.toSet());
                    java.util.Set<Long> selIds = aa.getAttemptAnswerOptions().stream().map(a -> a.getQuestionOption().getId()).collect(java.util.stream.Collectors.toSet());
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
        for (AttemptAnswerEntity aa : att.getAttemptAnswers()) {
            if (aa.getScore() != null) total += aa.getScore();
        }
        att.setScoreTotal(total);
        att.setSubmittedAt(Instant.now());
        att.setStatus(ExamAttemptStatus.SUBMITTED);
        return att;
    }
}
