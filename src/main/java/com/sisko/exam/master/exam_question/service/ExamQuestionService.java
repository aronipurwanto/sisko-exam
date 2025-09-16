package com.sisko.exam.master.exam_question.service;

import com.sisko.exam.master.exam_question.model.ExamQuestionEntity;
import com.sisko.exam.master.exam_question.model.ExamQuestionReq;

import java.util.List;
import java.util.Optional;

public interface ExamQuestionService {
    List<ExamQuestionEntity> get();
    Optional<ExamQuestionEntity> getById(Long id);
    Optional<ExamQuestionEntity> save(ExamQuestionReq request);
    Optional<ExamQuestionEntity> update(ExamQuestionReq request, Long id);
    Optional<ExamQuestionEntity> delete(Long id);
}
