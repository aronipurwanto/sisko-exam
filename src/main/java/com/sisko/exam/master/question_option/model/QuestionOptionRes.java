package com.sisko.exam.master.question_option.model;

import com.sisko.exam.master.attempt_answer_option.model.AttemptAnswerOptionRes;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionOptionRes {
    private String id;
    private String questionId;
    private String questionStem;
    private String label;
    private String content;
    private boolean correct = false;
    private Integer orderIndex;
    private List<AttemptAnswerOptionRes> attemptAnswerOptions = new ArrayList<>();
}
