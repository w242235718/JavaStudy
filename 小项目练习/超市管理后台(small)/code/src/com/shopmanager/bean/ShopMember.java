package com.shopmanager.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ShopMember {
    private Integer uid;
    private String username;
    private String passwd;
    private String contactPhone;
    private Float memberPoints;
    private BigDecimal balance;
    private Timestamp createtime;
    private Timestamp updatetime;

    public ShopMember() {
    }

    public ShopMember( String username, String passwd, String contactPhone, Timestamp createtime, Timestamp updatetime) {
        this.uid = null;
        this.username = username;
        this.passwd = passwd;
        this.contactPhone = contactPhone;
        this.memberPoints = null;
        this.balance = null;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return "ShopMember{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", passwd='" + passwd + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", memberPoints=" + memberPoints +
                ", balance=" + balance +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                '}';
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Float getMemberPoints() {
        return memberPoints;
    }

    public void setMemberPoints(Float memberPoints) {
        this.memberPoints = memberPoints;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public Timestamp getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Timestamp updatetime) {
        this.updatetime = updatetime;
    }
}
