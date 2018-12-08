package io.anshily.admin.controller;
import com.github.pagehelper.PageInfo;
import io.anshily.admin.service.CreditsService;
import io.anshily.base.core.Result;
import io.anshily.base.core.ResultGenerator;
import io.anshily.model.Message;
import io.anshily.admin.service.MessageService;
import io.anshily.base.core.PageBean;
import com.github.pagehelper.PageHelper;
import io.anshily.model.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by anshi on 2018/08/22.
*/
@RestController
@RequestMapping("/message")
public class MessageController {
    @Resource
    private MessageService messageService;

    @Autowired
    CreditsService creditsService;

    @PostMapping("/add")
    public Result add(@RequestBody Message message) {
        messageService.save(message);
        return ResultGenerator.successResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Integer id) {
        messageService.deleteById(id);
        return ResultGenerator.successResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Message message) {
        messageService.update(message);
        return ResultGenerator.successResult();
    }

    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Message message = messageService.findById(id);
        return ResultGenerator.successResult(message);
    }

    @GetMapping("/list")
    public Result list(PageBean<Message> page) {
        PageHelper.startPage(page.getPageNum(), page.getSize(),"add_time desc");

        User user = (User) SecurityUtils.getSubject().getPrincipal();

        Condition condition = new Condition(Message.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andCondition("uid = '" + user.getId() + "'");
        List<Message> list = messageService.findByCondition(condition);
        page.setList(list);
        return ResultGenerator.successResult(page);
    }

    @RequestMapping("/pcList")
    public PageInfo pcList(PageBean<Message> page){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Condition condition = new Condition(Message.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andCondition("uid = '" + user.getId() + "'");
        PageHelper.startPage(page.getPageNum(), page.getPageSize(),"add_time desc");
        List<Message> list = messageService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list,5);
        return pageInfo;
    }

//    @GetMapping("/list")
//    public Result list(PageBean<Message> page) {
//        PageHelper.startPage(page.getPageNum(),page.getSize());
//        List<Message> list = messageService.findAll();
//        page.setList(list);
//        return ResultGenerator.successResult(page);
//    }
}
