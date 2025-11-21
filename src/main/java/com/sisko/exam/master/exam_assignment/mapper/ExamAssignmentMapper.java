package com.sisko.exam.master.exam_assignment.mapper;

import com.sisko.exam.exception.NotFoundException;
import com.sisko.exam.master.exam.model.ExamEntity;
import com.sisko.exam.master.exam.repository.ExamRepository;
import com.sisko.exam.master.exam_assignment.model.ExamAssignmentEntity;
import com.sisko.exam.master.exam_assignment.model.ExamAssignmentReq;
import com.sisko.exam.master.exam_assignment.model.ExamAssignmentRes;
import com.sisko.exam.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ExamAssignmentMapper {
    private final ExamRepository examRepository;

    public ExamAssignmentRes toResponse(ExamAssignmentEntity entity) {
        return ExamAssignmentRes.builder()
                .id(entity.getId())
                .examId(entity.getExam().getId())
                .examName(entity.getExam().getName())
                .groupLabel(entity.getGroupLabel())
                .startAt(entity.getStartAt())
                .endAt(entity.getEndAt())
                .maxAttempts(entity.getMaxAttempts())
                .accessCode(entity.getAccessCode())
                .build();
    }

    public List<ExamAssignmentRes> toResponseList(List<ExamAssignmentEntity> entities) {
        if (entities == null || entities.isEmpty()) return Collections.emptyList();
        return entities.stream().map(this::toResponse).collect(Collectors.toList());
    }
    
    public ExamAssignmentEntity toEntity(ExamAssignmentReq request) {
        return ExamAssignmentEntity.builder()
                .id(CommonUtil.getUUID())
                .exam(this.getExamEntity(request.getExamId()))
                .groupLabel(request.getGroupLabel())
                .startAt(request.getStartAt())
                .endAt(request.getEndAt())
                .maxAttempts(request.getMaxAttempts())
                .accessCode(request.getAccessCode())
                .build();
    }

    public ExamAssignmentEntity toEntity(ExamAssignmentReq request, ExamAssignmentEntity entity) {
        return ExamAssignmentEntity.builder()
                .id(entity.getId())
                .exam(this.getExamEntity(request.getExamId()))
                .groupLabel(request.getGroupLabel())
                .startAt(request.getStartAt())
                .endAt(request.getEndAt())
                .maxAttempts(request.getMaxAttempts())
                .accessCode(request.getAccessCode())
                .build();
    }

    private ExamEntity getExamEntity(String id) {
        return this.examRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new NotFoundException(String.format("exam with id %s not found", id)));
    }
}
