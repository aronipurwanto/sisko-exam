package com.sisko.exam.master.exam_assignment.repository;

import com.sisko.exam.master.exam_assignment.model.ExamAssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExamAssignmentRepository extends JpaRepository<ExamAssignmentEntity, Long> {}
