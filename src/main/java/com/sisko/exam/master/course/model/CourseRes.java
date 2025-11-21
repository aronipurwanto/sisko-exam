package com.sisko.exam.master.course.model;

import com.sisko.exam.master.exam.model.ExamRes;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseRes {
    private String id;
    private String name;
    private String code;
    private List<ExamRes> exams;
}
