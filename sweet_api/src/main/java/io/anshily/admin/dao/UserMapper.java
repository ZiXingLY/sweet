package io.anshily.admin.dao;

import io.anshily.base.core.Mapper;
import io.anshily.model.User;

import java.util.List;
import java.util.Map;

public interface UserMapper extends Mapper<User> {
    List<User> selectByMap(Map<String, Object> map);
    List<User> findUserByPhone(String phone);
    List<User> selectAdmin(User bizAdmin);
    List<User> findUserByOpenid(String openid);
    List<User> getVipUserList();
    List<User> findVipUser();
    List<User> validationWxAccount(User user);
}
