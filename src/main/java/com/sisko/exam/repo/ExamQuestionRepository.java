package com.sisko.exam.repo;

import com.sisko.exam.model.entity.ExamQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExamQuestionRepository extends JpaRepository<ExamQuestionEntity, Long> {}
