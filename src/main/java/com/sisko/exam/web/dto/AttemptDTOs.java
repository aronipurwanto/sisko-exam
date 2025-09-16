package com.sisko.exam.web.dto;

import jakarta.validation.constraints.*;
import java.util.List;
public class AttemptDTOs {
    public record StartAttemptReq(@NotNull String examId, @NotNull String assignmentId, @Positive int attemptNo) {}
    public record StartAttemptResp(String attemptId, String status) {}


    public record AnswerEssayReq(@NotNull String questionId, @NotBlank String text) {}
    public record AnswerSingleReq(@NotNull String questionId, @NotNull String optionId) {}
    public record AnswerMultiReq(@NotNull String questionId, @Size(min=1) List<@NotNull String> optionIds) {}
    public record SubmitResp(String attemptId, Double scoreTotal, String status) {}
}
