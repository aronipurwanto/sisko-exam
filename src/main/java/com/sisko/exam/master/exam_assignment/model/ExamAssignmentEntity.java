package com.sisko.exam.master.exam_assignment.model;

import com.sisko.exam.base.BaseAuditableSoftDelete;
import com.sisko.exam.master.exam.model.ExamEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;


import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "exam_assignments", uniqueConstraints = @UniqueConstraint(name = "uk_ea", columnNames = {"exam_id","group_label"}))
@SQLDelete(sql = "UPDATE exam_assignments SET deleted_at=NOW() WHERE id=?")
public class ExamAssignmentEntity extends BaseAuditableSoftDelete {

    @Id
    @Column
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id", nullable = false)
    private ExamEntity exam;

    @Column(name = "group_label", nullable = false)
    private String groupLabel; // placeholder for class identifier

    @Column(name = "start_at", nullable = false)
    private LocalDateTime startAt;

    @Column(name = "end_at", nullable = false)
    private LocalDateTime endAt;

    @Builder.Default
    @Column(name = "max_attempts", nullable = false)
    private int maxAttempts = 1;

    @Column(name = "access_code")
    private String accessCode;

    @Column(name = "audience_code")
    private String audienceCode;
}
