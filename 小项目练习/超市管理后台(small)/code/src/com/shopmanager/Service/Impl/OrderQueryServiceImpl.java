package com.shopmanager.Service.Impl;


import com.shopmanager.DAO.Impl.OrderQueryDaoImpl;
import com.shopmanager.DAO.OrderQueryDao;
import com.shopmanager.Service.OrderQueryService;
import com.shopmanager.bean.Goods;
import com.shopmanager.bean.Order;
import com.shopmanager.bean.OrderInfo;
import com.shopmanager.utils.DBUtils;


import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class OrderQueryServiceImpl implements OrderQueryService {
    private OrderQueryDao queryDao=new OrderQueryDaoImpl();


    @Override
    public List<OrderInfo> queryAllOrder(Order order) {
        Integer oid = order.getOid();
        List<OrderInfo> orderForQueryList= null;
        try {
            orderForQueryList = queryDao.selectAllOrder(oid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return orderForQueryList;
    }

    @Override
    public List<Order> queryAndGetOrders(int uid) {
        List<Order> orderList= null;
        try {
            orderList = queryDao.selectOrdersByUid(uid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    public OrderInfo querySpecificOrder(Order order, int gid) {

        Integer uid = order.getUid();
        Timestamp paytime = order.getPaytime();
        try {
            return queryDao.selectSpecificOrder(uid,gid,paytime.toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Goods queryGoods(Integer gid) {
        Goods g= null;
        try {
            g = queryDao.selectGoods(gid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return g;
    }
}
