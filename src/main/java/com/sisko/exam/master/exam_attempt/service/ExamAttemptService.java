package com.sisko.exam.master.exam_attempt.service;

import com.sisko.exam.master.exam_attempt.model.ExamAttemptEntity;
import com.sisko.exam.master.exam_attempt.model.ExamAttemptReq;

import java.util.List;
import java.util.Optional;

public interface ExamAttemptService {
    List<ExamAttemptEntity> get();
    Optional<ExamAttemptEntity> getById(Long id);
    Optional<ExamAttemptEntity> save(ExamAttemptReq request);
    Optional<ExamAttemptEntity> update(ExamAttemptReq request, Long id);
    Optional<ExamAttemptEntity> delete(Long id);
}
