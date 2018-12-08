package io.anshily.biz.controller;

import com.github.pagehelper.PageHelper;
import io.anshily.admin.service.CreditsService;
import io.anshily.admin.service.UserService;
import io.anshily.base.core.*;
import io.anshily.base.utils.DateUtil;
import io.anshily.biz.model.BizAdmin;
import io.anshily.biz.model.BizRoles;
import io.anshily.biz.service.BizAdminService;
import io.anshily.biz.service.BizRolesPermissionsService;
import io.anshily.biz.service.BizRolesService;
import io.anshily.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sxd on 2018/07/21.
 */
@RestController
@RequestMapping("/sys/admin")
public class BizAdminController {
    @Resource
    private BizAdminService bizAdminService;
    @Resource
    private BizRolesService bizRolesService;
    @Resource
    private BizRolesPermissionsService bizRolesPermissionsService;

    @Autowired
    UserService userService;

    @Autowired
    CreditsService creditsService;

    /**
     * 获取用户总积分
     * @param id
     * @return
     */

    @RequestMapping("/getUserAllScore")
    public Result adminGetUserAllScore(Integer uid){

        User user1 = userService.findById(uid);

        Map map = new HashMap();
        map.put("user",user1);
        map.put("totalScore",(creditsService.getUserAllScore(uid) == null)? 0 : creditsService.getUserAllScore(uid));

        return ResultGenerator.successResult(map);
    }
//
//    @Autowired
//    SysUserService sysUserService;
//    @PostMapping("/add")
//    public Result add(@RequestBody BizAdmin bizAdmin) {
//        bizAdminService.save(bizAdmin);
//        return ResultGenerator.successResult();
//    }


    @PostMapping("/update")
    public Result update(@RequestBody BizAdmin bizAdmin) {
        bizAdminService.update(bizAdmin);
        return ResultGenerator.successResult();
    }

    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        BizAdmin bizAdmin = bizAdminService.findById(id);
        return ResultGenerator.successResult(bizAdmin);
    }


    //人员列表
    @GetMapping("/list")
    public Result list(BizAdmin admin, PageBean<BizAdmin> page) {
        PageHelper.startPage(page.getPageNum(), page.getSize(), "add_time desc");
        List<BizAdmin> bizAdminList = bizAdminService.selectAdmin(admin);
        page.setList(bizAdminList);
        return ResultGenerator.successResult(page);
    }

    /**
     * 获取用户列表
     * @param page
     * @return
     */

    @GetMapping("/userlist")
    public Result uesrlist(PageBean<User> page) {
        PageHelper.startPage(page.getPageNum(), page.getSize(), "add_time desc");
        List<User> userList = userService.findAll();
        page.setList(userList);
        return ResultGenerator.successResult(page);
    }

    @RequestMapping(value = "userForbid", method = RequestMethod.GET)
    public Result forbid(Integer id) {

        User user = userService.findById(id);

        if (user.getStatus().equals(0)) {
            user.setStatus(1);
        } else {
            user.setStatus(0);
        }
        userService.update(user);
        return ResultGenerator.successResult();
    }



