package com.sisko.exam.master.exam_question.repository;

import com.sisko.exam.master.exam_question.model.ExamQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExamQuestionRepository extends JpaRepository<ExamQuestionEntity, String> {}
