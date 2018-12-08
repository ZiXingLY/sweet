package io.anshily.front.controller;
import io.anshily.admin.service.CreditsService;
import io.anshily.base.core.Result;
import io.anshily.base.core.ResultGenerator;
import io.anshily.front.service.ArticleService;
import io.anshily.model.Article;
import io.anshily.model.Credits;
import io.anshily.model.Liker;
import io.anshily.front.service.LikerService;
import io.anshily.base.core.PageBean;
import com.github.pagehelper.PageHelper;
import io.anshily.model.User;
import io.anshily.base.core.Constants;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* Created by zaq on 2018/08/14.
*/
@RestController
@RequestMapping("/liker")
public class LikerController {
    @Resource
    private LikerService likerService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    CreditsService creditsService;

    @PostMapping("/add")
    public Result add(@RequestBody Liker liker) {
        likerService.save(liker);
        return ResultGenerator.successResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Integer id) {
        likerService.deleteById(id);
        return ResultGenerator.successResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Liker liker) {
        likerService.update(liker);
        return ResultGenerator.successResult();
    }

    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Liker liker = likerService.findById(id);
        return ResultGenerator.successResult(liker);
    }

    @GetMapping("/list")
    public Result list(PageBean<Liker> page) {
        PageHelper.startPage(page.getPageNum(),page.getSize());
        List<Liker> list = likerService.findAll();
        page.setList(list);
        return ResultGenerator.successResult(page);
    }


    /***
     * 新闻点赞
     */

    @RequestMapping("/clickLike")
    public Result clickLike(Integer id){
        User user = (User) SecurityUtils.getSubject().getPrincipal();


        Liker newLiker = new Liker();
        newLiker.setAid(id);
        newLiker.setUid(user.getId());
        likerService.save(newLiker);

        Credits credits = new Credits();
        credits.setUid(user.getId());
        credits.setType(Constants.ADD_SCORE_TYPE_LIKER);
        credits.setScore(1);
        credits.setInfo("点赞");
        credits.setAdd_time(new Date());

        // 更新积分
        creditsService.save(credits);
        Map map = likerService.articleLikerNumByAid(id);
        Article article = articleService.findById(id);
        System.out.println(map.get("totalLike"));
        article.setLikenum(Integer.parseInt(map.get("totalLike").toString()));
        articleService.update(article);

//        List<Liker> likers = likerService.findAllLikerById(id);
//        for (Liker liker : likers){
//            if (user.getId() != liker.getUid()){
//                return ResultGenerator.errResult();
//            }else {
//                Article article = articleService.findById(id);
//                article.setLikenum(article.getLikenum()+1);
//                articleService.update(article);
////                Liker newLiker = new Liker();
////                newLiker.setAid(id);
////                newLiker.setUid(user.getId());
////                likerService.save(newLiker);
//            }
//        }
        return ResultGenerator.successResult(map);
    }

    /***
     * 取消点赞
     */
    @RequestMapping("/cancelLike")
    public Result cancelLike(Integer aid){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        likerService.cancelLike(user.getId(),aid);
        return ResultGenerator.successResult();
    }

    /***
     *判断用户是否点赞
     * @param aid
     * @return
     */
    @RequestMapping("/isLike")
    public Result isLike(Integer aid){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Liker liker = new Liker();
        liker.setAid(aid);
        liker.setUid(user.getId());
        List<Liker> likers = likerService.findIsLike(liker);
        if (likers.size() > 0){
            return ResultGenerator.successResult("liked");
        }else {
            return ResultGenerator.successResult("unlike");
        }
    }
}
