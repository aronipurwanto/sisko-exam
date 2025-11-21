package com.sisko.exam.master.course.model;

import com.sisko.exam.base.BaseAuditableSoftDelete;
import com.sisko.exam.master.exam.model.ExamEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

import java.util.List;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "course")
@SQLDelete(sql = "UPDATE course SET deleted_at=NOW() WHERE id=?")
public class CourseEntity extends BaseAuditableSoftDelete {

    @Id
    @Column
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ExamEntity> examEntities;
}
