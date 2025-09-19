package com.sisko.exam.master.exam_attempt.service;

import com.sisko.exam.exception.NotFoundException;
import com.sisko.exam.master.exam_attempt.mapper.ExamAttemptMapper;
import com.sisko.exam.master.exam_attempt.model.ExamAttemptEntity;
import com.sisko.exam.master.exam_attempt.model.ExamAttemptReq;
import com.sisko.exam.master.exam_attempt.model.ExamAttemptRes;
import com.sisko.exam.master.exam_attempt.repository.ExamAttemptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExamAttemptServiceImpl implements ExamAttemptService{
    private final ExamAttemptRepository examAttemptRepository;
    private final ExamAttemptMapper examAttemptMapper;

    @Override
    public List<ExamAttemptRes> get() {
        return this.examAttemptMapper.toResponseList(this.examAttemptRepository.findAllByDeletedAtIsNull());
    }

    @Override
    public Optional<ExamAttemptRes> getById(String id) {
        ExamAttemptEntity result = this.getEntityById(id);
        return Optional.of(this.examAttemptMapper.toResponse(result));
    }

    @Override
    public Optional<ExamAttemptRes> save(ExamAttemptReq request) {
        ExamAttemptEntity result = this.examAttemptMapper.toEntity(request);
        this.examAttemptRepository.save(result);
        return Optional.of(this.examAttemptMapper.toResponse(result));
    }

    @Override
    public Optional<ExamAttemptRes> update(ExamAttemptReq request, String id) {
        ExamAttemptEntity entity = this.getEntityById(id);
        ExamAttemptEntity result = this.examAttemptMapper.toEntity(request, entity);
        this.examAttemptRepository.save(result);
        return Optional.of(this.examAttemptMapper.toResponse(result));
    }

    @Override
    public Optional<ExamAttemptRes> delete(String id) {
        ExamAttemptEntity result = this.getEntityById(id);
        this.examAttemptRepository.delete(result);
        return Optional.of(this.examAttemptMapper.toResponse(result));
    }

    private ExamAttemptEntity getEntityById(String id) {
        return this.examAttemptRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new NotFoundException("exam attempt with id: " + id + " not found"));
    }
}
