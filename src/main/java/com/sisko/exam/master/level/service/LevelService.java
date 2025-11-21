package com.sisko.exam.master.level.service;

import com.sisko.exam.master.level.model.LevelReq;
import com.sisko.exam.master.level.model.LevelRes;

import java.util.List;
import java.util.Optional;

public interface LevelService {
    List<LevelRes> getAll();
    Optional<LevelRes> getById(String id);
    Optional<LevelRes> save(LevelReq request);
    Optional<LevelRes> update(LevelReq request, String id);
    Optional<LevelRes> delete(String id);
}
