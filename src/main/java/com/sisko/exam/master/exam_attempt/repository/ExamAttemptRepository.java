package com.sisko.exam.master.exam_attempt.repository;

import com.sisko.exam.master.exam_attempt.model.ExamAttemptEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamAttemptRepository extends JpaRepository<ExamAttemptEntity, Long> {}
