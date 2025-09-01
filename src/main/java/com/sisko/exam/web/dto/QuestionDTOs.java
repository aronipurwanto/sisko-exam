package com.sisko.exam.web.dto;

import com.sisko.exam.model.Question;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public class QuestionDTOs {
    public record OptionReq(
            @NotBlank String label,
            @NotBlank String content,
            boolean correct
    ) {}


    public record CreateQuestionReq(
            @NotNull Question.Type qtype,
            @NotNull Question.AnswerPolicy answerPolicy,
            @NotBlank String stem,
            @Positive double pointsDefault,
            List<@Valid OptionReq> options
    ) {}

    public record QuestionResp(Long id, String stem, Question.Type qtype, Question.AnswerPolicy answerPolicy, double points) {}
}
