package com.shopmanager.Service.Impl;

import com.shopmanager.bean.GoodsType;
import org.junit.Test;

import static org.junit.Assert.*;

public class GoodsTypeServiceImplTest {

    @Test
    public void addProductType() {
    }

    @Test
    public void queryProductTypeById() {
        GoodsTypeServiceImpl goodsTypeService = new GoodsTypeServiceImpl();
        GoodsType goodsType = goodsTypeService.queryProductTypeById(2);
        System.out.println(goodsType);
    }

    @Test
    public void queryAll() {
        GoodsTypeServiceImpl g = new GoodsTypeServiceImpl();
        System.out.println(g.queryAll());
    }

    @Test
    public void editGoodType() {
        GoodsTypeServiceImpl gs = new GoodsTypeServiceImpl();
        GoodsType g = gs.queryProductTypeById(4);
        g.setTypeName("衣服2");
        System.out.println(gs.editGoodType(g));
    }
}