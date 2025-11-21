package com.sisko.exam.master.exam.service;

import com.sisko.exam.master.exam.model.ExamEntity;
import com.sisko.exam.master.exam.model.ExamRes;
import com.sisko.exam.master.exam.model.ExamReq;

import java.util.List;
import java.util.Optional;

public interface ExamService {
    List<ExamRes> get();
    List<ExamRes> getByCourseId(String courseId);
    List<ExamRes> getByLevelId(String levelId);
    Optional<ExamRes> getById(String id);
    Optional<ExamRes> save(ExamReq request);
    Optional<ExamRes> update(ExamReq request, String id);
    Optional<ExamRes> delete(String id);
}
