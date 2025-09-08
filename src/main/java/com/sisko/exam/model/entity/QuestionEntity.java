package com.sisko.exam.model.entity;

import com.sisko.exam.enums.QuestionAnswerPolicy;
import com.sisko.exam.enums.QuestionType;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private QuestionType qtype;

    @Enumerated(EnumType.STRING)
    @Column(name = "answer_policy", nullable = false, length = 12)
    private QuestionAnswerPolicy answerPolicy = QuestionAnswerPolicy.SINGLE;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String stem;

    @Column(name = "points_default", nullable = false)
    private double pointsDefault = 1.0;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("orderIndex ASC")
    private List<QuestionOptionEntity> questionOptions = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AttemptAnswerEntity>  attemptAnswers = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExamQuestionEntity> examQuestions = new ArrayList<>();
}