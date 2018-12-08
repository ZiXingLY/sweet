package io.anshily.admin.schedule;

import io.anshily.admin.service.LevelScoreService;
import io.anshily.admin.service.MessageService;
import io.anshily.admin.service.UserService;
import io.anshily.model.LevelScore;
import io.anshily.model.Message;
import io.anshily.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class CreditsResetService {

    @Autowired
    UserService userService;

    @Autowired
    LevelScoreService levelScoreService;

    @Autowired
    MessageService messageService;

    /**
     * 每月1日将免费发文条数重置
     */
    @Scheduled(cron = "0 0 0 1 * ?" )
    public void creditsResetService(){

        User user1 = null;
        List<LevelScore> levelScoreServiceAll= levelScoreService.findAll();
        Map<Integer,Integer> levelMap = new HashMap<Integer, Integer>();
        for (int i = 0;i < levelScoreServiceAll.size(); i++){
            levelMap.put(levelScoreServiceAll.get(i).getLevel(),levelScoreServiceAll.get(i).getFree_times());
        }
        List<User> userList = userService.getVipUserList();

        Iterator<User> it = userList.iterator();
        while(it.hasNext()){
            user1 = it.next();
            user1.setLeft_free_times(levelMap.get(user1.getVip_level()));
            userService.update(user1);


            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(currentTime);

            Message message = new Message();
            message.setUid(user1.getId());
            message.setType(4);
            message.setAdd_time(currentTime);
            message.setMessage_info("您的积分已于"+dateString+"重置为"+levelMap.get(user1.getVip_level()));

            messageService.save(message);
        }

//        Iterator<Map.Entry<Integer, Integer>> iterator1 = map.entrySet().iterator();
//        Map.Entry<Integer, Integer> entry;
//        while (iterator1.hasNext()) {
//            entry = iterator1.next();
//            entry.getKey();
//            entry.getValue();
//        }
//        for (int i = 0;i<userList.size();i++){
//            user1=userList.get(i);
//            user1.setLeft_free_times(levelMap.get(user1.getVip_level()));
//            userService.update(user1);
//
//
//            Date currentTime = new Date();
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String dateString = formatter.format(currentTime);
//
//            Message message = new Message();
//            message.setUid(user1.getId());
//            message.setType(4);
//            message.setAdd_time(currentTime);
//            message.setMessage_info("您的积分已于"+dateString+"重置为"+levelMap.get(user1.getVip_level()));
//
//            messageService.save(message);
//
//        }

    }

//    @Scheduled(cron = "0/20 * * * * ?" )
//    public void creditsResetServicetest(){
//
//        User user1 = null;
//        List<LevelScore> levelScoreServiceAll= levelScoreService.findAll();
//        Map<Integer,Integer> levelMap = new HashMap<Integer, Integer>();
//        for (int i = 0;i < levelScoreServiceAll.size(); i++){
//            levelMap.put(levelScoreServiceAll.get(i).getLevel(),levelScoreServiceAll.get(i).getFree_times());
//        }
//        List<User> userList = userService.getVipUserList();
//        for (int i = 0;i<userList.size();i++){
//            user1=userList.get(i);
//            user1.setLeft_free_times(levelMap.get(user1.getVip_level()));
//            userService.update(user1);
//            System.out.println(user1);
//
//            Message message = new Message();
//            message.setUid(user1.getId());
//            message.setType(4);
//            message.setAdd_time(new Date());
//            message.setMessage_info("您的积分已于"+new Date().toString()+"重置为"+levelMap.get(user1.getVip_level()));
//
//            messageService.save(message);
//        }
//
//    }
}
