package io.anshily.admin.service;
import io.anshily.model.User;
import io.anshily.base.core.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by hang on 2018/08/14.
 */
public interface UserService extends Service<User> {
    List<User> selectByMap(Map<String, Object> map);
    List<User> findUserByPhone(String phone);
    List<User> findUserByOpenid(String openid);
    List<User> selectAdmin(User bizAdmin);
    List<User> getVipUserList();

    List<User> findVipUser();

}
