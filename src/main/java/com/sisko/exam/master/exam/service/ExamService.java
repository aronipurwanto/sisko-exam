package com.sisko.exam.master.exam.service;

import com.sisko.exam.master.exam.model.ExamRes;
import com.sisko.exam.master.exam.model.ExamReq;

import java.util.List;
import java.util.Optional;

public interface ExamService {
    List<ExamRes> get();
    Optional<ExamRes> getById(String id);
    Optional<ExamRes> save(ExamReq request);
    Optional<ExamRes> update(ExamReq request, String id);
    Optional<ExamRes> delete(String id);
}
