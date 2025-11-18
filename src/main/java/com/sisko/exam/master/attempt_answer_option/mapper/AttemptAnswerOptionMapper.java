package com.sisko.exam.master.attempt_answer_option.mapper;

import com.sisko.exam.exception.NotFoundException;
import com.sisko.exam.master.attempt_answer.model.AttemptAnswerEntity;
import com.sisko.exam.master.attempt_answer.repository.AttemptAnswerRepository;
import com.sisko.exam.master.attempt_answer_option.model.AttemptAnswerOptionEntity;
import com.sisko.exam.master.attempt_answer_option.model.AttemptAnswerOptionReq;
import com.sisko.exam.master.attempt_answer_option.model.AttemptAnswerOptionRes;
import com.sisko.exam.master.question_option.model.QuestionOptionEntity;
import com.sisko.exam.master.question_option.repository.QuestionOptionRepository;
import com.sisko.exam.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AttemptAnswerOptionMapper {
    private final QuestionOptionRepository questionOptionRepository;
    private final AttemptAnswerRepository attemptAnswerRepository;

    public AttemptAnswerOptionRes toResponse(AttemptAnswerOptionEntity entity) {
        return AttemptAnswerOptionRes.builder()
                .attemptAnswerId(entity.getAttemptAnswer().getId())
                .attemptAnswerScore(entity.getAttemptAnswer().getScore())
                .questionOptionId(entity.getQuestionOption().getId())
                .questionContent(entity.getQuestionOption().getContent())
                .selected(entity.isSelected())
                .build();
    }

    public List<AttemptAnswerOptionRes> toResponseList(List<AttemptAnswerOptionEntity> entities) {
        if (entities == null || entities.isEmpty()) return Collections.emptyList();
        return entities.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public AttemptAnswerOptionEntity toEntity(AttemptAnswerOptionReq request) {
        return AttemptAnswerOptionEntity.builder()
                .id(CommonUtil.getUUID())
                .attemptAnswer(this.getEntityAttemptAnswer(request.getAttemptAnswerId()))
                .questionOption(this.getEntityQuestionOption(request.getQuestionOptionId()))
                .selected(request.isSelected())
                .build();
    }

    public AttemptAnswerOptionEntity toEntity(AttemptAnswerOptionReq request, AttemptAnswerOptionEntity entity) {
        return AttemptAnswerOptionEntity.builder()
                .id(entity.getId())
                .attemptAnswer(this.getEntityAttemptAnswer(request.getAttemptAnswerId()))
                .questionOption(this.getEntityQuestionOption(request.getQuestionOptionId()))
                .selected(request.isSelected())
                .build();
    }

    private AttemptAnswerEntity getEntityAttemptAnswer(String id) {
        return this.attemptAnswerRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new NotFoundException(String.format("question with id: %s not found", id)));
    }

    private QuestionOptionEntity getEntityQuestionOption(String id) {
        return this.questionOptionRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new NotFoundException(String.format("question-option with id: %s not found", id)));
    }
}
