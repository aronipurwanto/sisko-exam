package com.sisko.exam.master.attempt_answer.service;

import com.sisko.exam.exception.NotFoundException;
import com.sisko.exam.master.attempt_answer.mapper.AttemptAnswerMapper;
import com.sisko.exam.master.attempt_answer.model.AttemptAnswerEntity;
import com.sisko.exam.master.attempt_answer.model.AttemptAnswerReq;
import com.sisko.exam.master.attempt_answer.model.AttemptAnswerRes;
import com.sisko.exam.master.attempt_answer.repository.AttemptAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttemptAnswerServiceImpl implements AttemptAnswerService {
    private final AttemptAnswerRepository attemptAnswerRepository;
    private final AttemptAnswerMapper attemptAnswerMapper;

    @Override
    public List<AttemptAnswerRes> get() {
        return this.attemptAnswerMapper.toResponseList(this.attemptAnswerRepository.findAllByDeletedAtIsNull());
    }

    @Override
    public Optional<AttemptAnswerRes> getById(String id) {
        AttemptAnswerEntity result = this.getEntityById(id);

        return Optional.of(this.attemptAnswerMapper.toResponse(result));
    }

    @Override
    public Optional<AttemptAnswerRes> save(AttemptAnswerReq request) {
        AttemptAnswerEntity result = this.attemptAnswerMapper.toEntity(request);

        try {
            this.attemptAnswerRepository.save(result);
            return Optional.of(this.attemptAnswerMapper.toResponse(result));
        } catch (Exception ex) {
            throw new RuntimeException("save attempt answer failed", ex);
        }
    }

    @Override
    public Optional<AttemptAnswerRes> update(AttemptAnswerReq request, String id) {
        AttemptAnswerEntity entity = this.getEntityById(id);
        AttemptAnswerEntity result = this.attemptAnswerMapper.toEntity(request, entity);

        try {
            this.attemptAnswerRepository.save(result);
            return Optional.of(this.attemptAnswerMapper.toResponse(result));
        } catch (Exception ex) {
            throw new RuntimeException("update attempt answer failed", ex);
        }
    }

    @Override
    public Optional<AttemptAnswerRes> delete(String id) {
        AttemptAnswerEntity result = this.getEntityById(id);

        try {
            this.attemptAnswerRepository.delete(result);
            return Optional.of(this.attemptAnswerMapper.toResponse(result));
        } catch (Exception ex) {
            throw new RuntimeException("delete attempt answer failed", ex);
        }
    }

    private AttemptAnswerEntity getEntityById(String id) {
        return attemptAnswerRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new NotFoundException(String.format("attempt answer with id: %s not found", id)));
    }
}
