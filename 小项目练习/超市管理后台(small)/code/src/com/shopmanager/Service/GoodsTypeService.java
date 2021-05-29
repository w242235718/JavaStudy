package com.shopmanager.Service;

import com.shopmanager.bean.GoodsType;

import java.util.List;

public interface GoodsTypeService {
    int addProductType(GoodsType goodsType);

    GoodsType queryProductTypeById(int id);

    List<GoodsType> queryAll();

    int editGoodType(GoodsType g);

    int deleteProductType(int id);
}
