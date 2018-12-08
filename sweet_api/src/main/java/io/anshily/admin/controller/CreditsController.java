package io.anshily.admin.controller;

import com.github.pagehelper.PageInfo;
//import Constants;
import io.anshily.base.core.Constants;
import io.anshily.base.core.Result;
import io.anshily.base.core.ResultGenerator;
import io.anshily.front.service.ArticleService;
import io.anshily.front.service.FlashService;
import io.anshily.base.core.PageBean;
import com.github.pagehelper.PageHelper;
import io.anshily.admin.service.*;
import io.anshily.model.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

import static io.anshily.base.core.Constants.*;

/**
 * Created by zaq on 2018/08/15.
 */
@RestController
@RequestMapping("/credits")
public class CreditsController {

//    private static final Integer ADD_SCORE_BY_SHARE = 10;//每次分享所得积分
//    private static final Integer ADD_SCORE_TYPE_SHARE = 1;//积分类型 分享得到
//    private static final Integer ADD_SCORE_TYPE_VIP = 2; // 积分类型 会员获取到
//    private static final Integer ADD_SCORE_TYPE_PUBLISH = 3;  // 积分类型 发表文章
//    private static final Integer SUB_SCORE_BY_PUBLISH = -100; // 发表文章 时积分状态


    @Resource
    private CreditsService creditsService;

    @Autowired
    private FlashService flashService;

    @Autowired
    private LevelScoreService levelScoreService;

    @Autowired
    ArticleService articleService;

    @Autowired
    ShareService shareService;

    @Autowired
    VipService vipService;

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;


    @PostMapping("/add")
    public Result add(@RequestBody Credits credits) {
        creditsService.save(credits);
        return ResultGenerator.successResult();
    }


    /**
     * 通过分享获得的积分
     *
     * @param uid 分享人uid
     * @param aid 文章aid
     * @return
     */
    @RequestMapping("/shareToAdd")
    public Result shareToAdd(int uid, int aid) {

        Credits credits = new Credits();

        credits.setUid(uid);
        credits.setAid(aid);
        credits.setAdd_time(new Date());
//        credits.setType(1);
        credits.setScore(ADD_SCORE_BY_SHARE);
        credits.setType(ADD_SCORE_TYPE_SHARE);
        credits.setInfo("通过分享获得的积分:"+ADD_SCORE_BY_SHARE);
        creditsService.save(credits);

        return ResultGenerator.successResult();
    }

    @RequestMapping("/shareToAddByOpenid")
    public Result shareToAddByOpenid(String openid, String addByOpenid ,int aid) {

        List<User> userList = userService.findUserByOpenid(openid);
        List<User> userListRead = userService.findUserByOpenid(addByOpenid);

        Credits creditsQu = new Credits();

        creditsQu.setOpenid(openid);
        creditsQu.setAdd_by_openid(addByOpenid);
        creditsQu.setAid(aid);

        List<Credits> creditsList = creditsService.articleHasReadByReadOpenid(creditsQu);

        if (creditsList.size() > 0 || openid.equals(addByOpenid)){
            System.out.println("已阅读");
        }else {

            Credits credits = new Credits();

            credits.setUid(userList.get(0).getId());
            credits.setAid(aid);
            credits.setOpenid(openid);
            credits.setAdd_by_openid(addByOpenid);
            credits.setAdd_time(new Date());
            // 1：文章
            credits.setArticle_type(1);
            credits.setScore(ADD_SCORE_BY_REAL_WX);
            credits.setType(ADD_SCORE_TYPE_SHARE);
            credits.setInfo("文章分享");
            creditsService.save(credits);

            Credits credits1 = new Credits();

            credits1.setUid(userListRead.get(0).getId());
            credits1.setAid(aid);
            credits1.setOpenid(addByOpenid);
            credits1.setAdd_time(new Date());
            credits1.setArticle_type(1);
            credits1.setScore(ADD_SCORE_BY_READ);
            credits1.setInfo("阅读文章");
            creditsService.save(credits1);

        }

        return ResultGenerator.successResult();
    }

