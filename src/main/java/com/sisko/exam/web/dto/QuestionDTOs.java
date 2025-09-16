package com.sisko.exam.web.dto;

import com.sisko.exam.enums.QuestionAnswerPolicy;
import com.sisko.exam.enums.QuestionType;
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
            @NotNull QuestionType qtype,
            @NotNull QuestionAnswerPolicy answerPolicy,
            @NotBlank String stem,
            @Positive double pointsDefault,
            List<@Valid OptionReq> options
    ) {}

    public record QuestionResp(String id, String stem, QuestionType qtype, QuestionAnswerPolicy answerPolicy, double points) {}
}
