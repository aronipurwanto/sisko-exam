package com.sisko.exam.master.exam.repository;

import com.sisko.exam.master.exam.model.ExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<ExamEntity, Long> {}