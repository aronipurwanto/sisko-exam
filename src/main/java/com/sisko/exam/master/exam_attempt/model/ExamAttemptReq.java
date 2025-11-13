package com.sisko.exam.master.exam_attempt.model;

import com.sisko.exam.enums.ExamAttemptStatus;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamAttemptReq {

    @NotBlank(message = "exam name cannot be blank")
    private String examId;

    @NotBlank(message = "Student username cannot be blank")
    @Size(min = 3, max = 50, message = "Student username must be between 3 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_\\-\\.]+$", message = "Student username contains invalid characters")
    private String studentUsername;

    @NotNull(message = "no attempt is required")
    @Min(value = 1, message = "attempt number must be at least 1")
    @Max(value = 10, message = "attempt number cannot exceed 10")
    private int attemptNo;

    @NotNull(message = "started at is required")
    @PastOrPresent(message = "started at must be in the past or present")
    private LocalDateTime startedAt;

    @NotNull(message = "submitted at is required")
    @PastOrPresent(message = "submitted at must be in the past or present")
    private LocalDateTime submittedAt;

    @NotNull(message = "status is required")
    private ExamAttemptStatus status;

    @DecimalMin(value = "0.0", message = "Score total cannot be less than 0.0")
    @DecimalMax(value = "100.0", message = "Score total cannot exceed 100.0")
    private Double scoreTotal;

}
