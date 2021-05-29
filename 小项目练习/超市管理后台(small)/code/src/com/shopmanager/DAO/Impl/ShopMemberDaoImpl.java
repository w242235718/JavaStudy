package com.shopmanager.DAO.Impl;

import com.shopmanager.DAO.ShopMemberDao;
import com.shopmanager.bean.ShopMember;
import com.shopmanager.utils.DBUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class ShopMemberDaoImpl implements ShopMemberDao {
    @Override
    public int insertMember(ShopMember m) throws SQLException, InvocationTargetException, IllegalAccessException {
        return  DBUtils.executeInsert("shopmanager","t_shopmember",m);
    }

    @Override
    public List<ShopMember> queryAll() throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        String sql="SELECT* FROM `shopmanager`.t_shopmember";
        return DBUtils.executeQueryAll(ShopMember.class,sql);
    }

    @Override
    public ShopMember queryMemberById(int id) throws SQLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String sql="SELECT * FROM `shopmanager`.t_shopmember where uid=?";
        return  DBUtils.executeQueryByArgs(ShopMember.class,sql,id);
    }

    @Override
    public int updateMember(ShopMember m) throws SQLException, IllegalAccessException {
        return DBUtils.executeUpdateByPrimary("shopmanager","t_shopmember",m);
    }

    @Override
    public int deleteMember(int id) throws SQLException {
        String sql="DELETE FROM `shopmanager`.t_shopmember where uid=?";
        return DBUtils.execute(sql,id);
    }

    @Override
    public int updateCharge(ShopMember nm) throws SQLException, IllegalAccessException {

        return DBUtils.executeUpdateByPrimary("shopmanager","t_shopmember",nm);
    }

}
