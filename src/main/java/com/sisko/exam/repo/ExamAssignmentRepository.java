package com.sisko.exam.repo;

import com.sisko.exam.model.ExamAssignment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExamAssignmentRepository extends JpaRepository<ExamAssignment, Long> {}