    @GetMapping("toReadFlashAddScoreByOpenid")
    public Flash toReadFlashAddScoreByOpenid(String openid, String addByOpenid , Integer fid){
        Flash flash = flashService.findById(fid);

        List<User> userList = userService.findUserByOpenid(openid);
        List<User> userListRead = userService.findUserByOpenid(addByOpenid);

        Credits creditsQu = new Credits();

        creditsQu.setOpenid(openid);
        creditsQu.setAdd_by_openid(addByOpenid);
        creditsQu.setAid(fid);
//        creditsQu.setAid(aid);

        List<Credits> creditsList = creditsService.articleHasReadByReadOpenid(creditsQu);

        if (creditsList.size() > 0 || openid.equals(addByOpenid)){
            System.out.println("已阅读");
        }else {

            Credits credits = new Credits();

            credits.setUid(userList.get(0).getId());
            credits.setAid(fid);
            credits.setOpenid(openid);
            credits.setAdd_by_openid(addByOpenid);
            credits.setAdd_time(new Date());
            // 2: 快讯
            credits.setArticle_type(2);
            credits.setScore(ADD_SCORE_BY_REAL_WX);
            credits.setType(ADD_SCORE_TYPE_SHARE);
            credits.setInfo("文章分享");
            creditsService.save(credits);

            Credits credits1 = new Credits();

            credits1.setUid(userListRead.get(0).getId());
            credits1.setAid(fid);
            credits1.setOpenid(addByOpenid);
            credits1.setAdd_time(new Date());
            credits1.setArticle_type(2);
            credits1.setScore(ADD_SCORE_BY_READ);
            credits1.setInfo("阅读文章");
            creditsService.save(credits1);

        }

//        Credits credits = new Credits();
//        credits.setOpenid(openid);
//        credits.setAdd_by_openid(addByOpenid);
//        credits.setAid(fid);
//        // 2：快讯
//        credits.setArticle_type(2);
//        credits.setAdd_time(new Date());
//        credits.setScore(ADD_SCORE_BY_SHARE);
//        credits.setType(ADD_SCORE_TYPE_SHARE);
//        credits.setInfo("通过分享获得的积分:"+ADD_SCORE_BY_REAL_WX);
//        creditsService.save(credits);

        return flash;
    }

    @GetMapping("toReadFlashAddScore")
    public Flash toReadFlashAddScore(Integer uid, Integer fid, HttpSession session){
        Flash flash = flashService.findById(fid);

        //判断有没有读过该快讯
        if ((session.getAttribute("flashIdLit")) == null){
            LinkedList<Integer> flashIdLit = new LinkedList<Integer>();
            flashIdLit.add(fid);
            session.setAttribute("flashIdLit",flashIdLit);

        }else {
            if (!((LinkedList<Integer>)session.getAttribute("flashIdLit")).contains(fid)){
                flash.setReadnumber(flash.getReadnumber() + 1);
                flashService.update(flash);
            }
        }
//        return flash;

        Credits credits = new Credits();

        credits.setUid(uid);
        credits.setAid(fid);
        credits.setArticle_type(1);
        credits.setAdd_time(new Date());
//        credits.setType(1);
        credits.setScore(ADD_SCORE_BY_SHARE);
        credits.setType(ADD_SCORE_TYPE_SHARE);
        credits.setInfo("通过分享获得的积分:"+ADD_SCORE_BY_SHARE);
        creditsService.save(credits);

        return flash;
    }

    @GetMapping("toReadArticleAddScore")
    public Article toReadArticleAddScore(int uid, int aid){

        Article article = articleService.findById(aid);

        Credits credits = new Credits();

        credits.setUid(uid);
        credits.setAid(aid);
        credits.setArticle_type(2);
        credits.setAdd_time(new Date());
//        credits.setType(1);
        credits.setScore(ADD_SCORE_BY_SHARE);
        credits.setType(ADD_SCORE_TYPE_SHARE);
        credits.setInfo("通过分享获得的积分:"+ADD_SCORE_BY_SHARE);
        creditsService.save(credits);

        return article;
    }

