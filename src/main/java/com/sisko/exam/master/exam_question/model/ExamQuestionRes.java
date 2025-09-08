package com.sisko.exam.master.exam_question.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamQuestionRes {
    private Long examId;
    private String examName;
    private Long questionId;
    private String questionStem;
    private Double points;
    private Integer orderIndex;
    private Boolean required = true;
}
