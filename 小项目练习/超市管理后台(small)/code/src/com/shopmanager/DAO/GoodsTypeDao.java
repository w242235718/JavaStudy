package com.shopmanager.DAO;

import com.shopmanager.bean.GoodsType;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface GoodsTypeDao {
    /**
     * 添加商品
     * @param goodsType Bean对象
     * @return >0执行成功 -1执行失败
     */
    int insertProductType(GoodsType goodsType) throws SQLException;

    GoodsType selectGoodsTypeById(int id) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException;

    List<GoodsType> selectAll() throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException;

    int udpateProductType(GoodsType g) throws SQLException;

    int deleteProductType(int id) throws SQLException;
}
