package com.sisko.exam.master.exam.model;

import com.sisko.exam.enums.ExamStatus;
import com.sisko.exam.master.exam_assignment.model.ExamAssignmentRes;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamRes {

    private String id;
    private String name;
    private String instructions;
    private int durationMinutes = 60;
    private boolean randomizeQuestions = true;
    private boolean randomizeOptions = true;
    private ExamStatus status;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private List<ExamAssignmentRes> examAssignments;
}
