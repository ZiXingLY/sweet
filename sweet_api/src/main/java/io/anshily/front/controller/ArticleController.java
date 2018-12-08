package io.anshily.front.controller;
import com.github.pagehelper.PageInfo;
import io.anshily.admin.service.UserService;
import io.anshily.base.core.Result;
import io.anshily.base.core.ResultGenerator;
import io.anshily.base.utils.UploadFile;
import io.anshily.front.service.LikerService;
import io.anshily.front.service.ArticleService;
import io.anshily.base.core.PageBean;
import com.github.pagehelper.PageHelper;
import io.anshily.model.Article;
import io.anshily.model.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
* Created by zaq on 2018/08/14.
*/
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @Resource
    private UserService userService;

    @Resource
    private LikerService likerService;

//    设置查询分页参数
    Integer pageLists = 5;

    @PostMapping("/add")
    public Result add(@RequestBody Article article) {
        articleService.save(article);
        return ResultGenerator.successResult();
    }

    @RequestMapping("/delete")
    public Result delete(@RequestBody Article article) {
        articleService.deleteById(article.getAid());
        return ResultGenerator.successResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Article article) {
        articleService.update(article);
        return ResultGenerator.successResult();
    }

    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Article article = articleService.findById(id);
        return ResultGenerator.successResult(article);
    }

    @GetMapping("/list")
    public Result list(PageBean<Article> page) {
        System.out.print(page.getPageNum()+"$$$$$$$"+page.getSize());
        PageHelper.startPage(page.getPageNum(),page.getSize());
        List<Article> list = articleService.findAll();
        page.setList(list);
        return ResultGenerator.successResult(page);
    }
    //根据分类获取文章通过分类如果没有传分类默认为新闻
    @RequestMapping("/getArticleByCategory")
    public List<Map<String, Object>> getArticleByCategory(@RequestParam(defaultValue = "1",name = "category")Integer category,
                                                   @RequestParam(defaultValue = "0",name = "pageStart")Integer pageStart){

        Integer start = pageLists * pageStart;
        List<Map<String, Object>> map = articleService.findArticleByCategory(category,start);
        return map;

    }

    /***
     * 获取热门文章
     * @return
     */
    @RequestMapping("/getHotArticle")
    public List<Article> getHotArticle(){
        return articleService.findHotArticle();
    }

    /***
     *阅读文章
     * @param id
     * @return
     */
    @RequestMapping("/readArticle")
    public Map<String,Object> readArticle(Integer id,HttpSession session){
        Article article = articleService.findById(id);
        if (article.getState() == 1){
            if (session.getAttribute("readList") == null){
                List<Integer> readList = new LinkedList<>();
                session.setAttribute("readList",readList);
            }
            List<Integer> readList = (List<Integer>) session.getAttribute("readList");
            if (!readList.contains(id)) {
                readList.add(id);
                session.setAttribute("readList", readList);
                article.setReadnumber(article.getReadnumber() + 1);
                articleService.update(article);
            }
        }
        List<Article> articles = articleService.readArticleById(id);
        User user = userService.findById(article.getUid());
        Integer index = articles.indexOf(article);
//        Article preArticle = new Article();
//        preArticle.setTitle("没有上一篇");
//        preArticle.setAid(-1);
//        Article nextArticle = new Article();
//        nextArticle.setTitle("没有下一篇");
//        nextArticle.setAid(-1);
//        nextArticle  = index - 1 >= 0 ? articles.get(index-1) : nextArticle;
//        preArticle = index + 1 < articles.size() ? articles.get(index + 1) : preArticle;
        Map<String,Object> uAa = new HashMap<>();
        uAa.put("user",user);
        uAa.put("article",article);
//        uAa.put("preArticle",preArticle);
//        uAa.put("nextArticle",nextArticle);
        return uAa;
    }


    // 已在admin中添加控制器

