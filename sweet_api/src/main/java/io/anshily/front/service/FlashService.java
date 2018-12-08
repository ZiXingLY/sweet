package io.anshily.front.service;
import io.anshily.model.Flash;
import io.anshily.base.core.Service;

import java.util.List;


/**
 * Created by zaq on 2018/08/14.
 */
public interface FlashService extends Service<Flash> {
    public List<Flash> findList(Integer start);
    public List<Flash> findByTitle(String title);
    public int addReadNumber();
}
