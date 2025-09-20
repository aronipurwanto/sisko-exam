package com.sisko.exam.master.attempt_answer.model;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttemptAnswerReq {
    @NotBlank(message = "Attempt ID is required")
    private String examAttemptId;

    @NotBlank(message = "Question ID is required")
    private String questionId;

    @Size(max = 16777215, message = "Answer text must not exceed MEDIUMTEXT limit")
    private String answerText; // for ESSAY questions

    @NotBlank(message = "Question Option ID is required")
    private String questionOptionId; // for SINGLE MCQ

    @DecimalMin(value = "0.0", message = "Score must be non-negative")
    @DecimalMax(value = "100.0", message = "Score must not exceed 100.0")
    private Double score;

    @Size(max = 100, message = "Graded by must not exceed 100 characters")
    private String gradedBy;

    private Instant gradedAt;

    @Size(max = 1000, message = "Feedback must not exceed 1000 characters")
    private String feedback;
}
