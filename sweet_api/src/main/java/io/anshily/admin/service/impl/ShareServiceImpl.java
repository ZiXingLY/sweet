package io.anshily.admin.service.impl;

import io.anshily.admin.dao.ShareMapper;
import io.anshily.model.Share;
import io.anshily.admin.service.ShareService;
import io.anshily.base.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by anshi on 2018/08/20.
 */
@Service
@Transactional
public class ShareServiceImpl extends AbstractService<Share> implements ShareService {
    @Resource
    private ShareMapper qyShareMapper;

    @Override
    public List<Share> findSharesByToken(String token) {
        return qyShareMapper.findSharesByToken(token);
    }

    @Override
    public Map getArticleShareNumber(Integer id) {
        return qyShareMapper.getArticleShareNumber(id);
    }

    @Override
    public Map getFlashShareNumber(Integer id) {
        return qyShareMapper.getFlashShareNumber(id);
    }
}
