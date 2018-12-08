package io.anshily.biz.service.impl;

import io.anshily.base.core.AbstractService;
import io.anshily.biz.dao.BizPermissionsMapper;
import io.anshily.biz.model.BizPermissions;
import io.anshily.biz.service.BizPermissionsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by sxd on 2018/07/21.
 */
@Service
@Transactional
public class BizPermissionsServiceImpl extends AbstractService<BizPermissions> implements BizPermissionsService {
    @Resource
    private BizPermissionsMapper bizPermissionsMapper;

}
