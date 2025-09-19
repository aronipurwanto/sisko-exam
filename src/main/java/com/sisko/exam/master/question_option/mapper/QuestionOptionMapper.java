package com.sisko.exam.master.question_option.mapper;

import com.sisko.exam.exception.NotFoundException;
import com.sisko.exam.master.attempt_answer.repository.AttemptAnswerRepository;
import com.sisko.exam.master.attempt_answer_option.repository.AttemptAnswerOptionRepository;
import com.sisko.exam.master.question.model.QuestionEntity;
import com.sisko.exam.master.question.repository.QuestionRepository;
import com.sisko.exam.master.question_option.model.QuestionOptionEntity;
import com.sisko.exam.master.question_option.model.QuestionOptionReq;
import com.sisko.exam.master.question_option.model.QuestionOptionRes;
import com.sisko.exam.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class QuestionOptionMapper {
    private final QuestionRepository questionRepository;
    private final AttemptAnswerRepository attemptAnswerRepository;
    private final AttemptAnswerOptionRepository  attemptAnswerOptionRepository;

    public QuestionOptionRes toResponse(QuestionOptionEntity entity) {
        return QuestionOptionRes.builder()
                .id(entity.getId())
                .questionId(entity.getQuestion().getId())
                .questionStem(entity.getQuestion().getStem())
                .label(entity.getLabel())
                .content(entity.getContent())
                .correct(entity.isCorrect())
                .orderIndex(entity.getOrderIndex())
                .build();
    }

    public List<QuestionOptionRes> toResponseList(List<QuestionOptionEntity> entities) {
        if (entities == null || entities.isEmpty()) return Collections.emptyList();
        return entities.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public QuestionOptionEntity toEntity(QuestionOptionReq request) {
        return QuestionOptionEntity.builder()
                .id(CommonUtil.getUUID())
                .question(this.getEntityQuestion(request.getQuestionId()))
                .label(request.getLabel())
                .content(request.getContent())
                .correct(request.getCorrect())
                .orderIndex(request.getOrderIndex())
                .build();
    }

    public QuestionOptionEntity toEntity(QuestionOptionReq request, QuestionOptionEntity entity) {
        return QuestionOptionEntity.builder()
                .id(entity.getId())
                .question(this.getEntityQuestion(request.getQuestionId()))
                .label(request.getLabel())
                .content(request.getContent())
                .correct(request.getCorrect())
                .orderIndex(request.getOrderIndex())
                .build();
    }

    private QuestionEntity getEntityQuestion(String id) {
        return this.questionRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new NotFoundException(String.format("question with id: %s not found", id)));
    }
}
