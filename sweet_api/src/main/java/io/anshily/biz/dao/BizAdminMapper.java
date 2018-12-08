package io.anshily.biz.dao;

import io.anshily.base.core.Mapper;
import io.anshily.biz.model.BizAdmin;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BizAdminMapper extends Mapper<BizAdmin> {
    public List<BizAdmin> selectAdminByAcciuntAndPassword(BizAdmin bizAdmin);
    public List<BizAdmin> selectAdmin(BizAdmin bizAdmin);
}