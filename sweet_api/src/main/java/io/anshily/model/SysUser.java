package io.anshily.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_user")
public class SysUser {
    @Id
    @GeneratedValue(generator="UUID")
    private String id;

    /**
     * 用户昵称
     */
    private String username;

    /**
     * 邮箱|登录帐号
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 最后登录时间
     */
    private Date last_login_time;

    /**
     * 1:有效，0:禁止登录
     */
    private String status;

    private String create_name_id;

    private Date last_update_time;

    private String last_update_name_id;


    private String description;

    private String header;

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }



    private  String phone_number;

    public String getPhone_number() {
        return phone_number;
    }

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
     * 获取用户昵称
     *
     * @return username - 用户昵称
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户昵称
     *
     * @param username 用户昵称
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取邮箱|登录帐号
     *
     * @return email - 邮箱|登录帐号
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱|登录帐号
     *
     * @param email 邮箱|登录帐号
     */
    public void setEmail(String email) {
        this.email = email;
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
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreate_time() {
        return create_time;
    }

    /**
     * 设置创建时间
     *
     * @param create_time 创建时间
     */
    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    /**
     * 获取最后登录时间
     *
     * @return last_login_time - 最后登录时间
     */
    public Date getLast_login_time() {
        return last_login_time;
    }

    /**
     * 设置最后登录时间
     *
     * @param last_login_time 最后登录时间
     */
    public void setLast_login_time(Date last_login_time) {
        this.last_login_time = last_login_time;
    }

    /**
     * 获取1:有效，0:禁止登录
     *
     * @return status - 1:有效，0:禁止登录
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置1:有效，0:禁止登录
     *
     * @param status 1:有效，0:禁止登录
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return create_name_id
     */
    public String getCreate_name_id() {
        return create_name_id;
    }

    /**
     * @param create_name_id
     */
    public void setCreate_name_id(String create_name_id) {
        this.create_name_id = create_name_id;
    }

    /**
     * @return last_update_time
     */
    public Date getLast_update_time() {
        return last_update_time;
    }

    /**
     * @param last_update_time
     */
    public void setLast_update_time(Date last_update_time) {
        this.last_update_time = last_update_time;
    }

    /**
     * @return last_update_name_id
     */
    public String getLast_update_name_id() {
        return last_update_name_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * @param last_update_name_id
     */
    public void setLast_update_name_id(String last_update_name_id) {
        this.last_update_name_id = last_update_name_id;
    }
}