package io.anshily.admin.service.impl;

import io.anshily.admin.dao.UserRoleMapper;
import io.anshily.model.UserRole;
import io.anshily.admin.service.UserRoleService;
import io.anshily.base.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by hang on 2018/08/14.
 */
@Service
@Transactional
public class UserRoleServiceImpl extends AbstractService<UserRole> implements UserRoleService {
    @Resource
    private UserRoleMapper qyUserRoleMapper;

}
