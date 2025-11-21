package com.sisko.exam.master.course.model;

import com.sisko.exam.base.BaseAuditableSoftDelete;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.SQLDelete;


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
}
