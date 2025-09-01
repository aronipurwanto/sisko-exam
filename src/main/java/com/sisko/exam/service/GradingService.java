package com.sisko.exam.service;

import com.sisko.exam.model.AttemptAnswer;
import com.sisko.exam.model.AttemptAnswerOption;
import com.sisko.exam.model.Question;
import com.sisko.exam.model.QuestionOption;
import org.springframework.stereotype.Service;


import java.util.HashSet;

import java.util.Set;


@Service
public class GradingService {


    /**
     * MULTI_ALL grading: correct iff selected set == set of all correct options.
     */
    public boolean isMultiAllCorrect(AttemptAnswer aa) {
        Question q = aa.getQuestion();
        if (q.getQtype() != Question.Type.MCQ) return false;
// Build sets
        Set<Long> correct = new HashSet<>();
        for (QuestionOption opt : q.getOptions()) {
            if (opt.isCorrect()) correct.add(opt.getId());
        }
        Set<Long> selected = new HashSet<>();
        for (AttemptAnswerOption sel : aa.getSelectedOptions()) {
            selected.add(sel.getOption().getId());
        }
        return !correct.isEmpty() && correct.equals(selected);
    }


    /** SINGLE MCQ **/
    public boolean isSingleCorrect(AttemptAnswer aa) {
        if (aa.getSelectedOption() == null) return false;
        return aa.getSelectedOption().isCorrect();
    }
}
