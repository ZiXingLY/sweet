package io.anshily.service.impl;

import io.anshily.dao.BannerMapper;
import io.anshily.model.Banner;
import io.anshily.service.BannerService;
import io.anshily.base.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by anshi on 2019/02/22.
 */
@Service
@Transactional
public class BannerServiceImpl extends AbstractService<Banner> implements BannerService {
    @Resource
    private BannerMapper swBannerMapper;

}
