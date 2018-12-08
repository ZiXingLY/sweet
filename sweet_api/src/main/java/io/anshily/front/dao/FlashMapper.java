package io.anshily.front.dao;

import io.anshily.base.core.Mapper;
import io.anshily.model.Flash;

import java.util.List;

public interface FlashMapper extends Mapper<Flash> {
    public List<Flash> findList(Integer start);
    public List<Flash> findByTitle(String title);
    public int addReadNumber();

}