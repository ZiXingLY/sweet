package io.anshily.shiro;

import io.anshily.admin.extend.ExUsernamePasswordToken;
import io.anshily.admin.service.UserService;
import io.anshily.base.utils.MyMD5;
import io.anshily.model.User;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * shiro身份校验核心类
 *
 * @author 作者: z77z
 * @date 创建时间：2017年2月10日 下午3:19:48
 */

public class MyShiroRealm extends AuthorizingRealm {

    // 设置自定义token

    public MyShiroRealm() {
        setAuthenticationTokenClass(ExUsernamePasswordToken.class);

    }


    @Autowired
    private UserService userService;

//	@Autowired
//	StringRedisTemplate stringRedisTemplate;


    //用户登录次数计数  redisKey 前缀
//	private String SHIRO_LOGIN_COUNT = "shiro_login_count_";

    //用户登录是否被锁定    一小时 redisKey 前缀
//	private String SHIRO_IS_LOCK = "shiro_is_lock_";

    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {

        ExUsernamePasswordToken token = (ExUsernamePasswordToken) authcToken;

        String username = token.getUsername();
        String password = String.valueOf(token.getPassword());
        User user = null;

        if (token.getType() == 2) {
            List<User> wxList = userService.findUserByOpenid(String.valueOf(token.getPassword()));

            if (wxList.size() != 0){
                user = wxList.get(0);
            }
        } else {

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("phone", username);
            MyMD5.myMD5(password + username);
            //密码进行加密处理  明文为  password+name
//		String paw = password+name;
//		String pawDES = MyDES.encryptBasedDes(paw);
            map.put("password", MyMD5.myMD5(password + username));
            // 从数据库获取对应用户名密码的用户
            List<User> userList = userService.selectByMap(map);
            if (userList.size() != 0) {
                user = userList.get(0);
            }
        }

        if (null == user) {
            throw new AccountException("帐号或密码不正确！");
        } else if ("0".equals(user.getStatus())) {
            /**
             * 如果用户的status为禁用。那么就抛出<code>DisabledAccountException</code>
             */
            throw new DisabledAccountException("此帐号已经设置为禁止登录！");
        } else {
            //登录成功
            //更新登录时间 last login time
            user.setLast_login_time(new Date().toString());
//			user.setLastLoginTime(new Date());
            userService.update(user);
            //清空登录计数
//			opsForValue.set(SHIRO_LOGIN_COUNT+name, "0");
        }
        Logger.getLogger(getClass()).info("身份认证成功，登录用户：" + username);
        return new SimpleAuthenticationInfo(user, password, getName());
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        System.out.println("权限认证方法：MyShiroRealm.doGetAuthorizationInfo()");
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        int userId = user.getId();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //根据用户ID查询角色（role），放入到Authorization里。
		/*Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", userId);
		List<SysRole> roleList = sysRoleService.selectByMap(map);
		Set<String> roleSet = new HashSet<String>();
		for(SysRole role : roleList){
			roleSet.add(role.getType());
		}*/
        Set<String> roleSet = new HashSet<String>();
        roleSet.add("100002");
        info.setRoles(roleSet);
        //根据用户ID查询权限（permission），放入到Authorization里。
		/*List<SysPermission> permissionList = sysPermissionService.selectByMap(map);
		Set<String> permissionSet = new HashSet<String>();
		for(SysPermission Permission : permissionList){
			permissionSet.add(Permission.getName());
		}*/
        Set<String> permissionSet = new HashSet<String>();
        permissionSet.add("权限添加");
        permissionSet.add("权限删除");
        info.setStringPermissions(permissionSet);
        return info;
    }
}
