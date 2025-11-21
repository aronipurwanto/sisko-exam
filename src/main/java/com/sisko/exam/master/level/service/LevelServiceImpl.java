package com.sisko.exam.master.level.service;

import com.sisko.exam.exception.NotFoundException;
import com.sisko.exam.master.level.mapper.LevelMapper;
import com.sisko.exam.master.level.model.LevelEntity;
import com.sisko.exam.master.level.model.LevelReq;
import com.sisko.exam.master.level.model.LevelRes;
import com.sisko.exam.master.level.repository.LevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LevelServiceImpl implements LevelService{
    private final LevelRepository levelRepository;
    private final LevelMapper levelMapper;

    @Override
    public List<LevelRes> getAll() {
        return this.levelMapper.toResponseList(this.levelRepository.findAllByDeletedAtIsNull());
    }

    @Override
    public Optional<LevelRes> getById(String id) {
        return Optional.of(this.levelMapper.toResponse(this.getEntityById(id)));
    }

    @Override
    public Optional<LevelRes> save(LevelReq request) {
        LevelEntity result = this.levelMapper.toEntity(request);

        try {
            this.levelRepository.save(result);
            return Optional.of(this.levelMapper.toResponse(result));
        } catch (Exception ex) {
            throw new RuntimeException("save failed", ex);
        }
    }

    @Override
    public Optional<LevelRes> update(LevelReq request, String id) {
        LevelEntity result = this.levelMapper.toEntity(this.getEntityById(id), request);

        try {
            this.levelRepository.save(result);
            return Optional.of(this.levelMapper.toResponse(result));
        } catch (Exception ex) {
            throw new RuntimeException("update failed", ex);
        }
    }

    @Override
    public Optional<LevelRes> delete(String id) {
        LevelEntity result = this.getEntityById(id);

        try {
            this.levelRepository.delete(result);
            return Optional.of(this.levelMapper.toResponse(result));
        } catch (Exception ex) {
            throw new RuntimeException("delete failed", ex);
        }
    }

    private LevelEntity getEntityById(String id) {
        return this.levelRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new NotFoundException(String.format("Level with id %s not found", id)));
    }
}
