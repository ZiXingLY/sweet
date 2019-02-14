package io.anshily.admin.controller;

import io.anshily.admin.extend.ExUsernamePasswordToken;
import io.anshily.admin.service.CreditsService;
import io.anshily.admin.service.MessageService;
import io.anshily.base.core.*;
import io.anshily.base.utils.MyMD5;
import io.anshily.base.utils.UploadFile;
import io.anshily.base.utils.weixin.utils.HttpCommonUtil;
import io.anshily.base.utils.weixin.utils.WXAPPConfigUtil;
import io.anshily.model.Credits;
import io.anshily.model.Message;
import io.anshily.model.User;
import io.anshily.admin.service.UserService;
import com.github.pagehelper.PageHelper;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static io.anshily.base.core.Constants.ADD_SCORE_BY_REGIS;
import static io.anshily.base.core.Constants.ADD_SCORE_TYPE_REGIS;

/**
 * Created by hang on 2018/08/14.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @Autowired
    CreditsService creditsService;

    @Autowired
    MessageService messageService;


    @RequestMapping("/isLogin")
    public Result isLogin() {

        System.out.println("登录验证");

//        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();


        if (SecurityUtils.getSubject().isAuthenticated()) {
            System.out.println("已登录");
            User user = (User) SecurityUtils.getSubject().getPrincipal();

            user = userService.findById(user.getId());

            return ResultGenerator.successResult(user);
//            resultMap.put("islogin", "login");
//            resultMap.put("header", user.getHeader());
        } else {
//            resultMap.put("islogin", "noLogin");
            System.out.println("未登录");

            return ResultGenerator.successResult("unLogin");
        }

//        return resultMap;
    }

    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        userService.save(user);
        return ResultGenerator.successResult();
    }

    /**
     * 账户密码登录
     *
     * @param user
     * @return
     */

    @PostMapping("/ajaxLogin")
    public Result ajaxLogin(@RequestBody User user) {
        try {
            ExUsernamePasswordToken token = new ExUsernamePasswordToken(user.getPhone(), user.getPassword());

            SecurityUtils.getSubject().login(token);
            return ResultGenerator.successResult();
        } catch (Exception e) {
            return ResultGenerator.errResult(Constants.CODE_ERR_ACCOUNT_OR_PASSWORD_ERR);
        }
    }

    /**
     * 微信登录 匹配 phone 和 openid
     * 匹配则登录
     *
     * @param user
     * @return
     */

    @PostMapping("/wxLogin")
    public Result wxLogin(@RequestBody User user) {

        if (user.getOpenid() == null) {
            return ResultGenerator.errResult();
        }

        ExUsernamePasswordToken token = new ExUsernamePasswordToken(user.getOpenid(), user.getOpenid());
        token.setType(2);
//        token.setOpenid(user.getOpenid());

        try {
            SecurityUtils.getSubject().login(token);
            return ResultGenerator.successResult();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.errResult(Constants.CODE_ERR_ACCOUNT_OR_PASSWORD_ERR);
        }


    }

    /**
     * 通过openid 获取用户信息
     *
     * @param user
     * @return
     */
    @GetMapping("/getUserInfoByOpenid")
    public Result getUserInfoByOpenid(User user) {
        List<User> userList = userService.findUserByOpenid(user.getOpenid());

        if (userList.size() != 0) {
            return ResultGenerator.successResult(userList.get(0));
        } else {
            return ResultGenerator.errResult(Constants.CODE_ERR_ACCOUNT_NO_EXIST);
        }
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */

    @PostMapping("/ajaxRegister")
    public Result ajaxRegister(@RequestBody User user) {

        user.setPassword(MyMD5.myMD5(user.getPassword() + user.getPhone()));

        List<User> userList = userService.findUserByPhone(user.getPhone());

        if (userList.size() > 0) {
            return ResultGenerator.errResult(Constants.CODE_ERR_ACCOUNT_EXIST);
        } else {


            userService.save(user);

            List<User> users = userService.findUserByPhone(user.getPhone());

            Credits credits = new Credits();
            credits.setUid(users.get(0).getId());
            credits.setType(ADD_SCORE_TYPE_REGIS);
            credits.setScore(ADD_SCORE_BY_REGIS);
            credits.setInfo("注册赠送积分");
            credits.setAdd_time(new Date());

            creditsService.save(credits);

            Message message = new Message();
            message.setUid(users.get(0).getId());
            message.setType(3);
            message.setAdd_time(new Date());
            message.setMessage_info("会员注册！");

            messageService.save(message);


            return ResultGenerator.successResult();
        }

    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Integer id) {
        userService.deleteById(id);
        return ResultGenerator.successResult();
    }

    /**
     * 更新用户 头像 昵称信息
     *
     * @param user
     * @return
     */

    @RequestMapping("/update")
    public Result update(@RequestBody User user) {

        User user1 = (User) SecurityUtils.getSubject().getPrincipal();

        if (user.getInfo() != null) {

            String url = UploadFile.uploadBase64(user.getInfo());

            user1.setHeader("../" + url.substring(2, url.length() - 2));

            user1.setInfo("../" + url.substring(2, url.length() - 2));

        }
        user1.setNickname(user.getNickname());
        userService.update(user1);
        return ResultGenerator.successResult();
    }

    /**
     * 已登录用户 获取用户详细信息
     *
     * @return
     */

    @GetMapping("/detail")
    public Result detail() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        User user1 = userService.findById(user.getId());
        return ResultGenerator.successResult(user1);
    }

    @GetMapping("/list")
    public Result list(PageBean<User> page) {
        PageHelper.startPage(page.getPageNum(), page.getSize());
        List<User> list = userService.findAll();
        page.setList(list);
        return ResultGenerator.successResult(page);
    }

    /**
     * 修改密码
     *
     * @param user
     * @return
     */

    @RequestMapping("/ajaxAlterpass")
    public Result ajaxAlterpass(@RequestBody User user) {
//        String username = ((SysUser)SecurityUtils.getSubject().getPrincipal()).getPhone_number();
//        SysUser user =(SysUser)SecurityUtils.getSubject().getPrincipal();
//        user.setId(id);

        User user1 = (User) userService.findUserByPhone(user.getPhone()).get(0);
        user1.setPassword(MyMD5.myMD5(user.getPassword() + user.getPhone()));
        try {
            userService.update(user1);
            return ResultGenerator.successResult();
        } catch (Exception e) {
            return ResultGenerator.errResult(Constants.CODE_ERR_ACCOUNT_OR_PASSWORD_ERR);
        }
    }

    /**
     * 微信绑定手机号
     *
     * @param user
     * @return
     */

    @RequestMapping("/bindPhone")
    public Result bindPhone(@RequestBody User user) {


// 得不到已注册手机号返回去web端注册
        List<User> userList = userService.findUserByPhone(user.getPhone());


        if (userList.size() > 0) {

            try {
                ExUsernamePasswordToken token = new ExUsernamePasswordToken(user.getPhone(), user.getPassword());

                SecurityUtils.getSubject().login(token);

                User user1 = userList.get(0);

                List<User> wxList = userService.findUserByOpenid(user.getOpenid());

                if (wxList.size() > 0) {

                    List<Credits> creditsList = creditsService.findCreditsByUid(wxList.get(0).getId());
                    // openid 迁移
                    user1.setOpenid(user.getOpenid());
// 积分迁移
                    Credits credits = new Credits();
                    for (int i = 0; i < creditsList.size(); i++) {
                        credits = creditsList.get(i);
                        credits.setUid(userList.get(0).getId());

                        creditsService.update(credits);
                    }

                    userService.update(user1);
                    // 删除老用户
                    userService.deleteById(wxList.get(0).getId());

//                return ResultGenerator.successResult();
                }
//                return ResultGenerator.successResult();
            } catch (Exception e) {
                return ResultGenerator.errResult(Constants.CODE_ERR_ACCOUNT_OR_PASSWORD_ERR);
            }


        }else {
            return  ResultGenerator.errResult(2504,"尚未注册过PC端");
        }

        return ResultGenerator.successResult();
    }

    /**
     * 用openid注册
     *
     * @param
     * @return
     */