//    @RequestMapping("getUserAllScore")
//    public Result getUserAllScore(int id){
//
//        User user = userService.findById(id);
//
//        Map map = creditsService.getUserAllScore(user.getId());
//        map.put("user",user);
//
//        return ResultGenerator.successResult(map);
//    }

    @RequestMapping("getVipUserList")
    public Result getVipUserList(){

        List<User> userList = userService.getVipUserList();
        return ResultGenerator.successResult();
    }


    //重置密码
    @PostMapping("/reset")
    public Result reset(@RequestBody BizAdmin admin) {
        BizAdmin admin1 = bizAdminService.findById(admin.getId());

//        List<SysUser> userList = sysUserService.findUserByPhone(admin1.getAccount());
//        SysUser user = userList.get(0);
//        user.setPassword(MyMD5.myMD5(admin1.getAccount()+user.getPhone_number()));
//
//        try {
//            sysUserService.update(user);
//
////            resultMap.put("status",200);
////            resultMap.put("message","修改成功");
//        } catch (Exception e) {
//            throw new ServiceException(Constants.CODE_ERR_NEW_PASSWORD_ERR);
////            resultMap.put("status",500);
////            resultMap.put("message",e.getMessage());
////            System.err.println(e.getMessage());
//        }
        if (admin1 != null) {
            admin1.setPassword(admin.getPassword());
            bizAdminService.update(admin1);
        }
        return ResultGenerator.successResult();
    }

    /**
     * 后台登录
     *
     * @param bizAdmin
     * @return
     */
    @GetMapping("/login")
    public Result login(BizAdmin bizAdmin) {
        List<BizAdmin> bizAdminList = bizAdminService.selectAdminByAcciuntAndPassword(bizAdmin);
        if (bizAdminList == null || bizAdminList.size() != 1) {
            return ResultGenerator.errResult(Constants.CODE_ERR_ACCOUNT_OR_PASSWORD_ERR);
        }
//        try {
//            UsernamePasswordToken token = new UsernamePasswordToken(bizAdmin.getAccount(), bizAdmin.getPassword());
//            SecurityUtils.getSubject().login(token);
////            resultMap.put("status", 200);
////            resultMap.put("message", "登录成功");
//
////            UserExtend userExtend = new UserExtend();
////
////            SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
////            userExtend.setUid(user.getId());
////
////            userExtendService.save(userExtend);
//
//        } catch (Exception e) {
//            return ResultGenerator.errResult(Constants.INTERNAL_SERVER_ERROR);
//        }
        BizAdmin dbBizAdmin = bizAdminList.get(0);
        Integer roles_id = dbBizAdmin.getRoles_id();
        if (roles_id != null) {
            BizRoles bizRoles = bizRolesService.findById(roles_id);
            List<Map<String, String>> permissionsList = bizRolesPermissionsService.selectPermissionsByRoleId(roles_id);
            ModelMap modelMap = new ModelMap();
            modelMap.put("admin", dbBizAdmin);
            modelMap.put("roletitle", bizRoles.getRole_title());
            modelMap.put("permissions", permissionsList);
            return ResultGenerator.successResult(modelMap);
        } else {
            return ResultGenerator.errResult(Constants.INTERNAL_SERVER_ERROR);
        }

    }

    //增加用户
    @PostMapping("/add")
    public Result add(@RequestBody BizAdmin admin) {
        if (admin.getAccount() == null || admin.getAccount().isEmpty()) {
            throw new ServiceException(Constants.CODE_ERR_USER_NAME);
        }
        if (admin.getPassword() == null || admin.getPassword().isEmpty()) {
            throw new ServiceException(Constants.CODE_ERR_USER_NAME);
        }
        String username = admin.getAccount();
        String password = admin.getPassword();
        if (username == null || password == null) {
            throw new ServiceException(Constants.CODE_ERR_USER_NAME);
        } else {

            Condition condition = new Condition(BizAdmin.class);
            Example.Criteria criteria = condition.createCriteria();
            criteria.andCondition("account = '" + admin.getAccount() + "'");
            List<BizAdmin> adminList = bizAdminService.findByCondition(condition);

            if (adminList.size() != 0) {
                throw new ServiceException(Constants.CODE_ERR_ACCOUNT_EXIST);
            }
        }
        if (admin.getName() != null && !admin.getName().isEmpty()) {
            Condition condition = new Condition(BizAdmin.class);
            Example.Criteria criteria = condition.createCriteria();
            criteria.andCondition("`name` = '" + admin.getName() + "'");
            List<BizAdmin> adminList = bizAdminService.findByCondition(condition);
            if (adminList.size() != 0) {
                throw new ServiceException(Constants.CODE_ERR_REAL_NAME_EXIST);
            }
        }
        admin.setPassword(password);
        admin.setRoles_id(1);
        admin.setAdd_time(DateUtil.getNowTimestamp());
        bizAdminService.save(admin);

//        SysUser sysUser = new SysUser();
//        sysUser.setPhone_number(admin.getAccount());
//        sysUser.setUsername(admin.getPassword());
//        sysUser.setPassword(MyMD5.myMD5(password+username));
//
//        List<SysUser> userList = sysUserService.findUserByPhone(username);
//        if (userList.size()>0){
////            resultMap.put("status", 500);
//////            resultMap.put("message", "该手机号已注册");
//
//            throw new ServiceException(Constants.CODE_ERR_REAL_NAME_EXIST);
//
////            System.out.println(userList.size());
//        }else {
//            sysUserService.save(sysUser);
//        }
        return ResultGenerator.successResult();
    }


    //删除用户
    @PostMapping("/delete")
    public Result delete(@RequestBody BizAdmin admin) {

//        BizAdmin admin1 = bizAdminService.findById(admin.getId());

//        List<SysUser> userList = sysUserService.findUserByPhone(admin1.getAccount());
//        SysUser user = userList.get(0);
//
//        int total = sysUserService.deleteUserByUid(user.getId());
//        System.out.println(total);
        bizAdminService.deleteById(admin.getId());
        return ResultGenerator.successResult();
    }

    //修改密码
    @GetMapping("/changePassword")
    public Result changePassword(Integer id, String oldPassword, String newPassword) {
        BizAdmin admin = bizAdminService.findById(id);

//        List<SysUser> userList = sysUserService.findUserByPhone(admin.getAccount());
//        SysUser user = userList.get(0);
//        user.setPassword(MyMD5.myMD5(newPassword+user.getPhone_number()));
//
//        try {
//            sysUserService.update(user);
//
////            resultMap.put("status",200);
////            resultMap.put("message","修改成功");
//        } catch (Exception e) {
//            throw new ServiceException(Constants.CODE_ERR_NEW_PASSWORD_ERR);
////            resultMap.put("status",500);
////            resultMap.put("message",e.getMessage());
////            System.err.println(e.getMessage());
//        }
        if (admin == null) {
            return ResultGenerator.errResult(Constants.CODE_ERR_ACCOUNT_NO_EXIST);
        }
        if ((oldPassword).equals(admin.getPassword())) {
            if ((newPassword).equals(admin.getPassword())) {
                throw new ServiceException(Constants.CODE_ERR_NEW_PASSWORD_ERR);
            }
            admin.setPassword(newPassword);
            bizAdminService.update(admin);
            return ResultGenerator.successResult();
        } else {
            return ResultGenerator.errResult(Constants.CODE_ERR_OLD_PASSWORD_ERR);
        }
    }


}
