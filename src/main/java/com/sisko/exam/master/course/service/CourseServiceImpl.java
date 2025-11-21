package com.sisko.exam.master.course.service;

import com.sisko.exam.exception.NotFoundException;
import com.sisko.exam.master.course.mapper.CourseMapper;
import com.sisko.exam.master.course.model.CourseEntity;
import com.sisko.exam.master.course.model.CourseReq;
import com.sisko.exam.master.course.model.CourseRes;
import com.sisko.exam.master.course.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Override
    public List<CourseRes> getAll() {
        return this.courseMapper.toResponseList(this.courseRepository.findAllByDeletedAtIsNull());
    }

    @Override
    public Optional<CourseRes> getById(String id) {
        return Optional.of(this.courseMapper.toResponse(this.getEntityById(id)));
    }

    @Override
    public Optional<CourseRes> save(CourseReq request) {
        CourseEntity result = this.courseMapper.toEntity(request);

        try {
            this.courseRepository.save(result);
            return Optional.of(this.courseMapper.toResponse(result));
        } catch (Exception ex) {
            throw new RuntimeException("save failed", ex);
        }
    }

    @Override
    public Optional<CourseRes> update(CourseReq request, String id) {
        CourseEntity result = this.courseMapper.toEntity(this.getEntityById(id), request);

        try {
            this.courseRepository.save(result);
            return Optional.of(this.courseMapper.toResponse(result));
        } catch (Exception ex) {
            throw new RuntimeException("update failed", ex);
        }
    }

    @Override
    public Optional<CourseRes> delete(String id) {
        CourseEntity result = this.getEntityById(id);

        try {
            this.courseRepository.delete(result);
            return Optional.of(this.courseMapper.toResponse(result));
        } catch (Exception ex) {
            throw new RuntimeException("delete failed", ex);
        }
    }

    private CourseEntity getEntityById(String id) {
        return this.courseRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new NotFoundException(String.format("course with id %s not found", id)));
    }
}
