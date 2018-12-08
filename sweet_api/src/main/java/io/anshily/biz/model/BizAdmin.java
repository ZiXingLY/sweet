package io.anshily.biz.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "biz_admin")
public class BizAdmin {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色
     */
    private Integer roles_id;

    /**
     * 手机号
     */
    private String phone_number;

    /**
     * 账号类型:0查看运营加结算  1运营端 2运输承运公司 3维修站 4油气站 5汽配店 6结算公司
     */
    private String account_type;

    /**
     * 名称
     */
    private String name;

    /**
     * 创建时间
     */
    private String add_time;

    /**
     * 0：删除 1： 有效
     */
    private String del;

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
     * 获取账号
     *
     * @return account - 账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置账号
     *
     * @param account 账号
     */
    public void setAccount(String account) {
        this.account = account;
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

    public Integer getRoles_id() {
        return roles_id;
    }

    public void setRoles_id(Integer roles_id) {
        this.roles_id = roles_id;
    }

    /**
     * 获取手机号
     *
     * @return phone_number - 手机号
     */
    public String getPhone_number() {
        return phone_number;
    }

    /**
     * 设置手机号
     *
     * @param phone_number 手机号
     */
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    /**
     * 获取账号类型:0查看运营加结算  1运营端 2运输承运公司 3维修站 4油气站 5汽配店 6结算公司
     *
     * @return account_type - 账号类型:0查看运营加结算  1运营端 2运输承运公司 3维修站 4油气站 5汽配店 6结算公司
     */
    public String getAccount_type() {
        return account_type;
    }

    /**
     * 设置账号类型:0查看运营加结算  1运营端 2运输承运公司 3维修站 4油气站 5汽配店 6结算公司
     *
     * @param account_type 账号类型:0查看运营加结算  1运营端 2运输承运公司 3维修站 4油气站 5汽配店 6结算公司
     */
    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取创建时间
     *
     * @return add_time - 创建时间
     */
    public String getAdd_time() {
        return add_time;
    }

    /**
     * 设置创建时间
     *
     * @param add_time 创建时间
     */
    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    /**
     * 获取0：删除 1： 有效
     *
     * @return del - 0：删除 1： 有效
     */
    public String getDel() {
        return del;
    }

    /**
     * 设置0：删除 1： 有效
     *
     * @param del 0：删除 1： 有效
     */
    public void setDel(String del) {
        this.del = del;
    }
}