package io.anshily.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "qy_article")
public class Article {
    @Id
    private Integer aid;

    /**
     * 标题
     */
    private String title;

    /**
     * 上传时间
     */
    private Date addtime;

    /**
     * 作者
     */
    private String author;

    /**
     * 阅读量
     */
    private Integer readnumber;

    /**
     * 1上架2待审核3未通过
     */
    private Integer state;

    /**
     * 是否轮播
     */
    private Integer isbanner;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 分类id
     */
    private Integer cid;

    /**
     * 点赞次数
     */
    private Integer likenum;

    /**
     * 用户类型
     */
    private Integer type;

    /**
     * 来源
     */
    private String source;

    /**
     * 摘要
     */
    private String brief;

    /**
     * 内容
     */
    private String content;

    /**
     * 封面
     */
    private String coverimg;

    /**
     * @return aid
     */
    public Integer getAid() {
        return aid;
    }

    /**
     * @param aid
     */
    public void setAid(Integer aid) {
        this.aid = aid;
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取上传时间
     *
     * @return addtime - 上传时间
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * 设置上传时间
     *
     * @param addtime 上传时间
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * 获取作者
     *
     * @return author - 作者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置作者
     *
     * @param author 作者
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取阅读量
     *
     * @return readnumber - 阅读量
     */
    public Integer getReadnumber() {
        return readnumber;
    }

    /**
     * 设置阅读量
     *
     * @param readnumber 阅读量
     */
    public void setReadnumber(Integer readnumber) {
        this.readnumber = readnumber;
    }

    /**
     * 获取1上架2待审核3未通过
     *
     * @return state - 1上架2待审核3未通过
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置1上架2待审核3未通过
     *
     * @param state 1上架2待审核3未通过
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取是否轮播
     *
     * @return isbanner - 是否轮播
     */
    public Integer getIsbanner() {
        return isbanner;
    }

    /**
     * 设置是否轮播
     *
     * @param isbanner 是否轮播
     */
    public void setIsbanner(Integer isbanner) {
        this.isbanner = isbanner;
    }

    /**
     * 获取用户id
     *
     * @return uid - 用户id
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 设置用户id
     *
     * @param uid 用户id
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获取分类id
     *
     * @return cid - 分类id
     */
    public Integer getCid() {
        return cid;
    }

    /**
     * 设置分类id
     *
     * @param cid 分类id
     */
    public void setCid(Integer cid) {
        this.cid = cid;
    }

    /**
     * 获取点赞次数
     *
     * @return likenum - 点赞次数
     */
    public Integer getLikenum() {
        return likenum;
    }

    /**
     * 设置点赞次数
     *
     * @param likenum 点赞次数
     */
    public void setLikenum(Integer likenum) {
        this.likenum = likenum;
    }

    /**
     * 获取用户类型
     *
     * @return type - 用户类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置用户类型
     *
     * @param type 用户类型
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取来源
     *
     * @return source - 来源
     */
    public String getSource() {
        return source;
    }

    /**
     * 设置来源
     *
     * @param source 来源
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 获取摘要
     *
     * @return brief - 摘要
     */
    public String getBrief() {
        return brief;
    }

    /**
     * 设置摘要
     *
     * @param brief 摘要
     */
    public void setBrief(String brief) {
        this.brief = brief;
    }

    /**
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取封面
     *
     * @return coverimg - 封面
     */
    public String getCoverimg() {
        return coverimg;
    }

    /**
     * 设置封面
     *
     * @param coverimg 封面
     */
    public void setCoverimg(String coverimg) {
        this.coverimg = coverimg;
    }
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (getClass() != o.getClass()){
            return false;
        }
        Article article = (Article)o;
        return aid.equals(article.aid);
    }

    @Override
    public int hashCode() {
        return aid;
    }
}