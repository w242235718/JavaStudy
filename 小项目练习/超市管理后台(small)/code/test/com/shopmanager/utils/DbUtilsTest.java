package com.shopmanager.utils;

import com.shopmanager.bean.Goods;
import com.shopmanager.bean.GoodsType;
import com.shopmanager.bean.Order;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.*;

public class DbUtilsTest {

    @Test
    public void getValueByKey() {
    }

    @Test
    public void getConn() {
    }

    @Test
    public void execute() {
    }

    @Test
    public void executeQueryAll() {
    }

    @Test
    public void executeQueryByCondition() {
        String sql="SELECT * FROM `t_goods`";
        Goods goods = new Goods();
        goods.setGname("红米小金刚");
        try {
            Goods g = DBUtils.executeQueryByCondition(Goods.class, sql, goods);
            System.out.println(g);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void close() {
    }

    @Test
    public void executeInsert() {
        Order order = new Order();
        order.setOrdertotal(new BigDecimal(20));
        order.setPaymethod(0);
        order.setUid(3);
        order.setPaytime(new Timestamp(new Date().getTime()));
        try {
            System.out.println(DBUtils.executeInsertAndGet("shopmanager", "t_order", order));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Test
    public void queryByCondition() {
    }

    @Test
    public void executeInsertAndGet() {
    }

    @Test
    public void executeQueryByArgs() {
    }

    @Test
    public void executeUpdate() {
        //UPDATE `shopmanager`.`t_goods` SET gid=1, gname='红米小金刚', gtype=2, gstore=20,
        //price=1499, gstatus=1, gdiscount=1.0, createtime='2021-05-25 20:33:34.0', updatetime='2021-05-26 20:33:40.0' WHERE gid=1
        Goods goods = new Goods();
        goods.setGid(2);
        goods.setGname("黄大佬");
        goods.setPrice(new BigDecimal(1299));
        try {
            System.out.println(DBUtils.executeUpdateByPrimary("shopmanager", "t_goods", goods));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}