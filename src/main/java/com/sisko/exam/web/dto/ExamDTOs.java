package com.sisko.exam.web.dto;

import jakarta.validation.constraints.*;
import java.time.Instant;
import java.util.List;


public class ExamDTOs {
    public record CreateExamReq(@NotBlank String name, String instructions, @Positive int durationMinutes,
                                Boolean randomizeQuestions, Boolean randomizeOptions, Instant startAt, Instant endAt) {}
    public record ExamResp(Long id, String name, String status) {}


    public record AddQuestionsReq(@NotEmpty List<Item> items) {
        public record Item(@NotNull Long questionId, @Positive double points, @Positive int orderIndex, Boolean required) {}
    }


    public record CreateAssignmentReq(@NotBlank String groupLabel, @NotNull Instant startAt, @NotNull Instant endAt, @Positive int maxAttempts, String accessCode) {}
}