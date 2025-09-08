package com.sisko.exam.model.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttemptAnswerOptionReq {

    @NotNull(message = "Attempt answer ID is required")
    @Positive(message = "Attempt answer ID must be a positive number")
    private Long attemptAnswerId;

    @NotNull(message = "Option ID is required")
    @Positive(message = "Option ID must be a positive number")
    private Long questionOptionId;
}
