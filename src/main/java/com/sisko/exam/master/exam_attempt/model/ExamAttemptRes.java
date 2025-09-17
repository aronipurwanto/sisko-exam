package com.sisko.exam.master.exam_attempt.model;

import com.sisko.exam.enums.ExamAttemptStatus;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamAttemptRes {

    private String id;
    private String examId;
    private String examName;
    private String assignmentId;
    private String studentUsername;
    private int attemptNo;
    private Instant startedAt;
    private Instant submittedAt;
    private ExamAttemptStatus status;
    private Double scoreTotal;
}
