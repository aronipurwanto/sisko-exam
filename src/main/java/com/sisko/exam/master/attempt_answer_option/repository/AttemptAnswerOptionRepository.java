package com.sisko.exam.master.attempt_answer_option.repository;

import com.sisko.exam.master.attempt_answer_option.model.AttemptAnswerOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttemptAnswerOptionRepository extends JpaRepository<AttemptAnswerOptionEntity, String> {
    List<AttemptAnswerOptionEntity> findAllByDeletedAtIsNull();
    Optional<AttemptAnswerOptionEntity> findByIdAndDeletedAtIsNull(String Id);
}
