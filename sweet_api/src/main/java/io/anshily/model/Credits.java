package io.anshily.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "qy_credits")
public class Credits {
    /**
     * 积分id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * openid
     */
    private String openid;

    /**
     * 积分
     */
    private Integer score;

    /**
     * 积分类型 1分享积分2会员积分3发表文章积分变动
     */
    private Integer type;

    /**
     * 获取积分的文章id号
     */
    private Integer aid;

    /**
     * 文章类型 1:文章 2: 快讯
     */
    private Integer article_type;

    /**
     * 读者openid
     */
    private String add_by_openid;

    /**
     * 读者uid
     */
    private Integer add_by_uid;

    /**
     * 从会员获取积分时会员交易号
     */
    private Integer oid;

    /**
     * 积分增加时间
     */
    private Date add_time;

    /**
     * 积分存在修改可能记录修改时间
     */
    private Date last_update_time;

    /**
     * vip等级
     */
    private Integer vip_level;

    /**
     * 积分变更说明
     */
    private String info;

    /**
     * 获取积分id
     *
     * @return id - 积分id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置积分id
     *
     * @param id 积分id
     */
    public void setId(Integer id) {
        this.id = id;
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
     * 获取openid
     *
     * @return openid - openid
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 设置openid
     *
     * @param openid openid
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     * 获取积分
     *
     * @return score - 积分
     */
    public Integer getScore() {
        return score;
    }

    /**
     * 设置积分
     *
     * @param score 积分
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * 获取积分类型 1分享积分2会员积分3发表文章积分变动
     *
     * @return type - 积分类型 1分享积分2会员积分3发表文章积分变动
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置积分类型 1分享积分2会员积分3发表文章积分变动
     *
     * @param type 积分类型 1分享积分2会员积分3发表文章积分变动
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取获取积分的文章id号
     *
     * @return aid - 获取积分的文章id号
     */
    public Integer getAid() {
        return aid;
    }

    /**
     * 设置获取积分的文章id号
     *
     * @param aid 获取积分的文章id号
     */
    public void setAid(Integer aid) {
        this.aid = aid;
    }

    /**
     * 获取文章类型 1:文章 2: 快讯
     *
     * @return article_type - 文章类型 1:文章 2: 快讯
     */
    public Integer getArticle_type() {
        return article_type;
    }

    /**
     * 设置文章类型 1:文章 2: 快讯
     *
     * @param article_type 文章类型 1:文章 2: 快讯
     */
    public void setArticle_type(Integer article_type) {
        this.article_type = article_type;
    }

    /**
     * 获取读者openid
     *
     * @return add_by_openid - 读者openid
     */
    public String getAdd_by_openid() {
        return add_by_openid;
    }

    /**
     * 设置读者openid
     *
     * @param add_by_openid 读者openid
     */
    public void setAdd_by_openid(String add_by_openid) {
        this.add_by_openid = add_by_openid;
    }

    /**
     * 获取读者uid
     *
     * @return add_by_uid - 读者uid
     */
    public Integer getAdd_by_uid() {
        return add_by_uid;
    }

    /**
     * 设置读者uid
     *
     * @param add_by_uid 读者uid
     */
    public void setAdd_by_uid(Integer add_by_uid) {
        this.add_by_uid = add_by_uid;
    }

    /**
     * 获取从会员获取积分时会员交易号
     *
     * @return oid - 从会员获取积分时会员交易号
     */
    public Integer getOid() {
        return oid;
    }

    /**
     * 设置从会员获取积分时会员交易号
     *
     * @param oid 从会员获取积分时会员交易号
     */
    public void setOid(Integer oid) {
        this.oid = oid;
    }

    /**
     * 获取积分增加时间
     *
     * @return add_time - 积分增加时间
     */
    public Date getAdd_time() {
        return add_time;
    }

    /**
     * 设置积分增加时间
     *
     * @param add_time 积分增加时间
     */
    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
    }

    /**
     * 获取积分存在修改可能记录修改时间
     *
     * @return last_update_time - 积分存在修改可能记录修改时间
     */
    public Date getLast_update_time() {
        return last_update_time;
    }

    /**
     * 设置积分存在修改可能记录修改时间
     *
     * @param last_update_time 积分存在修改可能记录修改时间
     */
    public void setLast_update_time(Date last_update_time) {
        this.last_update_time = last_update_time;
    }

    /**
     * 获取vip等级
     *
     * @return vip_level - vip等级
     */
    public Integer getVip_level() {
        return vip_level;
    }

    /**
     * 设置vip等级
     *
     * @param vip_level vip等级
     */
    public void setVip_level(Integer vip_level) {
        this.vip_level = vip_level;
    }

    /**
     * 获取积分变更说明
     *
     * @return info - 积分变更说明
     */
    public String getInfo() {
        return info;
    }

    /**
     * 设置积分变更说明
     *
     * @param info 积分变更说明
     */
    public void setInfo(String info) {
        this.info = info;
    }
}