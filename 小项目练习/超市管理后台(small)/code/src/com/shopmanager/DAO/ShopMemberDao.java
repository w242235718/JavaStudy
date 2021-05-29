package com.shopmanager.DAO;

import com.shopmanager.bean.ShopMember;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface ShopMemberDao {
    int insertMember(ShopMember m) throws SQLException, InvocationTargetException, IllegalAccessException;

    List<ShopMember> queryAll() throws SQLException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException;

    ShopMember queryMemberById(int id) throws SQLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;

    int updateMember(ShopMember m) throws SQLException, IllegalAccessException;

    int deleteMember(int id) throws SQLException;

    int updateCharge(ShopMember nm) throws SQLException, IllegalAccessException;
}
