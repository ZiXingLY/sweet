package io.anshily.service.impl;

//import io.anshily.dao.EmotionsMapper;
import io.anshily.admin.dao.EmotionsMapper;
import io.anshily.model.Emotions;
import io.anshily.service.EmotionsService;
import io.anshily.base.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by anshi on 2019/02/12.
 */
@Service
@Transactional
public class EmotionsServiceImpl extends AbstractService<Emotions> implements EmotionsService {
    @Resource
    private EmotionsMapper swEmotionsMapper;

}