//    @PostMapping("/WXRegister")
//    public Result WXRegister(@RequestBody User user) {
//
//        if (user.getOpenid() == null) {
//            return ResultGenerator.errResult();
//        }
//        List<User> wxList = userService.findUserByOpenid(user.getOpenid());
//        if (wxList.size() > 0) {
//            return ResultGenerator.successResult(wxList.get(0));
//        } else {
//            userService.save(user);
//
//            List<User> wxUsers = userService.findUserByOpenid(user.getOpenid());
//            return ResultGenerator.successResult(wxUsers.get(0));
//        }
//    }

    @PostMapping("/wxRegiste")
    public Result wxRegiste(@RequestBody User user) {

        System.out.println(user.getPhone());

        if (user.getOpenid().equals("") || user.getOpenid() == null) {
            return ResultGenerator.errResult();
        }
        if (user.getPhone().equals("") || user.getPhone() == null){
            return ResultGenerator.errResult();
        }

        List<User> users = userService.validationWxAccount(user);

        System.out.println(users);

        if (users.size() > 0){
            return ResultGenerator.errResult(Constants.CODE_ERR_ACCOUNT_EXIST);
        }
        user.setPassword(MyMD5.myMD5(user.getPassword() + user.getPhone()));

        List<User> userList = userService.findUserByPhone(user.getPhone());

        if (userList.size() > 0) {
            return ResultGenerator.errResult(Constants.CODE_ERR_ACCOUNT_EXIST);
        } else {

            userService.save(user);
            return ResultGenerator.successResult();
        }
    }

    //获取openid
    @GetMapping("/getOpenid")
    public Result getOpenId(String code) {
        if (code == null || code.isEmpty()) {
            throw new ServiceException(Constants.CODE_ERR_PARAM);
        }
        String access_token_str = HttpCommonUtil.httpsRequest("https://api.weixin.qq.com/sns/jscode2session?appid=" + WXAPPConfigUtil.APPID + "&secret=" + WXAPPConfigUtil.APPSERCET + "&js_code=" + code + "&grant_type=authorization_code", "GET", null);
        System.out.println(access_token_str);
        JSONObject access_token_obj = JSONObject.fromObject(access_token_str);
        String openid = access_token_obj.get("openid").toString();
        return ResultGenerator.successResult(openid);
    }

    /**
     * 通过openid 获取用户信息
     *
     * @param user
     * @return
     */
    @RequestMapping("wxUserInfo")
    public Result wxUserInfo(@RequestBody User user) {

        List<User> wxList = userService.findUserByOpenid(user.getOpenid());
        if (wxList.size() > 0) {
            return ResultGenerator.successResult(wxList.get(0));
        } else {
            return ResultGenerator.successResult(Constants.CODE_ERR_ACCOUNT_EXIST);

        }
    }


//
//    //后台人员列表
//    @GetMapping("admin/list")
//    public Result adminList(User user, PageBean<User> page) {
//        PageHelper.startPage(page.getPageNum(), page.getSize(), "add_time desc");
//        user.setType(2);
//        List<User> bizAdminList = userService.selectAdmin(user);
//        page.setList(bizAdminList);
//        return ResultGenerator.successResult(page);
//    }
}
