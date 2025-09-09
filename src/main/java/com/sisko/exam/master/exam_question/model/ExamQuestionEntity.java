package com.sisko.exam.master.exam_question.model;

import com.sisko.exam.master.exam.model.ExamEntity;
import com.sisko.exam.base.BaseAuditableSoftDelete;
import com.sisko.exam.master.question.model.QuestionEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;


@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@Table(name = "exam_questions", uniqueConstraints = @UniqueConstraint(name = "uk_eq", columnNames = {"exam_id","question_id"}))
@SQLDelete(sql = "UPDATE exam_questions SET deleted_at=NOW() WHERE id=?")
public class ExamQuestionEntity extends BaseAuditableSoftDelete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id", nullable = false)
    private ExamEntity exam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionEntity question;

    @Column(nullable = false)
    private double points;

    @Column(name = "order_index", nullable = false)
    private int orderIndex;

    @Builder.Default
    @Column(nullable = false)
    private boolean required = true;
}
