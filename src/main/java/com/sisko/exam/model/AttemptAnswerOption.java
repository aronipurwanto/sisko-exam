package com.sisko.exam.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;


@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@Table(name = "attempt_answer_options",
        uniqueConstraints = @UniqueConstraint(name = "uk_aao", columnNames = {"attempt_answer_id","option_id"}))
@SQLDelete(sql = "UPDATE attempt_answer_options SET deleted_at=NOW() WHERE id=?")
public class AttemptAnswerOption extends BaseAuditableSoftDelete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attempt_answer_id", nullable = false)
    private AttemptAnswer attemptAnswer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id", nullable = false)
    private QuestionOption option;
}
