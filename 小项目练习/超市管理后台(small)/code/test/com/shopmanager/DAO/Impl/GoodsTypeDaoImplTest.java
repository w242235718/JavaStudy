package com.shopmanager.DAO.Impl;

import com.shopmanager.bean.GoodsType;
import com.shopmanager.utils.GoodsConst;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class GoodsTypeDaoImplTest {

    @Test
    public void insertProductType() {
        GoodsTypeDaoImpl goodsTypeDao = new GoodsTypeDaoImpl();
        GoodsType goodsType = new GoodsType();
        goodsType.setTypeName("手机");
        goodsType.setIsParentType(GoodsConst.IS_PARENT_TYPE);
        goodsType.setPid(0);
        int i = 0;
        try {
            i = goodsTypeDao.insertProductType(goodsType);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(i);
    }
}