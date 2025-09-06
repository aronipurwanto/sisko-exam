package com.sisko.exam.service;

import static org.junit.jupiter.api.Assertions.*;

import com.sisko.exam.enums.QuestionAnswerPolicy;
import com.sisko.exam.enums.QuestionType;
import com.sisko.exam.model.entity.AttemptAnswerEntity;
import com.sisko.exam.model.entity.AttemptAnswerOptionEntity;
import com.sisko.exam.model.entity.QuestionEntity;
import com.sisko.exam.model.entity.QuestionOptionEntity;
import org.junit.jupiter.api.Test;


class GradingServiceTest {
    @Test
    void multiAll_requires_exact_set() {
        QuestionEntity q = QuestionEntity.builder().id(1L).qtype(QuestionType.MCQ).answerPolicy(QuestionAnswerPolicy.MULTI_ALL).stem("s").pointsDefault(1).build();
        QuestionOptionEntity a = QuestionOptionEntity.builder().id(10L).question(q).correct(true).label("A").orderIndex(1).content("2").build();
        QuestionOptionEntity b = QuestionOptionEntity.builder().id(11L).question(q).correct(true).label("B").orderIndex(2).content("3").build();
        QuestionOptionEntity c = QuestionOptionEntity.builder().id(12L).question(q).correct(false).label("C").orderIndex(3).content("4").build();
        q.getOptions().add(a); q.getOptions().add(b); q.getOptions().add(c);


        AttemptAnswerEntity aa = AttemptAnswerEntity.builder().question(q).build();
        aa.getSelectedOptions().add(AttemptAnswerOptionEntity.builder().attemptAnswer(aa).option(a).build());
        aa.getSelectedOptions().add(AttemptAnswerOptionEntity.builder().attemptAnswer(aa).option(b).build());


        GradingService svc = new GradingService();
        assertTrue(svc.isMultiAllCorrect(aa));


        aa.getSelectedOptions().add(AttemptAnswerOptionEntity.builder().attemptAnswer(aa).option(c).build());
        assertFalse(svc.isMultiAllCorrect(aa));
    }
}