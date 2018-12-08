package io.anshily.biz.service;

import io.anshily.base.core.Service;
import io.anshily.biz.model.BizAdmin;

import java.util.List;


/**
 * Created by sxd on 2018/07/21.
 */
public interface BizAdminService extends Service<BizAdmin> {
    public List<BizAdmin> selectAdminByAcciuntAndPassword(BizAdmin bizAdmin);
    public List<BizAdmin> selectAdmin(BizAdmin bizAdmin);

}
