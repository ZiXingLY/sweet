package io.anshily.model;

import javax.persistence.*;

@Table(name = "sw_promise")
public class Promise {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 微信uuid
     */
    private String uuid;

    /**
     * sweet账号
     */
    private String account;

    /**
     * ipfs_hash
     */
    private String ipfs_hash;

    /**
     * 添加时间
     */
    private String add_time;

    /**
     * promise概览
     */
    private String promise;

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
     * 获取微信uuid
     *
     * @return uuid - 微信uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置微信uuid
     *
     * @param uuid 微信uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 获取sweet账号
     *
     * @return account - sweet账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置sweet账号
     *
     * @param account sweet账号
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取ipfs_hash
     *
     * @return ipfs_hash - ipfs_hash
     */
    public String getIpfs_hash() {
        return ipfs_hash;
    }

    /**
     * 设置ipfs_hash
     *
     * @param ipfs_hash ipfs_hash
     */
    public void setIpfs_hash(String ipfs_hash) {
        this.ipfs_hash = ipfs_hash;
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
     * 获取promise概览
     *
     * @return promise - promise概览
     */
    public String getPromise() {
        return promise;
    }

    /**
     * 设置promise概览
     *
     * @param promise promise概览
     */
    public void setPromise(String promise) {
        this.promise = promise;
    }
}