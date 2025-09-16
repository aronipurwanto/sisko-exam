package com.sisko.exam.master.question_option.repository;

import com.sisko.exam.master.question_option.model.QuestionOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionOptionRepository extends JpaRepository<QuestionOptionEntity, String> {}
