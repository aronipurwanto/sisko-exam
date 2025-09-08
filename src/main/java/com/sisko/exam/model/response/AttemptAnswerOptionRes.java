package com.sisko.exam.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttemptAnswerOptionRes {

    private Long attemptAnswerId;
    private Double attemptAnswerScore;
    private Long questionOptionId;
    private String questionStem;
}
