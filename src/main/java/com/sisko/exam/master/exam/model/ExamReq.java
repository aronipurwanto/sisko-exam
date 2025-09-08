package com.sisko.exam.master.exam.model;

import com.sisko.exam.enums.ExamStatus;
import jakarta.validation.constraints.*;
import java.time.Instant;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamReq {

    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name must not exceed 255 characters")
    private String name;

    @Size(max = 5000, message = "Instructions must not exceed 5000 characters")
    private String instructions;

    @NotNull(message = "Duration minutes is required")
    @Min(value = 1, message = "Duration must be at least 1 minute")
    @Max(value = 1440, message = "Duration must not exceed 1440 minutes (24 hours)")
    private Integer durationMinutes;

    private Boolean randomizeQuestions;

    private Boolean randomizeOptions;

    @NotNull(message = "Status is required")
    private ExamStatus status;

    @Future(message = "Start time must be in the future")
    private Instant startAt;

    @Future(message = "End time must be in the future")
    private Instant endAt;
}
