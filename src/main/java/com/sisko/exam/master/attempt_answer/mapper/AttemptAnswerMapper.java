package com.sisko.exam.master.attempt_answer.mapper;

import com.sisko.exam.exception.NotFoundException;
import com.sisko.exam.master.attempt_answer.model.AttemptAnswerEntity;
import com.sisko.exam.master.attempt_answer.model.AttemptAnswerReq;
import com.sisko.exam.master.attempt_answer.model.AttemptAnswerRes;
import com.sisko.exam.master.exam_attempt.model.ExamAttemptEntity;
import com.sisko.exam.master.exam_attempt.repository.ExamAttemptRepository;
import com.sisko.exam.master.question.model.QuestionEntity;
import com.sisko.exam.master.question.repository.QuestionRepository;
import com.sisko.exam.master.question_option.model.QuestionOptionEntity;
import com.sisko.exam.master.question_option.repository.QuestionOptionRepository;
import com.sisko.exam.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AttemptAnswerMapper {
    private final ExamAttemptRepository examAttemptRepository;
    private final QuestionRepository questionRepository;
    private final QuestionOptionRepository  questionOptionRepository;

    public AttemptAnswerRes toResponse(AttemptAnswerEntity entity) {
        return AttemptAnswerRes.builder()
                .examAttemptId(entity.getExamAttempt().getId())
                .examAttemptStudentUsername(entity.getExamAttempt().getStudentUsername())
                .questionId(entity.getQuestion().getId())
                .questionStem(entity.getQuestion().getStem())
                .answerText(entity.getAnswerText())
                .questionOptionId(entity.getQuestionOption().getId())
                .questionOptionContent(entity.getQuestionOption().getContent())
                .score(entity.getScore())
                .gradedBy(entity.getGradedBy())
                .gradedAt(entity.getGradedAt())
                .feedback(entity.getFeedback())
                .build();
    }

    public List<AttemptAnswerRes> toResponseList(List<AttemptAnswerEntity> entities) {
        if (entities == null || entities.isEmpty()) return Collections.emptyList();
        return entities.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public AttemptAnswerEntity toEntity(AttemptAnswerReq request) {
        return AttemptAnswerEntity.builder()
                .id(CommonUtil.getUUID())
                .examAttempt(this.getEntityExamAttempt(request.getExamAttemptId()))
                .question(this.getEntityQuestion(request.getQuestionId()))
                .answerText(request.getAnswerText())
                .questionOption(this.getEntityQuestionOption(request.getQuestionOptionId()))
                .score(request.getScore())
                .gradedBy(request.getGradedBy())
                .gradedAt(request.getGradedAt())
                .feedback(request.getFeedback())
                .build();
    }

    public AttemptAnswerEntity toEntity(AttemptAnswerReq request, AttemptAnswerEntity entity) {
        return AttemptAnswerEntity.builder()
                .id(entity.getId())
                .examAttempt(this.getEntityExamAttempt(request.getExamAttemptId()))
                .question(this.getEntityQuestion(request.getQuestionId()))
                .answerText(request.getAnswerText())
                .questionOption(this.getEntityQuestionOption(request.getQuestionOptionId()))
                .score(request.getScore())
                .gradedBy(request.getGradedBy())
                .gradedAt(request.getGradedAt())
                .feedback(request.getFeedback())
                .build();
    }

    private ExamAttemptEntity getEntityExamAttempt(String id) {
        return this.examAttemptRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new NotFoundException(String.format("exam-attempt with id: %s not found", id)));
    }

    private QuestionEntity getEntityQuestion(String id) {
        return this.questionRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new NotFoundException(String.format("question with id: %s not found", id)));
    }

    private QuestionOptionEntity getEntityQuestionOption(String id) {
        return this.questionOptionRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new NotFoundException(String.format("question-option with id: %s not found", id)));
    }
}
