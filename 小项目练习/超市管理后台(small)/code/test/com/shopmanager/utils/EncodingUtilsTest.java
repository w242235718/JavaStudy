package com.shopmanager.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class EncodingUtilsTest {

    @Test
    public void encoding() {

        String s="123456";
        System.out.println(EncodingUtils.Encoding(s));
    }
}