package com.sisko.exam.master.attempt_answer_option.service;

import com.sisko.exam.exception.NotFoundException;
import com.sisko.exam.master.attempt_answer_option.mapper.AttemptAnswerOptionMapper;
import com.sisko.exam.master.attempt_answer_option.model.AttemptAnswerOptionEntity;
import com.sisko.exam.master.attempt_answer_option.model.AttemptAnswerOptionReq;
import com.sisko.exam.master.attempt_answer_option.model.AttemptAnswerOptionRes;
import com.sisko.exam.master.attempt_answer_option.repository.AttemptAnswerOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttemptAnswerOptionServiceImpl implements AttemptAnswerOptionService {
    private final AttemptAnswerOptionRepository attemptAnswerOptionRepository;
    private final AttemptAnswerOptionMapper mapper;

    @Override
    public List<AttemptAnswerOptionRes> get() {
        return this.mapper.toResponseList(this.attemptAnswerOptionRepository.findAllByDeletedAtIsNull());
    }

    @Override
    public Optional<AttemptAnswerOptionRes> getById(String id) {
        AttemptAnswerOptionEntity result = this.getEntityById(id);
        return Optional.of(this.mapper.toResponse(result));
    }

    @Override
    public Optional<AttemptAnswerOptionRes> save(AttemptAnswerOptionReq request) {
        AttemptAnswerOptionEntity result = this.mapper.toEntity(request);

        try {
            this.attemptAnswerOptionRepository.save(result);
            return Optional.of(this.mapper.toResponse(result));
        } catch (Exception ex) {
            throw new RuntimeException("save failed", ex);
        }
    }

    @Override
    public Optional<AttemptAnswerOptionRes> update(AttemptAnswerOptionReq request, String id) {
        AttemptAnswerOptionEntity entity = this.getEntityById(id);
        AttemptAnswerOptionEntity result = this.mapper.toEntity(request, entity);

        try {
            this.attemptAnswerOptionRepository.save(result);
            return Optional.of(this.mapper.toResponse(result));
        } catch (Exception ex) {
            throw new RuntimeException("update failed", ex);
        }
    }

    @Override
    public Optional<AttemptAnswerOptionRes> delete(String id) {
        AttemptAnswerOptionEntity result = this.getEntityById(id);

        try {
            this.attemptAnswerOptionRepository.delete(result);
            return Optional.of(this.mapper.toResponse(result));
        } catch (Exception ex) {
            throw new RuntimeException("delete failed", ex);
        }
    }

    private AttemptAnswerOptionEntity getEntityById(String id) {
        return this.attemptAnswerOptionRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new NotFoundException(String.format("attempt-answer-option with id: %s not found", id)));
    }
}
