package io.anshily.controller;
import io.anshily.base.core.Result;
import io.anshily.base.core.ResultGenerator;
import io.anshily.model.Cet;
import io.anshily.service.CetService;
import io.anshily.base.core.PageBean;
import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
* Created by anshi on 2018/11/23.
*/
@RestController
@RequestMapping("/sys/cet")
public class CetController {
    @Resource
    private CetService cetService;

    @PostMapping("/add")
    public Result add(@RequestBody Cet cet) {
        cet.setAdd_time(new Date().toString());
        cetService.save(cet);
        return ResultGenerator.successResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Integer id) {
        cetService.deleteById(id);
        return ResultGenerator.successResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Cet cet) {
        cetService.update(cet);
        return ResultGenerator.successResult();
    }

    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Cet cet = cetService.findById(id);
        return ResultGenerator.successResult(cet);
    }

    @GetMapping("/list")
    public Result list(PageBean<Cet> page) {
        PageHelper.startPage(page.getPageNum(),page.getSize());
        List<Cet> list = cetService.findAll();
        page.setList(list);
        return ResultGenerator.successResult(page);
    }
}
