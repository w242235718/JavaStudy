package com.shopmanager.Service.Impl;

import com.shopmanager.DAO.Impl.RankDaoImpl;
import com.shopmanager.DAO.RankDao;
import com.shopmanager.Service.RankService;
import com.shopmanager.bean.Rank;

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
}