    @GetMapping("toReadArticleAddScoreByToken")
    public Article toReadArticleAddScoreByToken(String token,String readUid){


        Condition condition = new Condition(Share.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andCondition("share_token = '" + token + "'");

        List<Share> shares = shareService.findByCondition(condition);



        Credits credits = new Credits();

        if (readUid != null && !readUid.equals("")){

            Credits creditsQu = new Credits();
            creditsQu.setAid(shares.get(0).getAid());
            creditsQu.setUid(shares.get(0).getUid());
            creditsQu.setAdd_by_uid(Integer.parseInt(readUid));

            List<Credits> creditsList = creditsService.articleHasReadByReadId(creditsQu);
            // 该用户已读过此文章
            if (creditsList.size() > 0){
                System.out.println("该用户已读过此文章");
            }else {

                // 分享者加积分
                credits.setUid(shares.get(0).getUid());
                credits.setAdd_by_uid(Integer.parseInt(readUid));
                credits.setAid(shares.get(0).getAid());
                credits.setArticle_type(1);
                credits.setAdd_time(new Date());
                credits.setScore(ADD_SCORE_BY_SHARE);
                credits.setType(ADD_SCORE_TYPE_SHARE);
                credits.setInfo("文章分享");

                creditsService.save(credits);
                System.out.println("通过分享获得的积分:"+ADD_SCORE_BY_SHARE);
                // 阅读者加积分
                Credits credits1 = new Credits();
                credits1.setArticle_type(1);
                credits1.setUid(Integer.parseInt(readUid));
                credits1.setAid(shares.get(0).getAid());
                credits1.setType(ADD_SCORE_TYPE_READ);
                credits1.setScore(ADD_SCORE_BY_READ);
                credits1.setInfo("阅读文章");
                credits1.setAdd_time(new Date());

                creditsService.save(credits1);
            }

        }
        else {
            credits.setUid(shares.get(0).getUid());
            credits.setAid(shares.get(0).getAid());
            credits.setArticle_type(1);
            credits.setAdd_time(new Date());
            credits.setScore(ADD_SCORE_BY_ANON);
            credits.setType(ADD_SCORE_TYPE_SHARE);
            credits.setInfo("匿名阅读");

            creditsService.save(credits);
            System.out.println("通过分享给匿名用户的积分:"+ADD_SCORE_BY_ANON);
        }



        Article article = articleService.findById(shares.get(0).getAid());
//        ModelAndView mov = new ModelAndView("front/shareDetailArticle");
//
//        mov.addObject("token",token);
//        mov.addObject("aid",article.getAid());
        return article;
    }

    @GetMapping("toReadFlashAddScoreByToken")
    public Flash toReadFlashAddScoreByToken(String token,String readUid){

        Condition condition = new Condition(Share.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andCondition("share_token = '" + token + "'");

        List<Share> shares = shareService.findByCondition(condition);


        Credits credits = new Credits();

        if (readUid != null && !readUid.equals("")){

            Credits creditsQu = new Credits();
            creditsQu.setAid(shares.get(0).getAid());
            creditsQu.setUid(shares.get(0).getUid());
            creditsQu.setAdd_by_uid(Integer.parseInt(readUid));

            List<Credits> creditsList = creditsService.articleHasReadByReadId(creditsQu);
            // 该用户已读过此文章
            if (creditsList.size() > 0){
                System.out.println("该用户已读过此文章");
            }else {

                // 分享者加积分
                credits.setUid(shares.get(0).getUid());
                credits.setAdd_by_uid(Integer.parseInt(readUid));
                credits.setAid(shares.get(0).getFid());
                credits.setArticle_type(2);
                credits.setAdd_time(new Date());
                credits.setScore(ADD_SCORE_BY_SHARE);
                credits.setType(ADD_SCORE_TYPE_SHARE);
                credits.setInfo("文章分享");
                // 阅读者加积分
                Credits credits1 = new Credits();
                credits1.setArticle_type(2);
                credits1.setUid(Integer.parseInt(readUid));
                credits1.setAid(shares.get(0).getAid());
                credits1.setType(ADD_SCORE_TYPE_READ);
                credits1.setScore(ADD_SCORE_BY_READ);
                credits1.setAdd_time(new Date());
                credits1.setInfo("阅读文章");

                creditsService.save(credits1);
            }

        }
//        else {
//            credits.setUid(shares.get(0).getUid());
//            credits.setAid(shares.get(0).getFid());
//            credits.setArticle_type(2);
//            credits.setAdd_time(new Date());
////        credits.setType(1);
//            credits.setScore(ADD_SCORE_BY_SHARE);
//            credits.setType(ADD_SCORE_TYPE_SHARE);
//            credits.setInfo("通过分享获得的积分:"+ADD_SCORE_BY_SHARE);
//        }
        credits.setUid(shares.get(0).getUid());
        credits.setAid(shares.get(0).getFid());
        credits.setArticle_type(2);
        credits.setAdd_time(new Date());
//        credits.setType(1);
        credits.setScore(ADD_SCORE_BY_ANON);
        credits.setType(ADD_SCORE_TYPE_SHARE);
        credits.setInfo("匿名阅读");

        creditsService.save(credits);

        Flash flash = flashService.findById(shares.get(0).getFid());

//        Article article = articleService.findById(shares.get(0).getAid());
//
//        ModelAndView mov = new ModelAndView("front/shareFlashDetail");
//        mov.addObject("fid",flash.getFid());
        return flash;
    }

    /**
     * 充值demo
     * @param level
     * @param uid
     * @return
     */
    @Transactional(rollbackFor=RuntimeException.class)
    @RequestMapping("/vipToAdd")
    public Result vipToAdd(int level,int uid){
        User user = userService.findById(uid);

        int cost = 0;

        int orginlevel = user.getVip_level();

        LevelScore levelScoreOrgin = levelScoreService.findByLevel(orginlevel);

        LevelScore levelScore = levelScoreService.findByLevel(level);

        if (orginlevel >= level){
            return ResultGenerator.errResult(2504,"参数错误当前等级高于目标等级！");
        }

        cost = levelScore.getCost()-levelScoreOrgin.getCost();

        Vip vip = new Vip();
        vip.setLevel(level);
        vip.setLeft_free_times(levelScore.getFree_times());
        vip.setAdd_time(new Date());
        vip.setType(1);
        vip.setUid(uid);
        vip.setCost(String.valueOf(cost));
        vip.setInfo("会员充值至vip"+level);
// 生成会员订单
        vipService.save(vip);
        // 得到最新用户会员订单
        List<Vip> vips = vipService.findTheLastByUid(uid);
        vips.get(0);

        Message message = new Message();
        message.setUid(uid);
        message.setType(1);
        message.setAdd_time(new Date());
        message.setCost(cost);
        message.setMessage_info("会员充值,交易编号"+vips.get(0).getId());
// 将积分变动信息写入消息表
        messageService.save(message);


        Credits credits = new Credits();
        credits.setUid(uid);
        credits.setType(ADD_SCORE_TYPE_VIP);
        credits.setScore(levelScore.getScore());
        credits.setInfo("会员充值");
        credits.setAdd_time(new Date());
        credits.setVip_level(level);

        // 更新积分
        creditsService.save(credits);


        // 更新用户

        user.setVip_state(1);
        user.setVip_id(vips.get(0).getId());
        user.setVip_level(level);
        user.setLeft_free_times(levelScore.getFree_times());

        userService.update(user);

//        Credits credits = new Credits();
//        credits.setVip_level(level);
//
//        LevelScore levelScore = levelScoreService.findByLevel(level);
//        credits.setScore(levelScore.getScore());
//        credits.setType(ADD_SCORE_TYPE_VIP);
//        credits.setAdd_time(new Date());
//        credits.setInfo("充值会员level："+ level +" 获得积分:" +levelScore.getScore());


        return ResultGenerator.successResult();
    }

    @Transactional(rollbackFor=RuntimeException.class)
    @GetMapping("/exchangeCreditToVip")
    public Result exchangeCreditToVip(int level,int uid){
        User user = userService.findById(uid);

        int costScore = 0;

        int orginlevel = user.getVip_level();

        LevelScore levelScoreOrgin = levelScoreService.findByLevel(orginlevel);

        LevelScore levelScore = levelScoreService.findByLevel(level);


        if (levelScore.getScore() >= levelScoreOrgin.getScore()){
            costScore = levelScore.getScore()-levelScoreOrgin.getScore();
        }else {
            return ResultGenerator.errResult(2504,"当前等级为：lv"+orginlevel+"不能兑换至：lv"+level);
        }

//        LevelScore levelScore = levelScoreService.findByLevel(level);

        Map creditMap =  creditsService.getUserAllScore(uid);
        int totalScore = Integer.parseInt(creditMap.get("totalScore").toString());

        if (totalScore >= costScore){
            Vip vip = new Vip();
            vip.setLevel(level);
            vip.setLeft_free_times(levelScore.getFree_times());
            vip.setAdd_time(new Date());
            vip.setType(2);
            vip.setUid(uid);
            vip.setInfo("积分兑换至vip"+level);
// 生成会员兑换订单
            vipService.save(vip);



            // 得到最新用户会员订单
            List<Vip> vips = vipService.findTheLastByUid(uid);
            vips.get(0);
// 积分变动消息
            Message message = new Message();
            message.setUid(uid);
            message.setType(2);
            message.setAdd_time(new Date());
//            message.setCost(levelScore.getCost());
            message.setCredit(-costScore);
            message.setMessage_info("积分兑换会员"+vips.get(0).getId());
// 将积分变动信息写入消息表
            messageService.save(message);


            Credits credits = new Credits();
            credits.setUid(uid);
            credits.setType(ADD_SCORE_TYPE_VIP);
            credits.setScore(-costScore);
            credits.setInfo("积分兑换会员");
            credits.setAdd_time(new Date());
            credits.setVip_level(level);

            // 更新积分
            creditsService.save(credits);

            // 更新用户
//            User user = userService.findById(uid);
            user.setVip_state(1);
            user.setVip_id(vips.get(0).getId());
            user.setVip_level(level);
            user.setLeft_free_times(levelScore.getFree_times());

            userService.update(user);


        }else {
            return ResultGenerator.errResult(2504,"积分不足");
        }

        return ResultGenerator.successResult();
    }

    /**
     * 获取用户分享得到的积分
     *
     * @param uid
     * @return
     */

    @RequestMapping("/userTotalShareScore")
    public Result userTotalShareScore(int uid) {

        Credits credits = new Credits();
        credits.setType(ADD_SCORE_TYPE_SHARE);
        credits.setUid(uid);
        Map map = creditsService.getScoreByUidAndType(credits);

        return ResultGenerator.successResult(map);
    }

    /**
     * 获取用户充值VIP所得积分
     * @param uid
     * @return
     */


    @RequestMapping("userTotalVipScore")
    public Result userTotalVipScore(int uid) {


        Credits credits = new Credits();
        credits.setUid(uid);
        credits.setType(Constants.ADD_SCORE_TYPE_VIP);
        Map map = creditsService.getScoreByUidAndType(credits);



        return ResultGenerator.successResult(map);
    }

    /**
     *  获取总积分
     * @return
     */

    @RequestMapping("getUserAllScore")
    public Result getUserAllScore(){

        User user = (User) SecurityUtils.getSubject().getPrincipal();
//
        User user1 = userService.findById(user.getId());
        Map map = new HashMap();
        map.put("user",user1);
        map.put("totalScore",(creditsService.getUserAllScore(user.getId()) == null)? 0 : creditsService.getUserAllScore(user.getId()));

        return ResultGenerator.successResult(map);
    }



    /**
     * 获取用户发布文章的积分 通常为负
     *
     * @param uid
     * @return
     */

    @RequestMapping("userTotalPublishScore")
    public Result userTotalPublishScore(int uid) {
        Credits credits = new Credits();
        credits.setUid(uid);
        credits.setType(ADD_SCORE_TYPE_PUBLISH);
        Map map = creditsService.getScoreByUidAndType(credits);

        return ResultGenerator.successResult(map);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Integer id) {
        creditsService.deleteById(id);
        return ResultGenerator.successResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Credits credits) {
        creditsService.update(credits);
        return ResultGenerator.successResult();
    }

    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Credits credits = creditsService.findById(id);
        return ResultGenerator.successResult(credits);
    }

    @GetMapping("/list")
    public Result list(PageBean<Credits> page) {
        PageHelper.startPage(page.getPageNum(), page.getSize(),"add_time desc");

        User user = (User) SecurityUtils.getSubject().getPrincipal();

        Condition condition = new Condition(Credits.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andCondition("uid = '" + user.getId() + "'");
//        List<BizAdmin> adminList = bizAdminService.findByCondition(condition);
//        creditsService.findByCondition()
        List<Credits> list = creditsService.findByCondition(condition);
        page.setList(list);
        return ResultGenerator.successResult(page);
    }

    @GetMapping("/listByUid")
    public Result listByUid(PageBean<Credits> page,Integer uid) {
        PageHelper.startPage(page.getPageNum(), page.getSize(),"add_time desc");

        Condition condition = new Condition(Credits.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andCondition("uid = '" + uid + "'");
        List<Credits> list = creditsService.findByCondition(condition);
        page.setList(list);
        return ResultGenerator.successResult(page);
    }

    @GetMapping("/listAll")
    public Result listAll(PageBean<Credits> page) {
        PageHelper.startPage(page.getPageNum(), page.getSize(),"add_time desc");

        List<Credits> list = creditsService.findAll();
        page.setList(list);
        return ResultGenerator.successResult(page);
    }

    @RequestMapping("/pcList")
    public PageInfo pcList(PageBean<Credits> page){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Condition condition = new Condition(Credits.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andCondition("uid = '" + user.getId() + "'");
        PageHelper.startPage(page.getPageNum(), page.getPageSize(),"add_time desc");
        List<Credits> list = creditsService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list,5);
        return pageInfo;
    }
}
