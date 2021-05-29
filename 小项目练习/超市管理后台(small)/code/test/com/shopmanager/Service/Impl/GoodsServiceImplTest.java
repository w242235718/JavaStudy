package com.shopmanager.Service.Impl;

import com.shopmanager.Service.GoodsService;
import com.shopmanager.bean.Goods;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GoodsServiceImplTest {
    private GoodsService goodsService=new GoodsServiceImpl();
    @Test
    public void addProduct() {
    }

    @Test
    public void queryProductById() {
        Goods goods = goodsService.queryProductById(1);
        System.out.println(goods);
    }

    @Test
    public void queryAll() {
        List<Goods> goodsList = goodsService.queryAll();
        goodsList.forEach((g)-> System.out.println(g));
    }
}