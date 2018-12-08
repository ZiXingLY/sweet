package io.anshily.service.impl;

import io.anshily.dao.PromiseMapper;
import io.anshily.model.Promise;
import io.anshily.service.PromiseService;
import io.anshily.base.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by anshi on 2018/11/22.
 */
@Service
@Transactional
public class PromiseServiceImpl extends AbstractService<Promise> implements PromiseService {
    @Resource
    private PromiseMapper swPromiseMapper;

}
