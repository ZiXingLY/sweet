package io.anshily.model;

import javax.persistence.*;

@Table(name = "qy_permissions")
public class Permissions {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 权限名称
     */
    private String permissions_title;

    /**
     * 控制器名
     */
    private String controller;

    /**
     * 1：前台权限 2：后台权限
     */
    private Integer type;

    /**
     * 前台URL管理
     */
    private String url;

    /**
     * 添加时间
     */
    private String add_time;

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
     * 获取控制器名
     *
     * @return controller - 控制器名
     */
    public String getController() {
        return controller;
    }

    /**
     * 设置控制器名
     *
     * @param controller 控制器名
     */
    public void setController(String controller) {
        this.controller = controller;
    }

    /**
     * 获取1：前台权限 2：后台权限
     *
     * @return type - 1：前台权限 2：后台权限
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置1：前台权限 2：后台权限
     *
     * @param type 1：前台权限 2：后台权限
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取前台URL管理
     *
     * @return url - 前台URL管理
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置前台URL管理
     *
     * @param url 前台URL管理
     */
    public void setUrl(String url) {
        this.url = url;
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