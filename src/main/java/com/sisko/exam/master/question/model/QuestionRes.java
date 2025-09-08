package com.sisko.exam.master.question.model;

import com.sisko.exam.enums.QuestionAnswerPolicy;
import com.sisko.exam.enums.QuestionType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionRes {

    private Long id;
    private QuestionType qtype;
    private QuestionAnswerPolicy answerPolicy;
    private String stem;
    private double pointsDefault;
}
