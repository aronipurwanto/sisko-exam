package com.sisko.exam.master.course.repository;

import com.sisko.exam.master.course.model.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, String> {
    List<CourseEntity> findAllByDeletedAtIsNull();
    Optional<CourseEntity> findByIdAndDeletedAtIsNull(String id);
}
