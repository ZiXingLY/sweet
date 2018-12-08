package io.anshily.admin.controller;
import com.github.pagehelper.PageInfo;
import io.anshily.base.core.Result;
import io.anshily.base.core.ResultGenerator;
import io.anshily.model.User;
import io.anshily.model.Vip;
import io.anshily.admin.service.VipService;
import io.anshily.base.core.PageBean;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by anshi on 2018/08/22.
*/
@RestController
@RequestMapping("/vip")
public class VipController {
    @Resource
    private VipService vipService;

    @PostMapping("/add")
    public Result add(@RequestBody Vip vip) {
        vipService.save(vip);
        return ResultGenerator.successResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Integer id) {
        vipService.deleteById(id);
        return ResultGenerator.successResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Vip vip) {
        vipService.update(vip);
        return ResultGenerator.successResult();
    }

    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Vip vip = vipService.findById(id);
        return ResultGenerator.successResult(vip);
    }

    @GetMapping("/list")
    public Result list(PageBean<Vip> page) {
        PageHelper.startPage(page.getPageNum(),page.getSize(),"add_time desc");
        List<Vip> list = vipService.findAll();
        page.setList(list);
        return ResultGenerator.successResult(page);
    }

    @RequestMapping("/pcList")
    public PageInfo pcList(PageBean<Vip> page){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Condition condition = new Condition(Vip.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andCondition("uid = '" + user.getId() + "'");
        criteria.andCondition("type = '" + 1 + "'");
        PageHelper.startPage(page.getPageNum(), page.getPageSize(),"add_time desc");
        List<Vip> list = vipService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list,5);
        return pageInfo;
    }
}
