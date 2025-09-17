package com.sisko.exam.master.exam_assignment.repository;

import com.sisko.exam.master.exam_assignment.model.ExamAssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExamAssignmentRepository extends JpaRepository<ExamAssignmentEntity, String> {
    @Query("SELECT e FROM ExamAssignmentEntity e WHERE e.deletedAt IS NULL")
    List<ExamAssignmentEntity> findAllActivesExamAssignments();
    List<ExamAssignmentEntity> findAllByDeletedAtIsNull();
    Optional<ExamAssignmentEntity> findByIdAndDeletedAtIsNull(String id);
}
