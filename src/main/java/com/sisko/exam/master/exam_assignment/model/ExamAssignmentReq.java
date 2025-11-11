package com.sisko.exam.master.exam_assignment.model;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamAssignmentReq {

    @NotBlank(message = "Exam Name is required")
    private String examId;

    @NotBlank(message = "Group label is required")
    @Size(min = 1, max = 100, message = "Group label must be between 1 and 100 characters")
    private String groupLabel;

    @NotNull(message = "Start time is required")
    @Future(message = "Start time must be in the future")
    private LocalDateTime startAt;

    @NotNull(message = "End time is required")
    @Future(message = "End time must be in the future")
    private LocalDateTime endAt;

    @NotNull(message = "Max attempts is required")
    @Min(value = 1, message = "Max attempts must be at least 1")
    @Max(value = 10, message = "Max attempts must not exceed 10")
    private Integer maxAttempts;

    @Size(min = 6, max = 20, message = "Access code must be between 6 and 20 characters")
    @Pattern(regexp = "^[A-Z0-9]+$", message = "Access code can only contain uppercase letters and numbers")
    private String accessCode;

    @Size(min = 3, max = 50, message = "Audience code must be between 3 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$", message = "Audience code can only contain letters, numbers, underscores, and hyphens")
    private String audienceCode;
}
