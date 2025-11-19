package com.sisko.exam.master.exam.mapper;

import com.sisko.exam.master.exam.model.ExamEntity;
import com.sisko.exam.master.exam.model.ExamReq;
import com.sisko.exam.master.exam.model.ExamRes;
import com.sisko.exam.master.exam_assignment.model.ExamAssignmentEntity;
import com.sisko.exam.master.exam_assignment.model.ExamAssignmentRes;
import com.sisko.exam.master.exam_assignment.repository.ExamAssignmentRepository;
import com.sisko.exam.master.exam_question.model.ExamQuestionEntity;
import com.sisko.exam.master.exam_question.model.ExamQuestionRes;
import com.sisko.exam.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ExamMapper {
    private final ExamAssignmentRepository examAssignmentRepository;

    public ExamRes toResponse(ExamEntity entity) {
        return ExamRes.builder()
                .id(entity.getId())
                .name(entity.getName())
                .instructions(entity.getInstructions())
                .durationMinutes(entity.getDurationMinutes())
                .randomizeQuestions(entity.isRandomizeQuestions())
                .randomizeOptions(entity.isRandomizeOptions())
                .status(entity.getStatus())
                .startAt(entity.getStartAt())
                .endAt(entity.getEndAt())
                .examAssignments(this.toExamAssList(entity.getExamAssignments()))
                .examQuestions(this.toExamQuestionList(entity.getExamQuestions()))
                .build();
    }

    public List<ExamRes> toResponseList(List<ExamEntity> entities) {
        if (entities == null || entities.isEmpty()) return Collections.emptyList();

        return entities.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public ExamEntity toEntity(ExamReq request) {
        return ExamEntity.builder()
                .id(CommonUtil.getUUID())
                .name(request.getName())
                .instructions(request.getInstructions())
                .durationMinutes(request.getDurationMinutes())
                .randomizeQuestions(request.getRandomizeQuestions())
                .randomizeOptions(request.getRandomizeOptions())
                .status(request.getStatus())
                .startAt(request.getStartAt())
                .endAt(request.getEndAt())
                .build();
    }

    public ExamEntity toEntity(ExamEntity entity, ExamReq request) {
        return ExamEntity.builder()
                .id(entity.getId())
                .name(request.getName())
                .instructions(request.getInstructions())
                .durationMinutes(request.getDurationMinutes())
                .randomizeQuestions(request.getRandomizeQuestions())
                .randomizeOptions(request.getRandomizeOptions())
                .status(request.getStatus())
                .startAt(request.getStartAt())
                .endAt(request.getEndAt())
                .build();
    }

    private List<ExamQuestionRes> toExamQuestionList(List<ExamQuestionEntity> entities) {
        if (entities == null || entities.isEmpty()) return Collections.emptyList();
        return entities.stream().map(entity -> ExamQuestionRes.builder()
                .id(entity.getId())
                .examId(entity.getExam().getId())
                .examName(entity.getExam().getName())
                .questionId(entity.getQuestion().getId())
                .questionStem(entity.getQuestion().getStem())
                .points(entity.getPoints())
                .orderIndex(entity.getOrderIndex())
                .required(entity.isRequired())
                .build()).collect(Collectors.toList());
    }

    private List<ExamAssignmentRes> toExamAssList(List<ExamAssignmentEntity> entities) {
        if (entities == null || entities.isEmpty()) return Collections.emptyList();
        return entities.stream().map(entity -> ExamAssignmentRes.builder()
                .id(entity.getId())
                .examId(entity.getExam().getId())
                .examName(entity.getExam().getName())
                .groupLabel(entity.getGroupLabel())
                .startAt(entity.getStartAt())
                .endAt(entity.getEndAt())
                .maxAttempts(entity.getMaxAttempts())
                .accessCode(entity.getAccessCode())
                .audienceCode(entity.getAudienceCode())
                .build()
        ).collect(Collectors.toList());
    }
}
