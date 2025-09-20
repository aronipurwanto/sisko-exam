package com.sisko.exam.master.question_option.repository;

import com.sisko.exam.master.question_option.model.QuestionOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionOptionRepository extends JpaRepository<QuestionOptionEntity, String> {
    List<QuestionOptionEntity> findAllByDeletedAtIsNull();
    Optional<QuestionOptionEntity> findByIdAndDeletedAtIsNull(String id);
}
