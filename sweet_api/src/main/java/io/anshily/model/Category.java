package io.anshily.model;

import javax.persistence.*;

@Table(name = "qy_category")
public class Category {
    /**
     * 分类ID
     */
    @Id
    private Integer cid;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 获取分类ID
     *
     * @return cid - 分类ID
     */
    public Integer getCid() {
        return cid;
    }

    /**
     * 设置分类ID
     *
     * @param cid 分类ID
     */
    public void setCid(Integer cid) {
        this.cid = cid;
    }

    /**
     * 获取分类名称
     *
     * @return name - 分类名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置分类名称
     *
     * @param name 分类名称
     */
    public void setName(String name) {
        this.name = name;
    }
}