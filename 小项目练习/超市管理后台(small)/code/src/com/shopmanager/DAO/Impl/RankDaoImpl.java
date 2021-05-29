package com.shopmanager.DAO.Impl;

import com.shopmanager.DAO.RankDao;
import com.shopmanager.bean.Rank;
import com.shopmanager.utils.DBUtils;
import jdk.nashorn.internal.ir.SplitReturn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class RankDaoImpl implements RankDao {

    @Override
    public List<Rank> selectProductByMonth(int month) throws SQLException {
        String sql="SELECT g.gname,so.sales FROM (select od.gid,SUM(ordernum) sales from `shopmanager`.t_orderinfo od join (select oid from `shopmanager`.t_order where paytime BETWEEN '2021-"+month+"-01' and '2021-"+month+"-31') o on od.oid=o.oid GROUP BY gid ) so join `shopmanager`.t_goods g on so.gid=g.gid ORDER BY sales desc limit 0,9";
        return selectProduct(sql);
    }

    @Override
    public List<Rank> selectProductByType() throws SQLException {
        String sql="SELECT g.gname,so.sales FROM (select od.gid,SUM(ordernum) sales from `shopmanager`.t_orderinfo od join (select oid from `shopmanager`.t_order) o on od.oid=o.oid GROUP BY gid ) so join `shopmanager`.t_goods g on so.gid=g.gid ORDER BY sales desc limit 0,9";
        return selectProduct(sql);
    }

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
