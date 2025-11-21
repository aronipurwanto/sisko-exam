package com.sisko.exam.master.level.model;

import com.sisko.exam.master.exam.model.ExamRes;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LevelRes {
    private String id;
    private String code;
    private String name;
    private List<ExamRes> exams;
}
