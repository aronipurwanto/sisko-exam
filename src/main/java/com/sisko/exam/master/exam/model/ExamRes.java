package com.sisko.exam.master.exam.model;

import com.sisko.exam.enums.ExamStatus;
import lombok.*;

import java.time.Instant;

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
    private Instant startAt;
    private Instant endAt;
}
