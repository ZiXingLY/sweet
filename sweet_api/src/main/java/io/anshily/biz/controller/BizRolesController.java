package io.anshily.biz.controller;

import com.github.pagehelper.PageHelper;
import io.anshily.base.core.PageBean;
import io.anshily.base.core.Result;
import io.anshily.base.core.ResultGenerator;
import io.anshily.biz.model.BizRoles;
import io.anshily.biz.service.BizRolesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by sxd on 2018/07/23.
*/
@RestController
@RequestMapping("/sys/roles")
public class BizRolesController {
    @Resource
    private BizRolesService bizRolesService;

    @PostMapping("/add")
    public Result add(@RequestBody BizRoles bizRoles) {
        bizRolesService.save(bizRoles);
        return ResultGenerator.successResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Integer id) {
        bizRolesService.deleteById(id);
        return ResultGenerator.successResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody BizRoles bizRoles) {
        bizRolesService.update(bizRoles);
        return ResultGenerator.successResult();
    }

    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        BizRoles bizRoles = bizRolesService.findById(id);
        return ResultGenerator.successResult(bizRoles);
    }

    @GetMapping("/list")
    public Result list(PageBean<BizRoles> page) {
        PageHelper.startPage(page.getPageNum(),page.getSize());
        List<BizRoles> list = bizRolesService.findAll();
        page.setList(list);
        return ResultGenerator.successResult(page);
    }
}
