package io.anshily.model;


import javax.persistence.*;

@Table(name = "qy_user_extend")
public class UserExtend {
    /**
     * id
     */
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 对应用户uuid
     */
    private String uid;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 生日 格式 YYYY-MM-DD
     */
    private String birthday;

    private String trade;

//    @Transient
    private String nickname;

    /**
     * 钱包地址
     */
    private String mytoken;

    /**
     * 所在国家
     */
    private String country;

    private String address;


    /**
     * 备注信息
     */
    private String info;

    /**
     * 获取id
     *
     * @return id - id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取对应用户uuid
     *
     * @return uid - 对应用户uuid
     */
    public String getUid() {
        return uid;
    }

    /**
     * 设置对应用户uuid
     *
     * @param uid 对应用户uuid
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * 获取用户姓名
     *
     * @return name - 用户姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置用户姓名
     *
     * @param name 用户姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取生日 格式 YYYY-MM-DD
     *
     * @return birthday - 生日 格式 YYYY-MM-DD
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * 设置生日 格式 YYYY-MM-DD
     *
     * @param birthday 生日 格式 YYYY-MM-DD
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * @return trade
     */
    public String getTrade() {
        return trade;
    }

    /**
     * @param trade
     */
    public void setTrade(String trade) {
        this.trade = trade;
    }

    /**
     * 获取钱包地址
     *
     * @return mytoken - 钱包地址
     */
    public String getMytoken() {
        return mytoken;
    }

    /**
     * 设置钱包地址
     *
     * @param mytoken 钱包地址
     */
    public void setMytoken(String mytoken) {
        this.mytoken = mytoken;
    }

    /**
     * 获取所在国家
     *
     * @return country - 所在国家
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置所在国家
     *
     * @param country 所在国家
     */
    public void setCountry(String country) {
        this.country = country;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

//    /**
//     * @return gender
//     */
//    public String getGender() {
//        return gender;
//    }
//
//    /**
//     * @param gender
//     */
//    public void setGender(String gender) {
//        this.gender = gender;
//    }

    /**
     * 获取备注信息
     *
     * @return info - 备注信息
     */
    public String getInfo() {
        return info;
    }

    /**
     * 设置备注信息
     *
     * @param info 备注信息
     */
    public void setInfo(String info) {
        this.info = info;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}