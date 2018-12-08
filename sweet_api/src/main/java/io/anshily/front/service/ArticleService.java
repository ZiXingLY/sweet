package io.anshily.front.service;
import io.anshily.model.Article;
import io.anshily.base.core.Service;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * Created by zaq on 2018/08/14.
 */
public interface ArticleService extends Service<Article> {
    public List<Article> findAllBanner();
    public List<Map<String, Object>> findArticleByCategory(Integer category, Integer start);
    public List<Article> findHotArticle();
    public List<Article> readArticleById(Integer id);
    public List<Article> findMyCheckingArticle(Integer id);
    public List<Article> findMyReleaseArticle(Integer id);
    public List<Article> findMyNotPassArticle(Integer id);
    public List<Article> findArticleByState(@Param("state") Integer state);
    public List<Article> findArticleByUserId(Integer id);

    List<Article> wxFindArticleByState(Integer uid , Integer state);
    public int addReadNumber();
}
