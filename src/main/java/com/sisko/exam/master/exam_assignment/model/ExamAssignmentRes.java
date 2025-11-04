package com.sisko.exam.master.exam_assignment.model;

import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamAssignmentRes {

    private String id;
    private String examId;
    private String examName;
    private String groupLabel;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private Integer maxAttempts;
    private String accessCode;
    private String audienceCode;
}
