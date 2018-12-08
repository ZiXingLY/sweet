package io.anshily.admin.controller;

import io.anshily.admin.service.SweetTiebaService;
import io.anshily.base.core.Result;
import io.anshily.base.core.ResultGenerator;
import io.anshily.model.SweetTieba;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/sweet/tieba")
public class SweetTiebaController {

    @Autowired
    SweetTiebaService sweetTiebaService;

    @RequestMapping("/list")
    public Result list(Integer page){

        List<SweetTieba> list = sweetTiebaService.sweetList(page);
        Map<String,Object> map = new HashMap<>();
        map.put("list",list);
        map.put("total",sweetTiebaService.getTotals());
        ResultGenerator.successResult();
        return ResultGenerator.successResult(map);
    }

    @RequestMapping("/count")
    public Result count(){

        return ResultGenerator.successResult(sweetTiebaService.getTotals());
    }

    @RequestMapping("/startCrowler")
    public Result startCrowler(){

        sweetTiebaService.startCrowler();
        return ResultGenerator.successResult();
    }

    @RequestMapping("/detail")
    public Result detail(String tid){
        return ResultGenerator.successResult(sweetTiebaService.detail(tid));
    }
}
