package io.anshily.biz.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "biz_roles")
public class BizRoles {
    /**
     * 角色id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色的具体名称
     */
    private String role_title;



    /**
     * 创建时间
     */
    private String add_time;

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
     * 获取角色的具体名称
     *
     * @return role_title - 角色的具体名称
     */
    public String getRole_title() {
        return role_title;
    }

    /**
     * 设置角色的具体名称
     *
     * @param role_title 角色的具体名称
     */
    public void setRole_title(String role_title) {
        this.role_title = role_title;
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