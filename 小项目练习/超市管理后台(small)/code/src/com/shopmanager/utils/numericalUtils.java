package com.shopmanager.utils;

import java.math.BigDecimal;

public class numericalUtils {
    public static boolean isNegative(BigDecimal num, String tipMsg) {
        if (num.doubleValue() <= 0) {
            System.out.println(tipMsg + "不能为负数或零");
            return true;
        }
        return false;
    }
}
