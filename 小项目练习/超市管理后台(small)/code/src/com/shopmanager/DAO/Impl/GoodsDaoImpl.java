package com.shopmanager.DAO.Impl;

import com.shopmanager.DAO.GoodsDao;
import com.shopmanager.bean.Goods;
import com.shopmanager.utils.DBUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class GoodsDaoImpl implements GoodsDao {
    @Override
    public int insertGoods(Goods goods) throws SQLException, InvocationTargetException, IllegalAccessException {
        return DBUtils.executeInsert("shopmanager","t_goods", goods);
    }

    @Override
    public Goods selectGoodsById(int id) throws SQLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String sql="SELECT * FROM `shopmanager`.`t_goods` where gid=? and gstatus=1";
        return DBUtils.executeQueryByArgs(Goods.class,sql,id);
    }

    @Override
    public List<Goods> selectAll() throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        String sql="SELECT * FROM `shopmanager`.`t_goods` where gstatus=1";
        return DBUtils.executeQueryAll(Goods.class,sql);
    }

    /**
     * 逻辑删除
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public int deleteGoods(int id) throws SQLException {
        String sql="UPDATE `shopmanager`.`t_goods` SET gstatus=3 where gid=?";
        return DBUtils.execute(sql,id);
    }

    /**
     * 物理删除
     * @param id
     * @return
     * @throws SQLException
     */
    public int deleteGoodsPhysically(int id) throws SQLException {
        String sql="DELETE FROM `shopmanager`.`t_goods` where gid=?";
        return DBUtils.execute(sql,id);
    }

    /**
     * 下架
     * @param id
     * @return
     * @throws SQLException
     */
    public int takeGoodsOff(int id) throws SQLException {
        String sql="UPDATE `shopmanager`.`t_goods` SET gstatus=2 where gid=?";
        return DBUtils.execute(sql,id);
    }



    @Override
    public int updateGoods(Goods g) throws SQLException, IllegalAccessException {
        return DBUtils.executeUpdateByPrimary("shopmanager","t_goods",g);
    }
}
