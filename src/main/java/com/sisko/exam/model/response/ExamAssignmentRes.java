package com.sisko.exam.model.response;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamAssignmentRes {

    private Long id;
    private Long examId;
    private String examName;
    private String groupLabel;
    private Instant startAt;
    private Instant endAt;
    private Integer maxAttempts;
    private String accessCode;
    private String audienceCode;
}
