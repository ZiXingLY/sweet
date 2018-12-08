package io.anshily.admin.service.impl;

import io.anshily.admin.dao.RoleMapper;
import io.anshily.model.Role;
import io.anshily.admin.service.RoleService;
import io.anshily.base.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by hang on 2018/08/14.
 */
@Service
@Transactional
public class RoleServiceImpl extends AbstractService<Role> implements RoleService {
    @Resource
    private RoleMapper qyRoleMapper;

}
