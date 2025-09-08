package com.sisko.exam.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "attempt_answers",
        uniqueConstraints = @UniqueConstraint(name = "uk_attempt_question", columnNames = {"attempt_id","question_id"}))
@SQLDelete(sql = "UPDATE attempt_answers SET deleted_at=NOW() WHERE id=?")
public class AttemptAnswerEntity extends BaseAuditableSoftDelete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attempt_id", nullable = false)
    private ExamAttemptEntity attempt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionEntity question;

    @Column(name = "answer_text", columnDefinition = "MEDIUMTEXT")
    private String answerText; // for ESSAY

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "selected_option_id")
    private QuestionOptionEntity selectedOption; // for SINGLE MCQ

    @Column(name = "score")
    private Double score;

    @Column(name = "graded_by")
    private String gradedBy;

    @Column(name = "graded_at")
    private Instant gradedAt;

    @Column(name = "feedback")
    private String feedback;

    @OneToMany(mappedBy = "attemptAnswer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AttemptAnswerOptionEntity> selectedOptions = new ArrayList<>(); // for MULTI
}