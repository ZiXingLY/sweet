package io.anshily.admin.service.impl;

import io.anshily.admin.service.SweetWallService;
import io.anshily.model.SweetWall;
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
public class SweetWallServiceImpl implements SweetWallService {


    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public int addSweetWall(SweetWall sweetWall) {

//        DBObject document = new BasicDBObject();
        mongoTemplate.save(sweetWall);
        return 0;
    }

    @Override
    public List<SweetWall> sweetList(Integer page) {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC,"created")).skip(10*page).limit(10);
        return mongoTemplate.find(query, SweetWall.class);
//        return mongoTemplate.find(Query.query(''),SweetWall);
    }

    @Override
    public Long getTotals() {
        Query query = new Query();

        return mongoTemplate.count(query,SweetWall.class);
    }

    @Override
    public void startCrowler() {

//        int age;
// 接收python脚本的输出结果
//        int result;
// 若Python脚本在windows主机中
        int age = 1;
//        String cmdStr_windows = "python D:\\demo.py"+ " " + age;
// 若Python脚本在Linux主机中
        System.out.println("进入爬虫");
        String cmdStr_linux = "python3 /Users/anshi/Desktop/resource/anshi/sweet/sweetCrawler/biaobai.py"; //+ " " + age;
// 定义缓冲区、正常结果输出流、错误信息输出流
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        ByteArrayOutputStream outerrStream = new ByteArrayOutputStream();

        try {
            Process proc = Runtime.getRuntime().exec(cmdStr_linux);
            InputStream errStream = proc.getErrorStream();
            InputStream stream = proc.getInputStream();

            System.out.println("爬取中");

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
    public SweetWall detail(String tid) {
        Criteria criteria = new Criteria();
        criteria.andOperator(
                Criteria.where("tid").is(tid));
        Query query = new Query();
        query.addCriteria(criteria);
        return mongoTemplate.findOne(query,SweetWall.class);
    }
}
