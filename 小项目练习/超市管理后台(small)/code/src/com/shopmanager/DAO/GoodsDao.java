package com.shopmanager.DAO;

import com.shopmanager.bean.Goods;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface GoodsDao {
    int insertGoods(Goods goods) throws SQLException, InvocationTargetException, IllegalAccessException;

    Goods selectGoodsById(int id) throws SQLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;

    List<Goods> selectAll() throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException;

    int deleteGoods(int id) throws SQLException;

    int updateGoods(Goods g) throws SQLException, IllegalAccessException;
}
