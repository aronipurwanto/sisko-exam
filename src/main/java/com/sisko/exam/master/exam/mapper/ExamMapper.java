package com.sisko.exam.master.exam.mapper;

import com.sisko.exam.exception.NotFoundException;
import com.sisko.exam.master.course.model.CourseEntity;
import com.sisko.exam.master.course.repository.CourseRepository;
import com.sisko.exam.master.exam.model.ExamEntity;
import com.sisko.exam.master.exam.model.ExamReq;
import com.sisko.exam.master.exam.model.ExamRes;
import com.sisko.exam.master.exam_assignment.model.ExamAssignmentEntity;
import com.sisko.exam.master.exam_assignment.model.ExamAssignmentRes;
import com.sisko.exam.master.level.model.LevelEntity;
import com.sisko.exam.master.level.repository.LevelRepository;
import com.sisko.exam.master.question.model.QuestionEntity;
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
public class ExamMapper {
    private final LevelRepository levelRepository;
    private final CourseRepository courseRepository;

    public ExamRes toResponse(ExamEntity entity) {
        return ExamRes.builder()
                .id(entity.getId())
                .name(entity.getName())
                .courseId(entity.getCourse().getId())
                .courseName(entity.getCourse().getName())
                .levelId(entity.getLevel().getId())
                .levelName(entity.getLevel().getName())
                .year(entity.getYear())
                .semester(entity.getSemester())
                .instructions(entity.getInstructions())
                .durationMinutes(entity.getDurationMinutes())
                .randomizeQuestions(entity.isRandomizeQuestions())
                .randomizeOptions(entity.isRandomizeOptions())
                .status(entity.getStatus())
                .startAt(entity.getStartAt())
                .endAt(entity.getEndAt())
                .questions(this.toQuestionList(entity.getQuestions()))
                .examAssignments(this.toExamAssList(entity.getExamAssignments()))
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
                .course(this.getEntityCourse(request.getCourseId()))
                .level(this.getEntityLevel(request.getLevelId()))
                .year(request.getYear())
                .semester(request.getSemester())
                .instructions(request.getInstructions())
                .durationMinutes(request.getDurationMinutes())
                .randomizeQuestions(request.getRandomizeQuestions())
                .randomizeOptions(request.getRandomizeOptions())
                .status(request.getStatus())
                .startAt(request.getStartAt())
                .endAt(request.getStartAt().plusMinutes(request.getDurationMinutes()))
                .build();
    }

    public ExamEntity toEntity(ExamEntity entity, ExamReq request) {
        return ExamEntity.builder()
                .id(entity.getId())
                .name(request.getName())
                .course(this.getEntityCourse(request.getCourseId()))
                .level(this.getEntityLevel(request.getLevelId()))
                .year(request.getYear())
                .semester(request.getSemester())
                .instructions(request.getInstructions())
                .durationMinutes(request.getDurationMinutes())
                .randomizeQuestions(request.getRandomizeQuestions())
                .randomizeOptions(request.getRandomizeOptions())
                .status(request.getStatus())
                .startAt(request.getStartAt())
                .endAt(request.getStartAt().plusMinutes(request.getDurationMinutes()))
                .build();
    }

    private List<QuestionRes> toQuestionList(List<QuestionEntity> entities) {
        if (entities == null || entities.isEmpty()) return Collections.emptyList();
        return entities.stream()
                .filter(entity -> entity.getDeletedAt() == null)
                .map(entity -> QuestionRes.builder()
                        .id(entity.getId())
                        .examId(entity.getExam().getId())
                        .examName(entity.getExam().getName())
                        .qtype(entity.getQtype())
                        .questionAnswerPolicy(entity.getQuestionAnswerPolicy())
                        .stem(entity.getStem())
                        .pointsDefault(entity.getPointsDefault())
                        .questionOptions(this.toQuestionOptionList(entity.getQuestionOptions()))
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
                .build()
        ).collect(Collectors.toList());
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

    private LevelEntity getEntityLevel(String id) {
        return this.levelRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new NotFoundException(String.format("level with id %s not found", id)));
    }

    private CourseEntity getEntityCourse(String id) {
        return this.courseRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new NotFoundException(String.format("course with id %s not found", id)));
    }
}
