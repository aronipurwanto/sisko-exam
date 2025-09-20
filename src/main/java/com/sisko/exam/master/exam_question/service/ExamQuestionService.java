package com.sisko.exam.master.exam_question.service;

import com.sisko.exam.master.exam_question.model.ExamQuestionReq;
import com.sisko.exam.master.exam_question.model.ExamQuestionRes;

import java.util.List;
import java.util.Optional;

public interface ExamQuestionService {
    List<ExamQuestionRes> get();
    Optional<ExamQuestionRes> getById(String id);
    Optional<ExamQuestionRes> save(ExamQuestionReq request);
    Optional<ExamQuestionRes> update(ExamQuestionReq request, String id);
    Optional<ExamQuestionRes> delete(String id);
}
