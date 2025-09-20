package com.sisko.exam.master.attempt_answer.service;

import com.sisko.exam.master.attempt_answer.model.AttemptAnswerRes;
import com.sisko.exam.master.attempt_answer.model.AttemptAnswerReq;

import java.util.List;
import java.util.Optional;

public interface AttemptAnswerService {
    List<AttemptAnswerRes> get();
    Optional<AttemptAnswerRes> getById(String id);
    Optional<AttemptAnswerRes> save(AttemptAnswerReq request);
    Optional<AttemptAnswerRes> update(AttemptAnswerReq request, String id);
    Optional<AttemptAnswerRes> delete(String id);
}
