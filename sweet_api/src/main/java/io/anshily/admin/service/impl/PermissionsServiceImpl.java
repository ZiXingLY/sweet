package io.anshily.admin.service.impl;

import io.anshily.admin.dao.PermissionsMapper;
import io.anshily.model.Permissions;
import io.anshily.admin.service.PermissionsService;
import io.anshily.base.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by hang on 2018/08/14.
 */
@Service
@Transactional
public class PermissionsServiceImpl extends AbstractService<Permissions> implements PermissionsService {
    @Resource
    private PermissionsMapper qyPermissionsMapper;

}
