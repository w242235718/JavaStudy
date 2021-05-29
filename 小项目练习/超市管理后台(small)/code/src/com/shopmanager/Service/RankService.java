package com.shopmanager.Service;

import com.shopmanager.bean.Rank;

import java.util.List;
import java.util.Map;

public interface RankService {
    List<Rank> countProductByMonth(int month);

    List<Rank> countProductByType();

}
