package io.anshily.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "qy_flash")
public class Flash {
    /**
     * 快讯id
     */
    @Id
    private Integer fid;

    /**
     * 快讯标题
     */
    private String title;

    /**
     * 阅读量
     */
    private Integer readnumber;

    /**
     * 上传时间
     */
    private Date addtime;

    /**
     * 快讯内容
     */
    private String content;

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
     * 获取快讯标题
     *
     * @return title - 快讯标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置快讯标题
     *
     * @param title 快讯标题
     */
    public void setTitle(String title) {
        this.title = title;
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
     * 获取快讯内容
     *
     * @return content - 快讯内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置快讯内容
     *
     * @param content 快讯内容
     */
    public void setContent(String content) {
        this.content = content;
    }
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (getClass() != o.getClass()){
            return false;
        }
        Flash flash = (Flash)o;
        return fid.equals(flash.fid);
    }

    @Override
    public int hashCode() {
        return fid;
    }
}