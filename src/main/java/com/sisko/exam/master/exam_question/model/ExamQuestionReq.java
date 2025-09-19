package com.sisko.exam.master.exam_question.model;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamQuestionReq {

    @NotBlank(message = "Exam ID is required")
    private String examId;

    @NotBlank(message = "Question ID is required")
    private String questionId;

    @NotNull(message = "Points is required")
    @DecimalMin(value = "0.1", message = "Points must be at least 0.1")
    @DecimalMax(value = "100.0", message = "Points must not exceed 100.0")
    private Double points;

    @NotNull(message = "Order index is required")
    @Min(value = 0, message = "Order index must be non-negative")
    @Max(value = 999, message = "Order index must not exceed 999")
    private Integer orderIndex;

    @NotNull(message = "Required flag is required")
    @Builder.Default
    private Boolean required = true;
}
