package io.anshily.admin.dao;

import io.anshily.base.core.Mapper;
import io.anshily.model.LevelScore;

public interface LevelScoreMapper extends Mapper<LevelScore> {
    LevelScore findByLevel(int level);
}