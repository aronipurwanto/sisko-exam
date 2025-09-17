package com.sisko.exam.master.exam.repository;

import com.sisko.exam.master.exam.model.ExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExamRepository extends JpaRepository<ExamEntity, String> {
    List<ExamEntity> findAllByDeletedAtIsNull();
    Optional<ExamEntity> findByIdAndDeletedAtIsNull(String id);
}