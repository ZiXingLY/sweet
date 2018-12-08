package io.anshily.biz.controller;

import com.github.pagehelper.PageHelper;
import io.anshily.base.core.PageBean;
import io.anshily.base.core.Result;
import io.anshily.base.core.ResultGenerator;
import io.anshily.biz.model.BizRolesPermissions;
import io.anshily.biz.service.BizRolesPermissionsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by sxd on 2018/07/23.
*/
@RestController
@RequestMapping("/biz/roles/permissions")
public class BizRolesPermissionsController {
    @Resource
    private BizRolesPermissionsService bizRolesPermissionsService;

    @PostMapping("/add")
    public Result add(@RequestBody BizRolesPermissions bizRolesPermissions) {
        bizRolesPermissionsService.save(bizRolesPermissions);
        return ResultGenerator.successResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Integer id) {
        bizRolesPermissionsService.deleteById(id);
        return ResultGenerator.successResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody BizRolesPermissions bizRolesPermissions) {
        bizRolesPermissionsService.update(bizRolesPermissions);
        return ResultGenerator.successResult();
    }

    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        BizRolesPermissions bizRolesPermissions = bizRolesPermissionsService.findById(id);
        return ResultGenerator.successResult(bizRolesPermissions);
    }

    @GetMapping("/list")
    public Result list(PageBean<BizRolesPermissions> page) {
        PageHelper.startPage(page.getPageNum(),page.getSize());
        List<BizRolesPermissions> list = bizRolesPermissionsService.findAll();
        page.setList(list);
        return ResultGenerator.successResult(page);
    }
}
