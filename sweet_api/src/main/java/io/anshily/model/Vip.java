package io.anshily.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "qy_vip")
public class Vip {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单号
     */
    private String oid;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 剩余发文次数
     */
    private Integer left_free_times;

    /**
     * 订单类型 1：充值 2：积分兑换
     */
    private Integer type;

    /**
     * 添加时间
     */
    private Date add_time;

    /**
     * 会员等级
     */
    private Integer level;

    /**
     * 订单金额
     */
    private String cost;

    /**
     * 详情
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
     * 获取订单号
     *
     * @return oid - 订单号
     */
    public String getOid() {
        return oid;
    }

    /**
     * 设置订单号
     *
     * @param oid 订单号
     */
    public void setOid(String oid) {
        this.oid = oid;
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
     * 获取剩余发文次数
     *
     * @return left_free_times - 剩余发文次数
     */
    public Integer getLeft_free_times() {
        return left_free_times;
    }

    /**
     * 设置剩余发文次数
     *
     * @param left_free_times 剩余发文次数
     */
    public void setLeft_free_times(Integer left_free_times) {
        this.left_free_times = left_free_times;
    }

    /**
     * 获取订单类型 1：充值 2：积分兑换
     *
     * @return type - 订单类型 1：充值 2：积分兑换
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置订单类型 1：充值 2：积分兑换
     *
     * @param type 订单类型 1：充值 2：积分兑换
     */
    public void setType(Integer type) {
        this.type = type;
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
     * 获取会员等级
     *
     * @return level - 会员等级
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置会员等级
     *
     * @param level 会员等级
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取订单金额
     *
     * @return cost - 订单金额
     */
    public String getCost() {
        return cost;
    }

    /**
     * 设置订单金额
     *
     * @param cost 订单金额
     */
    public void setCost(String cost) {
        this.cost = cost;
    }

    /**
     * 获取详情
     *
     * @return info - 详情
     */
    public String getInfo() {
        return info;
    }

    /**
     * 设置详情
     *
     * @param info 详情
     */
    public void setInfo(String info) {
        this.info = info;
    }
}