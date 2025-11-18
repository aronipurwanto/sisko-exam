package com.sisko.exam.master.attempt_answer_option.service;

import com.sisko.exam.master.attempt_answer_option.model.AttemptAnswerOptionEntity;
import com.sisko.exam.master.attempt_answer_option.model.AttemptAnswerOptionReq;
import com.sisko.exam.master.attempt_answer_option.model.AttemptAnswerOptionRes;

import java.util.List;
import java.util.Optional;

public interface AttemptAnswerOptionService {
    List<AttemptAnswerOptionRes> get();
    Optional<AttemptAnswerOptionRes> getById(String id);
    Optional<AttemptAnswerOptionRes> save(AttemptAnswerOptionReq request);
    Optional<AttemptAnswerOptionRes> update(AttemptAnswerOptionReq request, String id);
    Optional<AttemptAnswerOptionRes> delete(String id);
}
