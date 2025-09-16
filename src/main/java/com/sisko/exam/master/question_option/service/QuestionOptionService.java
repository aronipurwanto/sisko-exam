package com.sisko.exam.master.question_option.service;

import com.sisko.exam.master.question_option.model.QuestionOptionEntity;
import com.sisko.exam.master.question_option.model.QuestionOptionReq;

import java.util.List;
import java.util.Optional;

public interface QuestionOptionService {
    List<QuestionOptionEntity> get();
    Optional<QuestionOptionEntity> getById(Long id);
    Optional<QuestionOptionEntity> save(QuestionOptionReq request);
    Optional<QuestionOptionEntity> update(QuestionOptionReq request, Long id);
    Optional<QuestionOptionEntity> delete(Long id);
}
