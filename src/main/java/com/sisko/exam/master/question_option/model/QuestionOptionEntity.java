package com.sisko.exam.master.question_option.model;

import com.sisko.exam.master.attempt_answer.model.AttemptAnswerEntity;
import com.sisko.exam.master.attempt_answer_option.model.AttemptAnswerOptionEntity;
import com.sisko.exam.master.question.model.QuestionEntity;
import com.sisko.exam.base.BaseAuditableSoftDelete;
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
@Table(name = "question_options",
        uniqueConstraints = @UniqueConstraint(name = "uk_qo_label", columnNames = {"question_id","label"}))
@SQLDelete(sql = "UPDATE question_options SET deleted_at=NOW() WHERE id=?")
public class QuestionOptionEntity extends BaseAuditableSoftDelete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionEntity question;

    @Column(length = 5, nullable = false)
    private String label; // A, B, C, ...

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "is_correct", nullable = false)
    private boolean correct;

    @Column(name = "order_index", nullable = false)
    private int orderIndex;

    @OneToMany(mappedBy = "questionOption", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AttemptAnswerEntity> attemptAnswers = new ArrayList<>();

    @OneToMany(mappedBy = "questionOption", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AttemptAnswerOptionEntity> attemptAnswerOptions = new ArrayList<>();
}