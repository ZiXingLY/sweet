package io.anshily.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sw_emotions")
public class Emotions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 上传时间
     */
    private Date add_time;

    /**
     * 作者
     */
    private String author;

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
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
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
     * @return add_time - 上传时间
     */
    public Date getAdd_time() {
        return add_time;
    }

    /**
     * 设置上传时间
     *
     * @param add_time 上传时间
     */
    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
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
}