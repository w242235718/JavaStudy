package com.shopmanager.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class Goods {
    private Integer gid;
    private String gname;
    private Integer gtype;
    private Integer gstore;
    private BigDecimal price;
    private Integer gstatus;
    private Float gdiscount;
    private Timestamp createtime;
    private Timestamp updatetime;

    public Goods() {
    }




    @Override
    public String toString() {
        return "Goods{" +
                "gid=" + gid +
                ", gname='" + gname + '\'' +
                ", gtype=" + gtype +
                ", gstore=" + gstore +
                ", price=" + price +
                ", gstatus=" + gstatus +
                ", gdiscount=" + gdiscount +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                '}';
    }

    public Goods(  String gname, Integer gtype, Integer gstore, BigDecimal price, Integer gstatus, Float gdiscount, Timestamp createTime, Timestamp updateTime) {
        this.gid = null;
        this.gname = gname;
        this.gtype = gtype;
        this.gstore = gstore;
        this.price = price;
        this.gstatus = gstatus;
        this.gdiscount = gdiscount;
        this.createtime = createTime;
        this.updatetime = updateTime;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public Integer getGtype() {
        return gtype;
    }

    public void setGtype(Integer gtype) {
        this.gtype = gtype;
    }

    public Integer getGstore() {
        return gstore;
    }

    public void setGstore(Integer gstore) {
        this.gstore = gstore;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getGstatus() {
        return gstatus;
    }

    public void setGstatus(Integer gstatus) {
        this.gstatus = gstatus;
    }

    public Float getGdiscount() {
        return gdiscount;
    }

    public void setGdiscount(Float gdiscount) {
        this.gdiscount = gdiscount;
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
