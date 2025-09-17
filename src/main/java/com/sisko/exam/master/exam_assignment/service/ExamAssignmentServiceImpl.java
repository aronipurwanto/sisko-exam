package com.sisko.exam.master.exam_assignment.service;

import com.sisko.exam.exception.NotFoundException;
import com.sisko.exam.master.exam_assignment.mapper.ExamAssignmentMapper;
import com.sisko.exam.master.exam_assignment.model.ExamAssignmentEntity;
import com.sisko.exam.master.exam_assignment.model.ExamAssignmentReq;
import com.sisko.exam.master.exam_assignment.model.ExamAssignmentRes;
import com.sisko.exam.master.exam_assignment.repository.ExamAssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExamAssignmentServiceImpl implements ExamAssignmentService {
    private final ExamAssignmentRepository examAssignmentRepository;
    private final ExamAssignmentMapper examAssignmentMapper;

    @Override
    public List<ExamAssignmentRes> get() {
        return this.examAssignmentMapper.toResponseList(this.examAssignmentRepository.findAllByDeletedAtIsNull());
    }

    @Override
    public Optional<ExamAssignmentRes> getById(String id) {
        ExamAssignmentEntity result = this.getEntityById(id);
        return Optional.of(this.examAssignmentMapper.toResponse(result));
    }

    @Override
    public Optional<ExamAssignmentRes> save(ExamAssignmentReq request) {
        ExamAssignmentEntity result = this.examAssignmentMapper.toEntity(request);

        try {
            this.examAssignmentRepository.save(result);
            return Optional.of(this.examAssignmentMapper.toResponse(result));
        } catch (Exception ex) {
            throw new RuntimeException("save failed", ex);
        }
    }

    @Override
    public Optional<ExamAssignmentRes> update(ExamAssignmentReq request, String id) {
        ExamAssignmentEntity entity = this.getEntityById(id);
        ExamAssignmentEntity result = this.examAssignmentMapper.toEntity(request, entity);

        try {
            this.examAssignmentRepository.save(result);
            return Optional.of(this.examAssignmentMapper.toResponse(result));
        } catch (Exception ex) {
            throw new RuntimeException("update failed", ex);
        }
    }

    @Override
    public Optional<ExamAssignmentRes> delete(String id) {
        ExamAssignmentEntity result = this.getEntityById(id);

        try {
            this.examAssignmentRepository.delete(result);
            return Optional.of(this.examAssignmentMapper.toResponse(result));
        } catch (Exception ex) {
            throw new RuntimeException("delete failed", ex);
        }
    }

    private ExamAssignmentEntity getEntityById(String id) {
        return this.examAssignmentRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new NotFoundException(String.format("exam assignment with id %s not found", id)));
    }
}
