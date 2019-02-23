package io.anshily.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sw_goods")
public class Goods {
    /**
     * 商品id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 品名
     */
    private String title;

    /**
     * 封面
     */
    private String image;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 添加时间
     */
    private Date add_time;

    /**
     * 新旧程度 1、全新2、99新 3、88新、4其他
     */
    private Integer degree;

    /**
     * 交易地点
     */
    private String address;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 交易方式 1、当面 2、网付 3、其他
     */
    private Integer method;

    /**
     * 详情
     */
    private String info;

    /**
     * 获取商品id
     *
     * @return id - 商品id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置商品id
     *
     * @param id 商品id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取品名
     *
     * @return title - 品名
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置品名
     *
     * @param title 品名
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取封面
     *
     * @return image - 封面
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置封面
     *
     * @param image 封面
     */
    public void setImage(String image) {
        this.image = image;
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
     * 获取新旧程度 1、全新2、99新 3、88新、4其他
     *
     * @return degree - 新旧程度 1、全新2、99新 3、88新、4其他
     */
    public Integer getDegree() {
        return degree;
    }

    /**
     * 设置新旧程度 1、全新2、99新 3、88新、4其他
     *
     * @param degree 新旧程度 1、全新2、99新 3、88新、4其他
     */
    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    /**
     * 获取交易地点
     *
     * @return address - 交易地点
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置交易地点
     *
     * @param address 交易地点
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取价格
     *
     * @return price - 价格
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置价格
     *
     * @param price 价格
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取交易方式 1、当面 2、网付 3、其他
     *
     * @return method - 交易方式 1、当面 2、网付 3、其他
     */
    public Integer getMethod() {
        return method;
    }

    /**
     * 设置交易方式 1、当面 2、网付 3、其他
     *
     * @param method 交易方式 1、当面 2、网付 3、其他
     */
    public void setMethod(Integer method) {
        this.method = method;
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