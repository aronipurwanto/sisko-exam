package com.sisko.exam.master.question_option.model;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionOptionReq {
    @NotBlank(message = "question stem is required")
    private String questionId;

    @NotBlank(message = "label is required")
    @Size(min = 1, max = 5, message = "label must be between 1 and 5 characters")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "label can only contain letters and numbers")
    private String label;

    @NotBlank(message = "content is required")
    @Size(min = 1, max = 5000, message = "content must be between 1 and 5000 characters")
    private String content;

    @NotNull(message = "correct is required")
    @Builder.Default
    private Boolean correct = false;

    @NotNull(message = "order index is required")
    @Min(value = 0, message = "order index must be non-negative")
    @Max(value = 99, message = "order index must not exceed 99")
    @Builder.Default
    private Integer orderIndex = 10;
}
