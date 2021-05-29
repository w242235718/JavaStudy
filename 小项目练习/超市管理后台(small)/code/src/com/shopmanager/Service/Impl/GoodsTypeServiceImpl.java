package com.shopmanager.Service.Impl;

import com.shopmanager.DAO.GoodsTypeDao;
import com.shopmanager.DAO.Impl.GoodsTypeDaoImpl;
import com.shopmanager.Service.GoodsTypeService;
import com.shopmanager.bean.GoodsType;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class GoodsTypeServiceImpl implements GoodsTypeService {
    private GoodsTypeDao goodsTypeDao=new GoodsTypeDaoImpl();
    @Override
    public int addProductType(GoodsType goodsType) {
        int result= 0;
        try {
            result = goodsTypeDao.insertProductType(goodsType);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public GoodsType queryProductTypeById(int id) {
        GoodsType g= null;
        try {
            g = goodsTypeDao.selectGoodsTypeById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return g;
    }

    @Override
    public List<GoodsType> queryAll() {
        List<GoodsType> lists = null;
        try {
            lists = goodsTypeDao.selectAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return lists;
    }

    @Override
    public int editGoodType(GoodsType g) {
        int row= -1;
        try {
            row = goodsTypeDao.udpateProductType(g);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return row;
    }

    @Override
    public int deleteProductType(int id) {
        int result=0;
        try {
           result=goodsTypeDao.deleteProductType(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
