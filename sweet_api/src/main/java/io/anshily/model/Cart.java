package io.anshily.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sw_cart")
public class Cart {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 商品id
     */
    private Integer gid;

    /**
     * 数量
     */
    private Integer num;

    /**
     * 添加时间
     */
    private Date add_time;

    /**
     * 状态1、正常2、已下单3、已过期
     */
    private Integer status;

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
     * 获取商品id
     *
     * @return gid - 商品id
     */
    public Integer getGid() {
        return gid;
    }

    /**
     * 设置商品id
     *
     * @param gid 商品id
     */
    public void setGid(Integer gid) {
        this.gid = gid;
    }

    /**
     * 获取数量
     *
     * @return num - 数量
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置数量
     *
     * @param num 数量
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 获取添加时间
     *
     * @return add_time - 添加时间
     */
    public Date getAdd_time() {
        return add_time;
    }

    /**
     * 设置添加时间
     *
     * @param add_time 添加时间
     */
    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
    }

    /**
     * 获取状态1、正常2、已下单3、已过期
     *
     * @return status - 状态1、正常2、已下单3、已过期
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态1、正常2、已下单3、已过期
     *
     * @param status 状态1、正常2、已下单3、已过期
     */
    public void setStatus(Integer status) {
        this.status = status;
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