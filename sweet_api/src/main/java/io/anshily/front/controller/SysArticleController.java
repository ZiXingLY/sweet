package io.anshily.front.controller;

import io.anshily.admin.service.MessageService;
import io.anshily.admin.service.UserService;
import io.anshily.base.core.Result;
import io.anshily.base.core.ResultGenerator;
import io.anshily.base.utils.UploadFile;
import io.anshily.front.service.ArticleService;
import io.anshily.model.Article;
import io.anshily.model.Message;
import io.anshily.model.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/power/article")
public class SysArticleController {
    @Resource
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Autowired
    MessageService messageService;

    /***
     * 前端上传文章
     * @param article
     * @return
     */
    @RequestMapping("/uploadArticle")
    public @ResponseBody
    Result uploadArticle(@RequestBody Article article){
        User user = (User) SecurityUtils.getSubject().getPrincipal();


        User user1 = userService.findById(user.getId());

        if (user.getId() != 33){

            if (user1.getLeft_free_times() <= 0){
                return ResultGenerator.errResult(2504,"本月发布文章剩余次数"+user1.getLeft_free_times()+"发布失败");
            }

            user1.setLeft_free_times(user1.getLeft_free_times()-1);
            userService.update(user1);

            Message message = new Message();
            message.setUid(user.getId());
            message.setType(2);
            message.setAdd_time(new Date());
//        message.setCost(levelScore.getCost());
            message.setMessage_info("发表文章本月免费发文次数减一");
// 将积分变动信息写入消息表
            messageService.save(message);


            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String addtime = format.format(new Date());
            String url = UploadFile.uploadBase64(article.getCoverimg());
            article.setCoverimg("/../"+url.substring(2,url.length() - 2));
            article.setUid(user.getId());
            article.setAddtime(new Date());
            article.setReadnumber(0);
            article.setState(2);
            article.setIsbanner(0);
            article.setLikenum(0);
            articleService.save(article);

        }else {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String addtime = format.format(new Date());
            String url = UploadFile.uploadBase64(article.getCoverimg());
            article.setCoverimg("/../"+url.substring(2,url.length() - 2));
            article.setUid(user.getId());
            article.setAddtime(new Date());
            article.setReadnumber(0);
            article.setState(1);
            article.setIsbanner(0);
            article.setLikenum(0);
            articleService.save(article);

        }


        return ResultGenerator.successResult(article);
    }


    @RequestMapping("/reuploadArticle")
    public @ResponseBody
    Result reuploadArticle(@RequestBody Article article){
        User user = (User) SecurityUtils.getSubject().getPrincipal();

//
//        User user1 = userService.findById(user.getId());
//
//        if (user1.getLeft_free_times() <= 0){
//            return ResultGenerator.errResult(2504,"本月发布文章剩余次数"+user1.getLeft_free_times()+"发布失败");
//        }
//
//        user1.setLeft_free_times(user1.getLeft_free_times()-1);
//        userService.update(user1);

//        Message message = new Message();
//        message.setUid(user.getId());
//        message.setType(2);
//        message.setAdd_time(new Date());
////        message.setCost(levelScore.getCost());
//        message.setMessage_info("发表文章本月免费发文次数减一");
//// 将积分变动信息写入消息表
//        messageService.save(message);


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String addtime = format.format(new Date());
        String url = UploadFile.uploadBase64(article.getCoverimg());
        article.setCoverimg("/../"+url.substring(2,url.length() - 2));
        article.setUid(user.getId());
        article.setAddtime(new Date());
        article.setReadnumber(0);
        article.setState(2);
        article.setIsbanner(0);
        article.setLikenum(0);
        articleService.update(article);
//        articleService.save(article);
        return ResultGenerator.successResult(article);
    }
}
