package io.anshily.admin.controller;


import io.anshily.base.core.Result;
import io.anshily.base.core.ResultGenerator;
import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 启动ipfs服务之后连接到ipfs端口
 */
@RestController
@RequestMapping("/sys/sweet/promise")
public class PromiseIPFSController {



//    public IPFS ipfs = new IPFS("/ip4/127.0.0.1/tcp/5001");
//
//
//    public Result add(String promise){
//
//        if (promise == null && promise.isEmpty()){
//            return ResultGenerator.errResult(1001,"不能为空");
//        }
//        NamedStreamable.ByteArrayWrapper file = new NamedStreamable.ByteArrayWrapper(promise.getBytes());
//        try {
//            MerkleNode addResult = ipfs.add(file).get(0);
//
//            System.out.println(addResult);
//
//            addResult.hash.toString();
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ResultGenerator.successResult(e);
//        }
////        return addResult.hash.toString();
//        return ResultGenerator.successResult();
//    }
}
