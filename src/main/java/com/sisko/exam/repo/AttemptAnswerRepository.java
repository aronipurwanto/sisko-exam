package com.sisko.exam.repo;

import com.sisko.exam.model.entity.AttemptAnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttemptAnswerRepository extends JpaRepository<AttemptAnswerEntity, Long> {}
