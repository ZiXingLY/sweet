package io.anshily.admin.service.impl;

import io.anshily.admin.dao.VipMapper;
import io.anshily.model.Vip;
import io.anshily.admin.service.VipService;
import io.anshily.base.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by anshi on 2018/08/22.
 */
@Service
@Transactional
public class VipServiceImpl extends AbstractService<Vip> implements VipService {
    @Resource
    private VipMapper qyVipMapper;

    @Override
    public List<Vip> findTheLastByUid(int uid) {
        return qyVipMapper.findTheLastByUid(uid);
    }
}
