package io.anshily.model;

import javax.persistence.*;

@Table(name = "qy_level_score")
public class LevelScore {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 等级
     */
    private Integer level;

    /**
     * 积分
     */
    private Integer score;

    /**
     * 对应等级花费
     */
    private Integer cost;

    /**
     * 免费发文次数
     */
    private Integer free_times;

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
     * 获取等级
     *
     * @return level - 等级
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置等级
     *
     * @param level 等级
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取积分
     *
     * @return score - 积分
     */
    public Integer getScore() {
        return score;
    }

    /**
     * 设置积分
     *
     * @param score 积分
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * 获取对应等级花费
     *
     * @return cost - 对应等级花费
     */
    public Integer getCost() {
        return cost;
    }

    /**
     * 设置对应等级花费
     *
     * @param cost 对应等级花费
     */
    public void setCost(Integer cost) {
        this.cost = cost;
    }

    /**
     * 获取免费发文次数
     *
     * @return free_times - 免费发文次数
     */
    public Integer getFree_times() {
        return free_times;
    }

    /**
     * 设置免费发文次数
     *
     * @param free_times 免费发文次数
     */
    public void setFree_times(Integer free_times) {
        this.free_times = free_times;
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