package com.sisko.exam.master.exam_attempt.service;

import com.sisko.exam.master.exam_attempt.model.ExamAttemptReq;
import com.sisko.exam.master.exam_attempt.model.ExamAttemptRes;

import java.util.List;
import java.util.Optional;

public interface ExamAttemptService {
    List<ExamAttemptRes> get();
    Optional<ExamAttemptRes> getById(String id);
    Optional<ExamAttemptRes> save(ExamAttemptReq request);
    Optional<ExamAttemptRes> update(ExamAttemptReq request, String id);
    Optional<ExamAttemptRes> delete(String id);
}
