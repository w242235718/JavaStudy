package com.demo.sharedbike.bean;

public class SharedBike {
    private Integer bikeId;
    private String bikeName;
    private Integer status;
    private String borrowTime;


    @Override
    public String toString() {
        return "SharedBike{" +
                "bikeId=" + bikeId +
                ", bikeName='" + bikeName + '\'' +
                ", status=" + status +
                ", borrowTime='" + borrowTime + '\'' +
                '}';
    }

    public SharedBike() {
    }

    public SharedBike(Integer bikeId, String bikeName, Integer status, String borrowTime) {
        this.bikeId = bikeId;
        this.bikeName = bikeName;
        this.status = status;
        this.borrowTime = borrowTime;
    }

    public Integer getBikeId() {
        return bikeId;
    }

    public void setBikeId(Integer bikeId) {
        this.bikeId = bikeId;
    }

    public String getBikeName() {
        return bikeName;
    }

    public void setBikeName(String bikeName) {
        this.bikeName = bikeName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(String borrowTime) {
        this.borrowTime = borrowTime;
    }
}
