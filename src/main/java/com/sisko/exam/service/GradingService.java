package com.sisko.exam.service;

import com.sisko.exam.enums.QuestionType;
import com.sisko.exam.model.entity.AttemptAnswerEntity;
import com.sisko.exam.model.entity.AttemptAnswerOptionEntity;
import com.sisko.exam.model.entity.QuestionEntity;
import com.sisko.exam.model.entity.QuestionOptionEntity;
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
        if (q.getQtype() != QuestionType.MCQ) return false;
// Build sets
        Set<Long> correct = new HashSet<>();
        for (QuestionOptionEntity opt : q.getOptions()) {
            if (opt.isCorrect()) correct.add(opt.getId());
        }
        Set<Long> selected = new HashSet<>();
        for (AttemptAnswerOptionEntity sel : aa.getSelectedOptions()) {
            selected.add(sel.getOption().getId());
        }
        return !correct.isEmpty() && correct.equals(selected);
    }


    /** SINGLE MCQ **/
    public boolean isSingleCorrect(AttemptAnswerEntity aa) {
        if (aa.getSelectedOption() == null) return false;
        return aa.getSelectedOption().isCorrect();
    }
}
