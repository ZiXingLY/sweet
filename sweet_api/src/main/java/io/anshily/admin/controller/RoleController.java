package io.anshily.admin.controller;
import io.anshily.base.core.Result;
import io.anshily.base.core.ResultGenerator;
import io.anshily.model.Role;
import io.anshily.admin.service.RoleService;
import io.anshily.base.core.PageBean;
import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by hang on 2018/08/14.
*/
@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @PostMapping("/add")
    public Result add(@RequestBody Role role) {
        roleService.save(role);
        return ResultGenerator.successResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Integer id) {
        roleService.deleteById(id);
        return ResultGenerator.successResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Role role) {
        roleService.update(role);
        return ResultGenerator.successResult();
    }

    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Role role = roleService.findById(id);
        return ResultGenerator.successResult(role);
    }

    @GetMapping("/list")
    public Result list(PageBean<Role> page) {
        PageHelper.startPage(page.getPageNum(),page.getSize());
        List<Role> list = roleService.findAll();
        page.setList(list);
        return ResultGenerator.successResult(page);
    }
}
