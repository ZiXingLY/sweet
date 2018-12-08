package io.anshily.biz.service.impl;

import io.anshily.base.core.AbstractService;
import io.anshily.biz.dao.BizAdminMapper;
import io.anshily.biz.model.BizAdmin;
import io.anshily.biz.service.BizAdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by sxd on 2018/07/21.
 */
@Service
@Transactional
public class BizAdminServiceImpl extends AbstractService<BizAdmin> implements BizAdminService {

    @Resource
    private BizAdminMapper bizAdminMapper;

    @Override
    public List<BizAdmin> selectAdminByAcciuntAndPassword(BizAdmin bizAdmin) {
        return bizAdminMapper.selectAdminByAcciuntAndPassword(bizAdmin);
    }

    @Override
    public List<BizAdmin> selectAdmin(BizAdmin bizAdmin) {
        return bizAdminMapper.selectAdmin(bizAdmin);
    }
}
