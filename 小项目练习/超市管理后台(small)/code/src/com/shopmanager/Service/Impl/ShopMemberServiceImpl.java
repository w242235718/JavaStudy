package com.shopmanager.Service.Impl;

import com.shopmanager.DAO.Impl.ShopMemberDaoImpl;
import com.shopmanager.DAO.ShopMemberDao;
import com.shopmanager.Service.ShopMemberService;
import com.shopmanager.bean.ShopMember;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class ShopMemberServiceImpl implements ShopMemberService {
    private ShopMemberDao shopMemberDao=new ShopMemberDaoImpl();
    @Override
    public int addMember(ShopMember m) {
        int row= 0;
        try {
            row = shopMemberDao.insertMember(m);
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
    public List<ShopMember> queryAll() {
        List<ShopMember> memberList= null;
        try {
            memberList = shopMemberDao.queryAll();
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
        return memberList;
    }

    @Override
    public ShopMember queryMemberById(int id) {
        ShopMember shopMember=null;
        try {
            shopMember=shopMemberDao.queryMemberById(id);
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
        return shopMember;
    }

    @Override
    public int editMember(ShopMember m) {
        int row= 0;
        try {
            row = shopMemberDao.updateMember(m);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int deleteMember(int id) {
        int row=0;
        try {
            row=shopMemberDao.deleteMember(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return row;
    }

    @Override
    public int chargeShopMember(ShopMember m, BigDecimal charge) {
        ShopMember nm=m;
        nm.setBalance(new BigDecimal(m.getBalance().doubleValue()+charge.doubleValue()));
        nm.setUpdatetime(new Timestamp(new Date().getTime()));
        int result= 0;
        try {
            result = shopMemberDao.updateCharge(nm);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }
}
