package com.sisko.exam.model.response;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionOptionRes {
    private Long questionId;
    private String questionStem;
    private String label;
    private String content;
    private Boolean correct = false;
    private Integer orderIndex;
}
