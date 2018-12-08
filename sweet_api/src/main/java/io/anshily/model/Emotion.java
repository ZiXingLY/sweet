package io.anshily.model;

import javax.persistence.*;

@Table(name = "qq_emotion")
public class Emotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 说说id
     */
    private String tid;

    /**
     * 时间
     */
    private long created;

    /**
     * qq号
     */
    private String uin;

    /**
     * 附加
     */
    private String info;

    /**
     * 说说内容
     */
    private String content;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取说说id
     *
     * @return tid - 说说id
     */
    public String getTid() {
        return tid;
    }

    /**
     * 设置说说id
     *
     * @param tid 说说id
     */
    public void setTid(String tid) {
        this.tid = tid;
    }

    /**
     * 获取时间
     *
     * @return created - 时间
     */
    public long getCreated() {
        return created;
    }

    /**
     * 设置时间
     *
     * @param created 时间
     */
    public void setCreated(long created) {
        this.created = created;
    }

    /**
     * 获取qq号
     *
     * @return uin - qq号
     */
    public String getUin() {
        return uin;
    }

    /**
     * 设置qq号
     *
     * @param uin qq号
     */
    public void setUin(String uin) {
        this.uin = uin;
    }

    /**
     * 获取附加
     *
     * @return info - 附加
     */
    public String getInfo() {
        return info;
    }

    /**
     * 设置附加
     *
     * @param info 附加
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * 获取说说内容
     *
     * @return content - 说说内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置说说内容
     *
     * @param content 说说内容
     */
    public void setContent(String content) {
        this.content = content;
    }
}