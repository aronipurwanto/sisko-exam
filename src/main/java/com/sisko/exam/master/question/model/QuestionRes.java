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

    private String id;
    private QuestionType qtype;
    private QuestionAnswerPolicy questionAnswerPolicy;
    private String stem;
    private double pointsDefault;
}
