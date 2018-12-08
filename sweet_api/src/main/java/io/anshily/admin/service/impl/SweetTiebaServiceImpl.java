package io.anshily.admin.service.impl;

import io.anshily.admin.service.SweetTiebaService;
import io.anshily.model.SweetTieba;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

@Service
public class SweetTiebaServiceImpl implements SweetTiebaService {


    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Integer addSweetWall(SweetTieba sweetTieba) {
        mongoTemplate.save(sweetTieba);
        return 0;
    }

    @Override
    public List<SweetTieba> sweetList(Integer page) {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC,"created")).skip(10*page).limit(10);
        return mongoTemplate.find(query, SweetTieba.class);
    }

    @Override
    public Long getTotals() {

        Query query = new Query();
//        query.addCriteria(new Criteria());
//        GroupBy groupBy = new GroupBy("uin").initialDocument("{count:0}").reduceFunction("function (doc,pre){pre.count +=1 ;}");
//        GroupByResults groupByResults = mongoTemplate.group("tieba_emotions",groupBy,SweetTieba.class);
//        System.out.println(groupByResults.getRawResults()+" "+groupByResults.getCount()+" "+groupByResults.getKeys());

        System.out.println(mongoTemplate.count(query,SweetTieba.class));
        return mongoTemplate.count(query,SweetTieba.class);
    }

    @Override
    public void startCrowler() {

        int age = 1;

        System.out.println("进入爬虫");
        String cmdStr_linux = "python3 /Users/anshi/Desktop/resource/anshi/sweet/sweetCrawler/tieba.py"; //+ " " + age;
// 定义缓冲区、正常结果输出流、错误信息输出流
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        ByteArrayOutputStream outerrStream = new ByteArrayOutputStream();

        try {
            Process proc = Runtime.getRuntime().exec(cmdStr_linux);
            InputStream errStream = proc.getErrorStream();
            InputStream stream = proc.getInputStream();

            System.out.println("贴吧爬取中");

            // 流读取与写入
            int len = 0;
            while ((len = errStream.read(buffer)) != -1) {

                System.out.println((byte)len);

                System.out.println("错误流");
                outerrStream.write(buffer, 0, len);
            }
            while ((len = stream.read(buffer)) != -1) {

                System.out.println((byte)len);

                System.out.println("结果流");
                outStream.write(buffer, 0, len);
            }
            proc.waitFor();// 等待命令执行完成

            // 打印流信息
            System.out.println(outStream.toString());
            System.out.println(outerrStream.toString());

            // 将接收的输出结果转换为目标类型
//            Integer res = Integer.parseInt(outStream.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public SweetTieba detail(String tid) {
        Criteria criteria = new Criteria();
        criteria.andOperator(
                Criteria.where("tid").is(tid));
        Query query = new Query();
        query.addCriteria(criteria);
        return mongoTemplate.findOne(query,SweetTieba.class);
    }
}
