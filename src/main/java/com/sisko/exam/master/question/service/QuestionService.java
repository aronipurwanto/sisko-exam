package com.sisko.exam.master.question.service;

import com.sisko.exam.master.question.model.QuestionRes;
import com.sisko.exam.master.question.model.QuestionReq;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    List<QuestionRes> get();
    Optional<QuestionRes> getById(String id);
    Optional<QuestionRes> save(QuestionReq request);
    Optional<QuestionRes> update(QuestionReq request, String id);
    Optional<QuestionRes> delete(String id);
}
