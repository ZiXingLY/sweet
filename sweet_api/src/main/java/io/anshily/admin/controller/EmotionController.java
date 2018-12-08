package io.anshily.admin.controller;
import io.anshily.base.core.Result;
import io.anshily.base.core.ResultGenerator;
import io.anshily.model.Emotion;
import io.anshily.admin.service.EmotionService;
import io.anshily.base.core.PageBean;
import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by anshi on 2018/09/05.
*/
@RestController
@RequestMapping("/emotion")
public class EmotionController {
    @Resource
    private EmotionService emotionService;

    @PostMapping("/add")
    public Result add(@RequestBody Emotion emotion) {
        emotionService.save(emotion);
        return ResultGenerator.successResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Integer id) {
        emotionService.deleteById(id);
        return ResultGenerator.successResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Emotion emotion) {
        emotionService.update(emotion);
        return ResultGenerator.successResult();
    }

    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Emotion emotion = emotionService.findById(id);
        return ResultGenerator.successResult(emotion);
    }

    @GetMapping("/list")
    public Result list(PageBean<Emotion> page) {
        PageHelper.startPage(page.getPageNum(),page.getSize(),"created desc");
        List<Emotion> list = emotionService.findAll();

        list.forEach(item -> item.setCreated(item.getCreated()*1000));

//        Emotion[] emotions = (Emotion[]) list.toArray();
//
//        for (int i = 0; i< list.size();i++){
//
//            Emotion emotion = list.get(i);
//            emotion.setCreated(emotion.getCreated()*1000);
//            list.set(i,emotion);
////            emotions[i].setCreated_time(emotions[i].getCreated_time()+"0");
//        }
//        list.forEach(item -> {item.setCreated_time(item.getCreated_time())});
        page.setList(list);
        return ResultGenerator.successResult(page);
    }
}
