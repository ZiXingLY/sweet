package io.anshily.admin.dao;

import io.anshily.base.core.Mapper;
import io.anshily.model.Credits;

import java.util.List;
import java.util.Map;

public interface CreditsMapper extends Mapper<Credits> {

    Map getScoreByUidAndType(Credits credits);
    Map getUserAllScore(int uid);
    List<Credits> findCreditsByUid(int uid);

    List<Credits> articleHasReadByReadId(Credits credits);

    List<Credits> articleHasReadByReadOpenid(Credits credits);
}