package io.anshily.admin.service.impl;

import io.anshily.admin.dao.EmotionMapper;
import io.anshily.model.Emotion;
import io.anshily.admin.service.EmotionService;
import io.anshily.base.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by anshi on 2018/09/05.
 */
@Service
@Transactional
public class EmotionServiceImpl extends AbstractService<Emotion> implements EmotionService {
    @Resource
    private EmotionMapper qqEmotionMapper;

}
