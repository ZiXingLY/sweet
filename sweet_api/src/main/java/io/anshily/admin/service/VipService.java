package io.anshily.admin.service;
import io.anshily.model.Vip;
import io.anshily.base.core.Service;

import java.util.List;


/**
 * Created by anshi on 2018/08/22.
 */
public interface VipService extends Service<Vip> {
    List<Vip> findTheLastByUid(int uid);
}
