package com.shopmanager.bean;

public class Rank {
    private String gid;
    private Integer sales;

    public Rank() {
    }

    public Rank(String gid, Integer sales) {
        this.gid = gid;
        this.sales = sales;
    }

    @Override
    public String toString() {
        return "Rank{" +
                "gid='" + gid + '\'' +
                ", sales=" + sales +
                '}';
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }
}
