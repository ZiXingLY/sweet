package io.anshily.admin.service;
import io.anshily.model.Credits;
import io.anshily.base.core.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by zaq on 2018/08/15.
 */
public interface CreditsService extends Service<Credits> {
    int getShareScore(int uid);
    Map getScoreByUidAndType(Credits credits);
    Map getUserAllScore(int uid);
    List<Credits> findCreditsByUid(int uid);

    List<Credits> articleHasReadByReadId(Credits credits);

    List<Credits> articleHasReadByReadOpenid(Credits credits);
}
