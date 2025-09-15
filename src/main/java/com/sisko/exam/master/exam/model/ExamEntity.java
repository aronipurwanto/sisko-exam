package com.sisko.exam.master.exam.model;

import com.sisko.exam.enums.ExamStatus;
import com.sisko.exam.base.BaseAuditableSoftDelete;
import com.sisko.exam.master.exam_assignment.model.ExamAssignmentEntity;
import com.sisko.exam.master.exam_attempt.model.ExamAttemptEntity;
import com.sisko.exam.master.exam_question.model.ExamQuestionEntity;
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
@Table(name = "exams")
@SQLDelete(sql = "UPDATE exams SET deleted_at=NOW() WHERE id=?")
public class ExamEntity extends BaseAuditableSoftDelete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String instructions;

    @Builder.Default
    @Column(name = "duration_minutes", nullable = false)
    private int durationMinutes = 60;

    @Builder.Default
    @Column(name = "randomize_questions", nullable = false)
    private boolean randomizeQuestions = true;

    @Builder.Default
    @Column(name = "randomize_options", nullable = false)
    private boolean randomizeOptions = true;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExamStatus status = ExamStatus.DRAFT;

    @Column(name = "start_at")
    private Instant startAt;

    @Column(name = "end_at")
    private Instant endAt;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("orderIndex ASC")
    private List<ExamQuestionEntity> examQuestions = new ArrayList<>();

    @OneToMany(mappedBy = "exam", cascade =  CascadeType.ALL, orphanRemoval = true)
    private List<ExamAssignmentEntity> examAssignments = new ArrayList<>();

    @OneToMany(mappedBy = "exam", cascade =  CascadeType.ALL, orphanRemoval = true)
    private List<ExamAttemptEntity> examAttempts = new ArrayList<>();
}