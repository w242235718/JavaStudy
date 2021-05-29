package com.shopmanager.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Order {
    private Integer oid;
    private Integer uid;
    private BigDecimal ordertotal;
    private Timestamp paytime;
    private Integer paymethod;

    public Order( Integer uid, BigDecimal ordertotal, Timestamp paytime, Integer paymethod) {
        this.oid = null;
        this.uid = uid;
        this.ordertotal = ordertotal;
        this.paytime = paytime;
        this.paymethod = paymethod;
    }

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid=" + oid +
                ", uid=" + uid +
                ", ordertotal=" + ordertotal +
                ", paytime=" + paytime +
                ", paymethod=" + paymethod +
                '}';
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public BigDecimal getOrdertotal() {
        return ordertotal;
    }

    public void setOrdertotal(BigDecimal ordertotal) {
        this.ordertotal = ordertotal;
    }

    public Timestamp getPaytime() {
        return paytime;
    }

    public void setPaytime(Timestamp paytime) {
        this.paytime = paytime;
    }

    public Integer getPaymethod() {
        return paymethod;
    }

    public void setPaymethod(Integer paymethod) {
        this.paymethod = paymethod;
    }
}
