package io.anshily.model;

import javax.persistence.*;

@Table(name = "qy_user")
public class User {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 电话
     */
    private String phone;

    /**
     * 微信openid
     */
    private String openid;

    /**
     * 1:web用户 2:wx用户
     */
    private Integer type;

    /**
     * email
     */
    private String email;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String header;

    /**
     * 注册时间
     */
    private String add_time;

    /**
     * 是否禁用 0:禁止登录 1:允许登录
     */
    private Integer status;

    /**
     * 最后登录时间
     */
    private String last_login_time;

    /**
     * 会员状态 1:会员 2:非会员
     */
    private Integer vip_state;

    /**
     * 会员等级
     */
    private Integer vip_level;

    /**
     * 剩余发文次数
     */
    private Integer left_free_times;

    /**
     * 会员id
     */
    private Integer vip_id;

    /**
     * 备注信息
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
     * 获取电话
     *
     * @return phone - 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话
     *
     * @param phone 电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取微信openid
     *
     * @return openid - 微信openid
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 设置微信openid
     *
     * @param openid 微信openid
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     * 获取1:web用户 2:wx用户
     *
     * @return type - 1:web用户 2:wx用户
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置1:web用户 2:wx用户
     *
     * @param type 1:web用户 2:wx用户
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取email
     *
     * @return email - email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置email
     *
     * @param email email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取昵称
     *
     * @return nickname - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取头像
     *
     * @return header - 头像
     */
    public String getHeader() {
        return header;
    }

    /**
     * 设置头像
     *
     * @param header 头像
     */
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * 获取注册时间
     *
     * @return add_time - 注册时间
     */
    public String getAdd_time() {
        return add_time;
    }

    /**
     * 设置注册时间
     *
     * @param add_time 注册时间
     */
    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    /**
     * 获取是否禁用 0:禁止登录 1:允许登录
     *
     * @return status - 是否禁用 0:禁止登录 1:允许登录
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置是否禁用 0:禁止登录 1:允许登录
     *
     * @param status 是否禁用 0:禁止登录 1:允许登录
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取最后登录时间
     *
     * @return last_login_time - 最后登录时间
     */
    public String getLast_login_time() {
        return last_login_time;
    }

    /**
     * 设置最后登录时间
     *
     * @param last_login_time 最后登录时间
     */
    public void setLast_login_time(String last_login_time) {
        this.last_login_time = last_login_time;
    }

    /**
     * 获取会员状态 1:会员 2:非会员
     *
     * @return vip_state - 会员状态 1:会员 2:非会员
     */
    public Integer getVip_state() {
        return vip_state;
    }

    /**
     * 设置会员状态 1:会员 2:非会员
     *
     * @param vip_state 会员状态 1:会员 2:非会员
     */
    public void setVip_state(Integer vip_state) {
        this.vip_state = vip_state;
    }

    /**
     * 获取会员等级
     *
     * @return vip_level - 会员等级
     */
    public Integer getVip_level() {
        return vip_level;
    }

    /**
     * 设置会员等级
     *
     * @param vip_level 会员等级
     */
    public void setVip_level(Integer vip_level) {
        this.vip_level = vip_level;
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
     * 获取会员id
     *
     * @return vip_id - 会员id
     */
    public Integer getVip_id() {
        return vip_id;
    }

    /**
     * 设置会员id
     *
     * @param vip_id 会员id
     */
    public void setVip_id(Integer vip_id) {
        this.vip_id = vip_id;
    }

    /**
     * 获取备注信息
     *
     * @return info - 备注信息
     */
    public String getInfo() {
        return info;
    }

    /**
     * 设置备注信息
     *
     * @param info 备注信息
     */
    public void setInfo(String info) {
        this.info = info;
    }
}