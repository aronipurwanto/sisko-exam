package com.sisko.exam.master.exam_question.model;

import com.sisko.exam.master.attempt_answer.model.AttemptAnswerRes;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    private List<AttemptAnswerRes> attemptAnswers = new ArrayList<>();
}
