package io.anshily.front.service.impl;

import io.anshily.front.dao.LikerMapper;
import io.anshily.model.Liker;
import io.anshily.front.service.LikerService;
import io.anshily.base.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by zaq on 2018/08/14.
 */
@Service
@Transactional
public class LikerServiceImpl extends AbstractService<Liker> implements LikerService {
    @Resource
    private LikerMapper qyLikerMapper;

    @Override
    public List<Liker> findAllLikerById(Integer id) {
        return qyLikerMapper.findAllLikerById(id);
    }

    @Override
    public Map articleLikerNumByAid(Integer id) {
        return qyLikerMapper.articleLikerNumByAid(id);
    }

    @Override
    public List<Liker> findIsLike(Liker liker) {
        return qyLikerMapper.findIsLike(liker);
    }

    @Override
    public void cancelLike(Integer uid,Integer aid){
        qyLikerMapper.cancelLike(uid,aid);
    }
}
