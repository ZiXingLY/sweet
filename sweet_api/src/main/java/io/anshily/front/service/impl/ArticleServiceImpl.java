package io.anshily.front.service.impl;

import io.anshily.front.dao.ArticleMapper;
import io.anshily.model.Article;
import io.anshily.front.service.ArticleService;
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
public class ArticleServiceImpl extends AbstractService<Article> implements ArticleService {
    @Resource
    private ArticleMapper qyArticleMapper;

    @Override
    public List<Article> findAllBanner() {
        return qyArticleMapper.findAllBanner();
    }

    @Override
    public List<Map<String, Object>> findArticleByCategory(Integer category, Integer start) {
        return qyArticleMapper.findArticleByCategory(category,start);
    }

    @Override
    public List<Article> findHotArticle() {
        return qyArticleMapper.findHotArticle();
    }

    @Override
    public List<Article> readArticleById(Integer id) {
        return qyArticleMapper.readArticleById(id);
    }

    @Override
    public List<Article> findMyCheckingArticle(Integer id) {
        return qyArticleMapper.findMyCheckingArticle(id);
    }

    @Override
    public List<Article> findMyReleaseArticle(Integer id) {
        return qyArticleMapper.findMyReleaseArticle(id);
    }

    @Override
    public List<Article> findMyNotPassArticle(Integer id) {
        return qyArticleMapper.findMyNotPassArticle(id);
    }

    @Override
    public List<Article> findArticleByState(Integer state) {
        return qyArticleMapper.findArticleByState(state);
    }

    @Override
    public List<Article> findArticleByUserId(Integer id) {
        return qyArticleMapper.findArticleByUserId(id);
    }

    @Override
    public List<Article> wxFindArticleByState(Integer uid, Integer state) {
        return qyArticleMapper.wxFindArticleByState(uid,state);
    }

    @Override
    public int addReadNumber() {
        return qyArticleMapper.addReadNumber();
    }
}
