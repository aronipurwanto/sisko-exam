package com.sisko.exam.model.entity;

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
@Table(name = "questions")
@SQLDelete(sql = "UPDATE questions SET deleted_at=NOW() WHERE id=?")
public class QuestionEntity extends BaseAuditableSoftDelete {
    public enum Type { ESSAY, MCQ }
    public enum AnswerPolicy { SINGLE, MULTI_ALL, MULTI_PARTIAL }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Type qtype;

    @Enumerated(EnumType.STRING)
    @Column(name = "answer_policy", nullable = false, length = 12)
    private AnswerPolicy answerPolicy = AnswerPolicy.SINGLE;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String stem;

    @Column(name = "points_default", nullable = false)
    private double pointsDefault = 1.0;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("orderIndex ASC")
    private List<QuestionOptionEntity> options = new ArrayList<>();
}