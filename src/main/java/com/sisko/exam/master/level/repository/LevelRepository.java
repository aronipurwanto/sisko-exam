package com.sisko.exam.master.level.repository;

import com.sisko.exam.master.level.model.LevelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LevelRepository extends JpaRepository<LevelEntity, String> {
    List<LevelEntity> findAllByDeletedAtIsNull();
    Optional<LevelEntity> findByIdAndDeletedAtIsNull(String id);
}
