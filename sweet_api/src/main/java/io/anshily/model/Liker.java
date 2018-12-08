package io.anshily.model;

import javax.persistence.*;

@Table(name = "qy_liker")
public class Liker {
    /**
     * 点赞id
     */
    @Id
    private Integer lid;

    /**
     * 点赞用户id
     */
    private Integer uid;

    /**
     * 点赞文章id
     */
    private Integer aid;

    /**
     * 获取点赞id
     *
     * @return lid - 点赞id
     */
    public Integer getLid() {
        return lid;
    }

    /**
     * 设置点赞id
     *
     * @param lid 点赞id
     */
    public void setLid(Integer lid) {
        this.lid = lid;
    }

    /**
     * 获取点赞用户id
     *
     * @return uid - 点赞用户id
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 设置点赞用户id
     *
     * @param uid 点赞用户id
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获取点赞文章id
     *
     * @return aid - 点赞文章id
     */
    public Integer getAid() {
        return aid;
    }

    /**
     * 设置点赞文章id
     *
     * @param aid 点赞文章id
     */
    public void setAid(Integer aid) {
        this.aid = aid;
    }
}