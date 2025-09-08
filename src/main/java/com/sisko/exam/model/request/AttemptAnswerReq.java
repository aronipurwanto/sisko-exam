package com.sisko.exam.model.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttemptAnswerReq {
    @NotNull(message = "Attempt ID is required")
    @Positive(message = "Attempt ID must be a positive number")
    private Long examAttemptId;

    @NotNull(message = "Question ID is required")
    @Positive(message = "Question ID must be a positive number")
    private Long questionId;

    @Size(max = 16777215, message = "Answer text must not exceed MEDIUMTEXT limit")
    private String answerText; // for ESSAY questions

    @Positive(message = "Selected option ID must be a positive number")
    private Long selectedOptionId; // for SINGLE MCQ

    @DecimalMin(value = "0.0", message = "Score must be non-negative")
    @DecimalMax(value = "100.0", message = "Score must not exceed 100.0")
    private Double score;

    @Size(max = 100, message = "Graded by must not exceed 100 characters")
    private String gradedBy;

    private Instant gradedAt;

    @Size(max = 1000, message = "Feedback must not exceed 1000 characters")
    private String feedback;
}
