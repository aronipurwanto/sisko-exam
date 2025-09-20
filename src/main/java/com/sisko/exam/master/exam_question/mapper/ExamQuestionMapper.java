package com.sisko.exam.master.exam_question.mapper;

import com.sisko.exam.exception.NotFoundException;
import com.sisko.exam.master.exam.model.ExamEntity;
import com.sisko.exam.master.exam.repository.ExamRepository;
import com.sisko.exam.master.exam_question.model.ExamQuestionEntity;
import com.sisko.exam.master.exam_question.model.ExamQuestionReq;
import com.sisko.exam.master.exam_question.model.ExamQuestionRes;
import com.sisko.exam.master.question.model.QuestionEntity;
import com.sisko.exam.master.question.repository.QuestionRepository;
import com.sisko.exam.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ExamQuestionMapper {
    private final ExamRepository examRepository;
    private final QuestionRepository questionRepository;

    public ExamQuestionRes toResponse(ExamQuestionEntity entity) {
        return ExamQuestionRes.builder()
                .examId(entity.getExam().getId())
                .examName(entity.getExam().getName())
                .questionId(entity.getQuestion().getId())
                .questionStem(entity.getQuestion().getStem())
                .points(entity.getPoints())
                .orderIndex(entity.getOrderIndex())
                .required(entity.isRequired())
                .build();
    }

    public List<ExamQuestionRes> toResponseList(List<ExamQuestionEntity> entities) {
        if (entities == null ||  entities.isEmpty()) return Collections.emptyList();
        return entities.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public ExamQuestionEntity toEntity(ExamQuestionReq request) {
        return ExamQuestionEntity.builder()
                .id(CommonUtil.getUUID())
                .exam(this.getEntityExam(request.getExamId()))
                .question(this.getEntityQuestion(request.getQuestionId()))
                .points(request.getPoints())
                .orderIndex(request.getOrderIndex())
                .required(request.getRequired())
                .build();
    }

    public ExamQuestionEntity toEntity(ExamQuestionReq request, ExamQuestionEntity entity) {
        return ExamQuestionEntity.builder()
                .id(entity.getId())
                .exam(this.getEntityExam(request.getExamId()))
                .question(this.getEntityQuestion(request.getQuestionId()))
                .points(request.getPoints())
                .orderIndex(request.getOrderIndex())
                .required(request.getRequired())
                .build();
    }

    private ExamEntity getEntityExam(String id) {
        return this.examRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new NotFoundException(String.format("exam with id %s not found", id)));
    }

    private QuestionEntity getEntityQuestion(String id) {
        return this.questionRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new NotFoundException(String.format("question with id %s not found", id)));
    }
}
