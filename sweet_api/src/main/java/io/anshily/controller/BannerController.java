package io.anshily.controller;
import io.anshily.base.core.PageBean;
import io.anshily.base.core.Result;
import io.anshily.base.core.ResultGenerator;
import io.anshily.model.Banner;
import io.anshily.service.BannerService;
import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by anshi on 2019/02/22.
*/
@RestController
@RequestMapping("/banner")
public class BannerController {
    @Resource
    private BannerService bannerService;

    @PostMapping("/add")
    public Result add(@RequestBody Banner banner) {
        bannerService.save(banner);
        return ResultGenerator.successResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Integer id) {
        bannerService.deleteById(id);
        return ResultGenerator.successResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Banner banner) {
        bannerService.update(banner);
        return ResultGenerator.successResult();
    }

    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Banner banner = bannerService.findById(id);
        return ResultGenerator.successResult(banner);
    }

    @GetMapping("/list")
    public Result list(PageBean<Banner> page) {
        PageHelper.startPage(page.getPageNum(),page.getSize());
        List<Banner> list = bannerService.findAll();
        page.setList(list);
        return ResultGenerator.successResult(page);
    }
}
