package com.shopmanager.DAO;

import com.shopmanager.bean.Goods;
import com.shopmanager.bean.Order;
import com.shopmanager.bean.OrderInfo;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface OrderQueryDao {


    Order selectOrderUid(int uid) throws SQLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;


    List<OrderInfo> selectAllOrder(Integer oid) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException;

    List<Order> selectOrdersByUid(int uid) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException;

    OrderInfo selectSpecificOrder(Integer uid, int gid, String s) throws SQLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;

    Goods selectGoods(Integer gid) throws SQLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;
}
