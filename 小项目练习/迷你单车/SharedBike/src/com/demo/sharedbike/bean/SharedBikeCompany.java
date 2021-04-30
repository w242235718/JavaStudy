package com.demo.sharedbike.bean;

import java.util.Arrays;

public class SharedBikeCompany {
    private Integer bikeCompanyId;
    private String bikeCompanyName;
    private SharedBike[] sharedBikes;
    private Integer sum;
    private Integer count;

    @Override
    public String toString() {
        return "SharedBikeCompany{" +
                "bikeCompanyId=" + bikeCompanyId +
                ", bikeCompanyName='" + bikeCompanyName + '\'' +
                ", sharedBikes=" + Arrays.toString(sharedBikes) +
                ", sum=" + sum +
                ", count=" + count +
                '}';
    }

    public Integer getBikeCompanyId() {

        return bikeCompanyId;
    }

    public void setBikeCompanyId(Integer bikeCompanyId) {
        this.bikeCompanyId = bikeCompanyId;
    }

    public String getBikeCompanyName() {
        return bikeCompanyName;
    }

    public void setBikeCompanyName(String bikeCompanyName) {
        this.bikeCompanyName = bikeCompanyName;
    }

    public SharedBike[] getSharedBikes() {
        return sharedBikes;
    }

    public void setSharedBikes(SharedBike[] sharedBikes) {
        this.sharedBikes = sharedBikes;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public SharedBikeCompany() {
    }

    public SharedBikeCompany(Integer bikeCompanyId, String bikeCompanyName, SharedBike[] sharedBikes, Integer sum, Integer count) {
        this.bikeCompanyId = bikeCompanyId;
        this.bikeCompanyName = bikeCompanyName;
        this.sharedBikes = sharedBikes;
        this.sum = sum;
        this.count = count;
    }
}
