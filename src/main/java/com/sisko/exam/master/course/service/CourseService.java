package com.sisko.exam.master.course.service;


import com.sisko.exam.master.course.model.CourseEntity;
import com.sisko.exam.master.course.model.CourseReq;
import com.sisko.exam.master.course.model.CourseRes;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<CourseRes> getAll();
    Optional<CourseRes> getById(String id);
    Optional<CourseRes> save(CourseReq request);
    Optional<CourseRes> update(CourseReq request, String id);
    Optional<CourseRes> delete(String id);
}
