package io.anshily.controller;
import io.anshily.base.core.Result;
import io.anshily.base.core.ResultGenerator;
import io.anshily.model.Promise;
import io.anshily.service.PromiseService;
import io.anshily.base.core.PageBean;
import com.github.pagehelper.PageHelper;
import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
* Created by anshi on 2018/11/22.
*/
@RestController
@RequestMapping("/sys/promise")
public class PromiseController {

//    IPFS ipfs = new IPFS("/ip4/127.0.0.1/tcp/5001");
//
//    @Resource
//    private PromiseService promiseService;
//
//    @PostMapping("/add")
//    public Result add(@RequestBody Promise promise) {
//
//        if (promise == null || promise.getPromise().isEmpty()){
//            return ResultGenerator.errResult(1001,"不能为空");
//        }
//        NamedStreamable.ByteArrayWrapper file = new NamedStreamable.ByteArrayWrapper(promise.getPromise().getBytes());
//        try {
//            MerkleNode addResult = ipfs.add(file).get(0);
//
//            System.out.println(addResult);
//            promise.setIpfs_hash(addResult.hash.toString());
//            promise.setAdd_time(new Date().toString());
//            promiseService.save(promise);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ResultGenerator.errResult();
//        }
////        return addResult.hash.toString();
//        return ResultGenerator.successResult();
////        return ResultGenerator.successResult();
//    }

//    @PostMapping("/delete")
//    public Result delete(@RequestBody Integer id) {
//        promiseService.deleteById(id);
//        return ResultGenerator.successResult();
//    }
//
//    @PostMapping("/update")
//    public Result update(@RequestBody Promise promise) {
//        promiseService.update(promise);
//        return ResultGenerator.successResult();
//    }
//
//    @GetMapping("/detail")
//    public Result detail(@RequestParam Integer id) {
//        Promise promise = promiseService.findById(id);
//
//        byte[] data = new byte[0];
//        try {
//            data = ipfs.cat(Multihash.fromBase58(promise.getIpfs_hash()));
//
//            return ResultGenerator.successResult(new String(data));
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ResultGenerator.errResult();
//        }
////        return ResultGenerator.successResult(promise);
//    }
//
//    @GetMapping("/list")
//    public Result list(PageBean<Promise> page) {
//        PageHelper.startPage(page.getPageNum(),page.getSize());
//        List<Promise> list = promiseService.findAll();
//        page.setList(list);
//        return ResultGenerator.successResult(page);
//    }
}
