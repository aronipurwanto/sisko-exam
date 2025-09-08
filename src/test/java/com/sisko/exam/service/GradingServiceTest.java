package com.sisko.exam.service;

import static org.junit.jupiter.api.Assertions.*;

import com.sisko.exam.enums.QuestionAnswerPolicy;
import com.sisko.exam.enums.QuestionType;
import com.sisko.exam.master.attempt_answer.model.AttemptAnswerEntity;
import com.sisko.exam.master.attempt_answer_option.model.AttemptAnswerOptionEntity;
import com.sisko.exam.master.question.model.QuestionEntity;
import com.sisko.exam.master.question_option.model.QuestionOptionEntity;
import org.junit.jupiter.api.Test;


class GradingServiceTest {
    @Test
    void multiAll_requires_exact_set() {
        QuestionEntity q = QuestionEntity.builder().id(1L).qtype(QuestionType.MCQ).answerPolicy(QuestionAnswerPolicy.MULTI_ALL).stem("s").pointsDefault(1).build();
        QuestionOptionEntity a = QuestionOptionEntity.builder().id(10L).question(q).correct(true).label("A").orderIndex(1).content("2").build();
        QuestionOptionEntity b = QuestionOptionEntity.builder().id(11L).question(q).correct(true).label("B").orderIndex(2).content("3").build();
        QuestionOptionEntity c = QuestionOptionEntity.builder().id(12L).question(q).correct(false).label("C").orderIndex(3).content("4").build();
        q.getQuestionOptions().add(a); q.getQuestionOptions().add(b); q.getQuestionOptions().add(c);


        AttemptAnswerEntity aa = AttemptAnswerEntity.builder().question(q).build();
        aa.getAttemptAnswerOptions().add(AttemptAnswerOptionEntity.builder().attemptAnswer(aa).questionOption(a).build());
        aa.getAttemptAnswerOptions().add(AttemptAnswerOptionEntity.builder().attemptAnswer(aa).questionOption(b).build());


        GradingService svc = new GradingService();
        assertTrue(svc.isMultiAllCorrect(aa));


        aa.getAttemptAnswerOptions().add(AttemptAnswerOptionEntity.builder().attemptAnswer(aa).questionOption(c).build());
        assertFalse(svc.isMultiAllCorrect(aa));
    }
}