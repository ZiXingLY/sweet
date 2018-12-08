package io.anshily.admin.controller;
import io.anshily.base.core.Result;
import io.anshily.base.core.ResultGenerator;
import io.anshily.model.UserRole;
import io.anshily.admin.service.UserRoleService;
import io.anshily.base.core.PageBean;
import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by hang on 2018/08/14.
*/
@RestController
@RequestMapping("/user/role")
public class UserRoleController {
    @Resource
    private UserRoleService userRoleService;

    @PostMapping("/add")
    public Result add(@RequestBody UserRole userRole) {
        userRoleService.save(userRole);
        return ResultGenerator.successResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Integer id) {
        userRoleService.deleteById(id);
        return ResultGenerator.successResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody UserRole userRole) {
        userRoleService.update(userRole);
        return ResultGenerator.successResult();
    }

    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        UserRole userRole = userRoleService.findById(id);
        return ResultGenerator.successResult(userRole);
    }

    @GetMapping("/list")
    public Result list(PageBean<UserRole> page) {
        PageHelper.startPage(page.getPageNum(),page.getSize());
        List<UserRole> list = userRoleService.findAll();
        page.setList(list);
        return ResultGenerator.successResult(page);
    }
}
