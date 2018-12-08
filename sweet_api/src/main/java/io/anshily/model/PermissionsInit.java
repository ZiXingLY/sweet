package io.anshily.model;

import javax.persistence.*;

@Table(name = "qy_permissions_init")
public class PermissionsInit {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 前台url控制
     */
    private String url;

    /**
     * 初始化过滤名单
     */
    private String permissions_init;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 后台初始化权限
     */
    private String controller;

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
     * 获取前台url控制
     *
     * @return url - 前台url控制
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置前台url控制
     *
     * @param url 前台url控制
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取初始化过滤名单
     *
     * @return permissions_init - 初始化过滤名单
     */
    public String getPermissions_init() {
        return permissions_init;
    }

    /**
     * 设置初始化过滤名单
     *
     * @param permissions_init 初始化过滤名单
     */
    public void setPermissions_init(String permissions_init) {
        this.permissions_init = permissions_init;
    }

    /**
     * 获取排序
     *
     * @return sort - 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序
     *
     * @param sort 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取后台初始化权限
     *
     * @return controller - 后台初始化权限
     */
    public String getController() {
        return controller;
    }

    /**
     * 设置后台初始化权限
     *
     * @param controller 后台初始化权限
     */
    public void setController(String controller) {
        this.controller = controller;
    }
}