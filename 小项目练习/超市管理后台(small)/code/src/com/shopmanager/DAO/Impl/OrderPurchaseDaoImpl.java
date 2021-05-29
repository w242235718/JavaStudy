package com.shopmanager.DAO.Impl;

import com.shopmanager.DAO.OrderPurchaseDao;
import com.shopmanager.bean.Goods;
import com.shopmanager.bean.Order;
import com.shopmanager.bean.OrderInfo;
import com.shopmanager.bean.ShopMember;
import com.shopmanager.common.PurchaseException;
import com.shopmanager.utils.DBUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class OrderPurchaseDaoImpl implements OrderPurchaseDao {
    @Override
    public Goods selectGoodsById(int id) throws SQLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String sql="SELECT * FROM `shopmanager`.`t_goods` where gid=?";
        return DBUtils.executeQueryByArgs(Goods.class,sql,id);
    }

    @Override
    public int insertOrder(Order order) throws SQLException, IllegalAccessException {
        return DBUtils.executeInsertAndGet("shopmanager","t_order",order);
    }


    @Override
    public ShopMember selectUserById(int uid) throws SQLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String sql="SELECT * FROM `shopmanager`.t_shopmember where uid=?";
        return DBUtils.executeQueryByArgs(ShopMember.class,sql,uid);
    }

    @Override
    public int insertOrderInfo(OrderInfo orderInfo) throws SQLException, InvocationTargetException, IllegalAccessException {
        return DBUtils.executeInsert("shopmanager","t_orderinfo",orderInfo);
    }

    @Override
    public int updateMember(ShopMember sm) throws SQLException, IllegalAccessException {
        return DBUtils.executeUpdateByPrimary("shopmanager","t_shopmember",sm);
    }

    @Override
    public OrderInfo selectOrderById(int gid) throws SQLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String sql="SELECT * FROM `shopmanager`.t_orderinfo where gid=?";
        return DBUtils.executeQueryByArgs(OrderInfo.class,sql,gid);
    }

    @Override
    public int updateGoodsStore(Integer gid, Integer ordernum) throws SQLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, PurchaseException {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        //安全校验
        String sql="SELECT gstore FROM `shopmanager`.t_goods WHERE gid=?";
        Integer store = DBUtils.executeQuery(Integer.class,sql,gid);
        if (store<ordernum){
            throw new PurchaseException("库存不足");
        }
        sql="UPDATE `shopmanager`.t_goods SET gstore=gstore-?, updatetime=? WHERE gid=?";
        return DBUtils.execute(sql,ordernum,timestamp,gid);
    }
}
