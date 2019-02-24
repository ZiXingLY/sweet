package io.anshily.controller;
import io.anshily.base.core.Result;
import io.anshily.base.core.ResultGenerator;
import io.anshily.model.Cart;
import io.anshily.model.User;
import io.anshily.service.CartService;
import io.anshily.base.core.PageBean;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
* Created by anshi on 2019/02/23.
*/
@RestController
@RequestMapping("/cart")
public class CartController {
    @Resource
    private CartService cartService;

    @PostMapping("/add")
    public Result add(@RequestBody Cart cart) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        cart.setUid(user.getId());
        cart.setAdd_time(new Date());
        cartService.save(cart);
        return ResultGenerator.successResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Integer id) {
        cartService.deleteById(id);
        return ResultGenerator.successResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Cart cart) {
        cartService.update(cart);
        return ResultGenerator.successResult();
    }

    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Cart cart = cartService.findById(id);
        return ResultGenerator.successResult(cart);
    }

    @GetMapping("/list")
    public Result list(PageBean<Cart> page) {
        PageHelper.startPage(page.getPageNum(),page.getSize());
        List<Cart> list = cartService.findAll();
        page.setList(list);
        return ResultGenerator.successResult(page);
    }
}
