package com.sisko.exam.repo;

import com.sisko.exam.model.entity.ExamAssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExamAssignmentRepository extends JpaRepository<ExamAssignmentEntity, Long> {}
