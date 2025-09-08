package com.sisko.exam.model.response;

import com.sisko.exam.enums.ExamStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamRes {

    private Long id;
    private String name;
    private String instructions;
    private int durationMinutes = 60;
    private boolean randomizeQuestions = true;
    private boolean randomizeOptions = true;
    private ExamStatus status = ExamStatus.DRAFT;
    private Instant startAt;
    private Instant endAt;
}
