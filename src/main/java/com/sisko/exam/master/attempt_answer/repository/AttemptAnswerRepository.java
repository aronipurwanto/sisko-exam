package com.sisko.exam.master.attempt_answer.repository;

import com.sisko.exam.master.attempt_answer.model.AttemptAnswerEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttemptAnswerRepository extends JpaRepository<AttemptAnswerEntity, String> {
    List<AttemptAnswerEntity> findAllByDeletedAtIsNull();
    Optional<AttemptAnswerEntity> findByIdAndDeletedAtIsNull(String id);
}
