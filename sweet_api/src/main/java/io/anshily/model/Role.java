package io.anshily.model;

import javax.persistence.*;

@Table(name = "qy_role")
public class Role {
    /**
     * 角色id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 用户类型 1前台角色 2后台角色
     */
    private Integer type;

    /**
     * 添加时间
     */
    private String add_time;

    /**
     * 备注
     */
    private String info;

    /**
     * 获取角色id
     *
     * @return id - 角色id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置角色id
     *
     * @param id 角色id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取角色名
     *
     * @return name - 角色名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置角色名
     *
     * @param name 角色名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取用户类型 1前台角色 2后台角色
     *
     * @return type - 用户类型 1前台角色 2后台角色
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置用户类型 1前台角色 2后台角色
     *
     * @param type 用户类型 1前台角色 2后台角色
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取添加时间
     *
     * @return add_time - 添加时间
     */
    public String getAdd_time() {
        return add_time;
    }

    /**
     * 设置添加时间
     *
     * @param add_time 添加时间
     */
    public void setAdd_time(String add_time) {
        this.add_time = add_time;
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