package com.sisko.exam.master.level.mapper;

import com.sisko.exam.master.exam.model.ExamEntity;
import com.sisko.exam.master.exam.model.ExamRes;
import com.sisko.exam.master.level.model.LevelEntity;
import com.sisko.exam.master.level.model.LevelReq;
import com.sisko.exam.master.level.model.LevelRes;
import com.sisko.exam.util.CommonUtil;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LevelMapper {

    public LevelRes toResponse(LevelEntity entity) {
        return LevelRes.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .name(entity.getName())
                .exams(this.toExamList(entity.getExamEntities()))
                .build();
    }

    public List<LevelRes> toResponseList(List<LevelEntity> entities) {
        if (entities == null || entities.isEmpty()) return Collections.emptyList();
        return entities.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public LevelEntity toEntity(LevelReq request) {
        return LevelEntity.builder()
                .id(CommonUtil.getUUID())
                .code(request.getCode())
                .name(request.getName())
                .build();
    }

    public LevelEntity toEntity(LevelEntity entity, LevelReq request) {
        return LevelEntity.builder()
                .id(entity.getId())
                .code(request.getCode())
                .name(request.getName())
                .build();
    }

    private List<ExamRes> toExamList(List<ExamEntity> entities) {
        if (entities == null || entities.isEmpty()) return Collections.emptyList();
        return entities.stream().map(entity -> ExamRes.builder()
                .id(entity.getId())
                .name(entity.getName())
                .courseId(entity.getCourse().getId())
                .courseName(entity.getCourse().getName())
                .levelId(entity.getLevel().getId())
                .levelName(entity.getLevel().getName())
                .instructions(entity.getInstructions())
                .durationMinutes(entity.getDurationMinutes())
                .randomizeQuestions(entity.isRandomizeQuestions())
                .randomizeOptions(entity.isRandomizeOptions())
                .status(entity.getStatus())
                .startAt(entity.getStartAt())
                .endAt(entity.getEndAt())
                .build()
        ).collect(Collectors.toList());
    }
}
