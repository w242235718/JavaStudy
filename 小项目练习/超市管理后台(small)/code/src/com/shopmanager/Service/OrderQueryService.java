package com.shopmanager.Service;

import com.shopmanager.bean.Goods;
import com.shopmanager.bean.Order;
import com.shopmanager.bean.OrderInfo;

import java.util.List;

public interface OrderQueryService {

    List<OrderInfo> queryAllOrder(Order order);

    List<Order> queryAndGetOrders(int uid);


    OrderInfo querySpecificOrder(Order order, int gid);

    Goods queryGoods(Integer gid);
}
