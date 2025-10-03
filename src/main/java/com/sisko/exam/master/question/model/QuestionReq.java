package com.sisko.exam.master.question.model;

import com.sisko.exam.enums.QuestionAnswerPolicy;
import com.sisko.exam.enums.QuestionType;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionReq {

    @NotNull(message = "Question type is required")
    private QuestionType qtype;

    @Builder.Default
    private QuestionAnswerPolicy questionAnswerPolicy = QuestionAnswerPolicy.SINGLE;

    @NotBlank(message = "Question stem is required")
    @Size(min = 10, max = 5000, message = "Question stem must be between 10 and 5000 characters")
    private String stem;

    @NotNull(message = "Default points is required")
    @DecimalMin(value = "0.1", message = "Default points must be at least 0.1")
    @DecimalMax(value = "100.0", message = "Default points must not exceed 100.0")
    @Builder.Default
    private Double pointsDefault = 1.0;
}
