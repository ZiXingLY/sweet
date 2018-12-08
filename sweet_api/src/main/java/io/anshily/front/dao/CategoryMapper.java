package io.anshily.front.dao;

import io.anshily.base.core.Mapper;
import io.anshily.model.Category;

public interface CategoryMapper extends Mapper<Category> {
    public Integer findAllCount();
}