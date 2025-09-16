package com.sisko.exam.master.question.service;

import com.sisko.exam.master.question.mapper.QuestionMapper;
import com.sisko.exam.master.question.model.QuestionEntity;
import com.sisko.exam.master.question.model.QuestionReq;
import com.sisko.exam.master.question.model.QuestionRes;
import com.sisko.exam.master.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    @Override
    public List<QuestionRes> get() {
        return this.questionMapper.toResponseList(this.questionRepository.findAll());
    }

    @Override
    public Optional<QuestionRes> getById(String id) {
        QuestionEntity result = this.questionMapper.getEntityById(id);

        return Optional.of(this.questionMapper.toResponse(result));
    }

    @Override
    public Optional<QuestionRes> save(QuestionReq request) {
        QuestionEntity result = this.questionMapper.toEntity(request);

        try {
            this.questionRepository.save(result);
            return Optional.of(this.questionMapper.toResponse(result));
        } catch (Exception ex) {
            throw new RuntimeException("save question failed", ex);
        }
    }

    @Override
    public Optional<QuestionRes> update(QuestionReq request, String id) {
        QuestionEntity entity = this.questionMapper.getEntityById(id);
        QuestionEntity result = this.questionMapper.toEntity(request, entity);

        try {
            this.questionRepository.save(result);
            return Optional.of(this.questionMapper.toResponse(result));
        } catch (Exception ex) {
            throw new RuntimeException("update question failed", ex);
        }
    }

    @Override
    public Optional<QuestionRes> delete(String id) {
        QuestionEntity result = this.questionMapper.getEntityById(id);

        try {
            this.questionRepository.delete(result);
            return Optional.of(this.questionMapper.toResponse(result));
        } catch (Exception ex) {
            throw new RuntimeException("delete question failed", ex);
        }
    }
}
