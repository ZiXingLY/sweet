package io.anshily.admin.service.impl;

import io.anshily.admin.dao.CreditsMapper;
import io.anshily.model.Credits;
import io.anshily.admin.service.CreditsService;
import io.anshily.base.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by zaq on 2018/08/15.
 */
@Service
@Transactional
public class CreditsServiceImpl extends AbstractService<Credits> implements CreditsService {
    @Resource
    private CreditsMapper qyCreditsMapper;

    @Override
    public int getShareScore(int uid) {
        return 0;
    }

    @Override
    public Map getScoreByUidAndType(Credits credits) {
        return qyCreditsMapper.getScoreByUidAndType(credits);
    }

    @Override
    public Map getUserAllScore(int uid) {
        return qyCreditsMapper.getUserAllScore(uid);
    }

    @Override
    public List<Credits> findCreditsByUid(int uid) {
        return qyCreditsMapper.findCreditsByUid(uid);
    }

    @Override
    public List<Credits> articleHasReadByReadId(Credits credits) {
        return qyCreditsMapper.articleHasReadByReadId(credits);
    }

    @Override
    public List<Credits> articleHasReadByReadOpenid(Credits credits) {
        return qyCreditsMapper.articleHasReadByReadOpenid(credits);
    }
}
