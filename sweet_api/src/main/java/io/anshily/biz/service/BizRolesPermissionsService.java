package io.anshily.biz.service;

import io.anshily.base.core.Service;
import io.anshily.biz.model.BizRolesPermissions;

import java.util.List;
import java.util.Map;


/**
 * Created by sxd on 2018/07/23.
 */
public interface BizRolesPermissionsService extends Service<BizRolesPermissions> {
    public List<Map<String, String>> selectPermissionsByRoleId(Integer role_id);

}
