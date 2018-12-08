package io.anshily.biz.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "biz_permissions")
public class BizPermissions {
    /**
     * 权限id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 权限名称
     */
    private String permissions_title;

    /**
     * 权限路由名称
     */
    private String controller;

    /**
     * 创建时间
     */
    private String add_time;

    /**
     * 获取权限id
     *
     * @return id - 权限id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置权限id
     *
     * @param id 权限id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取权限名称
     *
     * @return permissions_title - 权限名称
     */
    public String getPermissions_title() {
        return permissions_title;
    }

    /**
     * 设置权限名称
     *
     * @param permissions_title 权限名称
     */
    public void setPermissions_title(String permissions_title) {
        this.permissions_title = permissions_title;
    }

    /**
     * 获取权限路由名称
     *
     * @return controller - 权限路由名称
     */
    public String getController() {
        return controller;
    }

    /**
     * 设置权限路由名称
     *
     * @param controller 权限路由名称
     */
    public void setController(String controller) {
        this.controller = controller;
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
}