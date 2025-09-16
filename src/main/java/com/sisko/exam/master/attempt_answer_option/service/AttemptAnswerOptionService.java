package com.sisko.exam.master.attempt_answer_option.service;

import com.sisko.exam.master.attempt_answer_option.model.AttemptAnswerOptionEntity;
import com.sisko.exam.master.attempt_answer_option.model.AttemptAnswerOptionReq;

import java.util.List;
import java.util.Optional;

public interface AttemptAnswerOptionService {
    List<AttemptAnswerOptionEntity> get();
    Optional<AttemptAnswerOptionEntity> getById(Long id);
    Optional<AttemptAnswerOptionEntity> save(AttemptAnswerOptionReq request);
    Optional<AttemptAnswerOptionEntity> update(AttemptAnswerOptionReq request, Long id);
    Optional<AttemptAnswerOptionEntity> delete(Long id);
}
