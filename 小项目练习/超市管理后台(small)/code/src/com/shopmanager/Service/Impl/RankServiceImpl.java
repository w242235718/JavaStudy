package com.shopmanager.Service.Impl;

import com.shopmanager.DAO.Impl.RankDaoImpl;
import com.shopmanager.DAO.RankDao;
import com.shopmanager.Service.RankService;
import com.shopmanager.bean.Goods;
import com.shopmanager.bean.Rank;
import com.shopmanager.common.GoodsNotFoundException;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class RankServiceImpl implements RankService {
    private RankDao rankDao= new RankDaoImpl();
    @Override
    public List<Rank> countProductByMonth(int month) {
        try {
            return rankDao.selectProductByMonth(month);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Rank> countProductByType() {
        try {
            return rankDao.selectProductByType();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void printProductMap(List<Rank> productList) throws GoodsNotFoundException {

        if (productList==null){
            System.out.println("出错啦！");
            throw new GoodsNotFoundException("打印排名出错！");
        }
        System.out.println("排行榜：");
        for (int i = 0; i < productList.size(); i++) {
            Rank rank = productList.get(i);
            String gid = rank.getGid();
            System.out.print((i+1));
            System.out.print(": 销售量:"+rank.getSales());
            System.out.println();
            String[] gids = gid.split(",");
            for (String s : gids) {
                try {
                    Goods g=rankDao.queryGoods(s);
                    System.out.print("\t商品编号:"+g.getGid());
                    System.out.print("\t商品名称:"+g.getGname());
                    System.out.println();
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
            }

        }
    }
}
