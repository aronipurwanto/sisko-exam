package com.sisko.exam.master.question.model;

import com.sisko.exam.enums.QuestionAnswerPolicy;
import com.sisko.exam.enums.QuestionType;
import com.sisko.exam.master.attempt_answer.model.AttemptAnswerEntity;
import com.sisko.exam.master.exam_question.model.ExamQuestionEntity;
import com.sisko.exam.base.BaseAuditableSoftDelete;
import com.sisko.exam.master.question_option.model.QuestionOptionEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "questions")
@SQLDelete(sql = "UPDATE questions SET deleted_at=NOW() WHERE id=?")
public class QuestionEntity extends BaseAuditableSoftDelete {

    @Id
    @Column
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private QuestionType qtype;

    @Enumerated(EnumType.STRING)
    @Column(name = "answer_policy", length = 12)
    private QuestionAnswerPolicy questionAnswerPolicy;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String stem;

    @Column(name = "points_default", nullable = false)
    private double pointsDefault;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("orderIndex ASC")
    private List<QuestionOptionEntity> questionOptions = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AttemptAnswerEntity>  attemptAnswers = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExamQuestionEntity> examQuestions = new ArrayList<>();
}