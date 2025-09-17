package com.sisko.exam.master.question.repository;

import com.sisko.exam.master.exam.model.ExamEntity;
import com.sisko.exam.master.question.model.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, String>, JpaSpecificationExecutor<QuestionEntity> {
    List<QuestionEntity> findAllByDeletedAtIsNull();
    Optional<QuestionEntity> findByIdAndDeletedAtIsNull(String id);
}