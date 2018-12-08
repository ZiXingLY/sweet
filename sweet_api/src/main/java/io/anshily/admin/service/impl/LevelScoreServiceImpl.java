package io.anshily.admin.service.impl;

import io.anshily.admin.dao.LevelScoreMapper;
import io.anshily.model.LevelScore;
import io.anshily.admin.service.LevelScoreService;
import io.anshily.base.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by zaq on 2018/08/15.
 */
@Service
@Transactional
public class LevelScoreServiceImpl extends AbstractService<LevelScore> implements LevelScoreService {
    @Resource
    private LevelScoreMapper qyLevelScoreMapper;

    @Override
    public LevelScore findByLevel(int level) {
        return qyLevelScoreMapper.findByLevel(level);
    }
}
