package io.anshily.front.service;
import io.anshily.model.Liker;
import io.anshily.base.core.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by zaq on 2018/08/14.
 */
public interface LikerService extends Service<Liker> {
    public List<Liker> findAllLikerById(Integer id);
    Map articleLikerNumByAid(Integer id);
    List<Liker> findIsLike(Liker liker);
    public void cancelLike(Integer uid,Integer aid);
}
