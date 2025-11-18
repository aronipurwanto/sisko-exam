package com.sisko.exam.master.attempt_answer_option.model;

import com.sisko.exam.master.attempt_answer.model.AttemptAnswerEntity;
import com.sisko.exam.base.BaseAuditableSoftDelete;
import com.sisko.exam.master.question_option.model.QuestionOptionEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "attempt_answer_options",
        uniqueConstraints = @UniqueConstraint(name = "uk_aao", columnNames = {"attempt_answer_id","option_id"}))
@SQLDelete(sql = "UPDATE attempt_answer_options SET deleted_at=NOW() WHERE id=?")
public class AttemptAnswerOptionEntity extends BaseAuditableSoftDelete {

    @Id
    @Column
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attempt_answer_id", nullable = false)
    private AttemptAnswerEntity attemptAnswer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id", nullable = false)
    private QuestionOptionEntity questionOption;

    @Column(name = "selected")
    private boolean selected;
}
