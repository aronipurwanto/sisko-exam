package com.sisko.exam.service;

import com.sisko.exam.model.Question;
import com.sisko.exam.model.QuestionOption;
import com.sisko.exam.repo.QuestionOptionRepository;
import com.sisko.exam.repo.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepo;
    private final QuestionOptionRepository optionRepo;


    @Transactional
    public Question createQuestion(Question q, List<QuestionOption> options) {
        Question saved = questionRepo.save(q);
        int idx = 1;
        for (QuestionOption o : options) {
            o.setQuestion(saved);
            o.setOrderIndex(idx++);
            optionRepo.save(o);
        }
        return saved;
    }
}
