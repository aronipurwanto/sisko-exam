package com.sisko.exam.service;

import com.sisko.exam.enums.QuestionType;
import com.sisko.exam.master.attempt_answer.model.AttemptAnswerEntity;
import com.sisko.exam.master.attempt_answer_option.model.AttemptAnswerOptionEntity;
import com.sisko.exam.master.question.model.QuestionEntity;
import com.sisko.exam.master.question_option.model.QuestionOptionEntity;
import org.springframework.stereotype.Service;


import java.util.HashSet;

import java.util.Set;


@Service
public class GradingService {


    /**
     * MULTI_ALL grading: correct iff selected set == set of all correct options.
     */
    public boolean isMultiAllCorrect(AttemptAnswerEntity aa) {
        QuestionEntity q = aa.getQuestion();
        if (q.getQtype() != QuestionType.MCO) return false;
// Build sets
        Set<Long> correct = new HashSet<>();
        for (QuestionOptionEntity opt : q.getQuestionOptions()) {
            if (opt.isCorrect()) correct.add(opt.getId());
        }
        Set<Long> selected = new HashSet<>();
        for (AttemptAnswerOptionEntity sel : aa.getAttemptAnswerOptions()) {
            selected.add(sel.getAttemptAnswer().getId());
        }
        return !correct.isEmpty() && correct.equals(selected);
    }


    /** SINGLE MCQ **/
    public boolean isSingleCorrect(AttemptAnswerEntity aa) {
        if (aa.getQuestionOption() == null) return false;
        return aa.getQuestionOption().isCorrect();
    }
}
