package io.anshily.biz.dao;

import io.anshily.base.core.Mapper;
import io.anshily.biz.model.BizRolesPermissions;

import java.util.List;
import java.util.Map;

public interface BizRolesPermissionsMapper extends Mapper<BizRolesPermissions> {
    public List<Map<String, String>> selectPermissionsByRoleId(Integer role_id);
}