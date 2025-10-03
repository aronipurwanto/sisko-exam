package com.sisko.exam.master.question_option.service;

import com.sisko.exam.exception.NotFoundException;
import com.sisko.exam.master.question_option.mapper.QuestionOptionMapper;
import com.sisko.exam.master.question_option.model.QuestionOptionEntity;
import com.sisko.exam.master.question_option.model.QuestionOptionReq;
import com.sisko.exam.master.question_option.model.QuestionOptionRes;
import com.sisko.exam.master.question_option.repository.QuestionOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionOptionServiceImpl implements QuestionOptionService {
    private final QuestionOptionRepository questionOptionRepository;
    private final QuestionOptionMapper questionOptionMapper;

    @Override
    public List<QuestionOptionRes> get() {
        return this.questionOptionMapper.toResponseList(this.questionOptionRepository.findAllByDeletedAtIsNull());
    }

    @Override
    public Optional<QuestionOptionRes> getById(String id) {
        QuestionOptionEntity result = this.getEntityById(id);

        return Optional.of(this.questionOptionMapper.toResponse(result));
    }

    @Override
    public Optional<QuestionOptionRes> save(QuestionOptionReq request) {
        QuestionOptionEntity result = this.questionOptionMapper.toEntity(request);

        try {
            this.questionOptionRepository.save(result);
            return Optional.of(this.questionOptionMapper.toResponse(result));
        } catch (Exception ex) {
            throw new RuntimeException("save question option failed", ex);
        }
    }

    @Override
    public List<QuestionOptionRes> saveAll(List<QuestionOptionReq> requests) {
        List<QuestionOptionEntity> entities = this.questionOptionMapper.toEntityList(requests);

        try {
            this.questionOptionRepository.saveAll(entities);
            return this.questionOptionMapper.toResponseList(entities);
        } catch (Exception ex) {
            throw new RuntimeException("saveAll question option failed", ex);
        }
    }

    @Override
    public Optional<QuestionOptionRes> update(QuestionOptionReq request, String id) {
        QuestionOptionEntity entity = this.getEntityById(id);
        QuestionOptionEntity result = this.questionOptionMapper.toEntity(request, entity);

        try {
            this.questionOptionRepository.save(result);
            return Optional.of(this.questionOptionMapper.toResponse(result));
        } catch (Exception ex) {
            throw new RuntimeException("update question option failed", ex);
        }
    }

    @Override
    public Optional<QuestionOptionRes> delete(String id) {
        QuestionOptionEntity result = this.getEntityById(id);

        try {
            this.questionOptionRepository.delete(result);
            return Optional.of(this.questionOptionMapper.toResponse(result));
        } catch (Exception ex) {
            throw new RuntimeException("delete question option failed", ex);
        }
    }

    private QuestionOptionEntity getEntityById(String id) {
        return questionOptionRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new NotFoundException(String.format("question option with id: %s not found", id)));
    }
}
