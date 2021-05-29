package com.shopmanager.DAO;

import com.shopmanager.bean.Rank;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface RankDao {
    List<Rank> selectProductByMonth(int month) throws SQLException;

    List<Rank> selectProductByType() throws SQLException;

}
