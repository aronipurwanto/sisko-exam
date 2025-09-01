package com.sisko.exam.web.dto;

import jakarta.validation.constraints.*;
import java.util.List;
public class AttemptDTOs {
    public record StartAttemptReq(@NotNull Long examId, @NotNull Long assignmentId, @Positive int attemptNo) {}
    public record StartAttemptResp(Long attemptId, String status) {}


    public record AnswerEssayReq(@NotNull Long questionId, @NotBlank String text) {}
    public record AnswerSingleReq(@NotNull Long questionId, @NotNull Long optionId) {}
    public record AnswerMultiReq(@NotNull Long questionId, @Size(min=1) List<@NotNull Long> optionIds) {}
    public record SubmitResp(Long attemptId, Double scoreTotal, String status) {}
}
