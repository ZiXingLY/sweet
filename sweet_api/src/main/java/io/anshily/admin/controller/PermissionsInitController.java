package io.anshily.admin.controller;
import io.anshily.base.core.Result;
import io.anshily.base.core.ResultGenerator;
import io.anshily.model.PermissionsInit;
import io.anshily.admin.service.PermissionsInitService;
import io.anshily.base.core.PageBean;
import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by hang on 2018/08/14.
*/
@RestController
@RequestMapping("/permissions/init")
public class PermissionsInitController {
    @Resource
    private PermissionsInitService permissionsInitService;

    @PostMapping("/add")
    public Result add(@RequestBody PermissionsInit permissionsInit) {
        permissionsInitService.save(permissionsInit);
        return ResultGenerator.successResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Integer id) {
        permissionsInitService.deleteById(id);
        return ResultGenerator.successResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody PermissionsInit permissionsInit) {
        permissionsInitService.update(permissionsInit);
        return ResultGenerator.successResult();
    }

    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        PermissionsInit permissionsInit = permissionsInitService.findById(id);
        return ResultGenerator.successResult(permissionsInit);
    }

    @GetMapping("/list")
    public Result list(PageBean<PermissionsInit> page) {
        PageHelper.startPage(page.getPageNum(),page.getSize());
        List<PermissionsInit> list = permissionsInitService.findAll();
        page.setList(list);
        return ResultGenerator.successResult(page);
    }
}
