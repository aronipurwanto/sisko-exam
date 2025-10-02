package com.sisko.exam.master.question_option.model;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionOptionReq {
    @NotBlank(message = "Question ID is required")
    private String questionId;

    @NotBlank(message = "Label is required")
    @Size(min = 1, max = 5, message = "Label must be between 1 and 5 characters")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Label can only contain letters and numbers")
    private String label;

    @NotBlank(message = "Content is required")
    @Size(min = 1, max = 5000, message = "Content must be between 1 and 5000 characters")
    private String content;

    @Builder.Default
    private Boolean correct = false;

    @Min(value = 0, message = "Order index must be non-negative")
    @Max(value = 99, message = "Order index must not exceed 99")
    @Builder.Default
    private Integer orderIndex = 10;
}
