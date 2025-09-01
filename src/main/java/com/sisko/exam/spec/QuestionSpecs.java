package com.sisko.exam.spec;

import com.sisko.exam.model.Question;
import org.springframework.data.jpa.domain.Specification;


public class QuestionSpecs {
    public static Specification<Question> notDeleted() {
        return (root, q, cb) -> cb.isNull(root.get("deletedAt"));
    }
    public static Specification<Question> qtypeEquals(Question.Type type) {
        return type == null ? null : (root, q, cb) -> cb.equal(root.get("qtype"), type);
    }
    public static Specification<Question> policyEquals(Question.AnswerPolicy p) {
        return p == null ? null : (root, q, cb) -> cb.equal(root.get("answerPolicy"), p);
    }
    public static Specification<Question> stemContains(String keyword) {
        return (keyword == null || keyword.isBlank()) ? null : (root, q, cb) -> cb.like(cb.lower(root.get("stem")), "%" + keyword.toLowerCase() + "%");
    }
}