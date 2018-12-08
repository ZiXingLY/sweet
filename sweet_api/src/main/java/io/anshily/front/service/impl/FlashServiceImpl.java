package io.anshily.front.service.impl;

import io.anshily.front.dao.FlashMapper;
import io.anshily.model.Flash;
import io.anshily.front.service.FlashService;
import io.anshily.base.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by zaq on 2018/08/14.
 */
@Service
@Transactional
public class FlashServiceImpl extends AbstractService<Flash> implements FlashService {
    @Resource
    private FlashMapper qyFlashMapper;

    @Override
    public List<Flash> findList(Integer start) {
        return qyFlashMapper.findList(start);
    }

    @Override
    public List<Flash> findByTitle(String title) {
        return qyFlashMapper.findByTitle(title);
    }

    @Override
    public int addReadNumber() {
        return qyFlashMapper.addReadNumber();
    }
}
