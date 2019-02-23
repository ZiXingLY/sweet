package io.anshily.service.impl;

import io.anshily.dao.GoodsMapper;
import io.anshily.model.Goods;
import io.anshily.service.GoodsService;
import io.anshily.base.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by anshi on 2019/02/22.
 */
@Service
@Transactional
public class GoodsServiceImpl extends AbstractService<Goods> implements GoodsService {
    @Resource
    private GoodsMapper swGoodsMapper;

}
