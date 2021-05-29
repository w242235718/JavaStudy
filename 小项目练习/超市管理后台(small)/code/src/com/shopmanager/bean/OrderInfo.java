package com.shopmanager.bean;

public class OrderInfo {
    private Integer id;
    private Integer oid;
    private Integer gid;
    private Integer ordernum;

    public OrderInfo() {
    }

    public OrderInfo( Integer oid, Integer gid, Integer ordernum) {
        this.id = null;
        this.oid = oid;
        this.gid = gid;
        this.ordernum = ordernum;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                (id==null?"":"id="+id) +
                "," +(oid==null?"":"oid="+oid) +
                ", gid=" + gid +
                ", ordernum=" + ordernum +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Integer getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }
}
