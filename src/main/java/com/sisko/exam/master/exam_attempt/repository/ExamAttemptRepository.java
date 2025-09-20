package com.sisko.exam.master.exam_attempt.repository;

import com.sisko.exam.master.exam.model.ExamEntity;
import com.sisko.exam.master.exam_attempt.model.ExamAttemptEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExamAttemptRepository extends JpaRepository<ExamAttemptEntity, String> {
    List<ExamAttemptEntity> findAllByDeletedAtIsNull();
    Optional<ExamAttemptEntity> findByIdAndDeletedAtIsNull(String id);
}
