package io.anshily.service.impl;

import io.anshily.dao.CartMapper;
import io.anshily.model.Cart;
import io.anshily.service.CartService;
import io.anshily.base.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by anshi on 2019/02/23.
 */
@Service
@Transactional
public class CartServiceImpl extends AbstractService<Cart> implements CartService {
    @Resource
    private CartMapper swCartMapper;

}
