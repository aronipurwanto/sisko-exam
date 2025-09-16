package com.sisko.exam.master.question.repository;

import com.sisko.exam.master.question.model.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface QuestionRepository extends JpaRepository<QuestionEntity, String>, JpaSpecificationExecutor<QuestionEntity> {

}