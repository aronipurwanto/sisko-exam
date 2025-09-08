package com.sisko.exam.repo;

import com.sisko.exam.model.entity.QuestionOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionOptionRepository extends JpaRepository<QuestionOptionEntity, Long> {}
