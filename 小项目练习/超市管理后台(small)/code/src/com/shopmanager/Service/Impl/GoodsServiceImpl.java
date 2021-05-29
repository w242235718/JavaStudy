package com.shopmanager.Service.Impl;

import com.shopmanager.DAO.GoodsDao;
import com.shopmanager.DAO.Impl.GoodsDaoImpl;
import com.shopmanager.Service.GoodsService;
import com.shopmanager.bean.Goods;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class GoodsServiceImpl implements GoodsService {
    private GoodsDao goodsDao=new GoodsDaoImpl();
    @Override
    public int addProduct(Goods goods) {
        int row=-1;
        try {
            row=goodsDao.insertGoods(goods);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public Goods queryProductById(int id) {
        Goods g= null;
        try {
            g = goodsDao.selectGoodsById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return g;
    }

    @Override
    public List<Goods> queryAll() {
        List<Goods> goodsList=null;
        try {
            goodsList=goodsDao.selectAll();
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
        return goodsList;
    }

    @Override
    public int deleteProduct(int id) {
        int row= 0;
        try {
            row = goodsDao.deleteGoods(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return row;
    }

    @Override
    public int editProduct(Goods g) {
        int row= 0;
        try {
            row = goodsDao.updateGoods(g);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return row;
    }
}
