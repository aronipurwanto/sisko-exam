package com.sisko.exam.service;

import com.sisko.exam.master.question.model.QuestionEntity;
import com.sisko.exam.master.question_option.model.QuestionOptionEntity;
import com.sisko.exam.master.question_option.repository.QuestionOptionRepository;
import com.sisko.exam.master.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("questionServiceEx")
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepo;
    private final QuestionOptionRepository optionRepo;


    @Transactional
    public QuestionEntity createQuestion(QuestionEntity q, List<QuestionOptionEntity> options) {
        QuestionEntity saved = questionRepo.save(q);
        int idx = 1;
        for (QuestionOptionEntity o : options) {
            o.setQuestion(saved);
            o.setOrderIndex(idx++);
            optionRepo.save(o);
        }
        return saved;
    }
}
