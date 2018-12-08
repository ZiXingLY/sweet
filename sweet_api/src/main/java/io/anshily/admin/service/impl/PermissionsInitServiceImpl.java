package io.anshily.admin.service.impl;

import io.anshily.admin.dao.PermissionsInitMapper;
import io.anshily.model.PermissionsInit;
import io.anshily.admin.service.PermissionsInitService;
import io.anshily.base.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by hang on 2018/08/14.
 */
@Service
@Transactional
public class PermissionsInitServiceImpl extends AbstractService<PermissionsInit> implements PermissionsInitService {
    @Resource
    private PermissionsInitMapper qyPermissionsInitMapper;

}
