package io.anshily.front.service.impl;

import io.anshily.front.dao.CategoryMapper;
import io.anshily.model.Category;
import io.anshily.front.service.CategoryService;
import io.anshily.base.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by zaq on 2018/08/14.
 */
@Service
@Transactional
public class CategoryServiceImpl extends AbstractService<Category> implements CategoryService {
    @Resource
    private CategoryMapper qyCategoryMapper;

    @Override
    public Integer findAllCount() {
        return qyCategoryMapper.findAllCount();
    }
}
