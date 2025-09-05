package com.sisko.exam.model.response;

import com.sisko.exam.enums.ExamAttemptStatus;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamAttemptRes {

    private Long id;
    private Long examId;
    private Long assignmentId;
    private String studentUsername;
    private int attemptNo;
    private Instant startedAt;
    private Instant submittedAt;
    private ExamAttemptStatus status;
    private Double scoreTotal;
}
