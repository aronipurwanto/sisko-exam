package com.sisko.exam.master.question.mapper;

import com.sisko.exam.exception.NotFoundException;
import com.sisko.exam.master.exam.model.ExamEntity;
import com.sisko.exam.master.exam.repository.ExamRepository;
import com.sisko.exam.master.question.model.QuestionEntity;
import com.sisko.exam.master.question.model.QuestionReq;
import com.sisko.exam.master.question.model.QuestionRes;
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
    private final ExamRepository examRepository;

    public QuestionRes toResponse(QuestionEntity entity) {
        return QuestionRes.builder()
                .id(entity.getId())
                .examId(entity.getExam().getId())
                .examName(entity.getExam().getName())
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
                .exam(this.getEntityExam(request.getExamId()))
                .qtype(request.getQtype())
                .questionAnswerPolicy(request.getQuestionAnswerPolicy())
                .stem(request.getStem())
                .pointsDefault(request.getPointsDefault())
                .build();
    }

    public QuestionEntity toEntity(QuestionReq request, QuestionEntity entity) {
        return QuestionEntity.builder()
                .id(entity.getId())
                .exam(this.getEntityExam(request.getExamId()))
                .qtype(request.getQtype())
                .questionAnswerPolicy(request.getQuestionAnswerPolicy())
                .stem(request.getStem())
                .pointsDefault(request.getPointsDefault())
                .build();
    }

    private List<QuestionOptionRes> toQuestionOptionList(List<QuestionOptionEntity> entities) {
        if (entities == null || entities.isEmpty()) return Collections.emptyList();
        return entities.stream()
                .filter(option -> option.getDeletedAt() == null)
                .map(option -> QuestionOptionRes.builder()
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

    private ExamEntity getEntityExam(String id) {
        return this.examRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new NotFoundException(String.format("exam with id %s not found", id)));
    }
}
