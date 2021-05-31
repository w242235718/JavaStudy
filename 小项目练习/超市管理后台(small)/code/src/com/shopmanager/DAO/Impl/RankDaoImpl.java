package com.shopmanager.DAO.Impl;

import com.shopmanager.DAO.RankDao;
import com.shopmanager.bean.Goods;
import com.shopmanager.bean.Rank;
import com.shopmanager.utils.DBUtils;
import jdk.nashorn.internal.ir.SplitReturn;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.*;

public class RankDaoImpl implements RankDao {

    @Override
    public List<Rank> selectProductByMonth(int month) throws SQLException {
        String sql="select GROUP_CONCAT(t.gid) gid,sales from (select od.gid,SUM(ordernum) sales from `shopmanager`.t_orderinfo od join (select oid from `shopmanager`.t_order where paytime BETWEEN '2021-"+month+"-01' and '2021-"+month+"-31') o on od.oid=o.oid GROUP BY gid ) t GROUP BY sales ORDER BY sales desc LIMIT 0,10";
        return selectProduct(sql);
    }

    @Override
    public List<Rank> selectProductByType() throws SQLException {

        String sql="select GROUP_CONCAT(t.gid) gid,sales from (select od.gid,SUM(ordernum) sales from `shopmanager`.t_orderinfo od join (select oid from `shopmanager`.t_order) o on od.oid=o.oid GROUP BY gid) t GROUP BY sales ORDER BY sales desc LIMIT 0,10";

        return selectProduct(sql);
    }

    @Override
    public Goods queryGoods(String gid) throws SQLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String sql="SELECT * FROM `shopmanager`.`t_goods` where gid=?";
        return DBUtils.executeQueryByArgs(Goods.class,sql,gid);
    }

//    public  List<Rank> testQueryRank(String sql) throws SQLException {
//        List<Rank> rankList=new ArrayList<>();
//
//        Connection conn = null;
//        PreparedStatement ps=null;
//        ResultSet rs=null;
//
//        try {
//            conn=DBUtils.getConn();
//            ps =conn.prepareStatement(sql);
//            rs=ps.executeQuery();
//            ResultSetMetaData metaData = rs.getMetaData();
//            int columnCount = metaData.getColumnCount();
//            while (rs.next()) {
////                for (int i = 1; i <= columnCount; i++) {
////                    System.out.println(metaData.getColumnName(i));
////                    System.out.println(metaData.getColumnClassName(i));
////                    System.out.println("++++++++++++++++++++++");
////                }
//                Rank r = new Rank(rs.getString(1), rs.getInt(2));
//                rankList.add(r);
//                }
//
//        } finally {
//            DBUtils.close(rs,ps,conn);
//        }
//
//        return rankList;
//
//    }

    private List<Rank> selectProduct(String sql) throws SQLException {
        List<Rank> rankList=new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
            conn=DBUtils.getConn();
            ps =conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                Rank r = new Rank(rs.getString(1), rs.getInt(2));
                rankList.add(r);
            }

        } finally {
            DBUtils.close(rs,ps,conn);
        }

        return rankList;
    }
}
