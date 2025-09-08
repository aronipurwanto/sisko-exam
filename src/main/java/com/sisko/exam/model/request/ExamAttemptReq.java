package com.sisko.exam.model.request;

import com.sisko.exam.enums.ExamAttemptStatus;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamAttemptReq {

    @NotNull(message = "Exam ID cannot be null")
    @Positive(message = "Exam ID must be positive")
    private Long examId;

    @NotNull(message = "Assignment ID cannot be null")
    @Positive(message = "Assignment ID must be positive")
    private Long assignmentId;

    @NotBlank(message = "Student username cannot be blank")
    @Size(min = 3, max = 50, message = "Student username must be between 3 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_\\-\\.]+$", message = "Student username contains invalid characters")
    private String studentUsername;

    @Min(value = 1, message = "Attempt number must be at least 1")
    @Max(value = 10, message = "Attempt number cannot exceed 10")
    private int attemptNo;

    @NotNull(message = "Started at timestamp cannot be null")
    @PastOrPresent(message = "Started at must be in the past or present")
    private Instant startedAt;

    @PastOrPresent(message = "Submitted at must be in the past or present")
    private Instant submittedAt;

    @NotNull(message = "Status cannot be null")
    private ExamAttemptStatus status;

    @DecimalMin(value = "0.0", message = "Score total cannot be less than 0.0")
    @DecimalMax(value = "100.0", message = "Score total cannot exceed 100.0")
    private Double scoreTotal;

}
