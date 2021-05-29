package com.shopmanager.Service;

import com.shopmanager.bean.Goods;

import java.util.List;

public interface GoodsService {
    int addProduct(Goods goods);

    Goods queryProductById(int id);

    List<Goods> queryAll();

    int deleteProduct(int id);

    int editProduct(Goods g);
}
