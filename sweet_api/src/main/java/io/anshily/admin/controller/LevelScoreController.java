package io.anshily.admin.controller;
import io.anshily.base.core.Result;
import io.anshily.base.core.ResultGenerator;
import io.anshily.model.LevelScore;
import io.anshily.admin.service.LevelScoreService;
import io.anshily.base.core.PageBean;
import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by zaq on 2018/08/15.
*/
@RestController
@RequestMapping("/level/score")
public class LevelScoreController {
    @Resource
    private LevelScoreService levelScoreService;

    @PostMapping("/add")
    public Result add(@RequestBody LevelScore levelScore) {
        levelScoreService.save(levelScore);
        return ResultGenerator.successResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Integer id) {
        levelScoreService.deleteById(id);
        return ResultGenerator.successResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody LevelScore levelScore) {
        levelScoreService.update(levelScore);
        return ResultGenerator.successResult();
    }

    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        LevelScore levelScore = levelScoreService.findById(id);
        return ResultGenerator.successResult(levelScore);
    }

    @GetMapping("/list")
    public Result list(PageBean<LevelScore> page) {
        PageHelper.startPage(page.getPageNum(),page.getSize());
        List<LevelScore> list = levelScoreService.findAll();
        page.setList(list);
        return ResultGenerator.successResult(page);
    }
}
