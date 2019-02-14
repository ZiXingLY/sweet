package io.anshily.admin.service.impl;

import io.anshily.admin.dao.UserMapper;
import io.anshily.model.User;
import io.anshily.admin.service.UserService;
import io.anshily.base.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by hang on 2018/08/14.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper qyUserMapper;

    @Override
    public List<User> selectByMap(Map<String, Object> map) {
        return qyUserMapper.selectByMap(map);
    }

    @Override
    public List<User> findUserByPhone(String phone) {
        return qyUserMapper.findUserByPhone(phone);
    }

    @Override
    public List<User> findUserByOpenid(String openid) {
        return qyUserMapper.findUserByOpenid(openid);
    }

    @Override
    public List<User> selectAdmin(User bizAdmin) {
        return qyUserMapper.selectAdmin(bizAdmin);
    }

    @Override
    public List<User> getVipUserList() {
        return qyUserMapper.getVipUserList();
    }

    @Override
    public List<User> findVipUser() {
        return qyUserMapper.findVipUser();
    }

    @Override
    public List<User> validationWxAccount(User user) {
        return qyUserMapper.validationWxAccount(user);
    }
}
