package com.sisko.exam.master.exam_question.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamQuestionRes {
    private String examId;
    private String examName;
    private String questionId;
    private String questionStem;
    private Double points;
    private Integer orderIndex;
    private Boolean required = true;
}
