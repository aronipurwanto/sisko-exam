package com.sisko.exam.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;


import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@Table(name = "exams")
@SQLDelete(sql = "UPDATE exams SET deleted_at=NOW() WHERE id=?")
public class Exam extends BaseAuditableSoftDelete {
    public enum Status { DRAFT, PUBLISHED, ARCHIVED }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String instructions;

    @Column(name = "duration_minutes", nullable = false)
    private int durationMinutes = 60;

    @Column(name = "randomize_questions", nullable = false)
    private boolean randomizeQuestions = true;

    @Column(name = "randomize_options", nullable = false)
    private boolean randomizeOptions = true;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.DRAFT;

    @Column(name = "start_at")
    private Instant startAt;

    @Column(name = "end_at")
    private Instant endAt;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("orderIndex ASC")
    private List<ExamQuestion> questions = new ArrayList<>();
}