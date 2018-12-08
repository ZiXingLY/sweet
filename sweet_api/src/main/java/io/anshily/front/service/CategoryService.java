package io.anshily.front.service;
import io.anshily.model.Category;
import io.anshily.base.core.Service;


/**
 * Created by zaq on 2018/08/14.
 */
public interface CategoryService extends Service<Category> {
    public Integer findAllCount();

}
