package io.anshily.admin.service;
import io.anshily.model.LevelScore;
import io.anshily.base.core.Service;


/**
 * Created by zaq on 2018/08/15.
 */
public interface LevelScoreService extends Service<LevelScore> {
    LevelScore findByLevel(int level);
}
