package io.anshily.front.controller;

import io.anshily.admin.service.UserService;
import io.anshily.base.core.Result;
import io.anshily.base.core.ResultGenerator;
import io.anshily.base.utils.UploadFile;
import io.anshily.front.service.ArticleService;
import io.anshily.front.service.FlashService;
import io.anshily.front.service.CategoryService;
import io.anshily.front.service.LikerService;
import io.anshily.model.Article;
import io.anshily.service.BannerService;
import io.anshily.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    ArticleService articleService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    FlashService flashService;
    @Autowired
    LikerService likerService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    UserService userService;
    @Autowired
    BannerService bannerService;
    //进入首页
    @RequestMapping("/")
    public String index(){
        return "front/index";
    }

    //获取首页轮播数据
    @RequestMapping("/getBanner")
    public @ResponseBody List<Article> getBanner(){
        List<Article> banners = articleService.findAllBanner();
        return banners;
    }

    @RequestMapping("/uploadImage")
    public @ResponseBody String uploadImage(String base64,String rootUrl){
        String imgName = UploadFile.uploadBase64(base64);
        String url = "/../" + imgName.substring(2,imgName.length()-2);
        System.out.print(url);
        return url;
    }

    @RequestMapping("/wx/index")
    public @ResponseBody Result xwIndex(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("goods",goodsService.findAll());
        map.put("user",userService.findAll());
        map.put("banner",bannerService.findAll());
        return ResultGenerator.successResult(map);
    }

//    @RequestMapping("/setImage")
//    public void setImage(Integer id, HttpServletResponse response, @RequestParam(required = false) String erCode) throws IOException {
//        Flash flash = flashService.findById(id);
//        BufferedImage image = GenerateImage.getImage(flash.getTitle(),flash.getContent(),"../uploads/20180828/dc67b7b5-a4c6-451b-b010-1d392efd4c7a.png");
//        ImageIO.write(image,"png",response.getOutputStream());
//        System.out.println("二维码是:  "+erCode);
//    }
}
