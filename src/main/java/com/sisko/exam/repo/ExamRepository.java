package com.sisko.exam.repo;

import com.sisko.exam.model.entity.ExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExamRepository extends JpaRepository<ExamEntity, Long> {}