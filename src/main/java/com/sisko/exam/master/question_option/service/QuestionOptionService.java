package com.sisko.exam.master.question_option.service;

import com.sisko.exam.master.question_option.model.QuestionOptionRes;
import com.sisko.exam.master.question_option.model.QuestionOptionReq;

import java.util.List;
import java.util.Optional;

public interface QuestionOptionService {
    List<QuestionOptionRes> get();
    Optional<QuestionOptionRes> getById(String id);
    Optional<QuestionOptionRes> save(QuestionOptionReq request);
    Optional<QuestionOptionRes> update(QuestionOptionReq request, String id);
    Optional<QuestionOptionRes> delete(String id);
}
