package com.sisko.exam.master.attempt_answer.model;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttemptAnswerRes {

    private String examAttemptId;
    private String examAttemptStudentUsername;
    private String questionId;
    private String questionStem;
    private String answerText;
    private String questionOptionId;

    private Double score;
    private String gradedBy;
    private Instant gradedAt;
    private String feedback;
}
