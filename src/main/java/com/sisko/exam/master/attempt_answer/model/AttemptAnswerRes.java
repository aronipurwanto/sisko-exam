package com.sisko.exam.master.attempt_answer.model;

import com.sisko.exam.master.attempt_answer_option.model.AttemptAnswerOptionRes;
import lombok.*;

import java.time.Instant;
import java.util.List;

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
    private String questionOptionContent;
    private Double score;
    private String gradedBy;
    private Instant gradedAt;
    private String feedback;
    private List<AttemptAnswerOptionRes> attemptAnswerOptions;
}
