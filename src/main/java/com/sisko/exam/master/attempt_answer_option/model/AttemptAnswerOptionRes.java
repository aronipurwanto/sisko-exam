package com.sisko.exam.master.attempt_answer_option.model;

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
