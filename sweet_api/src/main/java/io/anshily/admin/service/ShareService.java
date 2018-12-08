package io.anshily.admin.service;
import io.anshily.model.Share;
import io.anshily.base.core.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by anshi on 2018/08/20.
 */
public interface ShareService extends Service<Share> {
    List<Share> findSharesByToken(String token);
    Map getArticleShareNumber(Integer id);
    Map getFlashShareNumber(Integer id);
}
