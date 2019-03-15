package io.anshily.admin.controller;

import io.anshily.admin.service.CreditsService;
import io.anshily.admin.service.ShareService;
import io.anshily.admin.service.UserService;
import io.anshily.base.core.Result;
import io.anshily.base.core.ResultGenerator;
import io.anshily.base.utils.MyMD5;
import io.anshily.gxchainbaas.GXCserve;
import io.anshily.ipfs.IPFSServe;
import io.anshily.model.Credits;
import io.anshily.model.Share;
import io.anshily.model.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static io.anshily.base.core.Constants.ADD_SCORE_BY_SHARE;
import static io.anshily.base.core.Constants.ADD_SCORE_TYPE_SHARE;

@RestController
public class AdminController {

    @Autowired
    ShareService shareService;

    @Autowired
    CreditsService creditsService;

    @Autowired
    UserService userService;

    @Autowired
    IPFSServe ipfsServe;

    @Autowired
    GXCserve gxCserve;

    @GetMapping("/reedit")
    public ModelAndView reedit(int id){
        ModelAndView mov = new ModelAndView("front/reedit");
        mov.addObject("eid",id);
        return mov;
    }

    @PostMapping("/ipfs/add")
    public Result add(String data){
        try {
            String res = ipfsServe.add(data);
           return ResultGenerator.successResult(res);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultGenerator.errResult(5432);
        }
    }

    @GetMapping("/ipfs/cat")
    public Result cat(String hash){
        try {
            String res = ipfsServe.cat(hash);
            return ResultGenerator.successResult(res);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultGenerator.errResult(5432);
        }
    }

    @GetMapping("/gxc/call")
    public Result callContract(){
        return ResultGenerator.successResult(gxCserve.callContract());
    }

    @GetMapping("/favicon.ico")
    public String favicon(){
        return "../../";
    }
    /**
     * 登录 页面控制器
     *
     * @return
     */
    @GetMapping("/login")
    public ModelAndView login(@RequestParam(value = "url",defaultValue = "/") String url) {


//        Map<String, String> map = new HashMap<String, String>();
//        map.put("url", url);
//        map.put("fid", fid);
        ModelAndView mov = new ModelAndView("front/login");
        if (url.equals("/logout")){
            url = "/";
        }else if (url.contains("/logout")){
            url = "/";
        }
        mov.addObject("url",url);
        return mov;
//        return new ModelAndView("front/login");
    }

    /**
     * 注册 页面控制器
     *
     * @return
     */
    @GetMapping("/regis")
    public ModelAndView regis() {
        return new ModelAndView("front/regis");
    }

    /**
     * 忘记密码 页面控制器
     *
     * @return
     */
    @GetMapping("/forgotPass")
    public ModelAndView forgotPass() {
        return new ModelAndView("front/forgetPass");
    }

