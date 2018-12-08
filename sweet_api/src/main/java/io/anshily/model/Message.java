package io.anshily.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "qy_message")
public class Message {
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
     * 消息类型 1：会员变动信息 2：积分变动信息 3：登录注册信息 4：其他
     */
    private Integer type;

    /**
     * 会员花费
     */
    private Integer cost;

    /**
     * 积分变动
     */
    private Integer credit;

    /**
     * 添加时间
     */
    private Date add_time;

    /**
     * 消息内容
     */
    private String message_info;

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
     * 获取消息类型 1：会员变动信息 2：积分变动信息 3：登录注册信息 4：其他
     *
     * @return type - 消息类型 1：会员变动信息 2：积分变动信息 3：登录注册信息 4：其他
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置消息类型 1：会员变动信息 2：积分变动信息 3：登录注册信息 4：其他
     *
     * @param type 消息类型 1：会员变动信息 2：积分变动信息 3：登录注册信息 4：其他
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取会员花费
     *
     * @return cost - 会员花费
     */
    public Integer getCost() {
        return cost;
    }

    /**
     * 设置会员花费
     *
     * @param cost 会员花费
     */
    public void setCost(Integer cost) {
        this.cost = cost;
    }

    /**
     * 获取积分变动
     *
     * @return credit - 积分变动
     */
    public Integer getCredit() {
        return credit;
    }

    /**
     * 设置积分变动
     *
     * @param credit 积分变动
     */
    public void setCredit(Integer credit) {
        this.credit = credit;
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
     * 获取消息内容
     *
     * @return message_info - 消息内容
     */
    public String getMessage_info() {
        return message_info;
    }

    /**
     * 设置消息内容
     *
     * @param message_info 消息内容
     */
    public void setMessage_info(String message_info) {
        this.message_info = message_info;
    }
}