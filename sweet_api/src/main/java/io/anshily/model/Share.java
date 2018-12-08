package io.anshily.model;

import javax.persistence.*;

@Table(name = "qy_share")
public class Share {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 分享用户id
     */
    private Integer uid;

    /**
     * 文章id
     */
    private Integer aid;

    /**
     * 快讯id
     */
    private Integer fid;

    /**
     * 类型1：文章2：快讯
     */
    private Integer type;

    /**
     * 分享token 由uid + aid/fid+type 散列而成
     */
    private String share_token;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取分享用户id
     *
     * @return uid - 分享用户id
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 设置分享用户id
     *
     * @param uid 分享用户id
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获取文章id
     *
     * @return aid - 文章id
     */
    public Integer getAid() {
        return aid;
    }

    /**
     * 设置文章id
     *
     * @param aid 文章id
     */
    public void setAid(Integer aid) {
        this.aid = aid;
    }

    /**
     * 获取快讯id
     *
     * @return fid - 快讯id
     */
    public Integer getFid() {
        return fid;
    }

    /**
     * 设置快讯id
     *
     * @param fid 快讯id
     */
    public void setFid(Integer fid) {
        this.fid = fid;
    }

    /**
     * 获取类型1：文章2：快讯
     *
     * @return type - 类型1：文章2：快讯
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置类型1：文章2：快讯
     *
     * @param type 类型1：文章2：快讯
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取分享token 由uid + aid/fid+type 散列而成
     *
     * @return share_token - 分享token 由uid + aid/fid+type 散列而成
     */
    public String getShare_token() {
        return share_token;
    }

    /**
     * 设置分享token 由uid + aid/fid+type 散列而成
     *
     * @param share_token 分享token 由uid + aid/fid+type 散列而成
     */
    public void setShare_token(String share_token) {
        this.share_token = share_token;
    }
}