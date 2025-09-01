package com.sisko.exam.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;


import java.time.Instant;


@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@Table(name = "exam_assignments", uniqueConstraints = @UniqueConstraint(name = "uk_ea", columnNames = {"exam_id","group_label"}))
@SQLDelete(sql = "UPDATE exam_assignments SET deleted_at=NOW() WHERE id=?")
public class ExamAssignment extends BaseAuditableSoftDelete {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id", nullable = false)
    private Exam exam;


    @Column(name = "group_label", nullable = false)
    private String groupLabel; // placeholder for class identifier


    @Column(name = "start_at", nullable = false)
    private Instant startAt;


    @Column(name = "end_at", nullable = false)
    private Instant endAt;


    @Column(name = "max_attempts", nullable = false)
    private int maxAttempts = 1;


    @Column(name = "access_code")
    private String accessCode;
}
