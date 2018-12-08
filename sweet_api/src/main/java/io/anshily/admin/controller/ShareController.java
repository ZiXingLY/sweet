package io.anshily.admin.controller;
import io.anshily.base.core.Constants;
import io.anshily.base.core.Result;
import io.anshily.base.core.ResultGenerator;
import io.anshily.base.utils.MyMD5;
import io.anshily.model.Share;
import io.anshily.admin.service.ShareService;
import io.anshily.base.core.PageBean;
import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* Created by anshi on 2018/08/20.
*/
@RestController
@RequestMapping("/share")
public class ShareController {
    @Resource
    private ShareService shareService;

    @PostMapping("/add")
    public Result add(@RequestBody Share share) {
        shareService.save(share);
        return ResultGenerator.successResult();
    }

    @RequestMapping("/genShareToken")
    public Result genShareToken(Share share){

        String token = null;

        if (share.getType() == 1){
            token = MyMD5.myMD5(share.getUid().toString()+share.getAid()+share.getType());
            share.setShare_token(token);
        }else if (share.getType() == 2){
            token = MyMD5.myMD5(share.getUid().toString()+share.getFid()+share.getType());
            share.setShare_token(token);
        }
//
//        Condition condition = new Condition(Share.class);
//        Example.Criteria criteria = condition.createCriteria();
//        criteria.andCondition("share_token = '" + token + "'");

        List<Share> shares = shareService.findSharesByToken(token);
//                shareService.findByCondition(condition);
        if (shares.size() > 0){
            share  = shares.get(0);
        }else {
            shareService.save(share);
        }

        return ResultGenerator.successResult(share);
    }
    @RequestMapping("/tokenToUAndA")
    public Result tokenToUAndA(String token){

        Condition condition = new Condition(Share.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andCondition("share_token = '" + token + "'");

        List<Share> shares = shareService.findByCondition(condition);

//        Share share = shareService.findBy("share_token",token);

//        System.out.println(share);

        return ResultGenerator.successResult(shares.get(0));
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Integer id) {
        shareService.deleteById(id);
        return ResultGenerator.successResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Share share) {
        shareService.update(share);
        return ResultGenerator.successResult();
    }

    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Share share = shareService.findById(id);
        return ResultGenerator.successResult(share);
    }

    @GetMapping("/list")
    public Result list(PageBean<Share> page) {
        PageHelper.startPage(page.getPageNum(),page.getSize());
        List<Share> list = shareService.findAll();
        page.setList(list);
        return ResultGenerator.successResult(page);
    }

    @GetMapping("getShareNumber")
    public Result getShareNumber(Integer id,Integer type){
        if (id == null){
            return ResultGenerator.errResult(Constants.CODE_ERR_TYPE_NULL);
        }if (type == null){
            return ResultGenerator.errResult(Constants.CODE_ERR_TYPE_NULL);
        }

        Map shareMap = null;
        if (type == 1){
            shareMap = shareService.getArticleShareNumber(id);
        }else if (type == 2){
            shareMap = shareService.getFlashShareNumber(id);
        }else {
        }

        if (shareMap != null){
            return ResultGenerator.successResult(shareMap);
        }else {
            return ResultGenerator.errResult(2504,"还没有人分享奥");
        }


//        return ResultGenerator.successResult();
    }
}
