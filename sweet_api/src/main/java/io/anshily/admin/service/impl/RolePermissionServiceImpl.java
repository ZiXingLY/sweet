package io.anshily.admin.service.impl;

import io.anshily.admin.dao.RolePermissionMapper;
import io.anshily.model.RolePermission;
import io.anshily.admin.service.RolePermissionService;
import io.anshily.base.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by hang on 2018/08/14.
 */
@Service
@Transactional
public class RolePermissionServiceImpl extends AbstractService<RolePermission> implements RolePermissionService {
    @Resource
    private RolePermissionMapper qyRolePermissionMapper;

}
