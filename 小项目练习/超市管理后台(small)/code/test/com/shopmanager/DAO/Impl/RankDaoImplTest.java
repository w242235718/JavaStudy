package com.shopmanager.DAO.Impl;

import com.shopmanager.bean.Rank;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class RankDaoImplTest {

    @Test
    public void testQueryRank() {
        String sql="select  GROUP_CONCAT(t.gid) gid,sales from (select od.gid,SUM(ordernum) sales from `shopmanager`.t_orderinfo od join (select oid from `shopmanager`.t_order) o on od.oid=o.oid GROUP BY gid) t GROUP BY sales ORDER BY sales desc LIMIT 0,10";
//        RankDaoImpl rankDao = new RankDaoImpl();
//        try {
//            List<Rank> ranks = rankDao.testQueryRank(sql);
//            for (Rank rank : ranks) {
//                System.out.println(rank);
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
    }
}