package io.anshily.front.controller;

import com.github.pagehelper.PageInfo;
import io.anshily.base.core.Result;
import io.anshily.base.core.ResultGenerator;
import io.anshily.model.Flash;
import io.anshily.front.service.FlashService;
import io.anshily.base.core.PageBean;
import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zaq on 2018/08/14.
 */
@RestController
@RequestMapping("/flash")
public class FlashController {
    @Resource
    private FlashService flashService;

    //分页大小
    Integer pageSize = 10;

    @PostMapping("/add")
    public Result add(@RequestBody Flash flash) {
        flashService.save(flash);
        return ResultGenerator.successResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Flash flash) {
        flashService.deleteById(flash.getFid());
        return ResultGenerator.successResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Flash flash) {
        flashService.update(flash);
        return ResultGenerator.successResult();
    }

    @RequestMapping("/detail")
    public Result detail(Flash flash) {
        Flash sf = flashService.findById(flash.getFid());
        return ResultGenerator.successResult(sf);
    }

    @RequestMapping("/ad")
    public Result ad(@RequestBody Flash flash) {
        return ResultGenerator.successResult();
    }

    @GetMapping("/list")
    public PageInfo list(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, pageSize);
        List<Flash> list = flashService.findAll();
        PageInfo pageInfo = new PageInfo(list, pageSize);
        return pageInfo;
    }

    @GetMapping("/sysList")
    public Result sysList(PageBean<Flash> page) {
        PageHelper.startPage(page.getPageNum(), page.getSize(), "addtime desc");
        List<Flash> flashes = flashService.findAll();
        return ResultGenerator.successResult(flashes);
    }

    @RequestMapping("/getFlashList")
    public List<Flash> getFlashList(@RequestParam(defaultValue = "0", name = "pageStart") Integer pageStart) {
        Integer start = pageSize * pageStart;
        List<Flash> flashes = flashService.findList(start);
        return flashes;
    }

    @RequestMapping("/toFlashPage")
    public ModelAndView toFlashPage(Integer id) {
        ModelAndView mav = new ModelAndView("front/flashDetail");
        mav.addObject("id", id);
        return mav;
    }

    /***
     * 快讯详情
     * @param id
     * @return
     */
    @RequestMapping("/toReadFlash")
    public Flash toReadFlash(Integer id, HttpSession session) {
        Flash flash = flashService.findById(id);
        //判断有没有读过该快讯
        if ((session.getAttribute("flashIdLit")) == null) {
            LinkedList<Integer> flashIdLit = new LinkedList<Integer>();
            flashIdLit.add(id);
            flash.setReadnumber(flash.getReadnumber() + 1);
            flashService.update(flash);
            session.setAttribute("flashIdLit", flashIdLit);

        } else {
            if (!((LinkedList<Integer>) session.getAttribute("flashIdLit")).contains(id)) {
                flash.setReadnumber(flash.getReadnumber() + 1);
                flashService.update(flash);
            }
        }
        return flash;
    }

    /***
     * 后台上传快讯
     */

    @RequestMapping("/sysUploadFlash")
    public Result sysUploadFlash(@RequestBody Flash flash) {
        flash.setReadnumber(1000);
        flash.setAddtime(new Date());
        flashService.save(flash);
        return ResultGenerator.successResult();
    }
}
