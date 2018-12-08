package io.anshily.model;

import javax.persistence.*;

@Table(name = "sw_cet")
public class Cet {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 准考证号
     */
    private String admission;

    /**
     * 考生姓名
     */
    private String cet_name;

    /**
     * 微信openid
     */
    private String openid;

    /**
     * 考生分数
     */
    private Long score;

    /**
     * 4：四级、6：六级
     */
    private Integer level;

    /**
     * 第几次考试
     */
    private Integer times;

    /**
     * sweet账户
     */
    private String account;

    /**
     * 备注
     */
    private String info;

    /**
     * 添加时间
     */
    private String add_time;

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
     * 获取准考证号
     *
     * @return admission - 准考证号
     */
    public String getAdmission() {
        return admission;
    }

    /**
     * 设置准考证号
     *
     * @param admission 准考证号
     */
    public void setAdmission(String admission) {
        this.admission = admission;
    }

    /**
     * 获取考生姓名
     *
     * @return cet_name - 考生姓名
     */
    public String getCet_name() {
        return cet_name;
    }

    /**
     * 设置考生姓名
     *
     * @param cet_name 考生姓名
     */
    public void setCet_name(String cet_name) {
        this.cet_name = cet_name;
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
     * 获取考生分数
     *
     * @return score - 考生分数
     */
    public Long getScore() {
        return score;
    }

    /**
     * 设置考生分数
     *
     * @param score 考生分数
     */
    public void setScore(Long score) {
        this.score = score;
    }

    /**
     * 获取4：四级、6：六级
     *
     * @return level - 4：四级、6：六级
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置4：四级、6：六级
     *
     * @param level 4：四级、6：六级
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取第几次考试
     *
     * @return times - 第几次考试
     */
    public Integer getTimes() {
        return times;
    }

    /**
     * 设置第几次考试
     *
     * @param times 第几次考试
     */
    public void setTimes(Integer times) {
        this.times = times;
    }

    /**
     * 获取sweet账户
     *
     * @return account - sweet账户
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置sweet账户
     *
     * @param account sweet账户
     */
    public void setAccount(String account) {
        this.account = account;
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
}