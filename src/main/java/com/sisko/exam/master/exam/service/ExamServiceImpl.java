package com.sisko.exam.master.exam.service;

import com.sisko.exam.exception.NotFoundException;
import com.sisko.exam.master.exam.mapper.ExamMapper;
import com.sisko.exam.master.exam.model.ExamEntity;
import com.sisko.exam.master.exam.model.ExamReq;
import com.sisko.exam.master.exam.model.ExamRes;
import com.sisko.exam.master.exam.repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {
    private final ExamRepository examRepository;
    private final ExamMapper examMapper;

    @Override
    public List<ExamRes> get() {
        return this.examMapper.toResponseList(this.examRepository.findAllByDeletedAtIsNull());
    }

    @Override
    public List<ExamRes> getByCourseId(String courseId) {
        return this.examMapper.toResponseList(this.examRepository.findAllByCourse_IdAndDeletedAtIsNull(courseId));
    }

    @Override
    public List<ExamRes> getByLevelId(String levelId) {
        return this.examMapper.toResponseList(this.examRepository.findAllByLevel_IdAndDeletedAtIsNull(levelId));
    }

    @Override
    public Optional<ExamRes> getById(String id) {
        ExamEntity result = this.getEntityById(id);
        return Optional.of(this.examMapper.toResponse(result));
    }

    @Override
    public Optional<ExamRes> save(ExamReq request) {
        ExamEntity result = this.examMapper.toEntity(request);

        try {
            this.examRepository.save(result);
            return Optional.of(this.examMapper.toResponse(result));
        } catch (Exception ex) {
            throw new RuntimeException("save failed", ex);
        }
    }

    @Override
    public Optional<ExamRes> update(ExamReq request, String id) {
        ExamEntity entity = this.getEntityById(id);
        ExamEntity result = this.examMapper.toEntity(entity, request);

        try {
            this.examRepository.save(result);
            return Optional.of(this.examMapper.toResponse(result));
        } catch (Exception ex) {
            throw new RuntimeException("update failed", ex);
        }
    }

    @Override
    public Optional<ExamRes> delete(String id) {
        ExamEntity result = this.getEntityById(id);

        try {
            this.examRepository.delete(result);
            return Optional.of(this.examMapper.toResponse(result));
        } catch (Exception ex) {
            throw new RuntimeException("delete failed", ex);
        }
    }

    private ExamEntity getEntityById(String id) {
        return this.examRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new NotFoundException(String.format("exam with id: %s not found", id)));
    }
}
