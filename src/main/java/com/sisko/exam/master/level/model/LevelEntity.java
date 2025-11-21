package com.sisko.exam.master.level.model;

import com.sisko.exam.base.BaseAuditableSoftDelete;
import com.sisko.exam.master.exam.model.ExamEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "level")
@SQLDelete(sql = "UPDATE level SET deleted_at=NOW() WHERE id=?")
public class LevelEntity extends BaseAuditableSoftDelete {

    @Id
    @Column
    private String id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "level", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExamEntity> examEntities = new ArrayList<>();
}
