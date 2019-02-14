package io.anshily.controller;
import io.anshily.base.core.PageBean;
import io.anshily.base.core.Result;
import io.anshily.base.core.ResultGenerator;
import io.anshily.model.Emotions;
import io.anshily.model.User;
import io.anshily.service.EmotionsService;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
* Created by anshi on 2019/02/12.
*/
@RestController
@RequestMapping("/emotions")
public class EmotionsController {
    @Resource
    private EmotionsService emotionsService;

    @PostMapping("/add")
    public Result add(@RequestBody Emotions emotions) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        System.out.println(user.toString());
        emotions.setUid(user.getId());
        emotions.setAdd_time(new Date());
        emotionsService.save(emotions);
        return ResultGenerator.successResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Integer id) {
        emotionsService.deleteById(id);
        return ResultGenerator.successResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Emotions emotions) {
        emotionsService.update(emotions);
        return ResultGenerator.successResult();
    }

    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Emotions emotions = emotionsService.findById(id);
        return ResultGenerator.successResult(emotions);
    }

    @GetMapping("/list")
    public Result list(PageBean<Emotions> page) {
        PageHelper.startPage(page.getPageNum(),page.getSize());
        List<Emotions> list = emotionsService.findAll();
        page.setList(list);
        return ResultGenerator.successResult(page);
    }
}
