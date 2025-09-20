package com.sisko.exam.master.attempt_answer_option.model;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttemptAnswerOptionReq {

    @NotEmpty(message = "Attempt answer ID is required")
    private String attemptAnswerId;

    @NotEmpty(message = "Option ID is required")
    private String questionOptionId;
}
