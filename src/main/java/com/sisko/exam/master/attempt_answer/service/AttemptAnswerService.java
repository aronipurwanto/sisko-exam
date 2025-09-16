package com.sisko.exam.master.attempt_answer.service;

import com.sisko.exam.master.attempt_answer.model.AttemptAnswerEntity;
import com.sisko.exam.master.attempt_answer.model.AttemptAnswerReq;

import java.util.List;
import java.util.Optional;

public interface AttemptAnswerService {
    List<AttemptAnswerEntity> get();
    Optional<AttemptAnswerEntity> getById(Long id);
    Optional<AttemptAnswerEntity> save(AttemptAnswerReq request);
    Optional<AttemptAnswerEntity> update(AttemptAnswerReq request, Long id);
    Optional<AttemptAnswerEntity> delete(Long id);
}
