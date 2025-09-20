package com.sisko.exam.master.exam_assignment.service;

import com.sisko.exam.master.exam_assignment.model.ExamAssignmentRes;
import com.sisko.exam.master.exam_assignment.model.ExamAssignmentReq;

import java.util.List;
import java.util.Optional;

public interface ExamAssignmentService {
    List<ExamAssignmentRes> get();
    Optional<ExamAssignmentRes> getById(String id);
    Optional<ExamAssignmentRes> save(ExamAssignmentReq request);
    Optional<ExamAssignmentRes> update(ExamAssignmentReq request, String id);
    Optional<ExamAssignmentRes> delete(String id);
}
