package com.sisko.exam.repo;

import com.sisko.exam.model.entity.ExamAttemptEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamAttemptRepository extends JpaRepository<ExamAttemptEntity, Long> {}