    /**
     * 退出
     *
     * @return
     */
    @RequestMapping("/logout")
    public ModelAndView logout() {
        try {
            //退出
            SecurityUtils.getSubject().logout();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return new ModelAndView("front/index");
    }

    @GetMapping("/userCenter")
    public ModelAndView userCenter() {
        return new ModelAndView("front/userCenter");
    }

    @GetMapping("/")
    public ModelAndView toindex() {
        return new ModelAndView("front/index");
    }

    @GetMapping("/userInfo")
    public ModelAndView userInfo() {
        return new ModelAndView("front/userInfo");
    }

    @GetMapping("/userModifyPass")
    public ModelAndView userModifyPass() {
        return new ModelAndView("front/userModifyPass");
    }

//    @GetMapping("/")
//    public ModelAndView index(){
//        return new ModelAndView("/index");
//    }

    @GetMapping("/banner")
    public ModelAndView banner() {
        return new ModelAndView("admin/banner");
    }

    @GetMapping("/flashList")
    public ModelAndView flashList() {
        return new ModelAndView("front/flashList");
    }

    @GetMapping("/userMessage")
    public ModelAndView userMessage() {
        return new ModelAndView("front/userMessage");
    }

    @GetMapping("/check/editor")
    public Result checkEditor(){
        User user = (User) SecurityUtils.getSubject().getPrincipal();


        User user1 = userService.findById(user.getId());

        if (user1.getVip_state() == 2){
            return ResultGenerator.errResult(2504,"您还不是会员，无法发布文章");
        }

        if (user1.getLeft_free_times() <= 0){
            return ResultGenerator.errResult(2504,"您本月的文章次数已经用完了");
        }
        return ResultGenerator.successResult();
    }

    @GetMapping("/editor")
    public ModelAndView editor() {
        return new ModelAndView("front/editor");
    }

    @GetMapping("/userVip")
    public ModelAndView userVip() {
        return new ModelAndView("front/userVip");
    }
//
//    @GetMapping("/forgetPass")
//    public ModelAndView forgetPass(){
//        return new ModelAndView("front/forgetPass");
//    }

    @GetMapping("/shareFlashDetail")
    public ModelAndView shareFlashDetail(String token) {

        Condition condition = new Condition(Share.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andCondition("share_token = '" + token + "'");

        List<Share> shares = shareService.findByCondition(condition);

//        Map<String, Integer> map = new HashMap<String, Integer>();
//        map.put("uid", uid);
//        map.put("fid", fid);
        ModelAndView mov = new ModelAndView("front/shareFlashDetail");
        mov.addObject("fid",shares.get(0).getFid());
        mov.addObject("token",token);
        return mov;
    }

    @GetMapping("/shareArticleDetail")
    public ModelAndView shareArticleDetail(String token) {

        Condition condition = new Condition(Share.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andCondition("share_token = '" + token + "'");

        List<Share> shares = shareService.findByCondition(condition);

        ModelAndView mov = new ModelAndView("front/shareDetailArticle");
        mov.addObject("aid",shares.get(0).getAid());
        mov.addObject("token",token);

        return mov;
    }

    @GetMapping("/exchange")
    public ModelAndView exchange(){
        return new ModelAndView("front/exchange");
    }

    @GetMapping("/editorSuccess")
    public ModelAndView editorSuccess(){
        return new ModelAndView("front/editorSuccess");
    }

    @GetMapping("/recharge")
    public ModelAndView recharge(){
        return new ModelAndView("front/recharge");
    }

    @GetMapping("/checkArticle")
    public ModelAndView checkArticle(int id){

        User user1 = (User) SecurityUtils.getSubject().getPrincipal();

        String token = MyMD5.myMD5(user1.getId().toString()+id+1);
        ModelAndView mov = new ModelAndView("front/checkArticle");
        mov.addObject("aid",id);
        mov.addObject("token",token);
        return mov;
    }

    @GetMapping("/resetPass")
    public ModelAndView resetPass(){
        return new ModelAndView("front/resetPass");
    }

    /**
     * 注册增加10积分
     * @param token
     * @param phone
     * @return
     */
    @GetMapping("/regisAddScore")
    public Result regisAddScore(String token,String phone){

        Condition condition = new Condition(Share.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andCondition("share_token = '" + token + "'");

        List<Share> shares = shareService.findByCondition(condition);

        int uid = shares.get(0).getUid();
//        int fid = 0;
        int aid = 0;

        int type = shares.get(0).getType();
        if (type == 1){
            aid = shares.get(0).getAid();
        }else if (type == 2){
            aid = shares.get(0).getFid();
        }

        Credits credits = new Credits();

        credits.setUid(shares.get(0).getUid());
        credits.setAid(aid);
        credits.setArticle_type(shares.get(0).getType());
        credits.setAdd_time(new Date());
//        credits.setType(1);
        credits.setScore(ADD_SCORE_BY_SHARE);
        credits.setType(ADD_SCORE_TYPE_SHARE);
        credits.setInfo("通过分享获得的积分:"+ADD_SCORE_BY_SHARE);
        creditsService.save(credits);

//        Share share = shareService.findBy("share_token",token);

//        System.out.println(share);

//        return ResultGenerator.successResult(shares.get(0));

        return ResultGenerator.successResult();
    }

    @RequestMapping("/loginrefuse")
    public Result loginrefuse(){
        return ResultGenerator.errResult(403,"unlogin");
    }
}
