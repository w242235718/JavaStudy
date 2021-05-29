package com.shopmanager.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderPurchaseUtil {
    private Integer normId;

    private OrderPurchaseUtil(){ }




    public static int getNonVipID(){
        OrderPurchaseUtil u=getInstance();
        return u.getNormId();
    }

    private static OrderPurchaseUtil getInstance() {
        return new OrderPurchaseUtil();
    }

    private int getNormId() {
        Integer uid=0;
        Connection conn=null;
        PreparedStatement preparedStatement =null;
        ResultSet rs=null;
        try {
            String sql="SELECT uid FROM `shopmanager`.t_order where uid>=2000000000 ORDER BY uid desc LIMIT 0,1;";
            conn = DBUtils.getConn();
            preparedStatement = conn.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            if (rs.next()){
                uid = rs.getInt("uid");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtils.close(rs,preparedStatement,conn);
        }

        //uid为空
        if (uid==null||uid==0){
            normId=2000000001;
            return normId;
        }
        //uid非空
        normId=uid+1;

        return normId;
    }

}
