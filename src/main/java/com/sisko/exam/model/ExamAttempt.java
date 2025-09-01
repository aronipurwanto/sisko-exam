package com.sisko.exam.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;


import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@Table(name = "exam_attempts",
        uniqueConstraints = @UniqueConstraint(name = "uk_assign_student_attempt", columnNames = {"assignment_id","student_username","attempt_no"}))
@SQLDelete(sql = "UPDATE exam_attempts SET deleted_at=NOW() WHERE id=?")
public class ExamAttempt extends BaseAuditableSoftDelete {
    public enum Status { IN_PROGRESS, SUBMITTED, GRADED, CANCELLED }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "exam_id", nullable = false)
    private Long examId; // simplified

    @Column(name = "assignment_id", nullable = false)
    private Long assignmentId; // simplified

    @Column(name = "student_username", nullable = false)
    private String studentUsername; // from Security principal

    @Column(name = "attempt_no", nullable = false)
    private int attemptNo = 1;

    @Column(name = "started_at", nullable = false)
    private Instant startedAt = Instant.now();

    @Column(name = "submitted_at")
    private Instant submittedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.IN_PROGRESS;

    @Column(name = "score_total")
    private Double scoreTotal;

    @OneToMany(mappedBy = "attempt", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AttemptAnswer> answers = new ArrayList<>();
}