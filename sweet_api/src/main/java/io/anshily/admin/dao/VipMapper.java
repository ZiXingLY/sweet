package io.anshily.admin.dao;

import io.anshily.base.core.Mapper;
import io.anshily.model.Vip;

import java.util.List;

public interface VipMapper extends Mapper<Vip> {
    List<Vip> findTheLastByUid(int uid);
//    List<Vip> findByVip()
}