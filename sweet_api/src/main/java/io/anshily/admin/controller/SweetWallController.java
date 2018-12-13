package io.anshily.admin.controller;


import io.anshily.admin.service.SweetWallService;
import io.anshily.base.core.Result;
import io.anshily.base.core.ResultGenerator;
import io.anshily.model.SweetWall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/sweet/wall")
public class SweetWallController {

    @Autowired
    SweetWallService sweetWallService;

    @RequestMapping("/save")
    public void save(){

        SweetWall sweetWall = new SweetWall();
        sweetWall.setTid("hang");
        sweetWall.setContent("content");
        sweetWall.setCreated("1537000000");
        sweetWallService.addSweetWall(sweetWall);
        System.out.println("success!");
    }

    @RequestMapping("/list")
    public Result list(Integer page){
        System.out.println(page);
        List<SweetWall> list = sweetWallService.sweetList(page);

        for (SweetWall sw:list
             ) {

            System.out.println(sw);

        }
        Map<String,Object> map = new HashMap<>();
        map.put("list",list);
        map.put("total",sweetWallService.getTotals());
        ResultGenerator.successResult();
        return ResultGenerator.successResult(map);
//        System.out.println("succeed!");
//        return ResultGenerator.successResult(list);
    }

    @RequestMapping("listByConditions")
    public Result listByConditions(@RequestParam(required = false, defaultValue = "2425936375") String uin,@RequestParam(required = false, defaultValue = "0") Integer page){

        System.out.println(uin);
        System.out.println(page);

        List<SweetWall> list = sweetWallService.listByConditions(uin,page);

        System.out.println(list);

        Map<String,Object> map = new HashMap<>();
        map.put("list",list);
        map.put("total",sweetWallService.getTotals());
        return ResultGenerator.successResult(map);
    }

    @RequestMapping("/count")
    public Result count(){
        return ResultGenerator.successResult(sweetWallService.getTotals());
    }

    @RequestMapping("/startCrowler")
    public Result startCrowler(){

        sweetWallService.startCrowler();
        return ResultGenerator.successResult();
    }

    @RequestMapping("/detail")
    public Result detail(String tid){
        if (tid == null || "".equals(tid)){
            return ResultGenerator.errResult(500,"日志ID不能为空！");
        }
        return ResultGenerator.successResult(sweetWallService.detail(tid));
    }
}
