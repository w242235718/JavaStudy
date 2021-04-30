package com.demo.sharedbike.bean;

import com.demo.sharedbike.Const.SharedBikeConst;

public class OfoBike extends SharedBike{
    public OfoBike() {
    }

    public OfoBike(Integer bikeId, String bikeName, Integer status, String borrowTime) {
        super(bikeId, bikeName, status, borrowTime);
    }
}
