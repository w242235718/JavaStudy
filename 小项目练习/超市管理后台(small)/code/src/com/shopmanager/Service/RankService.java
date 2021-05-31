package com.shopmanager.Service;

import com.shopmanager.bean.Rank;
import com.shopmanager.common.GoodsNotFoundException;

import java.util.List;
import java.util.Map;

public interface RankService {
    List<Rank> countProductByMonth(int month);

    List<Rank> countProductByType();

    void printProductMap(List<Rank> productList) throws GoodsNotFoundException;
}
