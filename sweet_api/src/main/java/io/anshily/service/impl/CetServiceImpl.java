package io.anshily.service.impl;

import io.anshily.dao.CetMapper;
import io.anshily.model.Cet;
import io.anshily.service.CetService;
import io.anshily.base.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by anshi on 2018/11/23.
 */
@Service
@Transactional
public class CetServiceImpl extends AbstractService<Cet> implements CetService {
    @Resource
    private CetMapper swCetMapper;

}
