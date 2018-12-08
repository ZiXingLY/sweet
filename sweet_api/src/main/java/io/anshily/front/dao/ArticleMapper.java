package io.anshily.front.dao;

import io.anshily.base.core.Mapper;
import io.anshily.model.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ArticleMapper extends Mapper<Article> {
    public List<Article> findAllBanner();
    public List<Map<String, Object>> findArticleByCategory(Integer category,Integer start);
    public List<Article> readArticleById(Integer id);
    public List<Article> findHotArticle();
    public List<Article> findMyCheckingArticle(Integer id);
    public List<Article> findMyReleaseArticle(Integer id);
    public List<Article> findMyNotPassArticle(Integer id);
    public List<Article> findArticleByState(@Param("state") Integer state);
    public List<Article> findArticleByUserId(Integer id);

    List<Article> wxFindArticleByState(Integer uid,Integer state);
    public int addReadNumber();
}