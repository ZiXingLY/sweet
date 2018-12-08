package io.anshily.model;

import javax.persistence.*;

@Table(name = "sys_role_permission")
public class SysRolePermission {
    @Id
    @GeneratedValue(generator="UUID")
    private String id;

    /**
     * 角色ID
     */
    private String rid;

    /**
     * 权限ID
     */
    private String pid;

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
     * 获取角色ID
     *
     * @return rid - 角色ID
     */
    public String getRid() {
        return rid;
    }

    /**
     * 设置角色ID
     *
     * @param rid 角色ID
     */
    public void setRid(String rid) {
        this.rid = rid;
    }

    /**
     * 获取权限ID
     *
     * @return pid - 权限ID
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置权限ID
     *
     * @param pid 权限ID
     */
    public void setPid(String pid) {
        this.pid = pid;
    }
}