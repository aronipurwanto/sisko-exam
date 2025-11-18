package com.sisko.exam.master.exam_attempt.model;

import com.sisko.exam.enums.ExamAttemptStatus;
import com.sisko.exam.master.attempt_answer.model.AttemptAnswerRes;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamAttemptRes {

    private String id;
    private String examId;
    private String examName;
    private String studentUsername;
    private int attemptNo;
    private LocalDateTime startedAt;
    private LocalDateTime submittedAt;
    private ExamAttemptStatus status;
    private Double scoreTotal;
    private List<AttemptAnswerRes> attemptAnswers;
}
