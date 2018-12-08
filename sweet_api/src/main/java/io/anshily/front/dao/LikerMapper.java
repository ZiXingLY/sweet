package io.anshily.front.dao;

import io.anshily.base.core.Mapper;
import io.anshily.model.Liker;

import java.util.List;
import java.util.Map;

public interface LikerMapper extends Mapper<Liker> {
    public List<Liker> findAllLikerById(Integer id);
    Map articleLikerNumByAid(Integer id);
    List<Liker> findIsLike(Liker liker);
    public void cancelLike(Integer uid,Integer aid);
}