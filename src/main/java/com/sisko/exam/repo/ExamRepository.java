package com.sisko.exam.repo;

import com.sisko.exam.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExamRepository extends JpaRepository<Exam, Long> {}