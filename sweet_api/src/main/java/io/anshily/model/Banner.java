package io.anshily.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sw_banner")
public class Banner {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 轮播图
     */
    private String image;

    /**
     * 顺序
     */
    private Integer sort;

    /**
     * 开始时间
     */
    private Date start_time;

    /**
     * 结束时间
     */
    private Date end_time;

    /**
     * 新增时间
     */
    private Date add_time;

    /**
     * 类型1、有链2、无链
     */
    private Integer type;

    /**
     * 外部链接
     */
    private String link;

    /**
     * 备注
     */
    private String info;

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
     * 获取轮播图
     *
     * @return image - 轮播图
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置轮播图
     *
     * @param image 轮播图
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 获取顺序
     *
     * @return sort - 顺序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置顺序
     *
     * @param sort 顺序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取开始时间
     *
     * @return start_time - 开始时间
     */
    public Date getStart_time() {
        return start_time;
    }

    /**
     * 设置开始时间
     *
     * @param start_time 开始时间
     */
    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    /**
     * 获取结束时间
     *
     * @return end_time - 结束时间
     */
    public Date getEnd_time() {
        return end_time;
    }

    /**
     * 设置结束时间
     *
     * @param end_time 结束时间
     */
    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    /**
     * 获取新增时间
     *
     * @return add_time - 新增时间
     */
    public Date getAdd_time() {
        return add_time;
    }

    /**
     * 设置新增时间
     *
     * @param add_time 新增时间
     */
    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
    }

    /**
     * 获取类型1、有链2、无链
     *
     * @return type - 类型1、有链2、无链
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置类型1、有链2、无链
     *
     * @param type 类型1、有链2、无链
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取外部链接
     *
     * @return link - 外部链接
     */
    public String getLink() {
        return link;
    }

    /**
     * 设置外部链接
     *
     * @param link 外部链接
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * 获取备注
     *
     * @return info - 备注
     */
    public String getInfo() {
        return info;
    }

    /**
     * 设置备注
     *
     * @param info 备注
     */
    public void setInfo(String info) {
        this.info = info;
    }
}