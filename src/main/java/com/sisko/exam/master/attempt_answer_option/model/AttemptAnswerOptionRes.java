package com.sisko.exam.master.attempt_answer_option.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttemptAnswerOptionRes {
    private String attemptAnswerId;
    private Double attemptAnswerScore;
    private String questionOptionId;
    private String questionContent;
}
