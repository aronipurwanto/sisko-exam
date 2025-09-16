package com.sisko.exam.master.exam.mapper;

import com.sisko.exam.exception.NotFoundException;
import com.sisko.exam.master.exam.model.ExamEntity;
import com.sisko.exam.master.exam.model.ExamReq;
import com.sisko.exam.master.exam.model.ExamRes;
import com.sisko.exam.master.exam.repository.ExamRepository;
import com.sisko.exam.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ExamMapper {
    private final ExamRepository examRepository;

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

    public ExamEntity getEntityById(String id) {
        return this.examRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("exam with id: %s not found", id)));
    }
}
