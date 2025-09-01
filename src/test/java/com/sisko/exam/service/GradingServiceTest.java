package com.sisko.exam.service;

import static org.junit.jupiter.api.Assertions.*;

import com.sisko.exam.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class GradingServiceTest {
    @Test
    void multiAll_requires_exact_set() {
        Question q = Question.builder().id(1L).qtype(Question.Type.MCQ).answerPolicy(Question.AnswerPolicy.MULTI_ALL).stem("s").pointsDefault(1).build();
        QuestionOption a = QuestionOption.builder().id(10L).question(q).correct(true).label("A").orderIndex(1).content("2").build();
        QuestionOption b = QuestionOption.builder().id(11L).question(q).correct(true).label("B").orderIndex(2).content("3").build();
        QuestionOption c = QuestionOption.builder().id(12L).question(q).correct(false).label("C").orderIndex(3).content("4").build();
        q.getOptions().add(a); q.getOptions().add(b); q.getOptions().add(c);


        AttemptAnswer aa = AttemptAnswer.builder().question(q).build();
        aa.getSelectedOptions().add(AttemptAnswerOption.builder().attemptAnswer(aa).option(a).build());
        aa.getSelectedOptions().add(AttemptAnswerOption.builder().attemptAnswer(aa).option(b).build());


        GradingService svc = new GradingService();
        assertTrue(svc.isMultiAllCorrect(aa));


        aa.getSelectedOptions().add(AttemptAnswerOption.builder().attemptAnswer(aa).option(c).build());
        assertFalse(svc.isMultiAllCorrect(aa));
    }
}