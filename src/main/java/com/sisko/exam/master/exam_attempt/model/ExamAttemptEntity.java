package com.sisko.exam.master.exam_attempt.model;

import com.sisko.exam.enums.ExamAttemptStatus;
import com.sisko.exam.master.attempt_answer.model.AttemptAnswerEntity;
import com.sisko.exam.base.BaseAuditableSoftDelete;
import com.sisko.exam.master.exam.model.ExamEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;


import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "exam_attempts",
        uniqueConstraints = @UniqueConstraint(name = "uk_assign_student_attempt", columnNames = {"assignment_id","student_username","attempt_no"}))
@SQLDelete(sql = "UPDATE exam_attempts SET deleted_at=NOW() WHERE id=?")
public class ExamAttemptEntity extends BaseAuditableSoftDelete {

    @Id
    @Column
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id")
    private ExamEntity exam;

    @Column(name = "student_username")
    private String studentUsername; // from Security principal

    @Builder.Default
    @Column(name = "attempt_no", nullable = false)
    private int attemptNo = 1;

    @Builder.Default
    @Column(name = "started_at", nullable = false)
    private LocalDateTime startedAt = LocalDateTime.now();

    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExamAttemptStatus status = ExamAttemptStatus.IN_PROGRESS;

    @Column(name = "score_total")
    private Double scoreTotal;

    @OneToMany(mappedBy = "examAttempt", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AttemptAnswerEntity> attemptAnswers = new ArrayList<>();
}