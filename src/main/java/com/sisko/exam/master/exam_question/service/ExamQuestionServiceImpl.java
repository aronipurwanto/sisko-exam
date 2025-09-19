package com.sisko.exam.master.exam_question.service;

import com.sisko.exam.exception.NotFoundException;
import com.sisko.exam.master.exam_question.mapper.ExamQuestionMapper;
import com.sisko.exam.master.exam_question.model.ExamQuestionEntity;
import com.sisko.exam.master.exam_question.model.ExamQuestionReq;
import com.sisko.exam.master.exam_question.model.ExamQuestionRes;
import com.sisko.exam.master.exam_question.repository.ExamQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExamQuestionServiceImpl implements ExamQuestionService {
    private final ExamQuestionRepository examQuestionRepository;
    private final ExamQuestionMapper examQuestionMapper;

    @Override
    public List<ExamQuestionRes> get() {
        return this.examQuestionMapper.toResponseList(this.examQuestionRepository.findAllByDeletedAtIsNull());
    }

    @Override
    public Optional<ExamQuestionRes> getById(String id) {
        ExamQuestionEntity result = this.getEntityById(id);

        return Optional.of(this.examQuestionMapper.toResponse(result));
    }

    @Override
    public Optional<ExamQuestionRes> save(ExamQuestionReq request) {
        ExamQuestionEntity result = this.examQuestionMapper.toEntity(request);

        try {
            this.examQuestionRepository.save(result);
            return Optional.of(this.examQuestionMapper.toResponse(result));
        } catch (Exception ex) {
            throw new RuntimeException("save exam question failed", ex);
        }
    }

    @Override
    public Optional<ExamQuestionRes> update(ExamQuestionReq request, String id) {
        ExamQuestionEntity entity = this.getEntityById(id);
        ExamQuestionEntity result = this.examQuestionMapper.toEntity(request, entity);

        try {
            this.examQuestionRepository.save(result);
            return Optional.of(this.examQuestionMapper.toResponse(result));
        } catch (Exception ex) {
            throw new RuntimeException("update exam question failed", ex);
        }
    }

    @Override
    public Optional<ExamQuestionRes> delete(String id) {
        ExamQuestionEntity result = this.getEntityById(id);

        try {
            this.examQuestionRepository.delete(result);
            return Optional.of(this.examQuestionMapper.toResponse(result));
        } catch (Exception ex) {
            throw new RuntimeException("delete exam question failed", ex);
        }
    }

    private ExamQuestionEntity getEntityById(String id) {
        return examQuestionRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new NotFoundException(String.format("exam question with id: %s not found", id)));
    }
}
