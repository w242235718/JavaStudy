package com.shopmanager.bean;

public class Rank {
    private String gname;
    private Integer sales;

    public Rank() {
    }

    public Rank(String gname, Integer sales) {
        this.gname = gname;
        this.sales = sales;
    }

    @Override
    public String toString() {
        return "商品名='" + gname + '\'' +
                ", 销售额=" + sales +
                '}';
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }
}
