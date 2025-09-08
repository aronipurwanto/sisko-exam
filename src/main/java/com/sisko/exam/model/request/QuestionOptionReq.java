package com.sisko.exam.model.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionOptionReq {
    @NotNull(message = "Question ID is required")
    @Positive(message = "Question ID must be a positive number")
    private Long questionId;

    @NotBlank(message = "Label is required")
    @Size(min = 1, max = 5, message = "Label must be between 1 and 5 characters")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Label can only contain letters and numbers")
    private String label;

    @NotBlank(message = "Content is required")
    @Size(min = 1, max = 5000, message = "Content must be between 1 and 5000 characters")
    private String content;

    @NotNull(message = "Correct flag is required")
    @Builder.Default
    private Boolean correct = false;

    @NotNull(message = "Order index is required")
    @Min(value = 0, message = "Order index must be non-negative")
    @Max(value = 99, message = "Order index must not exceed 99")
    private Integer orderIndex;
}
