package com.sisko.exam.master.exam_assignment.service;

import com.sisko.exam.master.exam_assignment.model.ExamAssignmentEntity;
import com.sisko.exam.master.exam_assignment.model.ExamAssignmentReq;

import java.util.List;
import java.util.Optional;

public interface ExamAssignmentService {
    List<ExamAssignmentEntity> get();
    Optional<ExamAssignmentEntity> getById(Long id);
    Optional<ExamAssignmentEntity> save(ExamAssignmentReq request);
    Optional<ExamAssignmentEntity> update(ExamAssignmentReq request, Long id);
    Optional<ExamAssignmentEntity> delete(Long id);
}
