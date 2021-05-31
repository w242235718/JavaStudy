package com.shopmanager.DAO.Impl;

import com.shopmanager.DAO.OrderQueryDao;
import com.shopmanager.bean.Goods;
import com.shopmanager.bean.Order;
import com.shopmanager.bean.OrderInfo;
import com.shopmanager.utils.DBUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class OrderQueryDaoImpl implements OrderQueryDao {
    @Override
    public Order selectOrderUid(int uid) throws SQLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String sql="SELECT * FROM `shopmanager`.t_order where uid=?";
        return DBUtils.executeQueryByArgs(Order.class, sql, uid);
    }


    @Override
    public List<OrderInfo> selectAllOrder(Integer oid) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        String sql="SELECT * FROM `shopmanager`.t_orderinfo where oid=?";
        return DBUtils.executeQueryAll(OrderInfo.class,sql,oid);
    }

    @Override
    public List<Order> selectOrdersByUid(int uid) throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        String sql="SELECT * FROM `shopmanager`.t_order where uid=? ORDER BY paytime desc";
        return DBUtils.executeQueryAll(Order.class,sql,uid);
    }

    @Override
    public OrderInfo selectSpecificOrder(Integer uid, int gid, String s) throws SQLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String sql="SELECT oi.id,oi.oid,oi.gid,oi.ordernum FROM `shopmanager`.t_order o JOIN `shopmanager`.t_orderinfo oi " +
                "ON o.oid=oi.oid WHERE uid=? and gid=? and paytime=?";
        return DBUtils.executeQueryByArgs(OrderInfo.class,sql,uid,gid,s);
    }

    @Override
    public Goods selectGoods(Integer gid) throws SQLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String sql="SELECT * FROM `shopmanager`.`t_goods` where gid=?";
        return DBUtils.executeQueryByArgs(Goods.class,sql,gid);
    }

}
