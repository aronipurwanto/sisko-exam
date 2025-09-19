package com.sisko.exam.master.exam_question.repository;

import com.sisko.exam.master.exam_question.model.ExamQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExamQuestionRepository extends JpaRepository<ExamQuestionEntity, String> {
    List<ExamQuestionEntity> findAllByDeletedAtIsNull();
    Optional<ExamQuestionEntity> findByIdAndDeletedAtIsNull(String id);
}
