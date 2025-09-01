package com.sisko.exam.repo;

import com.sisko.exam.model.ExamQuestion;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExamQuestionRepository extends JpaRepository<ExamQuestion, Long> {}