//    /***
//     * 写文章接口
//     * @return
//     */
//    @RequestMapping("/writeArticle")
//    public String writeArticle(){
//        return "front/editor";
//    }

    /***
     * 后台审核文章
     * @param state
     * @param id
     * @return
     */
    @RequestMapping("/sysChangeArticleState")
    public @ResponseBody Result changeArticleState(Integer state,Integer id){
        Article article = articleService.findById(id);
        article.setState(state);
        articleService.update(article);
        return ResultGenerator.successResult(article);
    }

    /***
     * 后台获取文章接口
     * @param id
     * @return
     */
    @RequestMapping("/sysReadArticle")
    public @ResponseBody Article readArticle(Integer id){
        Article article = articleService.findById(id);
        return article;
    }

    /***
     * 获取全部文章
     * @param page
     * @return
     */
    @RequestMapping("/myAllArticle")
    public PageInfo myAllArticle(PageBean<Article> page){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        List<Article> userArticles = articleService.findArticleByUserId(user.getId());
        PageInfo pageInfo = new PageInfo(userArticles,5);
        return pageInfo;
    }
    /***
     * /查找待审核文章
     * @param page
     * @return
     */
    @RequestMapping("/myArticleChecking")
    public @ResponseBody PageInfo myArticleChecking(PageBean<Article> page){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        List<Article> articles = articleService.findMyCheckingArticle(user.getId());
        PageInfo pageInfo = new PageInfo(articles,5);
        return pageInfo;
    }

    /***
     * 查找已通过文章
     * @param page
     * @return
     */
    @RequestMapping("/myArticleRelease")
    public @ResponseBody PageInfo myArticleRelease(PageBean<Article> page){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        List<Article> articles = articleService.findMyReleaseArticle(user.getId());
        PageInfo pageInfo = new PageInfo(articles,5);
        return pageInfo;
    }

    /***
     * 查找未通过文章
     * @param page
     * @return
     */
    @RequestMapping("/myArticleNotPass")
    public @ResponseBody PageInfo myArticleNotPass(PageBean<Article> page){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        List<Article> articles = articleService.findMyNotPassArticle(user.getId());
        PageInfo pageInfo = new PageInfo(articles,5);
        return pageInfo;
    }

    /***
     * //后台根据状态筛选文章
     * @param page
     * @param state
     * @return
     */
    @RequestMapping("/sysFindArticleByState")
    public @ResponseBody Result sysFindArticleByState(PageBean<Article> page,Integer state){
        PageHelper.startPage(page.getPageNum(),page.getSize());
        List<Article> articles = articleService.findArticleByState(state);
        page.setList(articles);
        return ResultGenerator.successResult(page);
    }

    /**
     *
     * @param page
     * @param state 1 审核中 2 通过 3 未通过
     * @return
     */
    @RequestMapping("/wxFindArticleByState")
    public @ResponseBody Result wxFindArticleByState(PageBean<Article> page,Integer state){
        PageHelper.startPage(page.getPageNum(),page.getSize());

        User user = (User) SecurityUtils.getSubject().getPrincipal();
//
//        Condition condition = new Condition(Article.class);
//        Example.Criteria criteria = condition.createCriteria();
//        criteria.andCondition("uid = '" + user.getId() + "'");

        int temp = state;
        switch (state){
            case 1:
                temp = 2;
                break;
            case 2:
                temp = 1;
                break;
            case 3:
                temp = 3;
                break;
        }


//        criteria.andCondition("state = '" +temp+ "'");

        List<Article> articles = articleService.wxFindArticleByState(user.getId(),temp);
//        List<Article> articles = articleService.findArticleByState(state);
        page.setList(articles);
        return ResultGenerator.successResult(page);
    }



    /***
     * 后台上传文章
     * @param article
     * @return
     */
    @RequestMapping("/sysUploadArticle")
    public @ResponseBody Result sysUploadArticle(@RequestBody Article article){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String url = UploadFile.uploadBase64(article.getCoverimg());
        article.setCoverimg("/../"+url.substring(2,url.length() - 2));
        article.setUid(1);
        article.setAddtime(new Date());
        article.setReadnumber(1000);
        article.setState(2);
        article.setIsbanner(0);
        article.setLikenum(0);
        articleService.save(article);
        return ResultGenerator.successResult(article);
    }

    @PostMapping("/uploadArticle")
    public @ResponseBody Result uploadArticle(@RequestBody Article article){

        User user = (User) SecurityUtils.getSubject().getPrincipal();
        User user1 = userService.findById(user.getId());

        if (user1.getLeft_free_times() <= 0){
            return ResultGenerator.errResult(2504,"剩余次数不足");
        }

        user1.setLeft_free_times(user1.getLeft_free_times()-1);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String url = UploadFile.uploadBase64(article.getCoverimg());
        article.setCoverimg("/../"+url.substring(2,url.length() - 2));

//        article.setAuthor(user.getNickname());
        article.setUid(user.getId());
        article.setAddtime(new Date());
        article.setReadnumber(1000);
        article.setState(2);
        article.setIsbanner(0);
        article.setLikenum(0);


        articleService.save(article);

        return ResultGenerator.successResult();

    }

    /***
     * 文章重新编辑
     * @param article
     * @return
     */
    @RequestMapping("/rebuild")
    public Result rebuild(Article article){
        articleService.update(article);
        return ResultGenerator.successResult();
    }

    /***
     * 后台上传图片
     * @param base64
     * @param rootUrl
     * @return
     */
    @RequestMapping("/uploadImage")
    public @ResponseBody String uploadImage(String base64,String rootUrl){
        String imgName = UploadFile.uploadBase64(base64);
        String url = "/../" + imgName.substring(2,imgName.length()-2);
        System.out.print(url);
        return url;
    }
}
