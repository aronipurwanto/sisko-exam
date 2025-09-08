package com.sisko.exam.master.attempt_answer.model;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttemptAnswerRes {

    private Long examAttemptId;
    private String examAttemptStudentUsername;
    private Long questionId;
    private String questionStem;
    private String answerText;
    private Long selectedOptionId;
    private Double score;
    private String gradedBy;
    private Instant gradedAt;
    private String feedback;
}
