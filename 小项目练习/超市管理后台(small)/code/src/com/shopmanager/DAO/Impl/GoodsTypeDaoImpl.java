package com.shopmanager.DAO.Impl;

import com.shopmanager.DAO.GoodsTypeDao;
import com.shopmanager.bean.GoodsType;
import com.shopmanager.utils.DBUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class GoodsTypeDaoImpl implements GoodsTypeDao {
    @Override
    public int insertProductType(GoodsType goodsType) throws SQLException {
        String sql="INSERT INTO `shopmanager`.`t_goodstype`(`pid`, `typeName`, `isParentType`, `createTime`) VALUES (?,?,?,?)";
        return DBUtils.execute(sql,goodsType.getPid(),goodsType.getTypeName(),goodsType.getIsParentType(),new Date());
    }

    @Override
    public GoodsType selectGoodsTypeById(int id) throws SQLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String sql="SELECT * FROM `shopmanager`.t_goodstype WHERE id=?";
        return DBUtils.executeQueryByArgs(GoodsType.class,sql,id);
    }

    @Override
    public List<GoodsType> selectAll() throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        String sql="SELECT * FROM `shopmanager`.t_goodstype";

        return DBUtils.executeQueryAll(GoodsType.class,sql);
    }

    @Override
    public int udpateProductType(GoodsType g) throws SQLException {
        String sql="UPDATE `shopmanager`.`t_goodstype` SET pid=?,typeName=?,isParentType=?,updateTime=? where id=?";
        return DBUtils.execute(sql,g.getPid(),g.getTypeName(),g.getIsParentType(),new Timestamp(new Date().getTime()),g.getId());
    }

    @Override
    public int deleteProductType(int id) throws SQLException {
        String sql="DELETE FROM `shopmanager`.`t_goodstype` where id=?";
        return DBUtils.execute(sql,id);
    }


}
