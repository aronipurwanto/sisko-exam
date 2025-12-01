package com.sisko.exam.master.question.model;

import com.sisko.exam.enums.QuestionAnswerPolicy;
import com.sisko.exam.enums.QuestionType;
import com.sisko.exam.master.exam.model.ExamRes;
import com.sisko.exam.master.exam_question.model.ExamQuestionRes;
import com.sisko.exam.master.question_option.model.QuestionOptionRes;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionRes {
    private String id;
    private String examId;
    private String examName;
    private QuestionType qtype;
    private QuestionAnswerPolicy questionAnswerPolicy;
    private String stem;
    private double pointsDefault;
    private List<QuestionOptionRes> questionOptions = new ArrayList<>();
}
