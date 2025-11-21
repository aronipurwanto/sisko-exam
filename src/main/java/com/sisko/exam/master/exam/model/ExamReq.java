package com.sisko.exam.master.exam.model;

import com.sisko.exam.enums.ExamStatus;
import jakarta.validation.constraints.*;
import java.time.Instant;
import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamReq {

    @NotBlank(message = "name is required")
    @Size(max = 255, message = "name must not exceed 255 characters")
    private String name;

    @NotBlank(message = "course is required")
    private String courseId;

    @NotBlank(message = "level is required")
    private String levelId;

    @NotBlank(message = "instructions is required")
    @Size(max = 5000, message = "instructions must not exceed 5000 characters")
    private String instructions;

    @NotNull(message = "duration minutes is required")
    @Min(value = 1, message = "duration must be at least 1 minute")
    @Max(value = 1440, message = "duration must not exceed 1440 minutes (24 hours)")
    private Integer durationMinutes;

    @NotNull(message = "randomize questions is required")
    private Boolean randomizeQuestions;

    @NotNull(message = "randomize options is required")
    private Boolean randomizeOptions;

    @NotNull(message = "status is required")
    private ExamStatus status;

    @NotNull(message = "start at is required")
    @Future(message = "start time must be in the future")
    private LocalDateTime startAt;

//    @NotNull(message = "end at is required")
//    @Future(message = "end time must be in the future")
    private LocalDateTime endAt;
}
