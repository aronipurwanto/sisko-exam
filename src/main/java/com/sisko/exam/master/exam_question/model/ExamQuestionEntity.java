package com.sisko.exam.master.exam_question.model;

import com.sisko.exam.master.attempt_answer.model.AttemptAnswerEntity;
import com.sisko.exam.master.exam.model.ExamEntity;
import com.sisko.exam.base.BaseAuditableSoftDelete;
import com.sisko.exam.master.question.model.QuestionEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

import java.util.ArrayList;
import java.util.List;


@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@Table(name = "exam_questions", uniqueConstraints = @UniqueConstraint(name = "uk_eq", columnNames = {"exam_id","question_id"}))
@SQLDelete(sql = "UPDATE exam_questions SET deleted_at=NOW() WHERE id=?")
public class ExamQuestionEntity extends BaseAuditableSoftDelete {

    @Id
    @Column
    private String id;

    @Column(nullable = false)
    private double points;

    @Column(name = "order_index", nullable = false)
    private int orderIndex;

    @Builder.Default
    @Column(nullable = false)
    private boolean required = true;

    @OneToMany(mappedBy = "examQuestion",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AttemptAnswerEntity>  attemptAnswers = new ArrayList<>();
}
