package io.anshily.admin.dao;

import io.anshily.base.core.Mapper;
import io.anshily.model.Share;

import java.util.List;
import java.util.Map;

public interface ShareMapper extends Mapper<Share> {
    List<Share> findSharesByToken(String token);
    Map getArticleShareNumber(Integer id);
    Map getFlashShareNumber(Integer id);

}