package com.shopmanager.DAO;

import com.shopmanager.bean.Goods;
import com.shopmanager.bean.Order;
import com.shopmanager.bean.OrderInfo;
import com.shopmanager.bean.ShopMember;
import com.shopmanager.common.PurchaseException;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface OrderPurchaseDao {




    /**
     * 根据id查询商品信息
     * @param id
     * @return
     */
    Goods selectGoodsById(int id) throws SQLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;

    int insertOrder(Order order) throws SQLException, IllegalAccessException;

    ShopMember selectUserById(int uid) throws SQLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;

    int insertOrderInfo(OrderInfo orderInfo) throws SQLException, InvocationTargetException, IllegalAccessException;

    int updateMember(ShopMember sm) throws SQLException, IllegalAccessException;

    OrderInfo selectOrderById(int gid) throws SQLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;

    int updateGoodsStore(Integer gid, Integer ordernum) throws SQLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, PurchaseException;
}
