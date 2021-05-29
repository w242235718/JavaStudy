package com.shopmanager.common;

import com.shopmanager.bean.Goods;

public class GoodsNotFoundException extends Exception {
    public GoodsNotFoundException() {
    }

    public GoodsNotFoundException(String message) {
        super(message);
    }
}
