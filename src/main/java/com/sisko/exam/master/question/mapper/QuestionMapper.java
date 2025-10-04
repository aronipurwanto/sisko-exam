package com.sisko.exam.master.question.mapper;

import com.sisko.exam.master.question.model.QuestionEntity;
import com.sisko.exam.master.question.model.QuestionReq;
import com.sisko.exam.master.question.model.QuestionRes;
import com.sisko.exam.master.question.repository.QuestionRepository;
import com.sisko.exam.master.question_option.model.QuestionOptionEntity;
import com.sisko.exam.master.question_option.model.QuestionOptionRes;
import com.sisko.exam.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class QuestionMapper {
    private final QuestionRepository questionRepository;

    public QuestionRes toResponse(QuestionEntity entity) {
        return QuestionRes.builder()
                .id(entity.getId())
                .qtype(entity.getQtype())
                .questionAnswerPolicy(entity.getQuestionAnswerPolicy())
                .stem(entity.getStem())
                .pointsDefault(entity.getPointsDefault())
                .questionOptions(toQuestionOptionList(entity.getQuestionOptions()))
                .build();
    }

    public List<QuestionRes> toResponseList(List<QuestionEntity> entities) {
        if (entities == null || entities.isEmpty()) return Collections.emptyList();

        return entities.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public QuestionEntity toEntity(QuestionReq request) {
        return QuestionEntity.builder()
                .id(CommonUtil.getUUID())
                .qtype(request.getQtype())
                .questionAnswerPolicy(request.getQuestionAnswerPolicy())
                .stem(request.getStem())
                .pointsDefault(request.getPointsDefault())
                .build();
    }

    public QuestionEntity toEntity(QuestionReq request, QuestionEntity entity) {
        return QuestionEntity.builder()
                .id(entity.getId())
                .qtype(request.getQtype())
                .questionAnswerPolicy(request.getQuestionAnswerPolicy())
                .stem(request.getStem())
                .pointsDefault(request.getPointsDefault())
                .build();
    }

    private List<QuestionOptionRes> toQuestionOptionList(List<QuestionOptionEntity> entities) {
        if (entities == null || entities.isEmpty()) return Collections.emptyList();

        return entities.stream().map(option -> QuestionOptionRes.builder()
                .id(option.getId())
                .questionId(option.getQuestion().getId())
                .questionStem(option.getQuestion().getStem())
                .label(option.getLabel())
                .content(option.getContent())
                .correct(option.isCorrect())
                .orderIndex(option.getOrderIndex())
                .build()
        ).collect(Collectors.toList());
    }
}
