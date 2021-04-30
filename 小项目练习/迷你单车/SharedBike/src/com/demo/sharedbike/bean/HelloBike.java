package com.demo.sharedbike.bean;

import com.demo.sharedbike.Const.SharedBikeConst;

public class HelloBike extends SharedBike {
    public HelloBike() {
    }

    public HelloBike(Integer bikeId, String bikeName, Integer status, String borrowTime) {
        super(bikeId, bikeName, status, borrowTime);
    }
}
