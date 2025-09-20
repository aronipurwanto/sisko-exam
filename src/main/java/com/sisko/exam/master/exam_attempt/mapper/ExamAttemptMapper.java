package com.sisko.exam.master.exam_attempt.mapper;

import com.sisko.exam.exception.NotFoundException;
import com.sisko.exam.master.exam.model.ExamEntity;
import com.sisko.exam.master.exam.repository.ExamRepository;
import com.sisko.exam.master.exam_attempt.model.ExamAttemptEntity;
import com.sisko.exam.master.exam_attempt.model.ExamAttemptReq;
import com.sisko.exam.master.exam_attempt.model.ExamAttemptRes;
import com.sisko.exam.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ExamAttemptMapper {
    private final ExamRepository examRepository;

    public ExamAttemptRes toResponse(ExamAttemptEntity entity) {
        return ExamAttemptRes.builder()
                .id(entity.getId())
                .examId(entity.getExam().getId())
                .examName(entity.getExam().getName())
                .studentUsername(entity.getStudentUsername())
                .attemptNo(entity.getAttemptNo())
                .startedAt(entity.getStartedAt())
                .submittedAt(entity.getSubmittedAt())
                .status(entity.getStatus())
                .scoreTotal(entity.getScoreTotal())
                .build();
    }

    public List<ExamAttemptRes> toResponseList(List<ExamAttemptEntity> entities) {
        if (entities == null || entities.isEmpty()) return Collections.emptyList();

        return entities.stream().map(this::toResponse).toList();
    }

    public ExamAttemptEntity toEntity(ExamAttemptReq request) {
        return ExamAttemptEntity.builder()
                .id(CommonUtil.getUUID())
                .exam(this.toExamEntity(request.getExamId()))
                .studentUsername(request.getStudentUsername())
                .attemptNo(request.getAttemptNo())
                .startedAt(request.getStartedAt())
                .submittedAt(request.getSubmittedAt())
                .status(request.getStatus())
                .scoreTotal(request.getScoreTotal())
                .build();
    }

    public ExamAttemptEntity toEntity(ExamAttemptReq request, ExamAttemptEntity entity) {
        return ExamAttemptEntity.builder()
                .id(entity.getId())
                .exam(this.toExamEntity(request.getExamId()))
                .studentUsername(request.getStudentUsername())
                .attemptNo(request.getAttemptNo())
                .startedAt(request.getStartedAt())
                .submittedAt(request.getSubmittedAt())
                .status(request.getStatus())
                .scoreTotal(request.getScoreTotal())
                .build();
    }

    private ExamEntity toExamEntity(String id) {
        return this.examRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new NotFoundException(String.format("exam with id %s not found", id)));
    }
}
