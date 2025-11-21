package com.sisko.exam.master.course.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseRes {
    private String id;
    private String name;
    private String code;
}
