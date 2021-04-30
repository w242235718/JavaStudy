package com.demo.sharedbike.bean;

import com.demo.sharedbike.Const.SharedBikeConst;

public class MoBike extends SharedBike {
    public MoBike() {
    }

    public MoBike(Integer bikeId, String bikeName, Integer status, String borrowTime) {
        super(bikeId, bikeName, status, borrowTime);
    }
}
