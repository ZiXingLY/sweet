package io.anshily.biz.service.impl;

import io.anshily.base.core.AbstractService;
import io.anshily.biz.dao.BizRolesMapper;
import io.anshily.biz.model.BizRoles;
import io.anshily.biz.service.BizRolesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by sxd on 2018/07/23.
 */
@Service
@Transactional
public class BizRolesServiceImpl extends AbstractService<BizRoles> implements BizRolesService {
    @Resource
    private BizRolesMapper bizRolesMapper;

}
