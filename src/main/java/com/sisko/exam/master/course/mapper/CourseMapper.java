package com.sisko.exam.master.course.mapper;

import com.sisko.exam.master.course.model.CourseEntity;
import com.sisko.exam.master.course.model.CourseReq;
import com.sisko.exam.master.course.model.CourseRes;
import com.sisko.exam.master.exam.model.ExamEntity;
import com.sisko.exam.master.exam.model.ExamRes;
import com.sisko.exam.util.CommonUtil;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseMapper {

    public CourseRes toResponse(CourseEntity entity) {
        return CourseRes.builder()
                .id(entity.getId())
                .name(entity.getName())
                .code(entity.getCode())
                .exams(this.toExamList(entity.getExamEntities()))
                .build();
    }

    public List<CourseRes> toResponseList(List<CourseEntity> entities) {
        if (entities == null || entities.isEmpty()) return Collections.emptyList();
        return entities.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public CourseEntity toEntity(CourseReq request) {
        return CourseEntity.builder()
                .id(CommonUtil.getUUID())
                .name(request.getName())
                .code(request.getCode())
                .build();
    }

    public CourseEntity toEntity(CourseEntity entity, CourseReq request) {
        return CourseEntity.builder()
                .id(entity.getId())
                .name(request.getName())
                .code(request.getCode())
                .build();
    }

    private List<ExamRes> toExamList(List<ExamEntity> entities) {
        if (entities == null || entities.isEmpty()) return Collections.emptyList();
        return entities.stream()
                .filter(entity -> entity.getDeletedAt() == null)
                .map(entity -> ExamRes.builder()
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
