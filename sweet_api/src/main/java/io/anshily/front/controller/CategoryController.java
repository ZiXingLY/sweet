package io.anshily.front.controller;
import io.anshily.base.core.Result;
import io.anshily.base.core.ResultGenerator;
import io.anshily.model.Category;
import io.anshily.front.service.CategoryService;
import io.anshily.base.core.PageBean;
import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by zaq on 2018/08/14.
*/
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @PostMapping("/add")
    public Result add(@RequestBody Category category) {
        int categoryCount = categoryService.findAllCount();
        if (categoryCount >= 7){
            return ResultGenerator.successResult("不能创建超过7个分类");
        }
        categoryService.save(category);
        return ResultGenerator.successResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Category category) {
        categoryService.deleteById(category.getCid());
        return ResultGenerator.successResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Category category) {
        categoryService.update(category);
        return ResultGenerator.successResult();
    }

    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Category category = categoryService.findById(id);
        return ResultGenerator.successResult(category);
    }

    @GetMapping("/list")
    public Result list(PageBean<Category> page) {
        PageHelper.startPage(page.getPageNum(),page.getSize());
        List<Category> list = categoryService.findAll();
        page.setList(list);
        return ResultGenerator.successResult(page);
    }
//获取分类
    @RequestMapping("/getCategory")
    public List<Category> getCategory(){
        List<Category> categories = categoryService.findAll();
        return categories;
    }


}
