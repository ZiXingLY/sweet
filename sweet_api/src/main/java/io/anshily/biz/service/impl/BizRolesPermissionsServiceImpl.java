package io.anshily.biz.service.impl;

import io.anshily.base.core.AbstractService;
import io.anshily.biz.dao.BizRolesPermissionsMapper;
import io.anshily.biz.model.BizRolesPermissions;
import io.anshily.biz.service.BizRolesPermissionsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by sxd on 2018/07/23.
 */
@Service
@Transactional
public class BizRolesPermissionsServiceImpl extends AbstractService<BizRolesPermissions> implements BizRolesPermissionsService {
    @Resource
    private BizRolesPermissionsMapper bizRolesPermissionsMapper;

    @Override
    public List<Map<String, String>> selectPermissionsByRoleId(Integer role_id) {
        return bizRolesPermissionsMapper.selectPermissionsByRoleId(role_id);
    }
}
